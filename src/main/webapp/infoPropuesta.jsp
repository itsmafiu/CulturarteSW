<%-- 
    Document   : infoPropuesta
    Created on : 8 oct 2025, 18:32:00
    Author     : nahud
--%>

<%@page import="WebServices.EnumEstado"%>
<%@page import="WebServices.DataComentario"%>
<%@page import="java.util.List"%>
<%@page import="WebServices.DataColaborador"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="WebServices.DataPropuesta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Culturarte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="css/estilosTarjetasUsuarios.css">
    </head>
    <body class="bg-light">

        <%@ include file="header.jsp" %>

        <%
            DataPropuesta p = (DataPropuesta) request.getSession().getAttribute("p");

            int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);

            long diasRestantes;

            java.time.LocalDate fechaPubli = java.time.LocalDate.parse(p.getFechaPubliStr());
            java.time.LocalDateTime fechaLimit = java.time.LocalDateTime.parse(p.getFechaLimitStr());

            if (fechaLimit.isAfter(fechaPubli.atStartOfDay())) {
                diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), fechaPubli), 0);
            } else {
                diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDateTime.now(), fechaLimit), 0);
            }

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
                         style="height: 400px; object-fit: contain;">
                </div>

                <div class="col-md-5">
                    <h2 class="fw-bold mb-2"><%= p.getTitulo()%></h2>
                    <div class="text-center bg-secondary-subtle rounded">
                        <% if(!esMovil){ %>
                        <a class="text-decoration-none"
                           href="SvPerfilUsuario?nickTarjeta=<%= p.getNickProponenteDe()%>&tipoTarjeta=Proponente">
                            by <%= p.getNickProponenteDe()%>
                        </a>
                        <% } else { %>
                        <p class="text-decoration-none">
                            by <%= p.getNickProponenteDe()%>
                        </p>
                        <% } %>
                    </div>
                    <p class="text-muted mb-4"><%= p.getDesc()%></p>

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
                                if (nickUsuario.equals(p.getNickProponenteDe()) && (p.getEstadoActual().getEstado() == EnumEstado.PUBLICADA || p.getEstadoActual().getEstado() == EnumEstado.EN_FINANCIACION)) {
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
                                } else if (p.getEstadoActual().getEstado() == EnumEstado.FINANCIADA) {
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
                            } else {
                            %>    
                            <p class="text-center text-secondary-subtle">Solo los colaboradores tienen permitido colaborar</p>
                            <%
                                }
                            %> 
                        </c:otherwise>
                    </c:choose>                                          

                    <c:choose>
                        <c:when test="${not empty nick}">
                            <%
                                if (p.getEstadoActual().getEstado() != EnumEstado.INGRESADA) {
                            %>
                            <!-- Contenedor flexible -->
                            <div class="d-flex flex-wrap align-items-center gap-2 mt-3">
                                <form action="SvFavorita" method="POST" class="m-0">
                                    <input type="hidden" name="titulo" value="<%= p.getTitulo()%>" required>
                                    <%
                                        Boolean esFavorita = (Boolean) request.getSession().getAttribute("esFavorita");
                                        if (esFavorita != null && esFavorita) {
                                    %>
                                    <button type="submit" class="btn btn-warning fw-bold text-light">
                                        Favorita
                                    </button>
                                    <%
                                    } else {
                                    %>
                                    <button type="submit" class="btn btn-outline-warning fw-bold">
                                        Favorita
                                    </button>
                                    <%
                                        }
                                    %>
                                </form>

                                <%
                                    String nick = (String) request.getSession().getAttribute("nick");
                                    Boolean esColaboracion = (Boolean) request.getSession().getAttribute("esColaboracion");
                                    if (esColaboracion != null && esColaboracion) {
                                %>
                                <a href="pagarCola.jsp" class="btn btn-outline-dark">
                                    Pagar colaboración
                                </a>
                                <%
                                    }
                                %>
                            </div>
                            <%
                                }
                            %>
                        </c:when>
                    </c:choose>
                            
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
                <%if (!(colab.isEmpty())) { %>
                <div class="row row-cols-1 row-cols-md-3 g-4" id="listaColaboradores">
                    <%
                        String img;
                        for (DataColaborador c : colab) {
                            if (c.getImagen().isBlank()) {
                                img = "fotos\\default.jpg";
                            } else {
                                img = c.getImagen();
                            }
                    %>
                    <div class="usuario col-sm-6 col-lg-4">
                        <div class="card" style="width: 18rem;">
                            <a href="SvPerfilUsuario?nickTarjeta=<%= c.getNickname()%>&tipoTarjeta="Colaborador" >
                                <img src="<%= img%>" alt="Foto de Perfil" class="card-img-top" style="width: 100% ; height:200px; align-items: center">
                            </a>
                            <div class="card-body">
                                <h5 class="card-title text-center"><%= c.getNickname()%></h5>
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
        <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>--%>                      
        <div class="m-1">
            <p class="mx-3">Comentarios</p>
            <ul class="list-group list-group-flush">
                <% List<DataComentario> DCs = (List<DataComentario>) request.getSession().getAttribute("DCs"); %>
                <c:choose>
                    <c:when test="${not empty nick}">
                        <%
                            String nick = (String) request.getSession().getAttribute("nick");
                            boolean esColab = false;
                            boolean hizoComent = false;
                            if (!(colab.isEmpty())) {
                                for (DataColaborador c : colab) {
                                    if (c.getNickname().equals(nick)) {
                                        esColab = true;
                                    }
                                }
                            }

                            if (!(DCs.isEmpty())) {
                                for (DataComentario dc : DCs) {
                                    if (dc.getNickColaborador().equals(nick)) {
                                        hizoComent = true;
                                    }
                                }
                            }

                            if (esColab && !hizoComent) {%>
                        <li class="list-group-item">
                            <form action="SvComentario" class="needs-validation" method="POST">
                                <div class="row">
                                    <div class="col-6">
                                        <input type="text" class="form-control" id="comentario" name="comentario" required>
                                        <input type="hidden" class="form-control" name="nick" value="<%=nick%>" required>
                                        <input type="hidden" class="form-control" name="titulo" value="<%=p.getTitulo()%>" required>
                                        <div class="invalid-feedback">Ingrese un comentario.</div>
                                    </div>
                                    <div class="col-1">
                                        <button type="submit" class="btn btn-primary">Comentar</button>
                                    </div>
                                </div>
                            </form>
                        </li>
                        <%}%>
                    </c:when>
                </c:choose>

                <%

                    if (!(DCs.isEmpty())) {
                        for (DataComentario dc : DCs) {
                %>
                <li class="list-group-item">
                    <div class="justify-content-between border border-secondary p-2 rounded">
                        <div> 
                            <h6 class="mb-0"><%=dc.getNickColaborador()%></h6>
                            <p class="mb-0 opacity-75"><%=dc.getComentario()%></p>
                        </div>
                        <small class="opacity-50 text-nowrap"><%=dc.getFecComentario()%></small> 
                    </div>
                </li>     
                <%}
                } else {%>

                <li class="list-group-item">
                    <div class="d-flex gap-2 w-100 justify-content-between">
                        <div> 
                            <p class="mb-0 opacity-75">Propuesta aún sin comentarios.</p>
                        </div>
                        <small class="opacity-50 text-nowrap"></small> 
                    </div>
                </li> 


                <%}%>
            </ul>
        </div>
        <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>  --%> 
        <%@include file="footer.jsp" %>
    </body>
</html>
