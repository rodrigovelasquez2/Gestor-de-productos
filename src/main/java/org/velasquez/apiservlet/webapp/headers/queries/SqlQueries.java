package org.velasquez.apiservlet.webapp.headers.queries;

public class SqlQueries {
    public static final String MySQL_SELECT_ALL = "SELECT * FROM usuarios";
    public static final String MySQL_SELECT_BY_USERNAME = "SELECT * FROM usuarios WHERE username = ?";
    public static final String MySQL_SELECT_BY_ID = "SELECT * FROM usuarios WHERE id = ?";
    public static final String MySQL_INSERT = "INSERT INTO usuarios (username, password, email) VALUES (?, ?, ?)";
    public static final String MySQL_UPDATE = "UPDATE usuarios SET username = ?, password = ?, email = ? WHERE id = ?";
    public static final String MySQL_DELETE = "DELETE FROM usuarios WHERE id = ?";
}
