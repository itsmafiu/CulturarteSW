/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;


import WebServices.DataAporte;
import WebServices.DataPropuesta;
import WebServices.EnumTarjeta;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SvPagarCola", urlPatterns = {"/SvPagarCola"})
public class SvPagarCola extends HttpServlet {

    LogicaWS_Service service;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        
        HttpSession misesion = request.getSession();
        String nick = (String) misesion.getAttribute("nick");
        DataPropuesta p = (DataPropuesta) request.getSession().getAttribute("p");
        String titulo = p.getTitulo();
        String metodoPago = request.getParameter("metodoPago");
        
       

        if ("tarjeta".equalsIgnoreCase(metodoPago)) {
            
            String tipoTarjeta = request.getParameter("tipoTarjeta");
            EnumTarjeta enumTarjeta;
            
            if("OCA".equals(tipoTarjeta)){
                enumTarjeta = EnumTarjeta.OCA;
            }else if("Visa".equals(tipoTarjeta)){
                enumTarjeta = EnumTarjeta.VISA;
            }else{
                enumTarjeta = EnumTarjeta.MASTER;
            }
            
            String numeroT = request.getParameter("numeroTarjeta");
            String fechaT =  request.getParameter("fechaExp");
            String CVC = request.getParameter("cvc");
            String titular = request.getParameter("nombreTitular");
            ic.setPagoT(titular,nick,titulo, numeroT, fechaT, CVC, enumTarjeta);
                        
        } else if ("transferencia".equalsIgnoreCase(metodoPago)) {
            String nombreB = request.getParameter("banco");
            String numeroB = request.getParameter("numeroCuenta");
            String titular = request.getParameter("nombreTitular");
            ic.setPagoB(titular, nick, titulo, nombreB, numeroB);
                        
        } else if ("paypal".equalsIgnoreCase(metodoPago)) {
            String numeroP = request.getParameter("cuentaPaypal");
            String titular = request.getParameter("nombreTitular");
            ic.setPagoP(titular, nick, titulo, numeroP);
            
        }
       
        response.sendRedirect("colaPagada.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
