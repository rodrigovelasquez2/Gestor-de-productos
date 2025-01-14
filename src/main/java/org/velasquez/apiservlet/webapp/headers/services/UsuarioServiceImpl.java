package org.velasquez.apiservlet.webapp.headers.services;

import org.velasquez.apiservlet.webapp.headers.models.Usuario;
import org.velasquez.apiservlet.webapp.headers.repositories.UsuarioRepository;
import org.velasquez.apiservlet.webapp.headers.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que implementa el repositorio del usuario para gestionar el Usuario
 * Si ocurre un error lanza una  {@link ServiceJdbcException}.
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 14/01/2025
 * @time 16:30
 */


public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }//Fin UsuarioServiceImpl

    /**
     * Intenta autenticar a un {@link Usuario} utilizando el nombre de usuario y la contraseña proporcionados.
     * @param username Nombre del usuario
     * @param password Clave del usuario
     * @return Un {@link Optional} que contiene el {@link Usuario} autenticado, o {@link Optional#empty()} si las
     * credenciales no son válidas.
     */
    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }//Fin login

    /**
     * Devuelve una lista {@link Usuario}
     * @return la lista de todos los usuarios encontrados
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
     * Guarda el objeto {@link Usuario} en la base de datos
     * @param usuario el nombre del usuario
     */
    @Override
    public void guardar(Usuario usuario) {
        try {
            usuarioRepository.guardar(usuario);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Devuelve el {@link Usuario} encontrado a travez de su id
     * @param id Identificador unico del usuario
     * @return el {@link Usuario} encontrado
     */
    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(usuarioRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Elimina el {@link Usuario } de la base de datos mediante su id
     * @param id Identificador unico
     */
    @Override
    public void eliminar(Long id) {
        try {
            usuarioRepository.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
