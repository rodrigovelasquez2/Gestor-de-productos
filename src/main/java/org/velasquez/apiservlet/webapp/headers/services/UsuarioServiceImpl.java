package org.velasquez.apiservlet.webapp.headers.services;

import org.velasquez.apiservlet.webapp.headers.models.Usuario;
import org.velasquez.apiservlet.webapp.headers.repositories.UsuarioRepository;
import org.velasquez.apiservlet.webapp.headers.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que implementa el repositorio del usuario
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version3 3
 * @date 9/08/2024
 * @time 11:05
 */

public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }//Fin UsuarioServiceImpl

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username))
                    .filter(u -> u.getPassword().equals(password)); // Si es igual el password del usuario al password que se pasa por parametro
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }//Fin login

    /**
     * @return la lista de productos.
     */
    @Override
    public List<Usuario> listar() {
        try {
            return usuarioRepository.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Guardar un nuevo usuario
     *
     * @param usuario
     */
    @Override
    public void guardar(Usuario usuario) {
        try {
            usuarioRepository.guardar(usuario);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(usuarioRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            usuarioRepository.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
