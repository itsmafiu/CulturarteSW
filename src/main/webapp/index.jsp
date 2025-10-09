<%@page import="java.net.URLEncoder"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="Logica.DataPropuesta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Culturarte</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <%
    
    List<DataPropuesta> DPcreadas = (List) request.getSession().getAttribute("propuestasCreadas");
    List<DataPropuesta> DPef = (List) request.getSession().getAttribute("propuestasEnFinanciacion");
    List<DataPropuesta> DPf = (List) request.getSession().getAttribute("propuestasFinanciadas");
    List<DataPropuesta> DPnf = (List) request.getSession().getAttribute("propuestasNoFinanciadas");
    List<DataPropuesta> DPca = (List) request.getSession().getAttribute("propuestasCanceladas");

    %>
    
    <h3>Bienvenido a Culturarte</h3>
    <p>Acá pondría todo lo que es recomendaciones uwu.</p>
    
    <div class="container-fluid bg-black"></div>

   <ul class="nav nav-tabs" id="myTab" role="tablist">
    <li class="nav-item" role="presentation">
      <button class="nav-link bg-dark-subtle rounded" id="creadas-tab" data-bs-toggle="tab" data-bs-target="#creadas" type="button" role="tab">Propuestas Creadas</button>
    </li>
    <li class="nav-item" role="presentation">
      <button class="nav-link bg-dark-subtle rounded" id="financiacion-tab" data-bs-toggle="tab" data-bs-target="#financiacion" type="button" role="tab">Propuestas en Financiación</button>
    </li>
    <li class="nav-item" role="presentation">
      <button class="nav-link bg-dark-subtle rounded" id="financiadas-tab" data-bs-toggle="tab" data-bs-target="#financiadas" type="button" role="tab">Propuestas Financiadas</button>
    </li>
    <li class="nav-item" role="presentation">
      <button class="nav-link bg-dark-subtle rounded" id="noFinanciadas-tab" data-bs-toggle="tab" data-bs-target="#noFinanciadas" type="button" role="tab">Propuestas NO Financiadas</button>
    </li>
    <li class="nav-item" role="presentation">
      <button class="nav-link bg-dark-subtle rounded" id="canceladas-tab" data-bs-toggle="tab" data-bs-target="#canceladas" type="button" role="tab">Propuestas Canceladas</button>
    </li>
  </ul>
    
    <div class="tab-content min-vh-100 w-100 d-flex align-items-center justify-content-center bg-dark-subtle" id="myTabContent">
        <div class="tab-pane fade" id="creadas" role="tabpanel">
            
            <div class="container my-4">
            <div class="row row-cols-1 row-cols-md-3 g-4">
            
            <%
        for(DataPropuesta p : DPcreadas){
        int colabs = p.getMisAportes().size();
        long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaARealizar()), 0);
        int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);
        String imagen = "";
        if (p.getImagen().isBlank()) {
            imagen = "fotos/default.jpg";
        }else{
            imagen = p.getImagen();
        }
        %>
          <div class="col-md-6 col-lg-4">
          <div class="card h-100">
              <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(p.getTitulo(), "UTF-8") %>">
              <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center">
              </a>
              <div class="card-body" style="max-height: 300px; overflow: hidden;">
              <h5 class="card-title text-center"><%=p.getTitulo()%></h5>
              <p class="card-text"  style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                  <%=p.getDescripcion()%></p>
              <p><b>Recaudado:</b> <%=p.getAlcanzada()%></p>
              <p><%= diasRestantes %> días restantes · <%=colabs%> colaboradores</p>
              <div class="progress mb-3 position-relative" style="height: 20px;">
                <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje %>%;">
                </div>
                <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                    <%= porcentaje %>%
                </span>
            </div>
            </div>
          </div>
        </div>
        <%}%>
        </div>
        </div>
  </div>
   <div class="tab-pane fade" id="financiacion" role="tabpanel">
      <div class="container my-4">
            <div class="row row-cols-1 row-cols-md-3 g-4">
            
            <%
        for(DataPropuesta p : DPef){
        int colabs = p.getMisAportes().size();
        long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaARealizar()), 0);
        int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);
        String imagen = "";
        if (p.getImagen().isBlank()) {
            imagen = "fotos/default.jpg";
        }else{
            imagen = p.getImagen();
        }
        %>
          <div class="col-md-6 col-lg-4">
          <div class="card h-100">
              <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(p.getTitulo(), "UTF-8") %>">
              <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center">
              </a>
              <div class="card-body" style="max-height: 300px; overflow: hidden;">
              <h5 class="card-title text-center"><%=p.getTitulo()%></h5>
              <p class="card-text"  style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                  <%=p.getDescripcion()%></p>
              <p><b>Recaudado:</b> <%=p.getAlcanzada()%></p>
              <p><%= diasRestantes %> días restantes · <%=colabs%> colaboradores</p>
              <div class="progress mb-3 position-relative" style="height: 20px;">
                <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje %>%;">
                </div>
                <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                    <%= porcentaje %>%
                </span>
            </div>
            </div>
          </div>
        </div>
        <%}%>
        </div>
        </div>
  </div>

  <div class="tab-pane fade" id="financiadas" role="tabpanel">
       <div class="container my-4">
            <div class="row row-cols-1 row-cols-md-3 g-4">
            
            <%
        for(DataPropuesta p : DPf){
        int colabs = p.getMisAportes().size();
        long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaARealizar()), 0);
        int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);
        String imagen = "";
        if (p.getImagen().isBlank()) {
            imagen = "fotos/default.jpg";
        }else{
            imagen = p.getImagen();
        }
        %>
          <div class="col-md-6 col-lg-4">
          <div class="card h-100">
              <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(p.getTitulo(), "UTF-8") %>">
              <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center">
              </a>
              <div class="card-body" style="max-height: 300px; overflow: hidden;">
              <h5 class="card-title text-center"><%=p.getTitulo()%></h5>
              <p class="card-text"  style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                  <%=p.getDescripcion()%></p>
              <p><b>Recaudado:</b> <%=p.getAlcanzada()%></p>
              <p><%= diasRestantes %> días restantes · <%=colabs%> colaboradores</p>
              <div class="progress mb-3 position-relative" style="height: 20px;">
                <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje %>%;">
                </div>
                <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                    <%= porcentaje %>%
                </span>
            </div>
            </div>
          </div>
        </div>
        <%}%>
        </div>
        </div>
  </div>

  <div class="tab-pane fade" id="noFinanciadas" role="tabpanel">
       <div class="container my-4">
            <div class="row row-cols-1 row-cols-md-3 g-4">
            
            <%
        for(DataPropuesta p : DPnf){
        int colabs = p.getMisAportes().size();
        long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaARealizar()), 0);
        int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);
        String imagen = "";
        if (p.getImagen().isBlank()) {
            imagen = "fotos/default.jpg";
        }else{
            imagen = p.getImagen();
        }
        %>
          <div class="col-md-6 col-lg-4">
          <div class="card h-100">
              <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(p.getTitulo(), "UTF-8") %>">
              <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center">
              </a>
              <div class="card-body" style="max-height: 300px; overflow: hidden;">
              <h5 class="card-title text-center"><%=p.getTitulo()%></h5>
              <p class="card-text"  style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                  <%=p.getDescripcion()%></p>
              <p><b>Recaudado:</b> <%=p.getAlcanzada()%></p>
              <p><%= diasRestantes %> días restantes · <%=colabs%> colaboradores</p>
              <div class="progress mb-3 position-relative" style="height: 20px;">
                <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje %>%;">
                </div>
                <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                    <%= porcentaje %>%
                </span>
            </div>
            </div>
          </div>
        </div>
        <%}%>
        </div>
        </div>
  </div>

  <div class="tab-pane fade" id="canceladas" role="tabpanel">
       <div class="container my-4">
            <div class="row row-cols-1 row-cols-md-3 g-4">
            
            <%
        for(DataPropuesta p : DPca){
        int colabs = p.getMisAportes().size();
        long diasRestantes = Math.max(ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaARealizar()), 0);
        int porcentaje = (int) Math.min((p.getAlcanzada() / p.getNecesaria()) * 100, 100);
        String imagen = "";
        if (p.getImagen().isBlank()) {
            imagen = "fotos/default.jpg";
        }else{
            imagen = p.getImagen();
        }
        %>
          <div class="col-md-6 col-lg-4">
          <div class="card h-100">
              <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(p.getTitulo(), "UTF-8") %>">
              <img src="<%=imagen%>" alt="Foto de la propuesta" style="width: 100%; height: 300px; align-items: center">
              </a>
              <div class="card-body" style="max-height: 300px; overflow: hidden;">
              <h5 class="card-title text-center"><%=p.getTitulo()%></h5>
              <p class="card-text"  style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                  <%=p.getDescripcion()%></p>
              <p><b>Recaudado:</b> <%=p.getAlcanzada()%></p>
              <p><%= diasRestantes %> días restantes · <%=colabs%> colaboradores</p>
              <div class="progress mb-3 position-relative" style="height: 20px;">
                <div class="progress-bar bg-success" role="progressbar" style="width: <%= porcentaje %>%;">
                </div>
                <span class="position-absolute top-50 start-50 translate-middle fw-semibold text-dark">
                    <%= porcentaje %>%
                </span>
            </div>
            </div>
          </div>
        </div>
        <%}%>
        </div>
        </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
