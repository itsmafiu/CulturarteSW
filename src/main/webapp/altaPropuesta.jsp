<%@page import="java.time.LocalDate"%>
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
            String hoy = LocalDate.now().plusDays(1).toString();
        %>
        
        <c:choose>
                <c:when test="${empty nick}">
                    <h1>Debes ser proponente para utilizar esta funcion.</h1>
        </c:when>
                    <c:when test="${tipoUsuario == 'prop'}">
                        <form action="SvAltaProp" method="POST" enctype="multipart/form-data">
                            <div class='container'>
            <div class='mt-4'>
                <p>
                    <input disabled="" type="text" name="categoria" placeholder="Categoria">
                </p>
            </div>
            <p>
                <input type="text" name="titulo" id="titulo" placeholder="Titulo" required="true">
                <span id="errorTitulo" style="color: red; display: none;">El título ya existe.</span>
            </p>
            <p style="display: flex; align-items: flex-start; gap: 5px;">
                <textarea name="descripcion" rows="6" cols="50" style="resize: none" placeholder="Descripcion" required="true"></textarea>
            </p>
            <p>
                <input type="text" name="lugar" placeholder="Lugar de realizacion" required="true">
            </p>
            <p>
                <label>Fecha Prevista: </label><br><input type="date" name="fecha" min="<%=hoy%>" required="true">
            </p>
            <p>
                <input type="number" name="montoEntrada" min="0" placeholder="Monto por entrada" required="true">
            </p>
            <p>
                <input type="number" name="montoNecesario" min="0" placeholder="Monto requerido" required="true">
            </p>
            <p>
                <label>Imagen: </label> <input type="file" name="foto" accept="image/*">
            </p>
            <div class="mb-3">
                <label class="form-label">Retorno:</label><br>
                
                <div class="ms-3">
                    
                    <div class="form-check form-check-inline">
                        <label class="form-check-label">Entradas gratis</label>
                        <input class="form-check-input" type="radio" name="retorno" value="Entradas gratis" required="true">
                    </div>
                    
                    <div class="form-check form-check-inline">
                        <label class="form-check-label">% de las ventas</label>
                        <input class="form-check-input" type="radio" name="retorno" value="%Ventas" required="true">
                    </div>
                
                    <div class="form-check form-check-inline">
                        <label class="form-check-label">Ambos</label>
                        <input class="form-check-input" type="radio" name="retorno" value="Ambos" required="true">
                    </div>
                    
                </div>
                
            </div>
                
            <div class="ms-1">
                <button type="submit">Enviar</button>
            </div>
            </div>
        </form>    
                    </c:when>    
                <c:otherwise>
                     <h1>Debes ser proponente para utilizar esta funcion.</h1>
                     
                </c:otherwise>
        </c:choose>
        
                <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
                <script src="js/validacionAltaProp.js"></script>  
    </body>
</html>
