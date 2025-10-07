/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.DataUsuario;
import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvConsultaUsuario", urlPatterns = {"/SvConsultaUsuario"})
public class SvConsultaUsuario extends HttpServlet {

    private IControlador ic;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ic = Fabrica.getInstancia().getIControlador();
        
        List<DataUsuario> listaUsuarios = ic.getDataUsuarios();
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("DtU", listaUsuarios);
        
        response.sendRedirect("conulstaUsuario.jsp");
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
