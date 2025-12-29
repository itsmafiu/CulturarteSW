/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import uy.culturarte.wsclient.DataUsuario;
import uy.culturarte.wsclient.LogicaWS;
import uy.culturarte.wsclient.LogicaWS_Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import utilidades.WSConfig;


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
        service = new LogicaWS_Service(new URL (WSConfig.getWsdlUrl()));
        LogicaWS ic = service.getLogicaWSPort();
        
        List<DataUsuario> listaUsuarios = ic.getDataUsuarios();
        List<DataUsuario> listaUsuariosRanking = ic.ordenarDTUporSeguidores(listaUsuarios);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("DtU", listaUsuarios);
        misesion.setAttribute("DtURanking", listaUsuariosRanking);
        
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
