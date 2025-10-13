<%@page import="java.net.URLEncoder"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Logica.DataPropuesta"%>
<%@page import="Logica.Propuesta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registra Colaboración</title>
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            input[type=text]{
                width: 100%;
            }
            input[type=date]{
                width: 50%;
            }
            div{
                margin: auto;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <%
            DataPropuesta p = (DataPropuesta) request.getSession().getAttribute("p");
            int colabs = p.getMisAportes().size();
            long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaARealizar()), 0);
            int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);
            String imagen = "";
            if (p.getImagen().isBlank()) {
                imagen = "fotos/default.jpg";
            } else {
                imagen = p.getImagen();
            }
        %>
        <div class="container mt-4">
            <div class="mt-4 p-5 bg-info text-bg-color rounded">

                <form action="SvRegistraCola" class="needs-validation" method="POST">
                    <div class="row">
                        <div class="col">
                            <h1>Registrar Colaboracion</h1>
                            <div class="form-group mb-3">
                                <label for="monto">Monto: </label>
                                <input type="text" class="form-control form-control-lg" id="monto" name="monto" required>
                                <div class="invalid-feedback">Ingrese un monto.</div>
                            </div>                            
                            <p>Elija el tipo de Retorno:<br>
                                <input type="radio" id="prop" name="tipoRetorno" value="ENTRADAS" checked> <label for="entradas">Entradas Gratis</label>
                                <input type="radio" id="cola" name="tipoRetorno" value="PORCENTAJE"> <label for="porcentaje">Porcentaje sobre Ventas</label>
                            </p>
                            <button type="submit" class="btn btn-primary">Colaborar</button>
                            <c:if test="${not empty error}">
                                <p style="color: red">${error}</p>
                            </c:if>
                        </div>
                        <div class="col">
                            <div id="datosProponente" class=" p-4 bg-secondary text-bg-color rounded">
                                <div class="col">
                                    <div class="card h-100">
                                        <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(p.getTitulo(), "UTF-8")%>">
                                            <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 414px; height: 300px; align-items: center">
                                        </a>
                                            <div class="card-body mb-4" style="max-height: 300px; overflow: hidden;">
                                                <h5 class="card-title text-center"><%=p.getTitulo()%></h5>
                                                <div class="text-center bg-secondary-subtle rounded">
                                                    <a class="text-decoration-none"
                                                       href="SvPerfilUsuario?nickTarjeta=<%= p.getNickProponenteDe()%>&tipoTarjeta=Proponente">
                                                        by <%= p.getNickProponenteDe()%>
                                                    </a>
                                                </div>
                                                <p class="card-text"  style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                                                    <%=p.getDescripcion()%>
                                                </p>
                                                <p><b>Recaudado:</b> <%=p.getAlcanzada()%></p>
                                                <p><%= diasRestantes%> días restantes · <%=colabs%> colaboradores</p>
                                                <div class="progress mb-3 position-relative" style="height: 20px;">
                                                <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje%>%;">
                                                </div>
                                                <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                                                    <%= porcentaje%>%
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                </form>
            </div>
        </div>