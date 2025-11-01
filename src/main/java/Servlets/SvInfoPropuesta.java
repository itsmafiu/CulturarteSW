/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.Aporte;
import WebServices.DataColaborador;
import WebServices.DataComentario;
import WebServices.DataPropuesta;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nahud
 */
@WebServlet(name = "SvInfoPropuesta", urlPatterns = {"/SvInfoPropuesta"})
public class SvInfoPropuesta extends HttpServlet {

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
        
        ic.comprobarPropuestas();
        
        HttpSession misesion = request.getSession();
        
        String titulo = request.getParameter("titulo");
        
        misesion.setAttribute("titulo", titulo);
        
        DataPropuesta DP = ic.consultaDePropuesta(titulo);
        
        List<DataColaborador> colabs = new ArrayList<>();
//            
//        for(Aporte a : DP.getMisAportes()){
//            a.getMiColaborador();
//            DataColaborador DC = ic.getDataColaborador(a.getMiColaborador().getNickname(), a.getMiColaborador().getNombre(), a.getMiColaborador().getApellido(), a.getMiColaborador().getEmail(), a.getMiColaborador().getFecNac().toString(), a.getMiColaborador().getImagenWeb());
//            //DataColaborador DC = new DataColaborador(a.getMiColaborador().getNickname(), a.getMiColaborador().getNombre(), a.getMiColaborador().getApellido(), a.getMiColaborador().getEmail(), a.getMiColaborador().getFecNac(), a.getMiColaborador().getImagenWeb());
//            colabs.add(DC);
//        } 
        
        List<DataComentario> DCs = ic.getDataComentarios(titulo);
        
        misesion.setAttribute("DCs", DCs);
        
        misesion.setAttribute("colabs", colabs);
        
        misesion.setAttribute("p", DP);
        
        String nick = (String) misesion.getAttribute("nick");
        if(nick!=null){
            boolean esFavorita = ic.esFavorita(titulo, nick);
            misesion.setAttribute("esFavorita", esFavorita);
            System.out.println("SVINFOPROP: "+esFavorita);
        }
        
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
