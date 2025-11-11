/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DataAporte;
import WebServices.DataColaborador;
import WebServices.DataPago;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SvConstancia", urlPatterns = {"/SvConstancia"})
public class SvConstancia extends HttpServlet {

    LogicaWS_Service service;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();
        HttpSession misesion = request.getSession();
        
        String nick = (String) misesion.getAttribute("nick");
        DataAporte DA = (DataAporte) misesion.getAttribute("DA");
        DataPago DPa = (DataPago) misesion.getAttribute("DPa");
        DataColaborador DC = ic.consultaDeColaborador(nick);
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=constancia.pdf");
        try {
           
                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, response.getOutputStream());
                document.open();
                
                Font tituloFont = new Font(Font.HELVETICA, 18, Font.BOLD);
                Paragraph titulo = new Paragraph("Plataforma CULTURARTE", tituloFont);
                titulo.setAlignment(Element.ALIGN_CENTER);
                titulo.setSpacingAfter(30);
                document.add(titulo);
                
                Font normalFont = new Font(Font.HELVETICA, 12);
                document.add(new Paragraph("Se deja constancia que:", normalFont));
                document.add(new Paragraph("Nombre: " + DC.getNombre(), normalFont));
                document.add(new Paragraph("Apellido: " + DC.getApellido(), normalFont));
                document.add(new Paragraph("Correo: " + DC.getEmail(), normalFont));
                document.add(new Paragraph("Nickname: " + DC.getNickname(), normalFont));
                
                document.add(new Paragraph("Ha realizado su pago satisfactoriamente a la propuesta:", normalFont));
                
                document.add(new Paragraph("Titulo: " + DA.getMiPropuesta(), normalFont));
                document.add(new Paragraph("Aporte: " + DA.getAporte().toString(), normalFont));
                document.add(new Paragraph("Tipo de Retorno: " + DA.getRetorno().toString(), normalFont));
                document.add(new Paragraph("Fecha de Aporte: " + DA.getFechaStr(), normalFont));
                //document.add(new Paragraph("Fecha de Pago: " + , normalFont));
                
                
                document.add(new Paragraph("\n\nConstancia emitida el: " + new java.util.Date(), normalFont));
                document.close();
                
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar PDF");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
