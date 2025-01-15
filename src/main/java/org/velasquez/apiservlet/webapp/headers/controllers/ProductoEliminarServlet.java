package org.velasquez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velasquez.apiservlet.webapp.headers.models.Producto;
import org.velasquez.apiservlet.webapp.headers.services.ProductoService;
import org.velasquez.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

/**
 * Servlet encargado de eliminar un producto del listado de productos
 * Maneja la peticion GET para evaluar si existe el producto que selecciono el ususuario
 * Si no existe, envia un mensaje que no existe el producto
 * @author Velasquez Quiroz Rodrigo Andres
 */

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {
    /**
     * Eliminará el producto de la lista de productos
     * @param req La solicitud HTTP que contiene los parametros del id
     * @param resp La respuesta HTTP que redirige al usuario a la pagina de productos.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        long id;
        try {
            //Encuentra el id del objeto
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            //Sino existe, se define como 0
            id = 0L;
        }
        //Si es mayor que 0
        if (id > 0) {
            //Se busca por el service el id seleccionado del producto
            Optional<Producto> o = service.porId(id);
            //Si esta presente el producto
            if (o.isPresent()) {
                //Elimina el producto pasando el id que seleccino el usuario
                service.eliminar(id);
                //Se redirige al Servlet Productos que luego mostrara listarUsuarios.jsp
                resp.sendRedirect(req.getContextPath()+ "/productos");
            } else {
                //Si no existe el producto, mandara un error.
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el producto en la base de datos!");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url!");
        }
    }
}
