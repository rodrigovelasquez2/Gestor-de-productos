package org.velasquez.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;
/**
 * Interface para obtener el nombre de usuario a partir de la petición HTTP.
 * Esta interfaz define el método necesario para extraer el nombre de usuario del objeto {@link HttpServletRequest}.
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 14/01/2025
 * @time 15:44
 */

public interface LoginService {
    /**
     * Obtiene el nombre de usuario de la petición HTTP.
     * @param request la petición HTTP que contiene la información del usuario.
     */
    Optional<String> getUsername(HttpServletRequest request);
}
