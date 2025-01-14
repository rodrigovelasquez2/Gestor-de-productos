package org.velasquez.apiservlet.webapp.headers.repositories;

import org.velasquez.apiservlet.webapp.headers.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Define como funcionara los métodos para la entidad Usuario
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 3.1.0
 * @date 8/08/2024
 * @time 18:28
 */

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private Connection conn;

    // Cosntructor donde se pasa la conexion.
    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Busca un registro a travez del nombre de usuario
     *
     * @param username Nombre del usuario
     * @return EL usuario encontrado.
     * @throws SQLException
     */
    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE username=?")) {
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
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        //Lista de usuarios
        List<Usuario> usuarios = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             //Consulta de listar todos los usuarios
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {
            while (rs.next()) { // Mientras haya registros
                Usuario u = getUsuario(rs);
                usuarios.add(u); // Se añade a la lista
            }
        }
        return usuarios;
    }


    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        try ( PreparedStatement stmt = conn.prepareStatement("select * from usuarios where id=?")) {
            stmt.setLong(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql;
        if (usuario.getId() != null && usuario.getId() > 0) { // Si el ID del usuario existe.
            sql = "update usuarios set username=?, password=?,email=? where id=?"; // Lo actualiza
        } else {
            sql = "insert into usuarios (username, password,email) values (?,?,?)"; // Sino, inserta uno nuevo
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,usuario.getUsername());
            stmt.setString(2,usuario.getPassword());
            stmt.setString(3,usuario.getGmail());

            if (usuario.getId() != null && usuario.getId() > 0) {
                stmt.setLong(4, usuario.getId());
            }

            stmt.executeUpdate();
        }
    }//Fin guardar

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from usuarios where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }//Fin eliminar

    /**
     * Obtiene todos los datos del objeto Usuario extraido del ResultSet.
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setGmail(rs.getString("email"));
        return u;
    }//Fin getUsuario


}
