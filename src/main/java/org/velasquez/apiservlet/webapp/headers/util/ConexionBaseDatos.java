package org.velasquez.apiservlet.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//  Clase donde se configura la conexion a la base de datos
public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/ServletJDBC?serverTimezone=America/Santiago";
    private static String username = "root";
    private static String password = "root";

    //Obtiene la conexion de la base de datos
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
