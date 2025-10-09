package Servlets;

import Logica.Fabrica;
import Logica.IControlador;
import Logica.Propuesta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvListarPropuestasNick", urlPatterns = {"/SvListarPropuestasNick"})
public class SvListarPropuestasNick extends HttpServlet {

    private IControlador ic;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ic = Fabrica.getInstancia().getIControlador();                
        HttpSession misesion = request.getSession();
        
        misesion.setAttribute("titulo_nick", ic.getPropuestas_Proponentes());
        RequestDispatcher dispatcher = request.getRequestDispatcher("registrarCola.jsp");
        dispatcher.forward(request, response);
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
