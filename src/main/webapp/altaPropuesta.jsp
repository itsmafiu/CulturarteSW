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
            String hoy = LocalDate.now().toString();
        %>
        
        <h1>Alta Propuesta</h1>
        <form action="SvAltaProp" method="POST" enctype="multipart/form-data">
            <p>
                <label>Proponente: (el que inicio sesion) </label> <input disabled="" type="text" name="proponente">
            </p>
            <p>
                <label>Categoria: (aca tiene que ser seleccion) </label> <input disabled="" type="text" name="categoria">
            </p>
            <p>
                <label>Titulo: </label> <input type="text" name="titulo">
            </p>
            <p style="display: flex; align-items: flex-start; gap: 5px;">
                <label>Descripcion: </label>
                <textarea name="descripcion" rows="6" cols="50" style="resize: none"></textarea>
            </p>
            <p>
                <label>Lugar de realizacion: </label> <input type="text" name="lugar">
            </p>
            <p>
                <label>Fecha Prevista: </label> <input type="date" name="fecha" min="<%=hoy%>">
            </p>
            <p>
                <label>Monto x entrada: </label> <input type="number" name="montoEntrada" min="0">
            </p>
            <p>
                <label>Monto necesario: </label> <input type="number" name="montoNecesario" min="0">
            </p>
            <p>
                <label>Imagen: </label> <input type="file" name="foto" accept="image/*">
            </p>
            <div class="mb-3">
                <label class="form-label">Retorno:</label><br>
                
                <div class="ms-3">
                    
                    <div class="form-check form-check-inline">
                        <label class="form-check-label">Entradas gratis</label>
                        <input class="form-check-input" type="radio" name="retorno" value="Entradas gratis">
                    </div>
                    
                    <div class="form-check form-check-inline">
                        <label class="form-check-label">% de las ventas</label>
                        <input class="form-check-input" type="radio" name="retorno" value="%Ventas">
                    </div>
                
                    <div class="form-check form-check-inline">
                        <label class="form-check-label">Ambos</label>
                        <input class="form-check-input" type="radio" name="retorno" value="Ambos">
                    </div>
                    
                </div>
                
            </div>
                
            <div class="ms-1">
                <button type="submit">Enviar</button>
            </div>
            
        </form>
        
        <h1>Ver Propuestas</h1>
        <form action="SvConsultaPropuesta" method="GET">
            <div class="ms-1">
                <button type ="submit"> Mostrar Usuarios </button>
            </div>
        </form>
        
    </body>
</html>
