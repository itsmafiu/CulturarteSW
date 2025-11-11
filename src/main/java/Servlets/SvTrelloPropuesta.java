/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DataProponente;
import WebServices.DataPropuesta;
import WebServices.DataUsuario;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import org.json.JSONObject;

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
        
//        String nickUsu = ((DataPropuesta) request.getAttribute("p")).getP().getNickname();
//        String tableroID = TrelloAPI.getTableroID("Propuestas " + nickUsu);
//        String accion;
//        if(tableroID != null){
//            accion = "crearTablero";
//        }else{
//            accion = "crearLista";
//        }
//        
//        switch(accion){ //capas hacer con ajax?? 
//            //(posible solucion: clickea crear tablero en web --> ajax hace crear llamando con post aqui --> si da bien mandamos mensaje feliz (o redireccionar a trello?), sino mensaje error)
//            //todo depende de como hay que hacerlo
//            case "crearTablero":
//                String respuestaTablero = TrelloAPI.crearTablero("Propuestas " + nickUsu);
//                //no se que deberia hacer despues (capas redireccionar a trello?)
//                break;
//            case "crearLista":
//                String nombreLista = ((DataPropuesta) request.getAttribute("p")).getTitulo();
//                if(TrelloAPI.buscarListaPorIDTablero(tableroID, nombreLista) == null){ //si busca la lista y no existe
//                    String respuestaLista = TrelloAPI.crearLista(tableroID, nombreLista);
//                }else{ //buscó y si existe
//                    //no se que deberia hacer despues
//                }
//                break;
//        }

        service = new LogicaWS_Service();
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
            
            TrelloAPI.crearTarjeta(JSONLista.getString("id"), "Descripción", dp.getDesc()); //le agrega tarjeta con descripcion
            //datos adicionales
        }
        
        
        
//        JSONObject obj = new JSONObject(JSONTablero);
//        String urlTablero = obj.getString("url");
        String link = "https://trello.com/b/" + tableroID;
        
        response.sendRedirect(link);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
