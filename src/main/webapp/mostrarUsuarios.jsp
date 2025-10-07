<%@page import="Logica.Colaborador"%>
<%@page import="Logica.Proponente"%>
<%@page import="Logica.Usuario"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Usuarios</title>
    </head>
    <body>
        <form action="altaUsuario" method="GET">
            <button type="submit"> Mostrar usuarios </button>
        </form>
        <h1>Lista de Usuarios Registrados</h1>
        
        <%
            List<Proponente> listaProponentes = (List) request.getSession().getAttribute("listaProponentes");
            List<Colaborador> listaColaboradores = (List) request.getSession().getAttribute("listaColaboradores");

        %>
        <c:if test="${listaProponentes != null}">
            <h2>Proponentes</h2>
            <%
                int prop = 1;
                for(Proponente u : listaProponentes){
            %>
            <p><b>Proponente N° <%=prop%></b></p>
            <p>Nickname: <%=u.getNickname()%></p>
            <p>Email: <%=u.getEmail()%></p>
            <p>Nombre: <%=u.getNombre()%></p>
            <p>Apellido: <%=u.getApellido()%></p>
            <p>Fecha Nacimiento: <%=u.getFecNacString()%></p>
            <p>Dirección: <%=u.getDireccion()%></p>
            <p>Biografía: <%=u.getBiografia()%></p>
            <p>Sitio Web: <%=u.getSitioWeb()%></p>
            <p>Imagen: <%=u.getImagen()%></p>
            <p>------------------------------------------------</p>
            <% 
                prop++;
                } %>
        </c:if>
        
        <c:if test="${listaColaboradores != null}">
            <h2>Colaboradores</h2>
            <%
                int cola = 1;
                for(Colaborador u : listaColaboradores){
            %>
            <p><b>Colaborador N° <%=cola%></b></p>
            <p>Nickname: <%=u.getNickname()%></p>
            <p>Email: <%=u.getEmail()%></p>
            <p>Nombre: <%=u.getNombre()%></p>
            <p>Apellido: <%=u.getApellido()%></p>
            <p>Fecha Nacimiento: <%=u.getFecNacString()%></p>
            <p>Imagen: <%=u.getImagen()%></p>
            <p>------------------------------------------------</p>
            <% 
                cola++;
                } %>
        </c:if>
        
            
    </body>
</html>
