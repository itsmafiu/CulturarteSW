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
 * @author mafiu
 */
@WebServlet(name = "cerrarSesion", urlPatterns = {"/cerrarSesion"})
public class SvCerrarSesion extends HttpServlet {

//    protected final IControlador ic = Fabrica.getInstancia().getIControlador();
    LogicaWS_Service service;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        
        String eliminar = (String) request.getAttribute("eliminarProp");
        if(eliminar != null && eliminar.equals("si")){
            String nick = (String) request.getAttribute("nick");
            ic.eliminarProponente(nick);
        }
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("nick", null);
        misesion.setAttribute("tipoUsuario", null);
        misesion.setAttribute("datosUsuario", null);
        
        String user = request.getHeader("User-Agent");
        boolean esMovil = user != null && (user.contains("Mobi") || user.contains("Android") || user.contains("iPhone"));
        if(esMovil){
            response.sendRedirect("inicioSesion.jsp");
        }else{
            response.sendRedirect("index.jsp");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
