/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.DataPropuesta;
import Logica.EnumRetorno;
import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvRegistraCola", urlPatterns = {"/SvRegistraCola"})
public class SvRegistraCola extends HttpServlet {

    protected final IControlador ic = Fabrica.getInstancia().getIControlador();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession misesion = request.getSession();
        DataPropuesta DP = ic.getDataPropuesta((String) misesion.getAttribute("titulo"));
        misesion.setAttribute("p", DP);
        response.sendRedirect("registraCola.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            
            ic.altaAporte(nick, tituloProp, monto, 0, retorno);
            
            //response.sendRedirect("exitoColaboracion.jsp");

        } catch (NumberFormatException e) {
            request.setAttribute("error", "El monto debe ser un número válido.");
            request.getRequestDispatcher("registrarCola.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("registrarCola.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
