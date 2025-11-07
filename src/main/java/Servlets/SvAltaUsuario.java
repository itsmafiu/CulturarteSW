/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DataUsuario;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import WebServices.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author mafiu
 */
@WebServlet(name = "altaUsuario", urlPatterns = {"/altaUsuario"})
@MultipartConfig
public class SvAltaUsuario extends HttpServlet {
    
    LogicaWS_Service service;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        
        String tipo = request.getParameter("tipoVerificarUsuario");
        if(tipo == null){
            response.sendRedirect("altaUsuario.jsp");
            return;
        }
        switch (tipo) {
            case "nick":
                {
                    String nick = request.getParameter("verificarNick");
                    response.setContentType("text/plain");
                    if (nick == null || nick.trim().isEmpty()) {
                        response.getWriter().write("noexiste");
                        return;
                    }       
                    boolean existe = ic.existeNick(nick);
                    if (existe) {
                        response.getWriter().write("existe");
                    } else {
                        response.getWriter().write("noexiste");
                    }       break;
                }
            case "correo":
                {
                    String correo = request.getParameter("verificarCorreo");
                    response.setContentType("text/plain");
                    if (correo == null || correo.trim().isEmpty()) {
                        response.getWriter().write("noexiste");
                        return;
                    }       
                    boolean existe = ic.existeCorreo(correo);
                    if (existe) {
                        response.getWriter().write("existe");
                    } else {
                        response.getWriter().write("noexiste");
                    }       break;
                }
            default:
                response.sendRedirect("altaUsuario.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        
        List <String> nicksProhibidos = new ArrayList<>(List.of("--Seleccionar--", "---"));
        
        request.setCharacterEncoding("UTF-8");
        
        String nick = request.getParameter("nick").trim();
        String nombre = request.getParameter("nombre").trim();
        String apellido = request.getParameter("apellido").trim();
        String correo = request.getParameter("correo").trim();
        String fecNac = request.getParameter("fecNac").trim();
        String tipoUsuario = request.getParameter("tipoUsuario");
        String pass = request.getParameter("password");
        //String confirmacion = request.getParameter("confirmacion");
        
        Part imagen = request.getPart("imagen"); //agarra la imagen subida
        String rutaImagen;
        String rutaWeb;
        
        File base = new File(getServletContext().getRealPath("")); //path al servlet (test)
        base = base.getParentFile().getParentFile();
        String rutaBase = base.getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "fotos"; 
        
        if(imagen == null || imagen.getSize() == 0){
            rutaImagen = rutaBase + File.separator + "default.jpg";
            rutaWeb = "fotos" + File.separator + "default.jpg";
        }else{
            String nombreOriginal = Paths.get(imagen.getSubmittedFileName()).getFileName().toString(); //nombre real de la imagen.extension
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf(".")); //solo extension (.jpg, .png, etc)
            String nombreUnico = nombreOriginal.subSequence(0, nombreOriginal.lastIndexOf(".")).toString() + ((int)(Math.random()*1001)) + extension; //nombre img + id unico para que no se repita + extension

            Files.createDirectories(Paths.get(rutaBase));
            rutaImagen = rutaBase + File.separator + nombreUnico; //ruta para el servidor
            rutaWeb = "fotos" + File.separator + nombreUnico; //ruta para la web
        }
        
        
        if(nicksProhibidos.contains(nick) || nick.contains(" ")){
            request.setAttribute("error", "El nick ingresado no está permitido.");
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
            return;
        }
        if(!correo.contains("@") ||  !correo.contains(".") || correo.contains(" ")){
            request.setAttribute("error", "El correo ingresado no es válido.");
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
            return;
        }
//        if(LocalDate.parse(fecNac).isAfter(LocalDate.now())){
//            request.setAttribute("error", "La fecha ingresada no es válida.");
//            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
//            return;
//        }
        if(!nombre.matches("[\\p{L} ]+")){
            request.setAttribute("error", "El nombre ingresado contiene caracteres inválidos.");
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
            return;
        }
        if(!apellido.matches("[\\p{L} ]+")){
            request.setAttribute("error", "El apellido ingresado contiene caracteres inválidos.");
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
            return;
        }
//        if(!pass.equals(confirmacion)){
//            request.setAttribute("error", "Confirme su contraseña.");
//            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
//            return;
//        }
        
        String passHash = BCrypt.hashpw(pass, BCrypt.gensalt());
        
        int aux;
        Usuario usu;
        boolean tipoUsu;
        if(tipoUsuario.equals("Proponente")){
            String direccion = request.getParameter("direccion");
            String bio = request.getParameter("bio");
            String sitioWeb = request.getParameter("sitioWeb");
            
//            if(direccion.isBlank()){
//                request.setAttribute("error", "Debe ingresar una dirección.");
//                request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
//                return;
//            }
            if(!sitioWeb.isBlank() && !sitioWeb.contains(".")){
                request.setAttribute("error", "El sitio web ingresado no es válido.");
                request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
                return;
            }
            
            aux = ic.añadirUsuarioP(nick, nombre, apellido, correo, fecNac, rutaImagen, passHash, direccion, bio, sitioWeb, rutaWeb); //CAMBIAR DATE A STRING 
            //usu = new Proponente(direccion, bio, sitioWeb, nick, correo, nombre, apellido, LocalDate.parse(fecNac), rutaImagen, passHash, rutaWeb);
            if(aux == 1){
                usu = ic.getUsuario(nick);
            }else{
                usu = null;
            }
            tipoUsu = true;
        }else{ //tipoUsuario.equals("Colaborador")
            aux = ic.añadirUsuarioC(nick, nombre, apellido, correo, fecNac, rutaImagen, passHash, rutaWeb); //CAMBIAR DATE A STRING
            //usu = new Colaborador(nick, correo, nombre, apellido, LocalDate.parse(fecNac), rutaImagen, passHash, rutaWeb);
            if(aux == 1){
                usu = ic.getUsuario(nick);
            }else{
                usu = null;
            }
            tipoUsu = false;
        }
        
        switch(aux){
            case 0:
                request.setAttribute("error", "El nick ingresado no está disponible.");
                request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
                return;
            case 2:
                request.setAttribute("error", "El correo ingresado no está disponible.");
                request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
                return;
            case 1:
                if(!(imagen == null || imagen.getSize() == 0)){ //si no se insertó imagen no copia nada
                    try(InputStream contenido = imagen.getInputStream()){
                        Files.copy(contenido, Paths.get(rutaImagen));
                    }
                }
                HttpSession misesion = request.getSession();
                misesion.setAttribute("datosUsuario", usu);
                if(tipoUsu){ //proponente
                    misesion.setAttribute("tipoUsuario", "prop");
                }else{ //colaborador
                    misesion.setAttribute("tipoUsuario", "cola");
                }
                misesion.setAttribute("DTUsuario", ic.getDataUsuarioWeb(nick));
                misesion.setAttribute("nick", nick);
                response.sendRedirect("index.jsp");
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
