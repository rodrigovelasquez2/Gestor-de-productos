package org.velasquez.apiservlet.webapp.headers.queries;

public class MySQLQueries_Producto {
    public static final String MySQL_SELECT_ALL_PRODUCTO_WITH_CATEGORIA = "SELECT p.*, c.nombre as categoria FROM productos as p inner join categorias as c ON (p.categoria_id = c.id) order by p.id ASC";
    public static final String MySQL_SELECT_BY_ID_PRODUCTO_WITH_CATEGORIA = "SELECT p.*, c.nombre as categoria FROM productos as p inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?";
    public static final String MySQL_INSERT_PRODUCTO = "insert into productos (nombre, precio, sku, categoria_id, fecha_registro) values (?,?,?,?,?)";
    public static final String MySQL_UPDATE_PRODUCTO = "update productos set nombre=?, precio=?, sku=?, categoria_id=? where id=?";
    public static final String MySQL_DELETE_PRODUCTO = "delete from productos where id=?";

}
