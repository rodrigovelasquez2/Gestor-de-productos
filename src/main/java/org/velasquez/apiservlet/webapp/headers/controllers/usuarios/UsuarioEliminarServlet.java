package org.velasquez.apiservlet.webapp.headers.controllers.usuarios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velasquez.apiservlet.webapp.headers.models.Usuario;
import org.velasquez.apiservlet.webapp.headers.services.UsuarioService;
import org.velasquez.apiservlet.webapp.headers.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

/**
 * Servlet encargado de eliminar un {@link Usuario} de la base de datos.
 * Este servlet maneja la solicitud HTTP GET para eliminar un usuario basado en su identificador {@code id} pasado como parámetro en la URL.
 * Si el id es válido y el usuario existe en la base de datos, se elimina al usuario y redirige a la lista de usuarios.
 * Si el id no es válido o el usuario no se encuentra, se devuelve un error 404 con un mensaje adecuado.
 * @author Velasquez Quiroz Rodrigo Andres
 */

@WebServlet("/usuarios/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {
    /**
     * Maneja la solicitud GET para eliminar un usuario.
     * @param req La solicitud HTTP que contiene el parámetro {@code id}.
     * @param resp La respuesta HTTP para redirigir o devolver un error.
     * @throws ServletException Si ocurre un error en el procesamiento del servlet.
     * @throws IOException Si ocurre un error al enviar la respuesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn"); // Obtiene la conexion de la base de datos
        UsuarioService service = new UsuarioServiceImpl(conn);
        long id;

        // Intenta obtener el parámetro 'id' de la URL y manejar excepciones si no es válido
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        // Si el 'id' es válido, busca al usuario en la base de datos
        if (id > 0) {
            Optional<Usuario> o = service.porId(id);
            // Si el usuario existe, lo elimina y redirige a la lista de usuarios
            if (o.isPresent()) {
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+ "/usuarios");
            } else {
                // Si el usuario no existe, se responde con un error 404
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el usuarios en la base de datos!");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null, se debe enviar como parametro en la url!");
        }
    }
}
