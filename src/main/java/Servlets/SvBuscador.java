/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.DataPropuesta;
import Logica.DataPropuestaSimple;
import Logica.EnumEstado;
import Logica.Fabrica;
import Logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Comparator;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luiano
 */
@WebServlet(name = "SvBuscador", urlPatterns = {"/SvBuscador"})
public class SvBuscador extends HttpServlet {

    private IControlador ic = Fabrica.getInstancia().getIControlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String query = request.getParameter("query");
        List<String> titulos = ic.getPropuestas();
        List<DataPropuestaSimple> resultados = new ArrayList<>();
        
        for (String t : titulos) {
            DataPropuestaSimple dp = ic.getDataPropuestaSimple(t);
            if (query == null || query.trim().isEmpty() || dp.getTitulo().toLowerCase().contains(query.toLowerCase())) {
                resultados.add(dp);
            } else if (query == null || query.trim().isEmpty() || dp.getDescripcion().toLowerCase().contains(query.toLowerCase())) {
                resultados.add(dp);
            } else if (query == null || query.trim().isEmpty() || dp.getLugar().toLowerCase().contains(query.toLowerCase())) {
                resultados.add(dp);
            }
        }

        try (PrintWriter out = response.getWriter()) {
            String json = new Gson().toJson(resultados);
            out.print(json);
            out.flush();
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String query = request.getParameter("query");
        List<String> titulos = ic.getPropuestas();
        List<DataPropuesta> resultados = new ArrayList<>();

        if (query != null) {
            query = query.trim().toLowerCase();
        }

        for (String t : titulos) {
            DataPropuesta dp = ic.consultaDePropuesta(t);

            if (query != null && !query.isEmpty()) {
                if (dp.getTitulo().toLowerCase().contains(query)
                        || dp.getDescripcion().toLowerCase().contains(query)
                        || dp.getLugar().toLowerCase().contains(query)) {
                    resultados.add(dp);
                }
            }
        }
        
        
        String orden = request.getParameter("orden");
        if (orden != null) {
            switch (orden) {
                case "az":
                    resultados.sort(Comparator.comparing(DataPropuesta::getTitulo, String.CASE_INSENSITIVE_ORDER));
                    break;
                case "fecha_desc":
                    resultados.sort(Comparator.comparing(DataPropuesta::getFecha).reversed());
                    break;
            }
        }
        String estado = request.getParameter("estado");
        if (estado != null) {
            switch (estado) {
                case "PUB":
                    resultados.removeIf(p->p.getEstadoActual().getEstado()!=EnumEstado.PUBLICADA);
                    break;
                case "ENF":
                    resultados.removeIf(p->p.getEstadoActual().getEstado()!=EnumEstado.EN_FINANCIACION);
                    break;
                case "NOF":
                    resultados.removeIf(p->p.getEstadoActual().getEstado()!=EnumEstado.NO_FINANCIADA);
                    break;
                case "CAN":
                    resultados.removeIf(p->p.getEstadoActual().getEstado()!=EnumEstado.CANCELADA);
                    break;
                case "FIN":
                    resultados.removeIf(p->p.getEstadoActual().getEstado()!=EnumEstado.FINANCIADA);
                    break;
            }
        }

        HttpSession misesion = request.getSession();
        misesion.setAttribute("query", query); 
        if (orden != null) {
        misesion.setAttribute("orden",orden);
        }
        if (estado != null){
            misesion.setAttribute("estado",estado);
        }
        misesion.setAttribute("DPF", resultados);

        response.sendRedirect(
                "busquedaFiltrada.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
