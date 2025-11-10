/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import WebServices.DataAporte;
import WebServices.DataColaborador;
import WebServices.DataPropuesta;
import WebServices.DataSugerencias;
import WebServices.DataUsuario;
import WebServices.LogicaWS;
import WebServices.LogicaWS_Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvRecomendaciones", urlPatterns = {"/SvRecomendaciones"})
public class SvRecomendaciones extends HttpServlet {
    
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
        
        DataColaborador dataCola = ic.consultaDeColaborador(nick);
        
        List<DataUsuario> seguidos = dataCola.getLosSigo();
        System.out.println("Seguidos: " + seguidos.size());
        
        List<DataColaborador> colabSeguidos = new ArrayList<>();
        
        for (DataUsuario seguido : seguidos) {
            if (seguido.getTipo().equals("Colaborador")) {
                DataColaborador dataColaborador = ic.consultaDeColaborador(seguido.getNickname());
                colabSeguidos.add(dataColaborador);
            //parece ser que aca puedo poner desde la linea anterior del for de aportes y deberia funcionar...
            }
        }
        
        List<DataSugerencias> listaDS = new ArrayList<>();
        
        int puntajeFinanciacion;
        
        for (DataColaborador c : colabSeguidos) {
            List<DataAporte> aportes = c.getMisAportes();
            for (DataAporte a : aportes) {
                
               DataPropuesta prop = ic.consultaDePropuesta(a.getMiPropuesta());
               
               int colaboraciones = prop.getMisAportes().size();
               
               double porcentaje = Math.min((prop.getAlcanzada() / prop.getNecesaria()) * 100, 100);
               
                if (0 <= porcentaje && porcentaje <= 25) {
                   puntajeFinanciacion = 1;
                } else if (25 < porcentaje && porcentaje <= 50) {
                    puntajeFinanciacion = 2;
                } else if (50 < porcentaje && porcentaje <= 75) {
                    puntajeFinanciacion = 3;
                } else {
                    puntajeFinanciacion = 4;
                }
                
                int favoritosCon = prop.getCantidadFav();
                
                int puntaje = colaboraciones + puntajeFinanciacion + favoritosCon;
                
                DataSugerencias DS = ic.getDataSugerencia(prop.getTitulo(), puntaje);
                boolean existe = false;
                for (DataSugerencias ds : listaDS) {
                    if (ds.getPropuesta().equals(DS.getPropuesta())) {
                        ds.setPuntaje(ds.getPuntaje() + DS.getPuntaje());
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    listaDS.add(DS);
                }
                
                List<DataAporte> coAportes = prop.getMisAportes();
                for (DataAporte coAporte : coAportes) {
                    String nickCola = coAporte.getMiColaborador();
                    if (!nickCola.equals(c.getNickname())) {
                        DataColaborador coColaborador = ic.consultaDeColaborador(nickCola);
                        for (DataAporte otrosAportes : coColaborador.getMisAportes()) {
                            DataPropuesta otraProp = ic.consultaDePropuesta(otrosAportes.getMiPropuesta());
                            if (!otraProp.getTitulo().equals(prop.getTitulo())) {
                                int coColaboraciones = otraProp.getMisAportes().size();
                                double coPorcentaje = Math.min((otraProp.getAlcanzada() / otraProp.getNecesaria()) * 100, 100);
                                int coPuntajeFinanciacion;
                                if (0 <= coPorcentaje && coPorcentaje <= 25) {
                                    coPuntajeFinanciacion = 1;
                                } else if (25 < coPorcentaje && coPorcentaje <= 50) {
                                    coPuntajeFinanciacion = 2;
                                } else if (50 < coPorcentaje && coPorcentaje <= 75) {
                                    coPuntajeFinanciacion = 3;
                                } else {
                                    coPuntajeFinanciacion = 4;
                                }
                                int coFavoritos = otraProp.getCantidadFav();
                                int coPuntaje = coColaboraciones + coPuntajeFinanciacion + coFavoritos;

                                DataSugerencias DSco = ic.getDataSugerencia(otraProp.getTitulo(), coPuntaje);
                                boolean existeCo = false;
                                for (DataSugerencias ds : listaDS) {
                                    if (ds.getPropuesta().equals(DSco.getPropuesta())) {
                                        ds.setPuntaje(ds.getPuntaje() + (DSco.getPuntaje() - coFavoritos));
                                        existeCo = true;
                                        break;
                                    }
                                }
                                if (!existeCo) {
                                    listaDS.add(DSco);
                                }
                            }
                        }
                    }
                }
            }
        }
        listaDS.sort(Comparator.comparingInt(DataSugerencias::getPuntaje).reversed().thenComparing(DataSugerencias::getPropuesta));
        if (listaDS.size() > 10) {
            listaDS = listaDS.subList(0, 10);
        }
        misesion.setAttribute("listaDS", listaDS);
        response.sendRedirect("Recomendaciones.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
