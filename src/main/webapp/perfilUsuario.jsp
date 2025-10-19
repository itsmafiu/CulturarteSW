<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.net.URLEncoder"%>
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
    
    DataUsuario usuarioIniciado = (DataUsuario) request.getSession().getAttribute("DTUsuario");
    List<DataUsuario> listaSeguidores = usuario.getMeSiguen();
    boolean loSigo = false;
    if(listaSeguidores.contains(usuarioIniciado) && !esMiPerfil){ //si el usuario iniciado está en la lista de seguidores del usuario consultado entonces sabemos que lo sigue
        loSigo = true;
    }

%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil de <%= usuario.getNickname() %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/estilosTarjetasUsuarios.css">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container mt-4">
    
    <%
    String imagenUserPerfil = "";
    if (usuario.getImagen() == null || usuario.getImagen().isBlank()) {
        imagenUserPerfil = "fotos\\default.jpg";
    }else{
        imagenUserPerfil = usuario.getImagen();
    }
    %>
        
    <!-- Datos del usuario -->
    <div class="d-flex align-items-center">
        <img src="<%= imagenUserPerfil %>" alt="Foto de Perfil" class="card-img-top mb-2 rounded-circle" style="max-height:400px; max-width:400px;">
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
            <% if(!esMiPerfil && request.getSession().getAttribute("nick")!=null){ %>
                <button class="btn btn-success mb-2" id="botonSeguir">Seguir</button>
            <% } %>
        </div>
     </div>

    
    <!-- Seguidores -->
    
     <!-- 🔽 BOTÓN para mostrar/ocultar Seguidores -->
    <button class="btn btn-outline-secondary mb-2" type="button"
            data-bs-toggle="collapse" data-bs-target="#collapseSeguidores"
            aria-expanded="false" aria-controls="collapseSeguidores">
        Mostrar/Ocultar Seguidores
    </button>
    <div class="collapse mb-4" id="collapseSeguidores">
        <h3>Seguidores</h3>
        <div class="row" id="listaSeguidores">
            <%
                List<DataUsuario> seguidores = usuario.getMeSiguen();
                if (seguidores != null && !seguidores.isEmpty()) {
                    for (DataUsuario u : seguidores) {
                    String imagenSeguidor = "";
                    if (u.getImagen() == null || u.getImagen().isBlank()) {
                        imagenSeguidor = "fotos\\default.jpg";
                    }else{
                        imagenSeguidor = u.getImagen();
                    }
            %>
          
            <div class="usuario col-md-6 col-lg-3">
                <div class="card" style="width: 18rem;">                                    
                    <a href="SvPerfilUsuario?nickTarjeta=<%= u.getNickname() %>&tipoTarjeta=<%= u.getTipo() %>" >
                        <img src="<%= imagenSeguidor%>" alt="Foto de Perfil" class="card-img-top" style="width: 100% ; height:200px; align-items: center">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title text-center"><%= u.getNickname() %></h5>
                        <p class="card-text text-center"><%= u.getTipo() %></p>
                    </div>
                </div>
            </div>
            <%      }
                } else { %>
                <p><b>No tiene seguidores aún.</b></p>
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
        <div class="row listaSeguidos">
            <%
                List<DataUsuario> losSigo = usuario.getLosSigo();
                if (losSigo != null && !losSigo.isEmpty()) {
                    for (DataUsuario u : losSigo) {
                    String imagenSeguidos = "";
                    if (u.getImagen() == null || u.getImagen().isBlank()) {
                        imagenSeguidos = "fotos\\default.jpg";
                    }else{
                        imagenSeguidos = u.getImagen();
                    }
            %>
                    
            <div class="usuario col-md-6 col-lg-3">
               <div class="card" style="width: 18rem;">
                    <a href="SvPerfilUsuario?nickTarjeta=<%= u.getNickname() %>&tipoTarjeta=<%= u.getTipo() %>" >
                        <img src="<%= imagenSeguidos %>" alt="Foto de Perfil" class="card-img-top" style="width: 100% ; height:200px; align-items: center">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title text-center"><%= u.getNickname() %></h5>
                        <p class="card-text text-center"><%= u.getTipo() %></p>
                    </div>
                </div>
            </div>
            <%      }
                } else { %>
                <p><b>No sigue a ningún usuario aún.</b></p>
            <% } %>
        </div>
    </div>
    <!-- Propuestas Favoritas -->
         <!-- 🔽 BOTÓN para mostrar/ocultar Propuestas Ingresadas -->
    <button class="btn btn-outline-secondary mb-2" type="button"
            data-bs-toggle="collapse" data-bs-target="#collapsePropuestasFav"
            aria-expanded="false" aria-controls="collapsePropuestasFav">
        Mostrar/Ocultar Propuestas Favoritas
    </button>
    
    <div class="collapse mb-4" id="collapsePropuestasFav">
    
        <h3>Propuestas favoritas de <%=usuario.getNickname()%></h3>
        <div class="container my-4">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <%
                List<DataPropuesta> propuestasFavs = usuario.getMisPropuestasFav();
                if (propuestasFavs != null && !propuestasFavs.isEmpty()) {
                    for (DataPropuesta prop : propuestasFavs) {
                    String imagenPropuestaFavs = "";
                    if (prop.getImagen() == null || prop.getImagen().isBlank()) {
                        imagenPropuestaFavs = "fotos/default.jpg";
                    }else{
                        imagenPropuestaFavs = prop.getImagen();
                    }
                    int colabs = prop.getCantidadColaboradores();
                    long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), prop.getFechaARealizar()), 0);
                    int porcentaje = (int) Math.min((prop.getAlcanzada() / prop.getNecesaria()) * 100, 100);
                    
            %>
    <div class="col-md-6 col-lg-4 d-flex">
        <div class="card h-100 d-flex flex-column justify-content-between">
            <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(prop.getTitulo(), "UTF-8") %>">
                <img src="<%=imagenPropuestaFavs%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center;">
            </a>

            <div class="card-body d-flex flex-column">
                <h5 class="card-title text-center"><%=prop.getTitulo()%></h5>

                <div class="text-center bg-secondary-subtle rounded mb-2">
                    <a class="text-decoration-none"
                       href="SvPerfilUsuario?nickTarjeta=<%= prop.getNickProponenteDe()%>&tipoTarjeta=Proponente"> by <%= prop.getNickProponenteDe()%>
                    </a>
                </div>
                
                <p class="card-text flex-grow-1"  
                   style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis;">
                    <%=prop.getDescripcion()%>
                </p>

                <p><b>Recaudado:</b> <%=prop.getAlcanzada()%></p>

                <p><%= diasRestantes%> días restantes · <%=colabs%> colaboradores</p>

                <div class="mt-auto">
                    <div class="progress mb-3 position-relative" style="height: 20px;">
                        <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje%>%;"></div>
                        <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                            <%= porcentaje%>%
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%      }
            } else { %>
    <p>No tiene propuestas favoritas.</p>
    <% } %>
        </div>
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
        <div class="container my-4">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <%
                List<DataPropuesta> propuestas = usuario.getMisPropuestas();
                if (propuestas != null && !propuestas.isEmpty()) {
                    for (DataPropuesta prop : propuestas) {
                    String imagenPropuesta = "";
                    if (prop.getImagen() == null || prop.getImagen().isBlank()) {
                        imagenPropuesta = "fotos/default.jpg";
                    }else{
                        imagenPropuesta = prop.getImagen();
                    }
                    int colabs = prop.getCantidadColaboradores();
                    long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), prop.getFechaARealizar()), 0);
                    int porcentaje = (int) Math.min((prop.getAlcanzada() / prop.getNecesaria()) * 100, 100);
                    
            %>
    <div class="col-md-6 col-lg-4 d-flex">
        <div class="card h-100 d-flex flex-column justify-content-between">
            <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(prop.getTitulo(), "UTF-8") %>">
                <img src="<%=imagenPropuesta%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center;">
            </a>

            <div class="card-body d-flex flex-column">
                <h5 class="card-title text-center"><%=prop.getTitulo()%></h5>

                <% if (!esProponente) { %>
                <div class="text-center bg-secondary-subtle rounded mb-2">
                    <a class="text-decoration-none"
                       href="SvPerfilUsuario?nickTarjeta=<%= prop.getNickProponenteDe()%>&tipoTarjeta=Proponente"> by <%= prop.getNickProponenteDe()%>
                    </a>
                </div>
                <% } %>

                <p class="card-text flex-grow-1"  
                   style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis;">
                    <%=prop.getDescripcion()%>
                </p>

                <p><b>Recaudado:</b> <%=prop.getAlcanzada()%></p>

                <% if (!esProponente && esMiPerfil) {
                    DataAporte aporte = usuario.getListaAporte().get(prop.getTitulo());
                %>
                <div class="d-flex justify-content-between align-items-center text-secondary mb-2" style="font-size: 0.95rem;">
                    <span><b>Aporte:</b> <%=aporte.get$aporte()%></span>
                    <span>📅 <%=aporte.getFechaHora().format(DateTimeFormatter.ISO_DATE)%></span>
                </div>
                <% } %>

                <p><%= diasRestantes%> días restantes · <%=colabs%> colaboradores</p>

                <div class="mt-auto">
                    <div class="progress mb-3 position-relative" style="height: 20px;">
                        <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje%>%;"></div>
                        <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                            <%= porcentaje%>%
                        </span>
                    </div>
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
    <% if (esMiPerfil && esProponente){ %>
        
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
                    int colabs = prop.getMisAportes().size();
                    long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), prop.getFechaARealizar()), 0);
                    int porcentaje = (int) Math.min((prop.getAlcanzada() / prop.getNecesaria()) * 100, 100);
                    
            %>
                     <div class="col-md-6 col-lg-4">
        <div class="card h-100">
            <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(prop.getTitulo(), "UTF-8") %>">
                <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center">
            </a>
            <div class="card-body" style="max-height: 300px; overflow: hidden;">
                <h5 class="card-title text-center"><%=prop.getTitulo()%></h5>
                    <% if(!esProponente){ %>
                    <div class="text-center bg-secondary-subtle rounded">
                        <a class="text-decoration-none"
                           href="SvPerfilUsuario?nickTarjeta=<%= prop.getNickProponenteDe()%>&tipoTarjeta=Proponente">
                            by <%= prop.getNickProponenteDe()%>
                        </a>
                    </div>
                    <% } %>
                <p class="card-text"  style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                    <%=prop.getDescripcion()%> </p>
                <p><b>Recaudado:</b> <%=prop.getAlcanzada()%></p>
              <p><%= diasRestantes %> días restantes · <%=colabs%> colaboradores</p>
                <div class="progress mb-3 position-relative" style="height: 20px;">
                    <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje %>%;">
                    </div>
                    <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                        <%= porcentaje%>%
                    </span>
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
        <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script> --%>
        <%@include file="footer.jsp" %>
        
        <script>
            $(document).ready(function() {     
                const boton = document.getElementById("botonSeguir"); 
                const loSigo = <%= loSigo%>;
                
                if(loSigo){ 
                    boton.classList.replace("btn-success", "btn-danger"); 
                    boton.textContent = "Dejar de Seguir"; 
                }else{ 
                    boton.classList.replace("btn-danger", "btn-success"); 
                    boton.textContent = "Seguir"; 
                } 
                
                let timerSeguir; 
                boton.addEventListener("click", () => { 
                    clearTimeout(timerSeguir); 
                    if(boton.textContent === "Seguir"){ 
                        boton.disabled = true;
                        boton.textContent = "Cargando...";
                        timerSeguir = setTimeout(function() {$.ajax({url: "SvPerfilUsuario", method: "GET", data: { tipoInputSeguirUsuario: "seguir", usuarioSeguidor1: "<%=request.getSession().getAttribute("nick")%>", usuarioSeguido2: "<%=usuario.getNickname()%>"}, 
                            success: function(respuesta){ 
                                if(respuesta === "exito"){ 
                                    boton.classList.replace("btn-success", "btn-danger"); 
                                    boton.textContent = "Dejar de Seguir"; 
                                    boton.disabled = false;
                                } else { 
                                    console.error("Error: No deberia llegar aqui"); 
                                    boton.disabled = false;
                                } 
                            }, error: function(){ 
                                console.error("Error fatal"); 
                            } 
                        }); 
                        }, 500); 
                    }else if(boton.textContent === "Dejar de Seguir"){ 
                        boton.disabled = true;
                        boton.textContent = "Cargando...";
                        timerSeguir = setTimeout(function() {$.ajax({url: "SvPerfilUsuario", method: "GET", data: { tipoInputSeguirUsuario: "dejarSeguir", usuarioSeguidor1: "<%=request.getSession().getAttribute("nick")%>", usuarioSeguido2: "<%=usuario.getNickname()%>"}, 
                            success: function(respuesta){ 
                                if(respuesta === "exito"){ 
                                    boton.classList.replace("btn-danger", "btn-success"); 
                                    boton.textContent = "Seguir"; 
                                    boton.disabled = false;
                                } else { 
                                    console.error("Error: No deberia llegar aqui");
                                    boton.disabled = false;
                                } 
                            }, error: function(){ 
                                console.error("Error fatal"); 
                            } 
                        }); 
                        }, 500); 
                    } 
                });
            });
        </script>
</body>
</html>
