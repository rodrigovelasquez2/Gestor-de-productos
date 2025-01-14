package org.velasquez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velasquez.apiservlet.webapp.headers.models.Categoria;
import org.velasquez.apiservlet.webapp.headers.models.Producto;
import org.velasquez.apiservlet.webapp.headers.services.ProductoService;
import org.velasquez.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Servlet encargado de obtener los valores o enviar a la  base datos el nuevo producto
 * extraidos del formulario formUsuario.jsp.
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 2
 * @date 4/08/2024
 * @time 21:39
 */

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtiene la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        //Implementa el servicijo
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id")); // Si existe un ID, lo guarda en una variable
        } catch (NumberFormatException e) {
            id = 0L; // Sino, lo muestra como 0, indicaria para poder registrar nuevos productos.
        }

        Producto producto = new Producto();
        producto.setCategoria(new Categoria());

        if (id > 0) { // Si el ID es mayor que 0
            Optional<Producto> o = service.porId(id); // Busca el producto
            if (o.isPresent()) { // Si esta presente
                producto = o.get(); // Lo obtiene y lo asigna a la variable producto.
            }
        }
        //Se define en la variable categorias
        req.setAttribute("categorias", service.listarCategoria());
        // Se define en la variable producto el producto encontrado.
        req.setAttribute("producto", producto);
        //Se pasa el titulo
        req.setAttribute("title",req.getAttribute("title")+": Formulario de productos");
        // Se rederige a formulario jsp.
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    /**
     * Método encargado de obtener los datos otorgados por el formulario de crear nuevos prodocutos a travez de getParametters
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Obtiene la conexión de Conexion filter
        Connection conn = (Connection) req.getAttribute("conn");
        //Implementar el servicio de productosService
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        //Obtiene el valor del atributo nombre del formulario.
        String nombre = req.getParameter("nombre");

        Integer precio;
        try {
            //Convertir el valor precio extraido del formulario
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }

        //Obtiene el valor SKU del formulario.
        String sku = req.getParameter("sku");
        //Obtiene el valor SKU de la fecha registro.
        String fechaStr = req.getParameter("fecha_registro");
        Long categoriaId;
        try {
            categoriaId = Long.parseLong(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "el nombre es requerido!");
        }
        if (sku == null || sku.isBlank()) {
            errores.put("sku", "el sku es requerido!");
        } else if (sku.length() > 10) {
            errores.put("sku", "el sku debe tener max 10 caracteres!");
        }

        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "la fecha es requerida");
        }
        if (precio.equals(0)) {
            errores.put("precio", "el precio es requerido!");
        }
        if (categoriaId.equals(0L)) {
            errores.put("categoria", "la categoria es requerida!");
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fecha = null;
        }
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        // Crea una instancia del objeto producto y guarda todos los valores en el objeto producto.
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setSku(sku);
        producto.setPrecio(precio);
        producto.setFechaRegistro(fecha);

        //Guarda los valores en categoria
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);

        if (errores.isEmpty()) { // Si errores esta vacio
            service.guardar(producto); // Guarda el producto
            resp.sendRedirect(req.getContextPath() + "/productos"); // Redirige a listar productos.
        } else {
            // Sino, define una variable global y los muestra en el formulario JSP.
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategoria());
            req.setAttribute("producto", producto);
            req.setAttribute("title",req.getAttribute("title")+": Formulario de productos");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
