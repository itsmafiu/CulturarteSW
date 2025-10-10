/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.net.URLEncoder;
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
@WebServlet(name = "SvFavorita", urlPatterns = {"/SvFavorita"})
public class SvFavorita extends HttpServlet {

    private final IControlador ic = Fabrica.getInstancia().getIControlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String titulo = request.getParameter("titulo");
        HttpSession session = request.getSession();
        String nick = (String) session.getAttribute("nick");


        boolean esFavorita = ic.esFavorita(titulo,nick);
        
        request.setAttribute("esFavorita", esFavorita);

        request.getRequestDispatcher("infoPropuesta.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titulo = request.getParameter("titulo");
        HttpSession session = request.getSession();
        String nickUsuario = (String) session.getAttribute("nick");

        response.setContentType("application/json;charset=UTF-8");

        // Validaciones
        if (nickUsuario == null || titulo == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Usuario no autenticado\"}");
            return;
        }

        // Alternar favorito
        ic.cambiarFavorita(titulo, nickUsuario);
        boolean esFavorita = ic.esFavorita(titulo, nickUsuario);

        // Devolver estado actualizado en formato JSON
        response.getWriter().write("{\"esFavorita\": " + esFavorita + "}");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
