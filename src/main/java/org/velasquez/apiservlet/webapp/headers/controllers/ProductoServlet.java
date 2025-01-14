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
 * Se encarga de listar todos los productos que estan en la base de datos.
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 3
 * @date 4/08/2024
 * @time 23:12
 */


@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtienbe la conexion del f ilter Conexxion Filter
        Connection conn = (Connection) req.getAttribute("conn");
        //Instancia del servicio del producto service.
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        //Asigna en  una lista a  productos, la lista de productos que retorna el service
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
