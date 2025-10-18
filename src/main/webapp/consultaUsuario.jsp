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
        
         <% 
        
        List<DataUsuario> DtU = (List) request.getSession().getAttribute("DtU");
        int tamanio = DtU.size();
        %>
        
        <div class="container my-4">
            <h2>Explora entre <%=tamanio%> Usuarios :</h2>
        </div>
        
        <div class="container mt-4">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            
        <%
        if (DtU != null && !DtU.isEmpty()) {
        for(DataUsuario usu : DtU){
        String imagen = "";
        if (usu.getImagen() == null || usu.getImagen().isBlank()) {
            imagen = "fotos\\default.jpg";
        }else{
            imagen = usu.getImagen();
        }
        %>
        
        <div class="col-12 col-sm-6 col-md-4 col-lg-3">
          <div class="card" style="width: 18rem;">
            <a href="SvPerfilUsuario?nickTarjeta=<%= usu.getNickname() %>&tipoTarjeta=<%= usu.getTipo() %>" >
            <img src="<%=imagen%>" alt="Foto de Perfil" style=" width: 100% ; height: 200px; align-items: center">
            </a>
            <div class="card-body" style="max-height: 300px; overflow: hidden;">
              <h5 class="card-title text-center"><%= usu.getNickname()%></h5>
              <p class="text-muted"><%= usu.getTipo()%></p>
              <p class="card-text"><%= usu.getNombre() + " " + usu.getApellido()%></p>
              <% if (usu.getTipo().equals("Proponente")){ %>
              <p class="card-text" style="display: -webkit-box; -webkit-line-clamp: 4 ; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                  <%= usu.getBiografia() %></p>
              <% }else{  %> 
                <p class="card-text" style="display: -webkit-box; -webkit-line-clamp: 4 ; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;"></p>
              <% } %>
            </div>
              
          </div>
        </div>
              <% } }else{ %>
              <h3> <b>No hay usuarios cargados o hubo error en la base de datos! </b></h3>
              <% } %>
        </div>
        </div>
              
      
        
        <%@include file="footer.jsp" %>
         
    </body>
</html>
