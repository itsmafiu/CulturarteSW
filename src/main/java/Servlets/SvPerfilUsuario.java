package Servlets;

import Logica.DataUsuario;
import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
        
        //ajax: seguir/dejar de seguir usuarios//(si no vino desde perfilUsuario.jsp lo ignora)
        String tipoSeguir = request.getParameter("tipoInputSeguirUsuario");
        if(tipoSeguir != null){
            String nick1 = request.getParameter("usuarioSeguidor1");
            String nick2 = request.getParameter("usuarioSeguido2");
            switch (tipoSeguir){
                case "seguir":
                    int aux = ic.seguirUsuario(nick1, nick2);
                    if(aux == 1){
                        response.getWriter().write("exito");
                    }else{
                        response.getWriter().write("error");
                    }
                    return;
                case "dejarSeguir":
                    int aux2 = ic.dejarSeguirUsuario(nick1, nick2);
                    if(aux2 == 1){
                        response.getWriter().write("exito");
                    }else{
                        response.getWriter().write("error");
                    }
                    return;
        }
        }
        
        String nick = request.getParameter("nickTarjeta");
        String tipo = request.getParameter("tipoTarjeta");
        
        if(nick==null || nick.isEmpty() || tipo==null || tipo.isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parametros");
            return;
        }
        
        
        ic = Fabrica.getInstancia().getIControlador();
        
        HttpSession misesion = request.getSession();
        
        switch(tipo){
            case "Proponente":
                misesion.setAttribute("ConsultaPerfil", ic.consultaDeProponenteWeb(nick));
                break;
            case "prop":
                misesion.setAttribute("ConsultaPerfil", ic.consultaDeProponenteWeb(nick));
                break;
            case "Colaborador":
                misesion.setAttribute("ConsultaPerfil", ic.consultaDeColaboradorWeb(nick));
                break;
            case "cola":
                misesion.setAttribute("ConsultaPerfil", ic.consultaDeColaboradorWeb(nick));
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
