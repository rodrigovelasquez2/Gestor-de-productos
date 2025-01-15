package org.velasquez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.velasquez.apiservlet.webapp.headers.models.Usuario;

import org.velasquez.apiservlet.webapp.headers.services.LoginService;
import org.velasquez.apiservlet.webapp.headers.services.LoginServiceSessionImpl;
import org.velasquez.apiservlet.webapp.headers.services.UsuarioService;
import org.velasquez.apiservlet.webapp.headers.services.UsuarioServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;

/**
 * Servlet encargado de manejar el login del {@link Usuario}
 * Maneja la peticion GET para evaluar si el usuario esta registrado
 * La peticion POST para registrar un nuevo usuario
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version
 * @date 14/01/2025
 * @time 18:40
 */

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    /**
     * Se encarga de obtener la session del usuario, de estar activo, mostrara un mensaje de bienvenida para el usuario.
     * @param req La solicitud HTTP que contiene los parametros del usuario
     * @param resp  La respuesta HTTP que redirige al usuario a la página de Login
     * @throws ServletException
     * @throws IOException Si ocurre un error al enviar la respuesta o redirigir.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Hola " + usernameOptional.get() + "</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Hola " + usernameOptional.get() + " has iniciado sesión con éxito!</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.jsp'>volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/logout'>cerrar sesión</a></p>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
            req.setAttribute("title",req.getAttribute("title")+": Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    /**
     * Obtiene del Login.JSP los parametros y valida si los datos ingresados corresponden a los datos para ingresar al sistema.
     * @param req Peticion del usuario
     * @param resp Respuesta del servidor
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtiene los parametros del formulario de Login.JSP
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsuarioService service = new UsuarioServiceImpl((Connection) req.getAttribute("conn"));
        Optional<Usuario> usuarioOptional = service.login(username,password);

        if (usuarioOptional.isPresent()) { // Si el usuario esta presenta
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            //            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no estas autorizado para ingresar a esta pagina!");
            HttpServletRequest httpRequest = (HttpServletRequest) req;
            HttpServletResponse httpResponse = (HttpServletResponse) resp;

            httpRequest.getRequestDispatcher("/401.jsp").forward(httpRequest, httpResponse);

        }
    }
}
