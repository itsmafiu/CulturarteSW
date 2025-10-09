<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header class="bg-dark-subtle">
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid align-items-center">
            <a class="navbar-brand btn text-primary fs-4" href="index.jsp">Culturarte</a>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="altaPropuesta.jsp">Alta Propuesta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="SvConsultaPropuesta">Consulta Propuesta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="SvConsultaUsuario">Consulta Usuario</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="titulo, descripción, lugar" aria-label="Buscar"/>
                <button class="btn bg-primary-subtle" type="submit">Buscar</button>
            </form>
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
                            <img src="${pageContext.request.contextPath}/${datosUsuario.imagenWeb}" alt="Imagen" width="40">                            
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href=""><%=request.getSession().getAttribute("nick")%></a>
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

</header>
