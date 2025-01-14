package org.velasquez.apiservlet.webapp.headers.services;

import org.velasquez.apiservlet.webapp.headers.models.Categoria;
import org.velasquez.apiservlet.webapp.headers.models.Producto;
import org.velasquez.apiservlet.webapp.headers.repositories.CategoriaRepositoryImpl;
import org.velasquez.apiservlet.webapp.headers.repositories.ProductoRepositoryJdbcImpl;
import org.velasquez.apiservlet.webapp.headers.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Clase que define los métodos de ProductService para Gestionar los productos y categorias de la base de datos
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 14/01/2025
 * @time 16:07
 */


public class ProductoServiceJdbcImpl implements ProductoService {
    private Repository<Producto> repositoryJdbc;

    private Repository<Categoria> repositoryCategoriaJdbc;

    //Se inicializa los repositorios
    public ProductoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryImpl(connection);
    }


    /**
     * Devuelve una lista de objetos {@link Producto}.
     *
     * @return Una lista de productos.
     */
    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Devuelve un optional si se encuentra el producto {@link Producto}
     *
     * @param id Identificador del producto
     * @return Un {@link Optional} que contiene el producto encontrado, o {@link Optional#empty()} si no se encuentra.
     */
    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    /**
     * Guarda el objeto {@link Producto} en la base de datos.
     * Si ocurre un error durante el proceso de guardado, se lanza una {@link ServiceJdbcException}.
     *
     * @param producto El objeto {@link Producto} que se desea guardar en la base de datos.
     */
    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Elimina el objeto {@link Producto} de la base de datos mediante su id
     * Si ocurre un error durante el proceso de guardado, se lanza una {@link ServiceJdbcException}.
     * @param id el identificador único del producto a eliminar.
     */
    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Devuelve una lista de categorias {@link Categoria}
     * Si ocurre un error lanza una  {@link ServiceJdbcException}.
     * @return la lista de todas las categorias
     */
    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Devuelve un optional si encuentra la categoria mediante su id
     * Si ocurre un error lanza una  {@link ServiceJdbcException}.
     * @param id Identificador de la categoría
     * @return
     */
    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
