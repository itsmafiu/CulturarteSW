<%-- 
    Document   : infoPropuesta
    Created on : 8 oct 2025, 18:32:00
    Author     : nahud
--%>

<%@page import="java.util.List"%>
<%@page import="Logica.DataColaborador"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="Logica.DataPropuesta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Culturarte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <%@ include file="header.jsp" %>

        <%
            DataPropuesta p = (DataPropuesta) request.getSession().getAttribute("p");

            int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);

            long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaARealizar()), 0);

            int colabs = p.getMisAportes().size();

            String imagen = "";

            if (p.getImagen().isBlank()) {
                imagen = "fotos/default.jpg";
            } else {
                imagen = p.getImagen();
            }

            String estado = p.getEstadoActual().getEstado().toString();

            if (estado == "EN_FINANCIACION") {
                estado = "En Financiación";
            } else if (estado == "PUBLICADA") {
                estado = "Publicada";
            } else if (estado == "CANCELADA") {
                estado = "Cancelada";
            } else if (estado == "NO_FINANCIADA") {
                estado = "No Financiada";
            } else if (estado == "FINANCIADA") {
                estado = "Financiada";
            }
            
            List<DataColaborador> colab = (List<DataColaborador>) request.getSession().getAttribute("colabs");
        %>

        <div class="container mt-5">
            <div class="row align-items-start">

                <div class="col-md-7 text-center">
                    <img src="<%=imagen%>" 
                         alt="Imagen de la propuesta" 
                         class="img-fluid rounded shadow-sm mb-3"
                         style="height: 400px; object-fit: cover;">
                </div>

                <div class="col-md-5">
                    <h2 class="fw-bold mb-2"><%= p.getTitulo()%></h2>
                    <div class="text-center bg-secondary-subtle rounded">
                        <a class="text-decoration-none"
                           href="SvPerfilUsuario?nickTarjeta=<%= p.getNickProponenteDe()%>&tipoTarjeta=Proponente">
                            by <%= p.getNickProponenteDe()%>
                        </a>
                    </div>
                    <p class="text-muted mb-4"><%= p.getDescripcion()%></p>

                    <h4 class="fw-semibold text-success mb-0">
                        <%= String.format("%.0f", p.getAlcanzada())%> $
                    </h4>
                    <p class="text-muted">recaudado de <%= String.format("%.0f", p.getNecesaria())%> $</p>

                    <div class="progress mb-3 position-relative" style="height: 20px;">
                        <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje%>%;">
                        </div>
                        <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                            <%= porcentaje%>%
                        </span>
                    </div>

                    <div class="d-flex justify-content-between text-center mb-3">
                        <div>
                            <h5 class="mb-0"><%=colabs%></h5>
                            <small class="text-muted">colaboradores</small>
                        </div>
                        <div> 
                            <h5 class="mb-0"><%=estado%></h5>
                            <small class="text-muted">estado actual</small>
                        </div>    
                        <div>
                            <h5 class="mb-0"><%= diasRestantes%></h5>
                            <small class="text-muted">días restantes</small>
                        </div>
                    </div>
                    <c:choose>

                        <c:when test="${empty nick}">
                            <a href="inicioSesion.jsp" class="btn btn-success w-100 py-2 fw-semibold">
                                Contribuir con esta propuesta
                            </a>
                        </c:when>

                        <c:when  test="${tipoUsuario=='cola'}">
                            <a href="registraCola.jsp" class="btn btn-success w-100 py-2 fw-semibold">
                                Contribuir con esta propuesta
                            </a>
                        </c:when>

                        <c:otherwise>
                            <p class="text-center text-secondary-subtle">Solo los colaboradores tienen permitido colaborar</p>
                        </c:otherwise>

                    </c:choose>
                    <%
                        request.getSession().setAttribute("titulo", p.getTitulo());
                       
                        Boolean esFavorita = (Boolean) request.getSession().getAttribute("esFavorita");
                        
                        if (esFavorita == null) {
                            esFavorita = false;
                        }
                    %>

                    <c:choose>
                        <c:when test="${not empty nick}">
                            <form action="SvFavorita" method="POST">
                                <button type="submit" class="btn <%= esFavorita ? "btn-primary" : "btn-secondary"%>">
                                    Favorita
                                </button>
                            </form>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="mt-3">                
        <button class="btn btn-outline-primary mb-2" type="button"
                data-bs-toggle="collapse" data-bs-target="#collapseColaboradores"
                aria-expanded="false" aria-controls="collapseColaboradores">
            Mostrar/Ocultar Colaboradores
        </button>
        </div>
        <div class="collapse mb-4" id="collapseColaboradores">
            <div class="my-4">
            <h3>Colaboradores</h3>
            </div>
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <%
                    for (DataColaborador c : colab) {
                %>
                            <div class="col">
                                <div class="card h-100">
                                    <img src="<%= c.getImagen() %>" alt="Foto de Perfil" class="card-img-top" style="max-height:200px;">
                                    <div class="card-body">
                                        <h5 class="card-title"><%= c.getNickname() %></h5>
                                        <a href="SvPerfilUsuario?nickTarjeta=<%= c.getNickname() %>&tipoTarjeta=Colaborador" class="btn btn-primary">Ver Perfil</a>
                                    </div>
                                </div>
                            </div>
                <%}%>
            </div>
        </div>                    
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>                      
    </body>
</html>
