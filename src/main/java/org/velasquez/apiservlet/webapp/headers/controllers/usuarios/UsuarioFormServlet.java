package org.velasquez.apiservlet.webapp.headers.controllers.usuarios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velasquez.apiservlet.webapp.headers.models.Usuario;
import org.velasquez.apiservlet.webapp.headers.services.UsuarioService;
import org.velasquez.apiservlet.webapp.headers.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form")
public class UsuarioFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id")); // Si existe el usuario, guarda su ID
        } catch (NumberFormatException e) {
            id = 0L; // Sino, su ID sera 0 indicando que es un nuevo usuario para añadir
        }
        Usuario usuario = new Usuario();

        if (id > 0) { // Si el id del usuario es mayor que 0
            Optional<Usuario> o = service.porId(id); // Se busca el usuario a travez de su id
            if (o.isPresent()) { // El usuario esta presente
                usuario = o.get(); // Se obtiene todos sus datos
            }
        }

        req.setAttribute("usuario", usuario);
        req.setAttribute("title", req.getAttribute("title") + ": Registro de usuario");

        //Se redirige a formulario JSP.
        getServletContext().getRequestDispatcher("/usuarios/formUsuario.jsp").forward(req, resp);
    }//Fin doGet

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtengo de Conexion filter la conexion
        Connection conn = (Connection) req.getAttribute("conn");
//        Implemento el servicio del Usuario Service
        UsuarioService service = new UsuarioServiceImpl(conn);

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

//      Obtengo del formulario "formUsuario" los parametros
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("gmail");


//        Valido si los parametros tiene campos vacios

        //Creo un map para guardar los errores por clave valor
        Map<String,String> errores= new HashMap<>();

        if (username == null || username.isBlank()) { // El usuario es vacio o blanco
            errores.put("username", "el username es requerido!"); // Guardo en la clave "username" el mensaje del error
        }

        if ((id == 0) && (password == null || password.isBlank())) { // El id es vacio o no se inserto parametros
            errores.put("password", "el password es requerido!"); // Guardo en la clave "id" el mensaje del error
        }

        if (email == null || email.isBlank()) {// El email introducido esta vacio
            errores.put("gmail", "el email es requerido!"); // Guardo en la clave "email" el mensaje del error
        }

        //Creo el usuario
        Usuario usuario = new Usuario();

        if (id > 0) { // Si el usuario existe
            Optional<Usuario> o = service.porId(id); //Se busca por id
            if (o.isPresent()) { // si esta presente
                usuario = o.get(); // Se obtiene todos sus datos
            }
        }
        //Pasa email y username al objeto usuario
        usuario.setGmail(email);
        usuario.setUsername(username);

        if (password != null && !password.isBlank()) { //Si el password no esta vacio
            usuario.setPassword(password); // Se pasa al objeto usuario
        }

        if (errores.isEmpty()) { // Si errores esta vacio
            service.guardar(usuario); // Guarda el nuevo usuario
            resp.sendRedirect(req.getContextPath() + "/usuarios");//Redirigie a listar usuarios
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("usuario", usuario);
            req.setAttribute("title", req.getAttribute("title") + ": Formulario de usuario");
            getServletContext().getRequestDispatcher("/usuarios/formUsuario.jsp").forward(req, resp);
        }

    }//Fin doPost
}//Fin UsuarioFormServlet
