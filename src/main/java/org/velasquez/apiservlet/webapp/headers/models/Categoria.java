package org.velasquez.apiservlet.webapp.headers.models;
/**
 * Clase que representa la tabla categorias en la base de datos
 * Aplica encapsulamiento a travez del uso de get and set
 * @author Velasquez Quiroz Rodrigo Andres
 * @version
 * @date 1/08/2024
 * @time 22:31
 */

public class Categoria {
    private Long id;
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
