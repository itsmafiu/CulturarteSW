<%@page import="Logica.DataUsuario"%>
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
        
        <h1>Consulta de Usuarios</h1>
        
         <% 
        
        List<DataUsuario> DtU = (List) request.getSession().getAttribute("DtU");
         
        %>
        
        <div class="container mt-4">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            
        <%
        if (DtU != null && !DtU.isEmpty()) {
        for(DataUsuario usu : DtU){
        %>
        
        <div class="col">
          <div class="card h-100">
            <img src="SvMostrarFoto?path=<%=usu.getImagen()%>" alt="Foto de Perfil" width="300" style="max-height: 202px">
            <div class="card-body">
              <h5 class="card-title"><%= usu.getNickname()%></h5>
              <p class="card-text"><%= usu.getNombre() + " " + usu.getApellido()%></p>
              <% if (usu.getTipo().equals("Proponente")){ %>
              <p class="card-text"><%= usu.getBiografia() %></p>
              <% }  %> 
              <a href="#" class="btn btn-primary">Ver Perfil</a>
            </div>
              
          </div>
        </div>
              <% } }else{ %>
              <h3> <b>No hay usuarios cargados o hubo error en la base de datos! </b></h3>
              <% } %>
        </div>
        </div>
              
      
        
        
         
    </body>
</html>
