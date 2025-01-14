package org.velasquez.apiservlet.webapp.headers.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

/**
 * Clase encargada de obtener el usuario a travez de las cookies
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 2
 * @date 4/08/2024
 * @time 21:24
 */

public class LoginServiceCookieImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies(): new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c-> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
