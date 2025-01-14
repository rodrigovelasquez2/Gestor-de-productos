package org.velasquez.apiservlet.webapp.headers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase que representa un carro de compras.
 * Contiene una lista de ítems de carro, que representan los productos seleccionados por el usuario.
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 2
 * @date 4/08/2024
 * @time 19:17
 */

public class Carro {
    private List<ItemCarro> items;

    /**
     * Constructor por defecto que inicializa un carro vacío.
     */
    public Carro() {
        this.items = new ArrayList<>();
    }

    /**
     * Agrega un ítem al carro. Si el ítem ya existe, incrementa la cantidad.
     *
     * @param itemCarro el ítem a agregar al carro.
     */
    public void addItemCarro(ItemCarro itemCarro) {
        if (items.contains(itemCarro)) {
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findAny();
            if (optionalItemCarro.isPresent()) { // Si el item existe
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad() + 1); // Se incrementa la cantidad en el carro de compras.
            }
        } else {
            this.items.add(itemCarro);
        }
    }

    /**
     * Obtiene la lista de ítems en el carro.
     *
     * @return la lista de ítems.
     */
    public List<ItemCarro> getItems() {
        return items;
    }

    /**
     * Calcula el importe total del carro sumando los importes de todos los ítems.
     *
     * @return el importe total del carro.
     */
    public int getTotal() {
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }

    /**
     * Elimina varios productos del carro basados en una lista de IDs de productos.
     *
     * @param productoIds la lista de IDs de los productos a eliminar.
     */
    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            productoIds.forEach(this::removeProducto);
            // Alternativa:
            // productoIds.forEach(productoId -> removeProducto(productoId));
        }
    }

    /**
     * Elimina un producto del carro basado en su ID.
     *
     * @param productoId el ID del producto a eliminar.
     */
    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    /**
     * Actualiza la cantidad de un producto en el carro.
     *
     * @param productoId el ID del producto cuya cantidad se va a actualizar.
     * @param cantidad   la nueva cantidad del producto.
     */
    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    /**
     * Busca un producto en el carro basado en su ID.
     *
     * @param productoId el ID del producto a buscar.
     * @return un Optional que contiene el ítem si se encuentra, o vacío si no.
     */
    private Optional<ItemCarro> findProducto(String productoId) {
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
