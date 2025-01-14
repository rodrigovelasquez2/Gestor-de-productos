package org.velasquez.apiservlet.webapp.headers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.velasquez.apiservlet.webapp.headers.services.ServiceJdbcException;
import org.velasquez.apiservlet.webapp.headers.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Filtro que gestiona la conexión a la base de datos para todas las peticiones.
 * <p>
 * El filtro establece una conexión a la base de datos y la almacena en un atributo de la solicitud (request).
 * Desactiva el auto-commit para permitir transacciones manuales.
 * En caso de una ejecución exitosa del filtro en cadena, se confirma la transacción
 * (commit). Si ocurre una excepción (SQLException o ServiceJdbcException), se deshace la transacción
 * (rollback) y se envía un error HTTP 500 al cliente.
 * <p>
 * Finalmente, cierra la conexión a la base de datos.
 */
@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try (Connection conn = ConexionBaseDatos.getConnection()) {

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                request.setAttribute("conn", conn);
                chain.doFilter(request, response);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback();
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
