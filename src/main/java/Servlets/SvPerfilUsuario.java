package Servlets;

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

@WebServlet(name = "SvPerfilUsuario", urlPatterns = {"/SvPerfilUsuario"})
public class SvPerfilUsuario extends HttpServlet {

   private IControlador ic;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nick = request.getParameter("nick");
        String tipo = request.getParameter("tipo");
        
        if(nick==null || nick.isEmpty() || tipo==null || tipo.isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parametros");
            return;
        }
        
        
        ic = Fabrica.getInstancia().getIControlador();
        
        HttpSession misesion = request.getSession();
        
        switch(tipo){
            case "Proponente":
                misesion.setAttribute("ConsultaPerfil", ic.consultaDeProponente(nick));
                break;
            case "Colaborador":
                misesion.setAttribute("ConsultaPerfil", ic.consultaDeColaborador(nick));
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no es Colaborador ni Proponente");
                return;
        }
     
        
        response.sendRedirect("perfilUsuario.jsp");
        
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
