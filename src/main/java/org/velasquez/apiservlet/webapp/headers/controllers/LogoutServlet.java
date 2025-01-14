package org.velasquez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.velasquez.apiservlet.webapp.headers.services.LoginService;
import org.velasquez.apiservlet.webapp.headers.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;
/**
 * Cierra la sesion del usuario a travez del metodo invalidate y lo redirige a la pagina principal.
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 2
 * @date 4/08/2024
 * @time 23:07
 */

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);
        if (username.isPresent()) {
            HttpSession session = req.getSession();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
