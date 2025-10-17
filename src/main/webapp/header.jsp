<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header class="bg-dark-subtle">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid align-items-center">
            <a class="navbar-brand btn text-primary fw-bold fs-4" href="SvCargaProp">Culturarte</a>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:choose>
                    <c:when test="${tipoUsuario == 'prop'}">
                        <li class="nav-item">
                            <a class="nav-link" href="SvAltaProp">Alta Propuesta</a>
                        </li>
                    </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="#" id="altaProp">Alta Propuesta</a>
                            </li>
                        </c:otherwise>
                </c:choose>
                <li class="nav-item">
                    <a class="nav-link" href="SvConsultaPropuesta">Consulta Propuesta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="SvConsultaUsuario">Consulta Usuario</a>
                </li>
            </ul>

            <form id="form-buscar" class="d-flex" role="search" action="SvBuscador" method="POST" style="min-width: 400px">
                <div class="dropdown me-1">
                    <input id="buscar" name="query" class="form-control" type="search" placeholder="título, descripción, lugar" autocomplete="off" aria-label="Buscar" />
                    <ul id="resultados" class="dropdown-menu "></ul>
                </div>
                <button class="btn bg-primary-subtle" type="submit">Buscar</button>
            </form>

            <script src="js/buscarPropuestas.js"></script>

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
                        <li class="nav-item">
                            <img src="${pageContext.request.contextPath}/${datosUsuario.imagenWeb}" alt="Imagen" style="width: 60px; height: 100%">                            
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="SvPerfilUsuario?nickTarjeta=${nick}&tipoTarjeta=${tipoUsuario}"><%=request.getSession().getAttribute("nick")%></a>
                        </li>
                        <li class="nav-item">
                            <form action="cerrarSesion" method="GET">
                                <button type="submit" class="btn">Cerrar Sesión</button>
                            </form>
                        </li>
                    </ul>                     
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
    <div class="container-fluid bg-black" style="padding: 1px"></div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/validacionAltaPropu.js"></script>
</header>
