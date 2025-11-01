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
@WebServlet(name = "SvFavorita", urlPatterns = {"/SvFavorita"})
public class SvFavorita extends HttpServlet {

//    private final IControlador ic = Fabrica.getInstancia().getIControlador();
    LogicaWS_Service service;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        String nick = (String) session.getAttribute("nick");
        String titulo = request.getParameter("titulo");

        boolean esFavorita;
        System.out.println("titulo: "+titulo);
        System.out.println("nick: "+nick);
        
        esFavorita = ic.cambiarFavorita(titulo, nick);
                
        session.setAttribute("esFavorita", esFavorita);

        response.sendRedirect("infoPropuesta.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
