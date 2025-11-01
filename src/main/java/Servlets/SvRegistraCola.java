/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.EnumRetorno;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "SvRegistraCola", urlPatterns = {"/SvRegistraCola"})
public class SvRegistraCola extends HttpServlet {

//    protected final IControlador ic = Fabrica.getInstancia().getIControlador();
    LogicaWS_Service service;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        response.sendRedirect("registraCola.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        HttpSession misesion = request.getSession();

        String montoStr = request.getParameter("monto");
        String tipo = request.getParameter("tipoRetorno");

        try {
            double monto = Double.parseDouble(montoStr);

            String nick = (String) misesion.getAttribute("nick");
            String tituloProp = (String) misesion.getAttribute("titulo");
            
            EnumRetorno retorno;
            if(tipo.equals("ENTRADAS")){
                retorno = EnumRetorno.ENTRADAS_GRATIS;
            }else{
                retorno = EnumRetorno.PORCENTAJE_VENTAS;
            }         
            
            int resultado = ic.altaAporteF(nick, tituloProp , monto, 0, retorno, "fechaActual"); //CAMBIAR DATE A STRING
            
            switch(resultado){
                case 0:
                    response.sendRedirect("colaRegistrada.jsp");
                    break;
                case -3:
                    request.setAttribute("error", "Usted ya colabora con esta propuesta.");
                    request.getRequestDispatcher("registraCola.jsp").forward(request, response);
                case -4:
                    request.setAttribute("error", "Tipo de Retorno no válido.");
                    request.getRequestDispatcher("registraCola.jsp").forward(request, response);
            }
                       

        } catch (NumberFormatException e) {
            request.setAttribute("error", "El monto debe ser un número válido.");
            request.getRequestDispatcher("registraCola.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("registraCola.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
