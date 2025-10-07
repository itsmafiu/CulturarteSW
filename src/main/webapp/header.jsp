<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header class="bg-dark-subtle p-3">
    <div class="container d-flex justify-content-between align-items-center">
        <a href="index.jsp" class=" text-decoration-none fs-4">Culturate</a>


        <a class="nav-link " href="altaPropuesta.jsp">Alta Propuesta</a>
        
        <a class="nav-link " href="SvConsultaPropuesta">Consulta Propuesta</a>

        <a class="nav-link " href="SvConsultaUsuario">Consulta Usuario</a>

        <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="titulo, descripción, lugar" aria-label="Buscar">
            <button class="btn btn-light" type="submit">Buscar</button>
        </form>

        <c:choose>
            <c:when test="${empty nick}">
                <a class="nav-link " href="inicioSesion.jsp">Iniciar Sesión</a>
                
                <a class="nav-link " href="altaUsuario.jsp">Registrarse</a>
            </c:when>
            <c:otherwise>
                <a class="nav-link " href=""><%=request.getSession().getAttribute("nick")%></a>

                <form action="cerrarSesion" method="GET">
                    <button type="submit" class="btn">Cerrar Sesión</button>
                </form>
                <!--<a class="nav-link active " href="#">Cierre Sesión</a>-->
            </c:otherwise>
        </c:choose>


        
    </div>
</header>   