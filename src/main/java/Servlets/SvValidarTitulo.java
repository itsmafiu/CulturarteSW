package Servlets;

import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SvValidarTitulo")
public class SvValidarTitulo extends HttpServlet {

    protected final IControlador ic = Fabrica.getInstancia().getIControlador();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titulo = request.getParameter("titulo");
        response.setContentType("text/plain");

        if (titulo == null || titulo.trim().isEmpty()) {
            response.getWriter().write("noexiste");
            return;
        }

        boolean existe = ic.existeTitulo(titulo);

        if (existe) {
            response.getWriter().write("existe");
        } else {
            response.getWriter().write("noexiste");
        }
    }
}