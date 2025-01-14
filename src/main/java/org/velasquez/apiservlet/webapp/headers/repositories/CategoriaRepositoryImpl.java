package org.velasquez.apiservlet.webapp.headers.repositories;

import org.velasquez.apiservlet.webapp.headers.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Repositorio de la entidad Categoria que implementa los métodos del Repositorio
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 2
 * @date 4/08/2024
 * @time 20:17
 */

public class CategoriaRepositoryImpl implements Repository<Categoria>{
    private Connection conn;

    // Cosntructor donde se pasa la conexion.
    public CategoriaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Obtiene todas los registros de categorias.
     * @return la lista de todas las categorias existentes en la base de datos
     * @throws SQLException
     */
    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from categorias")){
            while (rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }

        }
        return categorias;
    }

    /**
     * Busca un producto a travez de su ID
     * @param id el identificador único de la entidad.
     * @return El producto encontrado
     * @throws SQLException
     */
    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from categorias as c where c.id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    /**
     * Obtiene todos los datos extraidos del ResultSet.
     * @param rs
     * @return Todos los datos de la entidad categoria
     * @throws SQLException
     */
    private Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setId(rs.getLong("id"));
        return categoria;
    }

    //Métodos sin implementar.
    @Deprecated
    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }
    @Deprecated
    @Override
    public void eliminar(Long id) throws SQLException {

    }


}