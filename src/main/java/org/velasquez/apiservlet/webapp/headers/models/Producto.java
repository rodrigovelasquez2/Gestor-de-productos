package org.velasquez.apiservlet.webapp.headers.models;

import java.time.LocalDate;
/**
 * Clase que representa la tabla productos de la base de datos.
 * Aplica encapsulamiento a travez de get and set.
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 4/08/2024
 * @time 19:14
 */

public class Producto {
    private Long id;
    private String nombre;
    private Categoria categoria;
    private int precio;
    private String sku;
    private LocalDate fechaRegistro;

    public Producto() {
    }

    public Producto(Long id, String nombre, String tipo, int precio) {
        this.id = id;
        this.nombre = nombre;
        Categoria categoria = new Categoria();
        categoria.setNombre(tipo);
        this.categoria = categoria;
        this.precio = precio;
    }

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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
