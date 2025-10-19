<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="Logica.DataCategoria"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Culturarte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/jstree@3.3.15/dist/themes/default/style.min.css" />
    </head>
    <body>
        
        <%@ include file="header.jsp" %>
        
        <% 
        
        String hoy = LocalDate.now().plusDays(1).toString();       
                
        List<DataCategoria> datosCat = (List) request.getSession().getAttribute("DtC");
        
        JSONArray jsonArray = new JSONArray();
        if (datosCat != null){
            for (DataCategoria cat : datosCat) {
                JSONObject obj = new JSONObject();
                obj.put("id", cat.getNombre());
                obj.put("text", cat.getNombre());
                if (cat.getNombreCatPadre() == null || cat.getNombreCatPadre().isBlank())
                    obj.put("parent", "#");
                else
                    obj.put("parent", cat.getNombreCatPadre());
                jsonArray.put(obj);
            }
        %>
        
        <div class='d-flex justify-content-center align-items-start min-vh-100 pt-5'>
        <form id='miFormulario' class="border p-4 rounded shadow bg-light" style="width: 1000px;" action="SvAltaProp" method="POST" enctype="multipart/form-data">
            <div class="row">
            
           <div class="col-12 col-md-6">
            <p>
                <input type="text" name="titulo" id="titulo" placeholder="Titulo" required="true">
                <span id="errorTitulo" style="color: red; display: none;">El título ya existe.</span>
            </p>
            <p>
                <input type="text" name="categoriaSeleccionada" id="categoriaSeleccionada" placeholder="Seleccione categoria" required="true" readonly="true">
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
            <div class='col mt-4 mt-md-0' style="max-height: 874px; overflow-y: auto; border: 1px solid #ccc;">
                    <div id="arbolCategorias"></div>

            
            <!--String categoria = request.getParameter("categoriaSeleccionada");   Asi se llega desde el Servlet-->

           <% } %>
        
            </div>
            
            </div>
           </form>
            </div>
        
        <!--<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>-->
        
        <%@include file="footer.jsp" %>
        
        <script src="https://cdn.jsdelivr.net/npm/jstree@3.3.15/dist/jstree.min.js"></script>
        <script src="js/validacionAltaProp.js"></script>  
        
        <script>
            $('#miFormulario').on('submit', function(e) {
             if ($('#categoriaSeleccionada').val() === "") {
                 e.preventDefault(); // Esto evita que se envíe el formulario
                 alert("Por favor, selecciona una categoría");
             }
         });
        </script>
        <script>
            // Pasamos el JSON desde JSP al script JS
            const datosCategorias = <%= jsonArray.toString() %>;

            $(function () {
                $('#arbolCategorias').jstree({
                    'core': {
                        'data': datosCategorias,
                        'multiple': false
                    },
                    'plugins': ['wholerow']
                })

            //Terminaste de crear el jstree desplega todos los nodos
                    .on('ready.jstree', function() {
                $(this).jstree('open_all');
            });

            //Seleccionaste un nodo --mostralo--
            $('#arbolCategorias').on('select_node.jstree', function(e, data) {
                const texto = data.node.text;
                const id = data.node.id;
                if (id === "Categoria") {
                    e.preventDefault();
                    $('#arbolCategorias').jstree(true).deselect_node(id);
                    return false;
                }
                $('#categoriaSeleccionada').val(texto);          // se ve el valor
                $('#categoriaSeleccionada').attr('placeholder', texto); // también cambia placeholder
            });
        });
        </script>
    </body>
</html>