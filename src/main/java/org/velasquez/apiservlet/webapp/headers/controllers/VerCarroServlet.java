package org.velasquez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet que redirige al usuario al carro de compras cada vez que entra a la ruta de /carro/ver
 *
 * @author Velasquez Quiroz Rodrigo Andres
 * @date 4/08/2024
 * @time 21:44
 */

@WebServlet("/carro/ver")
public class VerCarroServlet extends HttpServlet {
    /**
     * @param req  La solicitud HTTP entrante.
     * @param resp La respuesta HTTP a ser enviada.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title",req.getAttribute("title")+": Carro de compras");
        getServletContext().getRequestDispatcher("/carro.jsp").forward(req, resp);
    }
}
