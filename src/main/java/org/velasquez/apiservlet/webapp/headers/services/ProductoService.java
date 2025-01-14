package org.velasquez.apiservlet.webapp.headers.services;

import org.velasquez.apiservlet.webapp.headers.models.Categoria;
import org.velasquez.apiservlet.webapp.headers.models.Producto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para gestionar los productos y categorías.
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 14/01/2025
 */
public interface ProductoService {

    /**
     * Obtiene la lista de productos.
     *
     * @return una lista de productos
     */
    List<Producto> listar();

    /**
     * Obtiene un producto por su ID.
     *
     * @param id Identificador del producto
     * @return Producto si existe, vacío si no
     */
    Optional<Producto> porId(Long id);

    /**
     * Guarda un producto en la base de datos.
     *
     * @param producto Producto a guardar
     */
    void guardar(Producto producto);

    /**
     * Elimina un producto por su ID.
     *
     * @param id Identificador del producto
     */
    void eliminar(Long id);

    /**
     * Obtiene la lista de categorías de productos.
     *
     * @return Lista de categorías
     */
    List<Categoria> listarCategoria();

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id Identificador de la categoría
     * @return Categoría si existe, vacío si no
     */
    Optional<Categoria> porIdCategoria(Long id);
}