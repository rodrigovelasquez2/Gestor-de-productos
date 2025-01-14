package org.velasquez.apiservlet.webapp.headers.models;

import java.util.Objects;

/**
 * Clase que representa un ítem en el carro de compras.
 * Contiene un producto y la cantidad de dicho producto en el carro.
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 4/08/2024
 * @time 19:17
 */

public class ItemCarro {
    private int cantidad;
    private Producto producto;

    /**
     * Constructor de la clase ItemCarro.
     * @param cantidad la cantidad del producto en el carro.
     * @param producto el producto asociado a este ítem.
     */
    public ItemCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    /**
     * @return la cantidad del producto en el carro.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto en el carro.
     * @param cantidad la cantidad a establecer.
     *
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto asociado a este ítem.
     * @return el producto asociado.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado a este ítem.
     *
     * @param producto el producto a establecer.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Compara este ítem con otro para verificar si son iguales.
     * La comparación se basa en el ID y el nombre del producto.
     *
     * @param o el objeto a comparar.
     * @return true si los ítems son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarro itemCarro = (ItemCarro) o;
        return Objects.equals(producto.getId(), itemCarro.producto.getId())
                && Objects.equals(producto.getNombre(), itemCarro.producto.getNombre());
    }

    /**
     * Calcula el importe total del ítem basado en la cantidad y el precio del producto.
     *
     * @return el importe total del ítem.
     */
    public int getImporte() {
        return cantidad * producto.getPrecio();
    }
}
