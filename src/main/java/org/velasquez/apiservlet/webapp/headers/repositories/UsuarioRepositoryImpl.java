package org.velasquez.apiservlet.webapp.headers.repositories;

import org.velasquez.apiservlet.webapp.headers.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Define como funcionará los métodos para la entidad Usuario
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 3.1.0
 * @date 8/08/2024
 * @time 18:28
 */

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private static final String MySQL_SELECT_ALL = "SELECT * FROM usuarios";
    private static final String MySQL_SELECT_BY_USERNAME = "SELECT * FROM usuarios WHERE username = ?";
    private static final String MySQL_SELECT_BY_ID = "SELECT * FROM usuarios WHERE id = ?";
    private static final String MySQL_INSERT = "INSERT INTO usuarios (username, password, email) VALUES (?, ?, ?)";
    private static final String MySQL_UPDATE = "UPDATE usuarios SET username = ?, password = ?, email = ? WHERE id = ?";
    private static final String MySQL_DELETE = "DELETE FROM usuarios WHERE id = ?";

    private Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement(MySQL_SELECT_BY_USERNAME)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setGmail(rs.getString("email"));
                }
            }
        }
        return usuario;
    }//Fin porUsername

    // Se encarga de listar todos los usuarios existentes en la base de datos
    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(MySQL_SELECT_ALL)) {
            while (rs.next()) {
                Usuario u = getUsuario(rs);
                usuarios.add(u);
            }
        }
        return usuarios;
    }//Fin listar


    // Devuelve el usuario encontrado mediante su id
    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement(MySQL_SELECT_BY_ID)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }//Fin porId

    // Actualiza el usuario que existe y guarda el usuario no existente.
    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql = (usuario.getId() != null && usuario.getId() > 0) ? MySQL_UPDATE : MySQL_INSERT;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getGmail());

            if (usuario.getId() != null && usuario.getId() > 0) {
                stmt.setLong(4, usuario.getId());
            }
            stmt.executeUpdate();
        }
    }//Fin guardar

    // Elimina el usuario
    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(MySQL_DELETE)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }//Fin eliminar

    // Obtiene todos los datos del objeto Usuario extraido del ResultSet.
    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setGmail(rs.getString("email"));
        return u;
    }//Fin getUsuario
}//Fin UsuarioRepositoryImpl
