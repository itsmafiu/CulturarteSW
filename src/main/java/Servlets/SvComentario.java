/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.DataComentario;
import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "SvComentario", urlPatterns = {"/SvComentario"})
public class SvComentario extends HttpServlet {

    IControlador ic = Fabrica.getInstancia().getIControlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();
        String titulo = (String) sesion.getAttribute("titulo");
        
        List<DataComentario> DCs = ic.getDataComentarios(titulo);
        
        sesion.setAttribute("DCs", DCs);
        
        response.sendRedirect("infoPropuesta.jsp");        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String titulo = (String) sesion.getAttribute("titulo");
        String nick = (String) sesion.getAttribute("nick");
        String comentario = (String) request.getAttribute("comentario");
        
        ic.addComentario(titulo, nick, comentario);
        response.sendRedirect("infoPropuesta.jsp");        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
