/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import uy.culturarte.wsclient.DataAporte;
import uy.culturarte.wsclient.DataProponente;
import uy.culturarte.wsclient.DataPropuesta;
import uy.culturarte.wsclient.DataUsuario;
import uy.culturarte.wsclient.LogicaWS;
import uy.culturarte.wsclient.LogicaWS_Service;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import utilidades.WSConfig;

/**
 *
 * @author mafiu
 */
@WebServlet(name = "SvTrelloPropuesta", urlPatterns = {"/SvTrelloPropuesta"})
public class SvTrelloPropuesta extends HttpServlet {
    
    LogicaWS_Service service;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        aqui debe fijarse si la propuesta ya existe para ocultar boton de crear tablero (o en lugar de crear que cambie apariencia y solo redireccione a trello)
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        service = new LogicaWS_Service(new URL (WSConfig.getWsdlUrl()));
        LogicaWS ic = service.getLogicaWSPort();
        HttpSession misesion = request.getSession();
        
        String nickUsu = ((DataUsuario) misesion.getAttribute("ConsultaPerfil")).getNickname();
        DataProponente p = ic.consultaDeProponente(nickUsu);
        String tableroID = TrelloAPI.getTableroID("Propuestas " + nickUsu);
        if(tableroID != null){
            TrelloAPI.eliminarTablero(tableroID);
        }
        
        //crea el tablero
        JSONObject JSONTablero = TrelloAPI.crearTablero("Propuestas " + nickUsu);
        tableroID = TrelloAPI.getTableroID("Propuestas " + nickUsu);
        
        //crea una lista por cada propuesta que tenga el proponente
        for(DataPropuesta dp : p.getMisPropuestas()){
            JSONObject JSONLista = TrelloAPI.crearLista(tableroID, dp.getTitulo()); //crea lista
            JSONObject tarjetaConImagen = TrelloAPI.crearTarjeta(JSONLista.getString("id"), "Imagen Propuesta", ""); //le agrega tarjeta de imagen (sin nada)
            
            String rutaRelativa = dp.getImagen();
            if(rutaRelativa == null || rutaRelativa.isBlank()){
                rutaRelativa = "fotos" + File.separator + "default.jpg";
            }
            String rutaAbsoluta = getServletContext().getRealPath(rutaRelativa);
            File imagen = new File(rutaAbsoluta);
            TrelloAPI.subirImagenATarjeta(tarjetaConImagen.getString("id"), imagen); //le agrega imagen a la tarjeta anterior
            
            DataPropuesta prop = ic.consultaDePropuesta(dp.getTitulo());
            for(DataAporte da : prop.getMisAportes()){
                TrelloAPI.crearTarjeta(JSONLista.getString("id"), da.getMiColaborador(), "Aporte: " + da.getAporte().toString());
            }
//            TrelloAPI.crearTarjeta(JSONLista.getString("id"), "Descripción", dp.getDesc()); //le agrega tarjeta con descripcion
            //datos adicionales
        }
        
        
        
//        JSONObject obj = new JSONObject(JSONTablero);
//        String urlTablero = obj.getString("url");
//        String link = "https://trello.com/b/" + tableroID;
//        
//        response.sendRedirect(link);

        response.getWriter().write("exito");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
