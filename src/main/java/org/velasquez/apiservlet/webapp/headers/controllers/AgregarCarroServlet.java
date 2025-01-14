package org.velasquez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.velasquez.apiservlet.webapp.headers.models.Carro;
import org.velasquez.apiservlet.webapp.headers.models.ItemCarro;
import org.velasquez.apiservlet.webapp.headers.models.Producto;
import org.velasquez.apiservlet.webapp.headers.services.ProductoService;
import org.velasquez.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtiene el id del producto seleccionado
        Long id = Long.parseLong(req.getParameter("id"));
        //Obtiene la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        //Busca el producto
        Optional<Producto> producto = service.porId(id);

        //Si esta presente
        if (producto.isPresent()) {
            //Obtiene el producto y añade +1 cantidad
            ItemCarro item = new ItemCarro(1, producto.get());
            //Obtiene la sesion y el atributo
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            //Añade el objeto a la lista Item
            carro.addItemCarro(item);
        }
        //Redirige al servlet de ver el listado de productos
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
