package org.velasquez.apiservlet.webapp.headers.services;

import org.velasquez.apiservlet.webapp.headers.models.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para gestionar el usuario
 * @author Velasquez Quiroz Rodrigo Andres
 * @version
 * @date 14/01/2025
 * @time 16:29
 */

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    void guardar(Usuario usuario);
    void eliminar(Long id);
}
