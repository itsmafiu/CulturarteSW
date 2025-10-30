/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DataPropuesta;
import WebServices.EnumEstado;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nahud
 */
@WebServlet(name = "SvConsultaPropuesta", urlPatterns = {"/SvConsultaPropuesta"})
public class SvConsultaPropuesta extends HttpServlet {

//    private IControlador ic;
    LogicaWS_Service service;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        ic = Fabrica.getInstancia().getIControlador();
        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        
        ic.comprobarPropuestas();
        
        List<String> listaPropStr = new ArrayList<>();
        
        listaPropStr = ic.getPropuestas();
        DataPropuesta p;
        List<DataPropuesta> DP = new ArrayList<>();
        
        for (String titulo : listaPropStr) {
            p = ic.consultaDePropuesta(titulo);
            if (!(p.getEstadoActual().getEstado().equals(EnumEstado.INGRESADA))) {
                DP.add(ic.consultaDePropuesta(titulo));
            }
        }
        
        //Agregado para filtrar por Categoria
        List<String> categorias = ic.getCategorias();
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("DP", DP);
        misesion.setAttribute("categorias", categorias); 
        
        response.sendRedirect("mostrarPropuestas.jsp");
        
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
