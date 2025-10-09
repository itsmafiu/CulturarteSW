/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Colaborador;
import Logica.Fabrica;
import Logica.IControlador;
import Logica.Proponente;
import Logica.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author mafiu
 */
@WebServlet(name = "altaUsuario", urlPatterns = {"/altaUsuario"})
@MultipartConfig
public class SvAltaUsuario extends HttpServlet {

    protected final IControlador ic = Fabrica.getInstancia().getIControlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("altaUsuario.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List <String> nicksProhibidos = new ArrayList<>(List.of("--Seleccionar--", "---"));
        
        String nick = request.getParameter("nick").trim();
        String nombre = request.getParameter("nombre").trim();
        String apellido = request.getParameter("apellido").trim();
        String correo = request.getParameter("correo").trim();
        String fecNac = request.getParameter("fecNac").trim();
        String tipoUsuario = request.getParameter("tipoUsuario");
        String pass = request.getParameter("password");
        String confirmacion = request.getParameter("confirmacion");
        
        Part imagen = request.getPart("imagen"); //agarra la imagen subida
        String rutaImagen;
        String rutaWeb;
        if(imagen == null || imagen.getSize() == 0){
            rutaImagen = "";
            rutaWeb = "";
        }else{
            String nombreOriginal = Paths.get(imagen.getSubmittedFileName()).getFileName().toString(); //nombre real de la imagen.extension
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf(".")); //solo extension (.jpg, .png, etc)
            String nombreUnico = nombreOriginal.subSequence(0, nombreOriginal.lastIndexOf(".")).toString() + ((int)(Math.random()*1001)) + extension; //nombre img + id unico para que no se repita + extension

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            String rutaBase = "C:\\Users\\nahud\\Documents\\NetBeansProjects\\CulturarteOnline\\CulturarteWeb\\src\\main\\webapp\\fotos\\"; //CAMBIAR RUTA SI SE QUIERE USAR IMAGENES
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //ruta mafiu: "C:\\Users\\mafiu\\Documents\\NetBeansProjects\\CulturarteOnline\\CulturarteWeb\\src\\main\\webapp\\fotos\\"
            //ruta nahue: "C:\\Users\\nahud\\Documents\\NetBeansProjects\\CulturarteOnline\\CulturarteWeb\\src\\main\\webapp\\fotos\\"

            Files.createDirectories(Paths.get(rutaBase));
            rutaImagen = rutaBase + nombreUnico; //ruta para el servidor
            rutaWeb = "fotos/" + nombreUnico; //ruta para la web
        }
        
        
        if(nicksProhibidos.contains(nick)){
            request.setAttribute("error", "El nick ingresado no está permitido.");
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
            return;
        }
        if(!correo.contains("@") || correo.contains(" ") || !correo.contains(".")){
            request.setAttribute("error", "El correo ingresado no es válido.");
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
            return;
        }
        if(LocalDate.parse(fecNac).isAfter(LocalDate.now())){
            request.setAttribute("error", "La fecha ingresada no es válida.");
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
            return;
        }
        if(!nombre.matches("[a-zA-Z ]+") || !apellido.matches("[a-zA-Z ]+")){
            request.setAttribute("error", "El nombre/apellido ingresado contiene caracteres inválidos.");
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
            return;
        }
        if(!pass.equals(confirmacion)){
            request.setAttribute("error", "Confirme su contraseña.");
            request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
            return;
        }
        
        String passHash = BCrypt.hashpw(pass, BCrypt.gensalt());
        
        int aux;
        Usuario usu;
        boolean tipoUsu;
        if(tipoUsuario.equals("Proponente")){
            String direccion = request.getParameter("direccion");
            String bio = request.getParameter("bio");
            String sitioWeb = request.getParameter("sitioWeb");
            
            if(direccion.isBlank()){
                request.setAttribute("error", "Debe ingresar una dirección.");
                request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
                return;
            }
            if(!sitioWeb.isBlank() && !sitioWeb.contains(".")){
                request.setAttribute("error", "El sitio web ingresado no es válido.");
                request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
                return;
            }
            
            aux = ic.añadirUsuario(nick, nombre, apellido, correo, LocalDate.parse(fecNac), rutaImagen, passHash, direccion, bio, sitioWeb, rutaWeb);
            usu = new Proponente(direccion, bio, sitioWeb, nick, correo, nombre, apellido, LocalDate.parse(fecNac), rutaImagen, passHash, rutaWeb);
            tipoUsu = true;
        }else{ //tipoUsuario.equals("Colaborador")
            aux = ic.añadirUsuario(nick, nombre, apellido, correo, LocalDate.parse(fecNac), rutaImagen, passHash, rutaWeb);
            usu = new Colaborador(nick, correo, nombre, apellido, LocalDate.parse(fecNac), rutaImagen, passHash, rutaWeb);
            tipoUsu = false;
        }
        //usu.setImagen(rutaWeb);
        
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
