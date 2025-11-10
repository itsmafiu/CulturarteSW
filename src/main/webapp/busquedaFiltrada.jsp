<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="WebServices.DataPropuesta"%>
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

            List<DataPropuesta> DPF = (List) request.getSession().getAttribute("DPF");
            int tamanio = DPF.size();
            String query = (String) request.getSession().getAttribute("query");
            String orden = (String) request.getSession().getAttribute("orden");
            String estado = (String) request.getSession().getAttribute("estado");
        %>

        <div class="container my-4">
    <div class="row align-items-center justify-content-between gy-3">

        <!-- Título -->
        <div class="col-12 col-md-6">
            <h2 class="mb-0">
                Resultados para "<%= query %>" : <%= tamanio %>
            </h2>
        </div>

        <!-- Controles de orden y filtro -->
        <div class="col-12 col-md-6 d-flex flex-wrap align-items-center justify-content-md-end gap-3">

            <!-- Ordenar -->
            <div class="d-flex align-items-center">
                <p class="mb-0 me-2 fw-semibold">Ordenar:</p>
                <div class="dropdown">
                    <button 
                        class="btn btn-outline-secondary dropdown-toggle" 
                        type="button" 
                        id="dropdownOrdenar" 
                        data-bs-toggle="dropdown" 
                        aria-expanded="false">
                        Seleccionar criterio
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownOrdenar">
                        <li>
                            <form action="SvBuscador" method="POST" class="m-0">
                                <input type="hidden" name="query" value="<%= query %>">
                                <input type="hidden" name="orden" value="az">
                                <button type="submit" class="dropdown-item">Alfabéticamente (A-Z)</button>
                            </form>
                        </li>
                        <li>
                            <form action="SvBuscador" method="POST" class="m-0">
                                <input type="hidden" name="query" value="<%= query %>">
                                <input type="hidden" name="orden" value="fecha_desc">
                                <button type="submit" class="dropdown-item">Fecha de creación (Desc)</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- Filtrar -->
            <div class="d-flex align-items-center">
                <p class="mb-0 me-2 fw-semibold">Filtrar:</p>
                <div class="dropdown">
                    <button 
                        class="btn btn-outline-secondary dropdown-toggle" 
                        type="button" 
                        id="dropdownEstados" 
                        data-bs-toggle="dropdown" 
                        aria-expanded="false">
                        Seleccionar estado
                    </button>

                    <%
                        String estadoSesion = (String) request.getSession().getAttribute("estado");
                        String[][] estados = {
                            {"PUB", "Publicadas"},
                            {"ENF", "En financiación"},
                            {"NOF", "No financiadas"},
                            {"CAN", "Canceladas"},
                            {"FIN", "Finalizadas"}
                        };
                    %>

                    <ul class="dropdown-menu" aria-labelledby="dropdownEstados">
                        <% for (int i = 0; i < estados.length; i++) {
                            String value = estados[i][0];
                            String label = estados[i][1];
                        %>
                        <li>
                            <form action="SvBuscador" method="POST" class="m-0">
                                <input type="hidden" name="query" value="<%= query %>">
                                <input type="hidden" name="orden" value="<%= orden %>">
                                <input type="hidden" name="estado" value="<%= value %>">
                                <button type="submit" class="dropdown-item"><%= label %></button>
                            </form>
                        </li>
                        <% } %>
                    </ul>
                </div>
            </div>

        </div>
    </div>
</div>

            <p class="text-center m-3 fw-bold text-muted">
                Propuestas 
                <% if (estado != null) {
                        switch (estado) {
                            case "PUB":
                                out.print("publicadas");
                                break;
                            case "ENF":
                                out.print("en financiación");
                                break;
                            case "NOF":
                                out.print("no financiadas");
                                break;
                            case "CAN":
                                out.print("canceladas");
                                break;
                            case "FIN":
                                out.print("financiadas");
                                break;
                        }
                    }

                    if (orden != null) {
                        out.print(" ");
                        switch (orden) {
                            case "az":
                                out.print("ordenadas alfabéticamente");
                                break;
                            case "fecha_desc":
                                out.print("ordenadas por fecha de creación");
                                break;
                        }
                    }
                %>
            </p>



            <div class="container my-4">
                <div class="row row-cols-1 row-cols-md-3 g-4">

                    <%
                        for (DataPropuesta p : DPF) {
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

                    <div class="col-md-6 col-lg-4">
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

<!--            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>-->
            <%@include file="footer.jsp" %>
            
    </body>
</html>
