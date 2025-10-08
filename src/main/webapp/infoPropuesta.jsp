<%-- 
    Document   : infoPropuesta
    Created on : 8 oct 2025, 18:32:00
    Author     : nahud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Culturarte</title>
    </head>
    <body>
         <div class="col">
          <div class="card h-100">
              <img src="SvMostrarFoto?path=<--%= p.getImagen() %-->" alt="Foto de la propuesta" width="300" style="max-height: 202px">
            <div class="card-body">
              <h5 class="card-title text-center"><--%=p.getTitulo()%--></h5>
              <p class="card-text"><--%=p.getDescripcion()%--></p>
              <p><b>Recaudado:</b> <--%=p.getAlcanzada()%--></p>
              <p>26 días restantes · 350 colaboradores <b>AUN SIN HACER!!!</b></p>
              <div class="progress">
                <div class="progress-bar bg-success" role="progressbar" style="width: 100%">1000%</div>
              </div>
            </div>
          </div>
        </div>
    </body>
</html>
