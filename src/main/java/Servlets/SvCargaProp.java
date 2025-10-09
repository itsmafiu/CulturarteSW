/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.DataPropuesta;
import Logica.EnumEstado;
import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "SvCargaProp", urlPatterns = {"/SvCargaProp"})
public class SvCargaProp extends HttpServlet {

    private final IControlador ic = Fabrica.getInstancia().getIControlador();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession misesion = request.getSession();
        
        List<String> listaPropStr = new ArrayList<>();
        
        listaPropStr = ic.getPropuestas();
        DataPropuesta p;
        List<DataPropuesta> DP = new ArrayList<>();
        List<DataPropuesta> DPcr = new ArrayList<>();
        List<DataPropuesta> DPef = new ArrayList<>();
        List<DataPropuesta> DPf = new ArrayList<>();
        List<DataPropuesta> DPnf = new ArrayList<>();
        List<DataPropuesta> DPca = new ArrayList<>();
        
        for (String titulo : listaPropStr) {
            p = ic.consultaDePropuesta(titulo);
            if (!(p.getEstadoActual().getEstado().toString().equals("INGRESADA"))) {
                DP.add(ic.consultaDePropuesta(titulo));
            }
        }
        
        for(DataPropuesta pr : DP){
            if (pr.getEstadoActual().getEstado().equals(EnumEstado.PUBLICADA)) {
                DPcr.add(pr);
            }
        }
        
        for(DataPropuesta pr : DP){
            if (pr.getEstadoActual().getEstado().equals(EnumEstado.EN_FINANCIACION)) {
                DPef.add(pr);
            }
        }
        
        for(DataPropuesta pr : DP){
            if (pr.getEstadoActual().getEstado().equals(EnumEstado.FINANCIADA)) {
                DPf.add(pr);
            }
        }
        
        for(DataPropuesta pr : DP){
            if (pr.getEstadoActual().getEstado().equals(EnumEstado.NO_FINANCIADA)) {
                DPnf.add(pr);
            }
        }
        
        for(DataPropuesta pr : DP){
            if (pr.getEstadoActual().getEstado().equals(EnumEstado.CANCELADA)) {
                DPca.add(pr);
            }
        }
        
        misesion.setAttribute("propuestasCreadas", DPcr);
        misesion.setAttribute("propuestasEnFinanciacion", DPef);
        misesion.setAttribute("propuestasFinanciadas", DPf);
        misesion.setAttribute("propuestasNoFinanciadas", DPnf);
        misesion.setAttribute("propuestasCanceladas", DPca);
        
//        misesion.setAttribute("propuestasCreadas", ic.getPropXEstado("PUBLICADA"));
//        misesion.setAttribute("propuestasEnFinanciacion", ic.getPropXEstado("EN_FINANCIACION"));
//        misesion.setAttribute("propuestasFinanciadas", ic.getPropXEstado("FINANCIADA"));
//        misesion.setAttribute("propuestasNoFinanciadas", ic.getPropXEstado("NO_FINANCIADA"));
//        misesion.setAttribute("propuestasCanceladas", ic.getPropXEstado("CANCELADA"));

        response.sendRedirect("index.jsp");
    }
}

