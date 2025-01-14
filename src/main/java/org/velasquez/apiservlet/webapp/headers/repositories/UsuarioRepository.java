package org.velasquez.apiservlet.webapp.headers.repositories;

import org.velasquez.apiservlet.webapp.headers.models.Usuario;

import java.sql.SQLException;
/**
 * Interface que hereda los métodos de Repository para ser implementado en {@link UsuarioRepositoryImpl}
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 8/08/2024
 * @time 18:27
 */


public interface UsuarioRepository extends Repository<Usuario>{
  Usuario porUsername(String username) throws SQLException;

}
