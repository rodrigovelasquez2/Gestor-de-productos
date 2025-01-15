package org.velasquez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velasquez.apiservlet.webapp.headers.models.Producto;
import org.velasquez.apiservlet.webapp.headers.services.LoginService;
import org.velasquez.apiservlet.webapp.headers.services.LoginServiceSessionImpl;
import org.velasquez.apiservlet.webapp.headers.services.ProductoService;
import org.velasquez.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
/**
 * Servlet encargado de listar todos los productos que estan en la base de datos.
 * @author Velasquez Quiroz Rodrigo Andres
 */

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    /**
     * Procesa la solicitud Get para obtener todos los productos
     * @param req La solicitud HTTP que contiene los parametros del producto
     * @param resp La respuesta HTTP que redirige a la pagina de listar productos
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn"); // Se obtiene de Filter
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        List<Producto> productos = service.listar();

        //Obtiene la sesion del usuario
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        //Define a productos la lista de productos que fue obtenido
        req.setAttribute("productos", productos);
        // Define a username el nombre del usuario que fue obtenido
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title",req.getAttribute("title")+": Listado de productos");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
