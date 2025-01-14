package org.velasquez.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;
/**
 * Servicio que obtiene el nombre del usuario de la peticion HTTP request
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 2
 * @date 4/08/2024
 * @time 21:23
 */

public interface LoginService {
    Optional<String> getUsername(HttpServletRequest request);
}
