package org.velasquez.apiservlet.webapp.headers.models;

/**
 * Entidad que maneja los atributos del usuario
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 8/08/2024
 * @time 18:18
 */

public class Usuario {
    private Long id;
    private String username;
    private String password;
    private String gmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}//Fin Usuario
