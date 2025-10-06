<%-- 
    Document   : mostrarPropuestas
    Created on : 6 oct 2025, 13:18:15
    Author     : nahud
--%>

<%@page import="Logica.DataPropuesta"%>
<%@page import="Logica.Propuesta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Culturarte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        
        <h1>Consulta Propuestas</h1>
        
         <% 
        
        List<DataPropuesta> DP = (List) request.getSession().getAttribute("DP");
        
        %>
        
        <div class="container mt-4">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            
        <%
        for(DataPropuesta p : DP){
        %>
        
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
              
        <% } %>
        
        </div>
        </div>
        
         
    </body>
</html>
