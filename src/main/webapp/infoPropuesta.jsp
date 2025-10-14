<%-- 
    Document   : infoPropuesta
    Created on : 8 oct 2025, 18:32:00
    Author     : nahud
--%>

<%@page import="Logica.EnumEstado"%>
<%@page import="Logica.DataComentario"%>
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
                            <%
                                String nickUsuario = (String) request.getSession().getAttribute("nick");
                                if (nickUsuario.equals(p.getNickProponenteDe()) && (p.getEstadoActual().getEstado()==EnumEstado.PUBLICADA || p.getEstadoActual().getEstado()==EnumEstado.EN_FINANCIACION)) {
                            %> 
                            <div class="row align-items-center text-center">
                                <div class="col bg-primary rounded">
                                    <form action="SvExtenderFinanciacion" method="POST">
                                        <button type="submit" class="btn btn-primary">
                                            Extender Financiación
                                        </button>
                                    </form>
                                </div>
                            <%
                                }else if (p.getEstadoActual().getEstado()==EnumEstado.FINANCIADA){
                            %> 
                                <div class="col bg-danger rounded">
                                    <form  action="SvCancelarPropuesta" method="POST">
                                        <button type="submit" class="btn btn-danger">
                                            Cancelar Propuesta
                                        </button>
                                    </form>
                                </div>
                            </div>
                            
                            <%
                                }else{
                            %>    
                            <p class="text-center text-secondary-subtle">Solo los colaboradores tienen permitido colaborar</p>
                            <%
                                }
                            %> 
                        </c:otherwise>
                            
                    </c:choose>

                                
                    <!--
                    <%
                        //request.getSession().setAttribute("titulo", p.getTitulo());

                        //boolean esFavorita = (boolean) request.getSession().getAttribute("esFavorita");
                    %>

                    <c:choose>
                        <c:when test="${not empty nick}">
                            <form action="SvFavorita" method="POST">
                                <button type="submit" class="btn">
                                    Favorita
                                </button>
                            </form>
                        </c:when>
                    </c:choose>
                    -->

                </div>
            </div>
        </div>
        <div class="container my-4">
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
            <%if(!(colab.isEmpty())){ %>
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <%
                    String img;
                    for (DataColaborador c : colab) {
                    if (c.getImagen().isBlank()) {
                            img = "fotos\\default.jpg";
                    }else{
                            img = c.getImagen();
                    }
                %>
                            <div class="col-sm-6 col-lg-4">
                                <div class="card h-100">
                                    <img src="<%=img %>" alt="Foto de Perfil" class="card-img-top" style="max-height:250px; height: 100%">
                                    <div class="card-body" style="max-height: 300px;">
                                        <h5 class="card-title"><%= c.getNickname() %></h5>
                                        <a href="SvPerfilUsuario?nickTarjeta=<%= c.getNickname() %>&tipoTarjeta=Colaborador" class="btn btn-primary">Ver Perfil</a>
                                    </div>
                                </div>
                            </div>
                <%}%>
            </div>
        </div>
       <% } else {%> 
            <p>No tiene colaboradores aún.</p>
       <%}%>
        </div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>                      
        <div class="m-1">
            <p class="mx-3">Comentarios</p>
            <ul class="list-group list-group-flush">
                <c:choose>
                    <c:when test="${not empty nick}">
                        <li class="list-group-item">
                            <form action="SvComentario" class="needs-validation" method="POST">
                                <input type="text" class="form-control" id="comentario" name="comentario" required>
                                <div class="invalid-feedback">Ingrese un comentario.</div>
                            </form>
                        </li>
                    </c:when>
                </c:choose>

                <%
                    List<DataComentario> DCs = (List<DataComentario>) request.getSession().getAttribute("DCs");
                    if (DCs != null) {
                        for (DataComentario dc : DCs) {
                %>
                <li class="list-group-item">
                    <img src="https://github.com/twbs.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0"> 
                    <div class="d-flex gap-2 w-100 justify-content-between">
                        <div> 
                            <h6 class="mb-0"><%=dc.getNickColaborador()%></h6>
                            <p class="mb-0 opacity-75"><%=dc.getComentario()%></p>
                        </div>
                        <small class="opacity-50 text-nowrap"><%=dc.getFecComentario().toString()%></small> 
                    </div>
                </li>     
                <%}
                    }%>
            </ul>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>                      
    </body>
</html>
