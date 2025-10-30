package Servlets;

import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SvValidarTitulo")
public class SvValidarTitulo extends HttpServlet {

//    protected final IControlador ic = Fabrica.getInstancia().getIControlador();
    LogicaWS_Service service;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        
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