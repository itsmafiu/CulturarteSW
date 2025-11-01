<%-- 
    Document   : mostrarPropuestas
    Created on : 6 oct 2025, 13:18:15
    Author     : nahud
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="WebServices.DataPropuesta"%>
<%@page import="WebServices.Propuesta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Culturarte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <style>
            /* Mantener fijo el panel lateral en pantallas medianas o grandes */
            @media (min-width: 768px) {
                #filtroCategorias {
                    position: sticky;
                    top: 1rem;
                }
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container-fluid mt-3">
            <div class="row">

                <!--  Panel lateral --> 

                <div class="col-12 col-md-3 mb-3">
                    <div class="p-3 border rounded bg-light" style="max-height: 800px; overflow-y: auto;">
                        <h5 class="mb-3">Filtrar por Categoría</h5>

                        <div id="filtroCategorias">
                            <div class="form-check">
                                <input class="form-check-input categoria-check" type="checkbox" id="cat-todas" value="todas" checked>
                                <label class="form-check-label" for="cat-todas">Todas</label>
                            </div>

                            <% List<String> categorias = (List) request.getSession().getAttribute("categorias");
                  for (String cat : categorias) {%> 
                            <div class="form-check">
                                <input class="form-check-input categoria-check" type="checkbox" id="cat-<%=cat%>" value="<%=cat%>">
                                <label class="form-check-label" for="cat-<%=cat%>"><%=cat%></label>
                            </div>
                            <% } %>
                        </div>
                    </div>
                </div>

                <!--  Zona de tarjetas --> 
                <div class="col-12 col-md-9">
                    <%
                        List<DataPropuesta> DP = (List) request.getSession().getAttribute("DP");
                        int tamanio = DP.size();
                    %>  

                    <div class="container my-4">
                        <% if (tamanio == 0) { %>
                        <h2 id = "tituloExplora">No hay propuestas en el Sistema</h2>
                        <%} else {%>
                        <h2 id = "tituloExplora">Explora entre <%=tamanio%> Propuestas :</h2>
                        <% } %>
                    </div>
                    <div class="row row-cols-1 row-cols-md-3 g-4">

                        <%
                            for (DataPropuesta p : DP) {
                                int colabs = p.getMisAportes().size();
                                long diasRestantes;

                                java.time.LocalDate fechaPubli = java.time.LocalDate.parse(p.getFechaPubliStr());
                                java.time.LocalDateTime fechaLimit = java.time.LocalDateTime.parse(p.getFechaLimitStr());

                                if (fechaLimit.isAfter(fechaPubli.atStartOfDay())) {
                                    diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), fechaPubli), 0);
                                } else {
                                 diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDateTime.now(), fechaLimit), 0);
                                }
                                int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);
                                String imagen = "";
                                if (p.getImagen().isBlank()) {
                                    imagen = "fotos/default.jpg";
                                } else {
                                    imagen = p.getImagen();
                                }
                        %>
                        <div class="col-md-6 col-lg-4 propuesta-card" data-categoria ="<%=p.getCategoria()%>">
                            <div class="card h-100">
                                <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(p.getTitulo(), "UTF-8")%>">
                                    <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center">
                                </a>
                                <div class="card-body" style="max-height: 300px; overflow: hidden;">
                                    <h5 class="card-title text-center"><%=p.getTitulo()%></h5>
                                    <div class="text-center bg-secondary-subtle rounded">
                                        <a class="text-decoration-none"
                                           href="SvPerfilUsuario?nickTarjeta=<%= p.getNickProponenteDe()%>&tipoTarjeta=Proponente">
                                            by <%= p.getNickProponenteDe()%>
                                        </a>
                                    </div>
                                    <p class="card-text"  style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                                        <%=p.getDesc()%></p>
                                    <p><b>Recaudado:</b> <%=p.getAlcanzada()%></p>
                                    <p><%= diasRestantes%> días restantes · <%=colabs%> colaboradores</p>
                                    <div class="progress mb-3 position-relative" style="height: 20px;">
                                        <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje%>%;">
                                        </div>
                                        <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                                            <%= porcentaje%>%
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <% }%>
                    </div>
                </div>

            </div>
        </div>

        <%@include file="footer.jsp" %>

        <script>
            const checks = document.querySelectorAll('.categoria-check');
            const tarjetas = document.querySelectorAll('.propuesta-card');
            const tituloExplora = document.getElementById('tituloExplora');

            checks.forEach(check => {
                check.addEventListener('change', () => {
                    // Desmarcar los demás
                    checks.forEach(c => {
                        if (c !== check)
                            c.checked = false;
                    });
                    // Si se desmarca el actual, volver a marcar "todas"
                    if (!check.checked) {
                        document.getElementById('cat-todas').checked = true;
                    }

                    const seleccion = document.querySelector('.categoria-check:checked').value.toLowerCase();
                    let visibles = 0;

                    tarjetas.forEach(card => {
                        const categoria = card.getAttribute('data-categoria').toLowerCase();
                        if (seleccion === 'todas' || categoria === seleccion) {
                            card.style.display = 'block';
                            visibles++;
                        } else {
                            card.style.display = 'none';
                        }
                    });

                    if (seleccion === 'todas') {
                        tituloExplora.textContent = visibles === 0
                                ? "No hay propuestas en el sistema."
                                : "Explora entre " + visibles + " Propuestas :";
                    } else {
                        const categoriaMayus = seleccion.charAt(0).toUpperCase() + seleccion.slice(1);
                        tituloExplora.textContent = visibles === 0
                                ? "No hay propuestas de la categoría: " + categoriaMayus
                                : "Explora entre " + visibles + " Propuestas de " + categoriaMayus + " :";
                    }
                });
            });

        </script>

    </body>
</html>
