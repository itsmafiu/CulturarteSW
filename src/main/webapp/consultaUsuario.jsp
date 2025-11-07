<%@page import="WebServices.DataUsuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Culturarte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilosTarjetasUsuarios.css">
        
    </head>
    <body>
        <%@ include file="header.jsp" %>
        
        <% 
        boolean NoHayUsuarios = false;
        List<DataUsuario> DtU = (List) request.getSession().getAttribute("DtU");
        int tamanio = DtU.size();
        %>
        
        <div class="container my-4">
            <h2 id = "tituloExplora">Explora entre <%=tamanio%> Usuarios :</h2>
        </div>




        <!-- Filtros tipo pestaña -->
        <ul id = "filtros" class="nav nav-tabs mb-2">
          <li class="nav-item">
            <button class="nav-link active bg-dark-subtle rounded" onclick="filtrarUsuarios('todos')">Todos</button>
          </li>
          <li class="nav-item">
            <button class="nav-link bg-dark-subtle rounded" onclick="filtrarUsuarios('proponente')">Proponentes</button>
          </li>
          <li class="nav-item">
            <button class="nav-link bg-dark-subtle rounded" onclick="filtrarUsuarios('colaborador')">Colaboradores</button>
          </li>
        </ul>


        <div class="container-fluid bg-dark-subtle p-0">
            <div id = "listaUsuarios" class="mt-0">
                <div class="row flex-nowrap">
                    <div class="col-md-9">
                        <div class="row">
                            <%
                                if (DtU != null && !DtU.isEmpty()) {
                                    for (DataUsuario usu : DtU) {
                                        String imagen = "";
                                        if (usu.getImagen() == null || usu.getImagen().isBlank()) {
                                            imagen = "fotos" + File.separator + "default.jpg";
                                        } else {
                                            imagen = usu.getImagen();
                                        }
                            %>

                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 usuario" data-tipo="<%=usu.getTipo()%>">
                                <div class="card" style="width: 18rem;">
                                    <a href="SvPerfilUsuario?nickTarjeta=<%= usu.getNickname()%>&tipoTarjeta=<%= usu.getTipo()%>" >
                                        <img src="<%=imagen%>" alt="Foto de Perfil" style=" width: 100% ; height: 200px; align-items: center">
                                    </a>
                                    <div class="card-body" style="max-height: 300px; overflow: hidden;">
                                        <h5 class="card-title text-center"><%= usu.getNickname()%></h5>
                                        <p class="text-muted"><%= usu.getTipo()%></p>
                                        <p class="card-text"><%= usu.getNombre() + " " + usu.getApellido()%></p>
                                        <% if (usu.getTipo().equals("Proponente")) {%>
                                        <p class="card-text" style="display: -webkit-box; -webkit-line-clamp: 4 ; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                                            <%= usu.getBiografia()%></p>
                                            <% } else {  %> 
                                        <p class="card-text" style="display: -webkit-box; -webkit-line-clamp: 4 ; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;"></p>
                                        <% } %>
                                    </div>

                                </div>
                            </div>
                            <% }
                                } else {
                                    NoHayUsuarios = true;
        } %>
                        </div>
                    </div>
                    <!--<div class="col-md-1"></div>-->

                    <!--ranking por seguidores-->
                    <div class="col-md-3">
                        <h3 class=" mb-2 mt-4"><b>Ranking de Usuarios por Seguidores</b></h3>
                        <div class="d-flex flex-column align-items-start p-2" id="listaSeguidores">
                            <%
                                List<DataUsuario> DtURanking = (List) request.getSession().getAttribute("DtURanking");
                                if (DtURanking != null && !DtURanking.isEmpty()) {
                                    int posicion = 1;
                                    for (DataUsuario u : DtURanking) {
                                        String imagenUsuario = "";
                                        if (u.getImagen() == null || u.getImagen().isBlank()) {
                                            imagenUsuario = ("fotos" + File.separator + "default.jpg");
                                        } else {
                                            imagenUsuario = u.getImagen();
                                        }
                            %>

                            <div class="usuario" id="Usuario-<%= u.getNickname()%>">
                                <h3><b><%=posicion%></b></h3>
                                <!-- Tarjeta horizontal -->
                                <% if (posicion == 1) { %>
                                <div class="card shadow-sm border-0 rounded-3 overflow-hidden my-0 bg-warning" style="max-width: 360px;">
                                <% } else if (posicion == 2) { %>
                                <div class="card shadow-sm border-0 rounded-3 overflow-hidden my-0 bg-secondary" style="max-width: 360px;">
                                <% } else if (posicion == 3) { %>
                                <div class="card shadow-sm border-0 rounded-3 overflow-hidden my-0" style="max-width: 360px; background-color: peru">
                                <% } else { %>
                                <div class="card shadow-sm border-0 rounded-3 overflow-hidden my-0" style="max-width: 360px;">
                                <% }%>
                                    <div class="d-flex align-items-center" style="height: 110px;">

                                        <!-- Imagen -->
                                        <div class="flex-shrink-0" style="width: 40%; height: 100%;">
                                            <a href="SvPerfilUsuario?nickTarjeta=<%= u.getNickname()%>&tipoTarjeta=<%= u.getTipo()%>">
                                                <img src="<%= imagenUsuario%>"
                                                     alt="Foto de Perfil"
                                                     class="img-fluid object-fit-cover w-100 h-100">
                                            </a>
                                        </div>

                                        <!-- Info -->
                                        <div class="flex-grow-1 text-center p-2">
                                            <h6 class="mb-1 fw-bold"><%= u.getNickname()%></h6>
                                            <p class="mb-1 text-muted"><%= u.getTipo()%></p>
                                            <h5 class="mb-0"><b><%= u.getMeSiguen().size()%> seguidores</b></h5>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <%  posicion++;
                                    }
                                } else { %>
                            <p  id="sinSeguidores"><b>No existen usuarios con seguidores aún.</b></p>
                             <% } %>
                        </div>
                    </div>
                </div>
            </div>    
        </div>
        
        <%@include file="footer.jsp" %>
      
        <script>
        const noHayUser = <%= NoHayUsuarios %>  
          if (noHayUser){
              document.getElementById("tituloExplora").textContent = "No hay usuarios registrados, animate a ser el primero";
              document.getElementById("filtros").style.display = "none";
          }  
        
        function filtrarUsuarios(tipo) {
          const usuarios = document.querySelectorAll('.usuario');
          const tituloExplora = document.getElementById('tituloExplora');
          let visibles = 0;

          usuarios.forEach(u => {
            const esTipo = u.dataset.tipo.toLowerCase() === tipo.toLowerCase();
            const mostrar = (tipo === 'todos' || esTipo);
            u.style.display = mostrar ? 'block' : 'none';
            if (mostrar) visibles++;
          });

          // Actualizar título dinámicamente
          const tipoMayus = tipo.charAt(0).toUpperCase() + tipo.slice(1).toLowerCase();
          if (visibles === 0) {
            if (tipo === 'todos') {
              tituloExplora.textContent = "No hay usuarios en el sistema.";
            } else {
              tituloExplora.textContent = "No hay usuarios del tipo " + tipoMayus + ".";
            }
          } else {
            if (tipo === 'todos') {
              tituloExplora.textContent = "Explora entre " + visibles + " Usuarios :";
            } else {
              tituloExplora.textContent = "Explora entre " + visibles + " Usuarios " + tipoMayus + "s :";
            }
          }

          // Marcar pestaña activa
          document.querySelectorAll('.nav-link').forEach(b => b.classList.remove('active'));
          const boton = [...document.querySelectorAll('.nav-link')]
            .find(b => b.textContent.toLowerCase().includes(tipo.toLowerCase()));
          if (boton) boton.classList.add('active');
        }
      </script>
    </body>
</html>
