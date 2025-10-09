<%@page import="Logica.DataUsuario"%>
<%@page import="Logica.DataPropuesta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    DataUsuario usuario = (DataUsuario) request.getSession().getAttribute("ConsultaPerfil");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil de <%= usuario.getNickname() %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container mt-4">

    <!-- Datos del usuario -->
    <div class="mb-4">
        <h2>Perfil de <%= usuario.getNickname() %></h2>
        <p><b>Nombre:</b> <%= usuario.getNombre() %> <%= usuario.getApellido() %></p>
        <p><b>Email:</b> <%= usuario.getEmail() %></p>
        <% if (!usuario.getBiografia().isEmpty() && usuario.getTipo().equals("Proponente")) { %>
            <p><b>Biografía:</b> <%= usuario.getBiografia() %></p>
        <% } %>
        <% if (!usuario.getDireccion().isEmpty()) { %>
            <p><b>Dirección:</b> <%= usuario.getDireccion() %></p>
        <% } %>
        <% if (!usuario.getSitioWeb().isEmpty() && usuario.getTipo().equals("Proponente")) { %>
            <p><b>Sitio Web:</b> <a href="<%= usuario.getSitioWeb() %>" target="_blank"><%= usuario.getSitioWeb() %></a></p>
        <% } %>
    </div>

    <!-- Seguidores -->
    <div class="mb-4">
        <h3>Seguidores</h3>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <%
                List<DataUsuario> seguidores = usuario.getMeSiguen();
                if (seguidores != null && !seguidores.isEmpty()) {
                    for (DataUsuario u : seguidores) {
            %>
                        <div class="col">
                            <div class="card h-100">
                                <img src="SvMostrarFoto?path=<%= u.getImagen() %>" alt="Foto de Perfil" class="card-img-top" style="max-height:200px;">
                                <div class="card-body">
                                    <h5 class="card-title"><%= u.getNickname() %></h5>
                                    <p class="card-text"><%= u.getTipo() %></p>
                                    <a href="SvPerfilUsuario?nickTarjeta=<%= u.getNickname() %>&tipoTarjeta=<%= u.getTipo() %>" class="btn btn-primary">Ver Perfil</a>
                                </div>
                            </div>
                        </div>
            <%      }
                } else { %>
                    <p>No tiene seguidores aún.</p>
            <% } %>
        </div>
    </div>

    <!-- Usuarios que sigue -->
    <div class="mb-4">
        <h3>Seguidos</h3>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <%
                List<DataUsuario> losSigo = usuario.getLosSigo();
                if (losSigo != null && !losSigo.isEmpty()) {
                    for (DataUsuario u : losSigo) {
            %>
                        <div class="col">
                            <div class="card h-100">
                                <img src="SvMostrarFoto?path=<%= u.getImagen() %>" alt="Foto de Perfil" class="card-img-top" style="max-height:200px;">
                                <div class="card-body">
                                    <h5 class="card-title"><%= u.getNickname() %></h5>
                                    <p class="card-text"><%= u.getTipo() %></p>
                                    <a href="SvPerfilUsuario?nickTarjeta=<%= u.getNickname() %>&tipoTarjeta=<%= u.getTipo() %>" class="btn btn-primary">Ver Perfil</a>
                                </div>
                            </div>
                        </div>
            <%      }
                } else { %>
                    <p>No sigue a ningún usuario aún.</p>
            <% } %>
        </div>
    </div>

    <!-- Propuestas -->
    <div class="mb-4">
        <% if(usuario.getTipo().equals("Proponente")){ %>
            <h3>Propuestas de <%=usuario.getNickname()%></h3>
        <% }else if(usuario.getTipo().equals("Colaborador")){ %>
        <h3>Colaboraciones de <%=usuario.getNickname()%></h3>
        <% } %>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <%
                List<DataPropuesta> propuestas = usuario.getMisPropuestas();
                if (propuestas != null && !propuestas.isEmpty()) {
                    for (DataPropuesta prop : propuestas) {
            %>
                        <div class="col">
                        <div class="card h-100">
                            <img src="SvMostrarFoto?path=<%= prop.getImagen() %>" alt="Foto de la propuesta" width="300" style="max-height: 202px">
                          <div class="card-body">
                            <h5 class="card-title text-center"><%=prop.getTitulo()%></h5>
                            <p class="card-text"><%=prop.getDescripcion()%></p>
                            <p><b>Recaudado:</b> <%=prop.getAlcanzada()%></p>
                            <p> Estado: <%=prop.getEstadoActual().getEstado()%></p>
                            <p>26 días restantes · 350 colaboradores <b>AUN SIN HACER!!!</b></p>
                            <div class="progress">
                              <div class="progress-bar bg-success" role="progressbar" style="width: 100%">1000%</div>
                            </div>
                          </div>
                        </div>
                      </div>

            <%      }
                } else { %>
                    <p>No tiene propuestas activas.</p>
            <% } %>
        </div>
    </div>

</div>
</body>
</html>
