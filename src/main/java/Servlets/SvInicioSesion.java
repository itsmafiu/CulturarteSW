/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Fabrica;
import Logica.IControlador;
import Logica.Proponente;
import Logica.Usuario;
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

/**
 *
 * @author mafiu
 */
@WebServlet(name = "inicioSesion", urlPatterns = {"/inicioSesion"})
public class SvInicioSesion extends HttpServlet {

    protected final IControlador ic = Fabrica.getInstancia().getIControlador();
    
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
        
        List <String> nicksProhibidos = new ArrayList<>(List.of("--Seleccionar--", "---"));
        
        String usuario = request.getParameter("usuario").trim();
        String pass = request.getParameter("password").trim();
        
        if(nicksProhibidos.contains(usuario)){
            request.setAttribute("error", "El nick ingresado no está permitido.");
            request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
            return;
        }

        int existe = ic.verificarUsuario(usuario, pass);
        HttpSession misesion = request.getSession();
        Usuario usu;
        switch(existe){
            case 0: //no existe
                request.setAttribute("error", "Usuario o contraseña incorrecta.");
                request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
                return;
            case 1: //existe, por nick
                usu = ic.getUsuario(usuario);
                if(usu instanceof Proponente){ //proponente
                    misesion.setAttribute("tipoUsuario", "prop");
                }else{ //colaborador
                    misesion.setAttribute("tipoUsuario", "cola");
                }
                misesion.setAttribute("datosUsuario", usu);
                misesion.setAttribute("nick", usuario);
                response.sendRedirect("index.jsp");
                return;
            case 2: // existe, por mail
                String nick = ic.getUsuarioPorMail(usuario);
                usu = ic.getUsuario(nick);
                if(usu instanceof Proponente){ //proponente
                    misesion.setAttribute("tipoUsuario", "prop");
                }else{ //colaborador
                    misesion.setAttribute("tipoUsuario", "cola");
                }
                misesion.setAttribute("datosUsuario", usu);
                misesion.setAttribute("nick", nick);
                response.sendRedirect("index.jsp");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
