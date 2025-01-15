package org.velasquez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.velasquez.apiservlet.webapp.headers.models.Carro;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Servlet encargado de manejar la actualización del carrito de compras.
 * Este servlet procesa las solicitudes POST para actualizar los productos y las cantidades en el carrito de compras.
 * Si el carrito está presente en la sesión del usuario, se actualizarán los productos a eliminar y las cantidades de los productos.
 * Finalmente, redirige al usuario a la vista del carrito actualizado.
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 14/01/2025
 */
@WebServlet("/carro/actualizar")
public class ActualizarCarroServlet extends HttpServlet {

    /**
     * Procesa la solicitud POST para actualizar el carrito de compras.
     * Luego, redirige al usuario a la vista del carrito.
     * @param req La solicitud HTTP que contiene los parámetros para actualizar el carrito.
     * @param resp La respuesta HTTP que redirige al usuario a la página del carrito actualizado.
     * @throws IOException Si ocurre un error al enviar la respuesta o redirigir.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        //Obtiene la sesion
        HttpSession session = req.getSession();
        if (session.getAttribute("carro") != null) {
            Carro carro = (Carro) session.getAttribute("carro");
            //Actualiza los productos
            updateProductos(req, carro);
            //Actualiza las cantidades
            updateCantidades(req, carro);
        }

        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }

    /**
     * Actualiza los productos eliminados del carrito.
     * Si se reciben productos para eliminar en la solicitud, se eliminan del carrito.
     *
     * @param request La solicitud HTTP que contiene los productos a eliminar.
     * @param carro El objeto {@link Carro} que se actualizará con los productos eliminados.
     */
    private void updateProductos(HttpServletRequest request, Carro carro) {
        String[] deleteIds = request.getParameterValues("deleteProductos");

        if (deleteIds != null && deleteIds.length > 0) {
            List<String> productoIds = Arrays.asList(deleteIds);
            // Borramos los productos del carrito.
            carro.removeProductos(productoIds);
        }

    }

    /**
     * Actualiza las cantidades de los productos en el carrito.
     * Itera a través de los parámetros de la solicitud y actualiza la cantidad de cada producto cuyo nombre de parámetro empieza con "cant_".
     *
     * @param request La solicitud HTTP que contiene las cantidades de los productos.
     * @param carro El objeto {@link Carro} que se actualizará con las nuevas cantidades.
     */
    private void updateCantidades(HttpServletRequest request, Carro carro) {

        Enumeration<String> enumer = request.getParameterNames();

        // Iteramos a traves de los parámetros y buscamos los que empiezan con
        // "cant_". El campo cant en la vista fueron nombrados "cant_" + productoId.
        // Obtenemos el id de cada producto y su correspondiente cantidad ;-).
        while (enumer.hasMoreElements()) {
            String paramName = enumer.nextElement();
            if (paramName.startsWith("cant_")) {
                String id = paramName.substring(5);
                String cantidad = request.getParameter(paramName);
                if (cantidad != null) {
                    carro.updateCantidad(id, Integer.parseInt(cantidad));
                }
            }
        }
    }
}
