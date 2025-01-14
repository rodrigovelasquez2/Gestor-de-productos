package org.velasquez.apiservlet.webapp.headers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velasquez.apiservlet.webapp.headers.services.LoginService;
import org.velasquez.apiservlet.webapp.headers.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

/**
 * Filtro de seguridad que verifica si un usuario está autenticado antes de acceder a ciertos recursos.
 *
 * El filtro valida si el usuario tiene un nombre de usuario almacenado en la sesión. Si no lo tiene,
 * se envía un error HTTP 401 (Unauthorized) con un mensaje informativo.
 */
@WebFilter({"/carro/*", "/productos/form/*", "/productos/eliminar/*"})
public class LoginFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        if (username.isPresent()) {
            chain.doFilter(request, response);
        } else {
//            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no estas autorizado para ingresar a esta pagina!");
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            httpRequest.getRequestDispatcher("/401.jsp").forward(httpRequest, httpResponse);
        }
    }
}
