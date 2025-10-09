/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.EnumRetorno;
import Logica.Fabrica;
import Logica.IControlador;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 5 * 1024 * 1024,   // 5 MB
    maxRequestSize = 10 * 1024 * 1024 // 10 MB
)

/**
 *
 * @author nahud
 */
@WebServlet(name = "SvAltaProp", urlPatterns = {"/SvAltaProp"})
public class SvAltaProp extends HttpServlet {
    
    private IControlador ic;

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
        
        //FALTAN CONTROLES DE SI LAS COSAS VIENEN VACIAS!
        
        ic = Fabrica.getInstancia().getIControlador();
        HttpSession misesion = request.getSession();
        
        String proponente = (String) misesion.getAttribute("nick");
        String categoria = request.getParameter("categoria"); //de momento vacio
        
        categoria = "Musica";
        
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String lugar = request.getParameter("lugar");
        String fechaStr = request.getParameter("fecha");
        String retorno = request.getParameter("retorno");
        
        LocalDate fecha = LocalDate.parse(fechaStr);
        
        String montoEntrada = request.getParameter("montoEntrada");
                
        String montoNecesario = request.getParameter("montoNecesario");
           
        Part archivo = request.getPart("foto");
        
        String nombreArchivo;
        
        if(archivo != null && archivo.getSize() > 0){
            nombreArchivo = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();

            File base = new File(getServletContext().getRealPath(""));
        
            File ubi = base.getParentFile().getParentFile();

            String uploadPath = ubi.getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "fotos";

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            archivo.write(uploadPath + File.separator + nombreArchivo);
        }else{
            nombreArchivo = "default.jpg";
        }

        EnumRetorno posibleRetorno;
        
        String ruta = "fotos\\" + nombreArchivo;
        
        switch (retorno) {
            case "Entradas gratis":
                posibleRetorno = EnumRetorno.valueOf("ENTRADAS_GRATIS");
                break;
            case "%Ventas":
                posibleRetorno = EnumRetorno.valueOf("PORCENTAJE_VENTAS");
                break;
            default:
                posibleRetorno = EnumRetorno.valueOf("AMBOS");
                break;
        }
        
        LocalDate fechaActual = LocalDate.now();
        
        ic.altaPropuesta(proponente, categoria, titulo, descripcion, lugar, fecha, montoEntrada, montoNecesario, posibleRetorno, fechaActual, ruta);
        
        response.sendRedirect("index.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
