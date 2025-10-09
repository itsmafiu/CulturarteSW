/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.DataPropuesta;
import Logica.EnumEstado;
import Logica.Fabrica;
import Logica.IControlador;
import Logica.Propuesta;
import java.io.IOException;
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
@WebServlet(name = "SvConsultaPropuesta", urlPatterns = {"/SvConsultaPropuesta"})
public class SvConsultaPropuesta extends HttpServlet {

    private IControlador ic;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ic = Fabrica.getInstancia().getIControlador();
        
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
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("DP", DP);
        
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
