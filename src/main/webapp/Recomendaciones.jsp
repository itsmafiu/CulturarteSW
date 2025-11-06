<%-- 
    Document   : Recomendaciones
    Created on : 5 nov 2025, 12:43:54
    Author     : nahud
--%>

<%@page import="WebServices.DataSugerencias"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Culturarte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        
        <%
            
        List<DataSugerencias> DS = (List) request.getSession().getAttribute("listaDS");
        %>

        <div class="container mt-5">
            <h2 class="text-center mb-4">Sugerencias para ti</h2>

            <div class="table-responsive" style="max-height: 400px; overflow-y: auto;">
                <table class="table table-bordered table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th style="width: 70%">Propuesta</th>
                            <th style="width: 30%">Puntaje</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int i = 1;
                            if (DS != null && !DS.isEmpty()) {
                                for (DataSugerencias datos : DS) {
                        %>
                        <tr>
                             <td>
                        <%= i++ %>.
                        <a class="text-decoration-none text-dark" href="SvInfoPropuesta?titulo=<%= java.net.URLEncoder.encode(datos.getPropuesta(), "UTF-8") %>">
                            <%= datos.getPropuesta() %>
                        </a>
                    </td>
                            <td><%= datos.getPuntaje()%></td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="2" class="text-center">No hay sugerencias disponibles</td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
