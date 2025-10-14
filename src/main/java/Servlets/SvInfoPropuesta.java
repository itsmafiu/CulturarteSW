/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Aporte;
import Logica.Colaborador;
import Logica.DataColaborador;
import Logica.DataPropuesta;
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
 * @author nahud
 */
@WebServlet(name = "SvInfoPropuesta", urlPatterns = {"/SvInfoPropuesta"})
public class SvInfoPropuesta extends HttpServlet {

    protected final IControlador ic = Fabrica.getInstancia().getIControlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession misesion = request.getSession();
        
        String titulo = request.getParameter("titulo");
        
        misesion.setAttribute("titulo", titulo);
        
        DataPropuesta DP = ic.consultaDePropuesta(titulo);
        
        List<DataColaborador> colabs = new ArrayList<>();
            
        for(Aporte a : DP.getMisAportes()){
            a.getColaborador();
            DataColaborador DC = new DataColaborador(a.getColaborador().getNickname(), a.getColaborador().getNombre(), a.getColaborador().getApellido(), a.getColaborador().getEmail(), a.getColaborador().getFecNac(), a.getColaborador().getImagenWeb());
            colabs.add(DC);
        }
        
       
        String nick = (String) misesion.getAttribute("nick");
        
//        if(!nick.equals(null)){
//            boolean esFavorita = ic.esFavorita(titulo, nick);
//            misesion.setAttribute("esFavorita",esFavorita);
//        }        
        
        misesion.setAttribute("colabs", colabs);
        
        misesion.setAttribute("p", DP);
        
        response.sendRedirect("infoPropuesta.jsp");
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
