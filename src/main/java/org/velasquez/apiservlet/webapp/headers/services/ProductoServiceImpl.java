package org.velasquez.apiservlet.webapp.headers.services;

import org.velasquez.apiservlet.webapp.headers.models.Categoria;
import org.velasquez.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Clase que simulaba el crud de productos a nivel de consola
 * @author Velasquez Quiroz Rodrigo Andres
 * @version
 * @date 14/01/2025
 * @time 16:06
 */


@Deprecated
public class ProductoServiceImpl implements ProductoService {

    @Deprecated
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000));
    }

    @Deprecated
    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findAny();
    }

    @Deprecated
    @Override
    public void guardar(Producto producto) {

    }

    @Deprecated
    @Override
    public void eliminar(Long id) {
    }

    @Deprecated
    @Override
    public List<Categoria> listarCategoria() {
        return null;
    }

    @Deprecated
    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }
}
