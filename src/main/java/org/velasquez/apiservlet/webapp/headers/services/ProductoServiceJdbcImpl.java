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
 * Clase que implementa los repositorios
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @version 2
 * @date 4/08/2024
 * @time 21:14
 */

public class ProductoServiceJdbcImpl implements ProductoService {
    //Declarando la variable de instancia Repositorio Producto
    private Repository<Producto> repositoryJdbc;

    //Declarando la variable de instancia del repositorio Categoria
    private Repository<Categoria> repositoryCategoriaJdbc;

    //Inicializa los repositorios con cada una de sus implementaciones que previamente se le paso la conexion.
    public ProductoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryImpl(connection);
    }

    /**
     * @return la lista de todos los productos.
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
     * @param id el identificador único del producto a buscar.
     * @return el producto encontrado a travez del id
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
     * @param producto el objeto {@link Producto} a guardar.
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

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
