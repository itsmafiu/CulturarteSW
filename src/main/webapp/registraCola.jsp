<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Logica.DataPropuesta"%>
<%@page import="Logica.Propuesta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro Usuario</title>
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
            request.getSession().getAttribute("titulo");
            
            DataPropuesta p = (DataPropuesta) 
        %>
        <div class="container mt-4">
            <div class="mt-4 p-5 bg-info text-bg-color rounded">
                <h1>Registrar Colaboracion</h1>
                <form action="registraCola" class="needs-validation" method="POST">
                    <div class="row">
                        <div class="col">
                            <div class="form-group mb-3">
                                <label for="monto">Monto: </label>
                                <input type="text" class="form-control form-control-lg" id="monto" name="monto" required>
                                <div class="invalid-feedback">Ingrese un nick.</div>
                            </div>                            
                            <p>Elija el tipo de Retorno:<br>
                                <input type="radio" id="prop" name="tipoRetorno" value="ENTRADAS" checked> <label for="entradas">Entradas Gratis</label>
                                <input type="radio" id="cola" name="tipoRetorno" value="PORCENTAJE"> <label for="porcentaje">Porcentaje sobre Ventas</label>
                            </p>
                        </div>
                        <div class="col">
                            <div id="datosProponente" class="mt-4 p-5 bg-secondary text-bg-color rounded">
                                <div class="col">
                                    <div class="card h-100">
                                        <img src="SvMostrarFoto?path=<%= p.getImagen() %>" alt="Foto de la propuesta" width="300" style="max-height: 202px">
                                        <div class="card-body">
                                            <h5 class="card-title text-center"><%=p.getTitulo()%></h5>
                                            <p class="card-text"><%=p.getDescripcion()%></p>
                                            <p><b>Recaudado:</b> <%=p.getAlcanzada()%></p>
                                            <p>26 días restantes · 350 colaboradores <b>AUN SIN HACER!!!</b></p>
                                            <div class="progress">
                                                <div class="progress-bar bg-success" role="progressbar" style="width: 100%">1000%</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <c:if test="${not empty error}">
                        <p style="color: red">${error}</p>
                    </c:if>
                </form>
            </div>
        </div>