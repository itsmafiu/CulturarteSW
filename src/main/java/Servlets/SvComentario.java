/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DataComentario;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Luiano
 */
@WebServlet(name = "SvComentario", urlPatterns = {"/SvComentario"})
public class SvComentario extends HttpServlet {

//    IControlador ic = Fabrica.getInstancia().getIControlador();
    
    LogicaWS_Service service;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        HttpSession sesion = request.getSession();
//        String titulo = (String) sesion.getAttribute("titulo");
//        
//        List<DataComentario> DCs = ic.getDataComentarios(titulo);
//        
//        sesion.setAttribute("DCs", DCs);
//        
//        response.sendRedirect("infoPropuesta.jsp");      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        
        HttpSession sesion = request.getSession();
        String titulo = (String) request.getParameter("titulo");
        String nick = (String) request.getParameter("nick");
        String comentario = (String) request.getParameter("comentario");
        System.out.println(titulo);
        System.out.println(nick);
        ic.addComentario(titulo, nick, comentario);
        List<DataComentario> DCs = ic.getDataComentarios(titulo);
        sesion.setAttribute("DCs", DCs);
        response.sendRedirect("infoPropuesta.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
