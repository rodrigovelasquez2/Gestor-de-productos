package org.velasquez.apiservlet.webapp.headers.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

/**
 * Clase que implementa el método de Login Service para obtener el usuario de las cookies
 * Es una clase que se remplazo por manejo de sesiones
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 14/01/2025
 * @time 15:50
 */

@Deprecated
public class LoginServiceCookieImpl implements LoginService {
    /**
     * Obtiene el nombre de usuario desde las cookies de la petición HTTP.
     * Busca una cookie con el nombre "username" y devuelve su valor como el nombre de usuario.
     * @param req la petición HTTP que contiene la información del usuario.
     * @return un optional si esta presente o no el usuario
     */
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies(): new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c-> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
