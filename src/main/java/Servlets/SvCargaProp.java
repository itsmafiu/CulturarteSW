/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DataPropuesta;
import WebServices.EnumEstado;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;

@WebServlet(name = "SvCargaProp", urlPatterns = {"/SvCargaProp"})
public class SvCargaProp extends HttpServlet {

//    private final IControlador ic = Fabrica.getInstancia().getIControlador();

    LogicaWS_Service service;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        URL wsdlURL = new URL("http://localhost:9128/logicaWS?wsdl");
        QName qname = new QName("http://WebServices/", "LogicaWS");
        LogicaWS_Service service = new LogicaWS_Service(wsdlURL, qname);
        LogicaWS ic = service.getLogicaWSPort();
        
        ic.comprobarPropuestas();
        
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

        response.sendRedirect("index.jsp");
    }
}

