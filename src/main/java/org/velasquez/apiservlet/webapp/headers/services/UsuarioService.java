package org.velasquez.apiservlet.webapp.headers.services;

import org.velasquez.apiservlet.webapp.headers.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);

    List<Usuario> listar();
    Optional<Usuario> porId(Long id);

    void guardar(Usuario usuario);

    void eliminar(Long id);
}
