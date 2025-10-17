<%-- 
    Document   : mostrarPropuestas
    Created on : 6 oct 2025, 13:18:15
    Author     : nahud
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="Logica.DataPropuesta"%>
<%@page import="Logica.Propuesta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Culturarte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        
         <% 
        
        List<DataPropuesta> DP = (List) request.getSession().getAttribute("DP");
        int tamanio = DP.size();
        %>
        
        <div class="container my-4">
        <h2>Explora entre <%=tamanio%> Propuestas :</h2>
        </div>
        
        <div class="container my-4">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            
        <%
        for(DataPropuesta p : DP){
        int colabs = p.getMisAportes().size();
        long diasRestantes;
            if(p.getFechaLimit().toLocalDate().isAfter(p.getFechaARealizar())){
                diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaARealizar()), 0);
            }else{
                diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDateTime.now(), p.getFechaLimit()), 0); 
            }
        int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);
        String imagen = "";
        if (p.getImagen().isBlank()) {
            imagen = "fotos/default.jpg";
        }else{
            imagen = p.getImagen();
        }
        %>
        
        <div class="col-md-6 col-lg-4">
          <div class="card h-100">
              <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(p.getTitulo(), "UTF-8") %>">
              <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center">
              </a>
              <div class="card-body" style="max-height: 300px; overflow: hidden;">
              <h5 class="card-title text-center"><%=p.getTitulo()%></h5>
                    <div class="text-center bg-secondary-subtle rounded">
                        <a class="text-decoration-none"
                           href="SvPerfilUsuario?nickTarjeta=<%= p.getNickProponenteDe()%>&tipoTarjeta=Proponente">
                            by <%= p.getNickProponenteDe()%>
                        </a>
                    </div>
              <p class="card-text"  style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                  <%=p.getDescripcion()%></p>
              <p><b>Recaudado:</b> <%=p.getAlcanzada()%></p>
              <p><%= diasRestantes %> días restantes · <%=colabs%> colaboradores</p>
              <div class="progress mb-3 position-relative" style="height: 20px;">
                <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje %>%;">
                </div>
                        <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                            <%= porcentaje%>%
                        </span>
            </div>
        </div>
            </div>
        </div>
              
        <% } %>
        
        </div>
        </div>
        
         
    </body>
</html>
