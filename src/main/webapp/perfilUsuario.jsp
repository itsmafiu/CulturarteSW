<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Logica.DataAporte"%>
<%@page import="Logica.DataUsuario"%>
<%@page import="Logica.DataPropuesta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    DataUsuario usuario = (DataUsuario) request.getSession().getAttribute("ConsultaPerfil");
    boolean esMiPerfil = false;
    boolean esProponente = false;
    if(request.getSession().getAttribute("nick")!=null){
        esMiPerfil = request.getSession().getAttribute("nick").equals(usuario.getNickname());
    }
    if(usuario.getTipo().equals("Proponente") || usuario.getTipo().equals("propo")){
        esProponente = true;
    }
    

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
    <div class="d-flex align-items-center">
        <img src="SvMostrarFoto?path=<%= usuario.getImagen() %>" alt="Foto de Perfil" class="card-img-top" style="max-height:400px; max-width:400px;">
        <div class="p-2">                    </div>
        <div>
            <h3><%=usuario.getTipo()%></h3>
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
     </div>

    
    <!-- Seguidores -->
    
     <!-- 🔽 BOTÓN para mostrar/ocultar Seguidores -->
    <button class="btn btn-outline-primary mb-2" type="button"
            data-bs-toggle="collapse" data-bs-target="#collapseSeguidores"
            aria-expanded="false" aria-controls="collapseSeguidores">
        Mostrar/Ocultar Seguidores
    </button>
    <div class="collapse mb-4" id="collapseSeguidores">
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
     <!-- 🔽 BOTÓN para mostrar/ocultar Seguidos -->
    <button class="btn btn-outline-secondary mb-2" type="button"
            data-bs-toggle="collapse" data-bs-target="#collapseSeguidos"
            aria-expanded="false" aria-controls="collapseSeguidos">
        Mostrar/Ocultar Seguidos
    </button>

    <div class="collapse mb-4" id="collapseSeguidos">
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
         <!-- 🔽 BOTÓN para mostrar/ocultar Propuestas Ingresadas -->
    <button class="btn btn-outline-secondary mb-2" type="button"
            data-bs-toggle="collapse" data-bs-target="#collapsePropuestas"
            aria-expanded="false" aria-controls="collapsePropuestas">
        Mostrar/Ocultar Propuestas 
    </button>
    
    <div class="collapse mb-4" id="collapsePropuestas">
    
        <% if(esProponente){ %>
            <h3>Propuestas publicadas por <%=usuario.getNickname()%></h3>
        <% }else { %>
        <h3>Colaboraciones de <%=usuario.getNickname()%></h3>
        <% } %>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <%
                List<DataPropuesta> propuestas = usuario.getMisPropuestas();
                if (propuestas != null && !propuestas.isEmpty()) {
                    for (DataPropuesta prop : propuestas) {
                    String imagen = "";
                    if (prop.getImagen() == null || prop.getImagen().isBlank()) {
                        imagen = "fotos/default.jpg";
                    }else{
                        imagen = prop.getImagen();
                    }
            %>
                        <div class="col">
                        <div class="card h-100">
                            <%-- <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(prop.getTitulo(), "UTF-8") %>"> --%>
                            <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 414px; height: 300px; align-items: center">
                          <div class="card-body">
                            <h5 class="card-title text-center"><%=prop.getTitulo()%></h5>
                            <p class="card-text"><%=prop.getDescripcion()%></p>
                            <p><b>Recaudado:</b> <%=prop.getAlcanzada()%></p>
                            <p> Estado: <%=prop.getEstadoActual().getEstado()%></p>
                            <p>26 días restantes · 350 colaboradores <b>AUN SIN HACER!!!</b></p>
                            <% if (!esProponente && esMiPerfil){ 
                                DataAporte aporte = usuario.getListaAporte().get(prop.getTitulo()); 
                            %>
                            <p> Aporte: <%=aporte.get$aporte() %></p>
                            <p> Fecha del Aporte: <%=aporte.getFechaHora().format(DateTimeFormatter.ISO_DATE) %></p>
                            
                            <% } %>
                            
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
        <% if(esMiPerfil && esProponente){ %>
        
         <!-- 🔽 BOTÓN para mostrar/ocultar Propuestas Ingresadas -->
    <button class="btn btn-outline-secondary mb-2" type="button"
            data-bs-toggle="collapse" data-bs-target="#collapsePropuestasIngresadas"
            aria-expanded="false" aria-controls="collapsePropuestasIngresadas">
        Mostrar/Ocultar Propuestas Ingresadas
    </button>
    
    <div class="collapse mb-4" id="collapsePropuestasIngresadas">
        <h3>Propuestas en Estado Ingresadas de <%=usuario.getNickname()%></h3>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <%
                List<DataPropuesta> propuestasIngresadas = usuario.getMisPropuestasIngresadas();
                if (propuestasIngresadas != null && !propuestasIngresadas.isEmpty()) {
                    for (DataPropuesta prop : propuestasIngresadas) {
                    String imagen = "";
                    if (prop.getImagen() == null || prop.getImagen().isBlank()) {
                        imagen = "fotos/default.jpg";
                    }else{
                        imagen = prop.getImagen();
                    }
            %>
                        <div class="col">
                        <div class="card h-100">
                            <%-- <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(prop.getTitulo(), "UTF-8") %>"> --%>
                            <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 414px; height: 300px; align-items: center">
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
                    <p>No tienes propuestas en Estado Ingresado.</p>
            <% } %>
        </div>
    </div>
        <% } %>
        

</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
