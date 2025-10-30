/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Luiano
 */
@WebServlet(name = "SvCancelarPropuesta", urlPatterns = {"/SvCancelarPropuesta"})
public class SvCancelarPropuesta extends HttpServlet {

//    private IControlador ic = Fabrica.getInstancia().getIControlador();
    LogicaWS_Service service;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        HttpSession misesion = request.getSession();
        
        String titulo = (String) misesion.getAttribute("titulo");
        
        ic.cancelarPropuesta(titulo);
        
        response.sendRedirect("propuestaCancelada.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
