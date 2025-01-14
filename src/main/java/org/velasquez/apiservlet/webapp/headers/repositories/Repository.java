package org.velasquez.apiservlet.webapp.headers.repositories;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica que define operaciones CRUD básicas para un repositorio de objetos de tipo T.
 * <p>Al ser implementada, proporciona la estructura para interactuar con una fuente de datos
 * específica (como una base de datos) y manejar entidades de tipo T.</p>
 *
 * @param <T> el tipo de entidad que maneja el repositorio
 */
public interface Repository<T> {

    /**
     * Recupera una lista de todas las entidades de tipo T.
     *
     * @return una lista de objetos de tipo T.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    List<T> listar() throws SQLException;

    /**
     * Busca una entidad de tipo T por su identificador único.
     *
     * @param id el identificador único de la entidad.
     * @return un objeto de tipo T si se encuentra, o {@code null} si no se encuentra.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    T porId(Long id) throws SQLException;

    /**
     * Guarda una entidad de tipo T. Puede ser una operación de inserción o actualización.
     *
     * @param t la entidad de tipo T a guardar.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    void guardar(T t) throws SQLException;

    /**
     * Elimina una entidad de tipo T basada en su identificador único.
     *
     * @param id el identificador único de la entidad a eliminar.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    void eliminar(Long id) throws SQLException;
}
