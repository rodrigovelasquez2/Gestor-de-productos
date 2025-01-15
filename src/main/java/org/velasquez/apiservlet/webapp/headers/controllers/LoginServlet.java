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

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    /**
     * Se encarga de obtener la session del usuario, de estar activo, mostrara un mensaje de bienvenida para el usuario.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            getServletContext().getRequestDispatcher("/menu.jsp").forward(req, resp);

        } else {
            req.setAttribute("title",req.getAttribute("title")+": Login");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
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
