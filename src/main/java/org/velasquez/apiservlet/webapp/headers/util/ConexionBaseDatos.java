package org.velasquez.apiservlet.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * Clase donde se maneja las configuraciones de las base de datos
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 1
 * @date 1/08/2024
 * @time 22:25
 */

public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/ServletJDBC?serverTimezone=America/Santiago";
    private static String username = "root";
    private static String password = "root";

    /**
     * Método que obtiene la conexión de la base de datos y regresa la petición
     * @return Regresa la cadena de conexión
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
