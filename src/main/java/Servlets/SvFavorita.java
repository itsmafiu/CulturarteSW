/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luiano
 */
@WebServlet(name = "SvFavorita", urlPatterns = {"/SvFavorita"})
public class SvFavorita extends HttpServlet {

    private final IControlador ic = Fabrica.getInstancia().getIControlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String nick = (String) session.getAttribute("nick");
        String titulo = (String) session.getAttribute("titulo");

        boolean esFavorita = ic.esFavorita(titulo,nick);
        //ic.cambiarFavorita(titulo, nick);
        
        session.setAttribute("esFavorita", esFavorita);

        response.sendRedirect("infoPropuesta.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String nickUsuario = (String) session.getAttribute("nick");
        String titulo = (String) session.getAttribute("titulo");

        boolean esFavorita= false;
        
        int n = ic.cambiarFavorita(titulo, nickUsuario);
        
        if(n == 1){
            esFavorita = true;
        } 
        
        session.setAttribute("esFavorita", esFavorita);

        response.sendRedirect("infoPropuesta.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
