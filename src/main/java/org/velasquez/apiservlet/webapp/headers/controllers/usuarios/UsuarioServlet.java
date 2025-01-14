package org.velasquez.apiservlet.webapp.headers.controllers.usuarios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velasquez.apiservlet.webapp.headers.models.Usuario;
import org.velasquez.apiservlet.webapp.headers.services.LoginService;
import org.velasquez.apiservlet.webapp.headers.services.LoginServiceSessionImpl;
import org.velasquez.apiservlet.webapp.headers.services.UsuarioService;
import org.velasquez.apiservlet.webapp.headers.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * Encargado de listar todos los usuarios.
 * @author Velasquez Quiroz Rodrigo Andres
 * @version
 * @date 8/08/2024
 * @time 23:11
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtienbe la conexion del f ilter Conexxion Filter
        Connection conn = (Connection) req.getAttribute("conn");

        //Instancia del servicio del Usuario service.
        UsuarioService service = new UsuarioServiceImpl(conn);

        //Asigna en una lista de usuarios, la lista de prodcutos que retorna el service
        List<Usuario> usuarios = service.listar();

        //Obtiene la sesion del usuario
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        //Define a productos la lista de productos que fue obtenido
        req.setAttribute("usuarios", usuarios);
        // Define a username el nombre del usuario que fue obtenido
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title",req.getAttribute("title")+": Listado de usuarios");
        getServletContext().getRequestDispatcher("/usuarios/listarUsuarios.jsp").forward(req, resp);
    }//Fin doGet
}//Fin UsuarioServlet
