package org.velasquez.apiservlet.webapp.headers.queries;

public class MySQLQueries_Categoria {
    public static final String MySQL_SELECT_ALL_CATEGORIAS = "SELECT * FROM categorias";
    public static final String MySQL_SELECT_BY_ID_CATEGORIAS = "SELECT * FROM categorias as c where c.id=?";
}
