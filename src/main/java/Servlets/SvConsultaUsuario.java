/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DataUsuario;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "SvConsultaUsuario", urlPatterns = {"/SvConsultaUsuario"})
public class SvConsultaUsuario extends HttpServlet {

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
        
        List<DataUsuario> listaUsuarios = ic.getDataUsuarios();
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("DtU", listaUsuarios);
        
        response.sendRedirect("consultaUsuario.jsp");
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
