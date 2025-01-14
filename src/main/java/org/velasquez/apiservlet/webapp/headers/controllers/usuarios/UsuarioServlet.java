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
 * Servlet encargado delistar todos los usuarios.
 * @author Velasquez Quiroz Rodrigo Andres
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    /**
     * Maneja la solicitud GET para obtener todos los usuarios
     * Obtiene la conexion de la base de datos desde filter
     * @param req La solicitud HTTP que contiene el parámetro {@code id}.
     * @param resp La respuesta HTTP para redirigir o devolver un error.
     * @throws ServletException Si ocurre un error en el procesamiento del servlet.
     * @throws IOException Si ocurre un error al enviar la respuesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");

        UsuarioService service = new UsuarioServiceImpl(conn);

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
