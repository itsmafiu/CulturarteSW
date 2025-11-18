/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DataAporte;
import WebServices.DataPago;
import WebServices.DataPropuesta;
import WebServices.DataUsuario;
import WebServices.EnumTarjeta;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 *
 * @author Luiano
 */
@WebServlet(name = "SvPagarCola", urlPatterns = {"/SvPagarCola"})
public class SvPagarCola extends HttpServlet {

    LogicaWS_Service service;

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

        service = new LogicaWS_Service();
        LogicaWS ic = service.getLogicaWSPort();

        HttpSession misesion = request.getSession();
        String nick = (String) misesion.getAttribute("nick");
        DataPropuesta p = (DataPropuesta) request.getSession().getAttribute("p");
        DataAporte da = (DataAporte) request.getSession().getAttribute("DA");
        DataUsuario dp = (DataUsuario) ic.consultaDeProponenteWeb(p.getNickProponenteDe());
        DataUsuario dc = (DataUsuario) ic.consultaDeColaboradorWeb(nick);

        String titulo = p.getTitulo();
        String metodoPago = request.getParameter("metodoPago");

        if ("tarjeta".equalsIgnoreCase(metodoPago)) {

            String tipoTarjeta = request.getParameter("tipoTarjeta");
            EnumTarjeta enumTarjeta;

            if ("OCA".equals(tipoTarjeta)) {
                enumTarjeta = EnumTarjeta.OCA;
            } else if ("Visa".equals(tipoTarjeta)) {
                enumTarjeta = EnumTarjeta.VISA;
            } else {
                enumTarjeta = EnumTarjeta.MASTER;
            }

            String numeroT = request.getParameter("numeroTarjeta");
            String fechaT = request.getParameter("fechaExp");
            String CVC = request.getParameter("cvc");
            String titular = request.getParameter("nombreTitular");
            ic.setPagoT(titular, nick, titulo, numeroT, fechaT, CVC, enumTarjeta);

        } else if ("transferencia".equalsIgnoreCase(metodoPago)) {
            String nombreB = request.getParameter("banco");
            String numeroB = request.getParameter("numeroCuenta");
            String titular = request.getParameter("nombreTitular");
            ic.setPagoB(titular, nick, titulo, nombreB, numeroB);

        } else if ("paypal".equalsIgnoreCase(metodoPago)) {
            String numeroP = request.getParameter("cuentaPaypal");
            String titular = request.getParameter("nombreTitular");
            ic.setPagoP(titular, nick, titulo, numeroP);

        }

        try {
            DataPago dpa = (DataPago) ic.getDataPago(nick, p.getTitulo());
            Properties props = new Properties();
            props.put("mail.smtp.host", "localhost");
            props.put("mail.smtp.port", "1025");

            Session session = Session.getInstance(props, null);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("no-reply@culturarte.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                    "usuario@ejemplo.com, admin@miapp.com"
            ));

            msg.setText("Usuario: " + nick + "\nMétodo: " + metodoPago + "\n¡Gracias por tu pago!");

            Message propMsg = new MimeMessage(session);
            propMsg.setFrom(new InternetAddress("no-reply@culturarte.com"));
            propMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dp.getEmail()));
            propMsg.setSubject("[Culturarte] " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " Pago de colaboracion registrado");
            propMsg.setText("Estimado/a " + dp.getNombre() + " " + dp.getApellido()
                    + ", El pago correspondiente a la colaboracion de la propuesta"
                    + p.getTitulo() + "realizada por " + dc.getNickname() + " ha sido registrado en forma exitosa.\n\n"
                    + "--Detalles de la Colaboracion\n"
                    + "-Propuesta:\n"
                    + "  -" + p.getTitulo() + "\n"
                    + "-Proponente:\n"
                    + "  -" + dp.getNickname() + "\n"
                    + "-Colaborador:\n"
                    + "  -" + dc.getNickname() + "\n"
                    + "-Monto:\n"
                    + "  -" + da.getAporte().toString() + "\n"
                    + "- Fecha de pago:\n"
                    + "  -" + dpa.getFechaPagoStr() + "\n"
                    + "Gracias por preferirnos,\n"
                    + "Saludos,\n"
                    + "Tus amigos de Culturarte."
            );
            Transport.send(propMsg);

            Message colabMsg = new MimeMessage(session);
            colabMsg.setFrom(new InternetAddress("no-reply@culturarte.com"));
            colabMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dc.getEmail()));
            colabMsg.setSubject("[Culturarte] " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " Pago de colaboracion registrado");
            colabMsg.setText("Estimado/a " + dc.getNombre() + " " + dc.getApellido()
                    + ", El pago correspondiente a la colaboracion de la propuesta"
                    + p.getTitulo() + "realizada por " + dc.getNickname() + " ha sido registrado en forma exitosa.\n\n"
                    + "--Detalles de la Colaboracion\n"
                    + "-Propuesta:\n"
                    + "  -" + p.getTitulo() + "\n"
                    + "-Proponente:\n"
                    + "  -" + dp.getNickname() + "\n"
                    + "-Colaborador:\n"
                    + "  -" + dc.getNickname() + "\n"
                    + "-Monto:\n"
                    + "  -" + da.getAporte().toString() + "\n"
                    + "- Fecha de pago:\n"
                    + "  -" + dpa.getFechaPagoStr() + "\n"
                    + "http://localhost:8080/CulturarteOnline"
                    + "\n\nGracias por preferirnos,\n"
                    + "Saludos,\n"
                    + "Tus amigos de Culturarte."
            );
            Transport.send(colabMsg);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        response.sendRedirect("colaPagada.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
