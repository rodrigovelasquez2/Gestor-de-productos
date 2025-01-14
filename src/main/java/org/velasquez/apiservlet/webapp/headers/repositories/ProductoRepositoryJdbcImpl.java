package org.velasquez.apiservlet.webapp.headers.repositories;

import org.velasquez.apiservlet.webapp.headers.models.Categoria;
import org.velasquez.apiservlet.webapp.headers.models.Producto;
import static org.velasquez.apiservlet.webapp.headers.queries.MySQLQueries_Producto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa la función de cada método visto {@link Repository<Producto>}
 * para gestionar los productos con sus categorias
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 1/08/2024
 * @time 22:29
 */

public class ProductoRepositoryJdbcImpl implements Repository<Producto> {
    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Hace un inner join para obtener la toda la lista de productos
     * @return una lista de productos
     * @throws SQLException por si devuelve una excepcion de tipo SQL
     */
    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(MySQL_SELECT_ALL_PRODUCTO_WITH_CATEGORIA)) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    /**
     * Busca un producto en la base de datos a través de su ID utilizando una consulta SQL.
     *
     * @param id el identificador único del producto que se desea encontrar.
     * @return un objeto {@link Producto} si se encuentra el producto con el ID especificado,
     *         o {@code null} si no se encuentra ningún producto con ese ID.
     * @throws SQLException si ocurre un error al acceder a la base de datos o al ejecutar la consulta.
     */
    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement(MySQL_SELECT_BY_ID_PRODUCTO_WITH_CATEGORIA)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    /**
     * Guarda un objeto de tipo producto, contiene todos sus atributos a guardar.
     * @param producto Objeto producto que se pasa por la cabecera del método
     * @throws SQLException
     */

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql = (producto.getId() != null && producto.getId() > 0) ? MySQL_UPDATE_PRODUCTO : MySQL_INSERT_PRODUCTO;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(5, producto.getId());
            } else {
                //Registra con al fecha actual
                stmt.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            //Actualiza
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina un producto a travez de su id que se pasa por la cabecera del método.
     * @param id Identificador
     * @throws SQLException
     */
    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(MySQL_DELETE_PRODUCTO)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    /**
     * Obtiene todos los daotos del objeto Producto y categoria extraido del ResultSet.
     * @param rs
     * @return retorna un objeto Producto con los datos extraídos del ResultSet. Esto ayuda a entender qué se espera obtener al llamar este método.
     * @throws SQLException
     */
    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setSku(rs.getString("sku"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);

        return p;
    }
}
