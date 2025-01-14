package org.velasquez.apiservlet.webapp.headers.services;

import org.velasquez.apiservlet.webapp.headers.models.Categoria;
import org.velasquez.apiservlet.webapp.headers.models.Producto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios relacionados con la gestión de productos.
 */
public interface ProductoService {

    /**
     * Recupera una lista de todos los productos del sistema.
     *
     * @return una lista de objetos de tipo {@link Producto}.
     */
    List<Producto> listar();

    /**
     * Busca un producto específico por su identificador único.
     *
     * @param id el identificador único del producto a buscar.
     * @return un {@link Optional} que contiene el producto encontrado
     *         o {@link Optional#empty()} si no se encuentra.
     */
    Optional<Producto> porId(Long id);

    /**
     * Guarda un nuevo producto en el sistema.
     *
     * @param producto el objeto {@link Producto} a guardar.
     */
    void guardar(Producto producto);

    /**
     * Elimina un producto del sistema basado en su identificador único.
     *
     * @param id el identificador único del producto a eliminar.
     */
    void eliminar(Long id);

    /**
     * Recupera una lista de todas las categorías de productos del sistema.
     *
     * @return una lista de objetos de tipo {@link Categoria}.
     */
    List<Categoria> listarCategoria();

    /**
     * Busca una categoría específica por su identificador único.
     *
     * @param id el identificador único de la categoría a buscar.
     * @return un {@link Optional} que contiene la categoría encontrada
     *         o {@link Optional#empty()} si no se encuentra.
     */
    Optional<Categoria> porIdCategoria(Long id);
}
