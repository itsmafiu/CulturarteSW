/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
