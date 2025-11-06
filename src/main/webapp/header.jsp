<%@page import="WebServices.Usuario"%>
<%@page import="java.io.File"%>
<%@page import="WebServices.DataUsuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String user = request.getHeader("User-Agent");
    boolean esMovil = user != null && (user.contains("Movi") || user.contains("Android") || user.contains("iPhone"));
    
    if(request.getSession().getAttribute("nick") == null){
        String nick = "vacio";
    }
    
    if(!esMovil || request.getSession().getAttribute("nick") != null) { 
    
%>
<header class="bg-dark-subtle">
    <nav class="navbar navbar-expand-xl">
        <div class="container-fluid align-items-center">
            <a class="navbar-brand btn text-primary fw-bold fs-4" href="SvCargaProp">Culturarte</a>
            <button class="navbar-toggler mx-2" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:choose>
                        <c:when test="${tipoUsuario == 'prop'}">
                            <li class="nav-item">
                                <a class="nav-link" href="SvAltaProp">Alta Propuesta</a>
                            </li>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                    <li class="nav-item">
                        <a class="nav-link" href="SvConsultaPropuesta">Consulta Propuesta</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="SvConsultaUsuario">Consulta Usuario</a>
                    </li>
                     <c:choose>
                        <c:when test="${nick != null and tipoUsuario == 'cola'}">
                            <li class="nav-item">
                                <a class="nav-link" href="SvRecomendaciones">Sugerencias</a>
                            </li>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </ul>

                <form id="form-buscar" class="d-flex" role="search" action="SvBuscador" method="POST" style="min-width: 400px">
                    <div class="dropdown me-1">
                        <input id="buscar" name="query" class="form-control" type="search" placeholder="título, descripción, lugar" autocomplete="off" aria-label="Buscar" style="width: 300px"/>
                        <ul id="resultados" class="dropdown-menu " style="width: 300px"></ul>
                    </div>
                    <button class="btn bg-primary-subtle" type="submit">Buscar</button>
                </form>

                <!--<script src="js/buscarPropuestas.js"></script>-->

                <c:choose>
                    <c:when test="${empty nick}">
                        <ul class="navbar-nav ms-2">
                            <li class="nav-item">
                                <a class="nav-link" href="inicioSesion.jsp">Iniciar Sesión</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="altaUsuario.jsp">Registrarse</a>
                            </li>
                        </ul> 
                    </c:when>
                    <c:otherwise>    
                        <ul class="navbar-nav ms-2">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="perfilDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <img src="${pageContext.request.contextPath}/${datosUsuario.imagen}" alt="Imagen" class="rounded-circle border" style="width: 40px; height: 40px">
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="perfilDropdown">
                                    <li><a class="dropdown-item" href="SvPerfilUsuario?nickTarjeta=${nick}&tipoTarjeta=${tipoUsuario}"><%=request.getSession().getAttribute("nick")%></a></li>
                                    <li><div class="dropdown-divider"></div></li>
                                    <li><a class="dropdown-item" href="cerrarSesion">Cerrar Sesión</a></li>
                                </ul>
                            </li>
                        </ul>                     
                    </c:otherwise>
                </c:choose>
        </div>
    </nav>
    <div class="container-fluid bg-black" style="padding: 1px"></div>
    <% }%>
    <!--    <script src="https://code.jquery.com/jquery-3.6.4.min.js" defer></script>
        <script src="js/validacionAltaPropu.js" defer></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" defer></script>-->

    <style>
        .nav-item.dropdown {
            position: relative;
        }

        .nav-item.dropdown .dropdown-menu {
            right: 0;
            left: auto; /* fuerza que aparezca hacia la izquierda del botón si está a la derecha */
            transform: translateX(-10px); /* opcional, pequeño ajuste visual */
        }

        .navbar, header {
            overflow: visible !important; /* evita que el menú se recorte */
        }

    </style>
    <script>
document.addEventListener("DOMContentLoaded", function() {
    const inputBuscar = document.getElementById("buscar");
    const botonBuscar = document.querySelector("#form-buscar button[type='submit']");
    const formBuscar = document.getElementById("form-buscar");

    // Interceptar la tecla Enter
    inputBuscar.addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            event.preventDefault(); // evita el envío automático
            botonBuscar.click();   // ejecuta el botón Buscar
        }
    });
});
    </script>
</header>
