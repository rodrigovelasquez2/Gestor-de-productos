package org.velasquez.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

/**
 * Clase que obtiene la sesión del usuario que previamente se creo a travez del HTTPSession en los Servlets
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 2
 * @date 4/08/2024
 * @time 21:25
 */

public class LoginServiceSessionImpl implements LoginService{
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
