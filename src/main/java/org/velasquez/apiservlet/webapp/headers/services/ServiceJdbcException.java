package org.velasquez.apiservlet.webapp.headers.services;
/**
 * Clase que maneja las excepciones de manera mas personalizada
 * @author Velasquez Quiroz Rodrigo Andres
 * @version
 * @date 4/08/2024
 * @time 21:32
 */


public class ServiceJdbcException extends RuntimeException{
    public ServiceJdbcException(String message) {
        super(message);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
