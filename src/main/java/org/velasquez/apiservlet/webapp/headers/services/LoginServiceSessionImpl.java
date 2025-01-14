package org.velasquez.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

/**
 * Clase que implementa el método de LoginService para obtener el usuario mediante manejo de sesiones
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 4/08/2024
 * @time 21:25
 */

public class LoginServiceSessionImpl implements LoginService{
    /**
     * Obtiene el nombre de usuario desde el manejo de sesiones
     * Busca en la sesion el atributo con el nombre "username" y devuelve su valor como el nombre de usuario
     * @param request la petición HTTP que contiene la información del usuario.
     * @return Retorna un optional si el usuario esta presente o no
     */
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
