<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro Usuario</title>
            <!-- Latest compiled and minified CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            input[type=text]{
                width: 100%;
            }
            input[type=date]{
                width: 50%;
            }
            div{
                margin: auto;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        
        <div class="container mt-4">
            <div class="mt-4 p-5 bg-info text-bg-color rounded">
                <h1>Registrarse</h1>
                <form action="altaUsuario" class="needs-validation" method="POST">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="nick">Nick: </label>
                                <input type="text" class="form-control form-control-lg" id="nick" name="nick" required>
                                <div class="invalid-feedback">Ingrese un nick.</div>
                            </div>
                            <div class="form-group">
                                <label for="correo">Correo: </label> 
                                <input type="text" class="form-control form-control-lg" id="correo" name="correo" required>
                                <div class="invalid-feedback">Ingrese un correo.</div>
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre: </label> 
                                <input type="text" class="form-control" id="nombre" name="nombre" required>
                                <div class="invalid-feedback">Ingrese un nombre.</div>
                            </div>
                            <div class="form-group">
                                <label for="apellido">Apellido: </label> 
                                <input type="text" class="form-control" id="apellido" name="apellido" required>
                                <div class="invalid-feedback">Ingrese un apellido.</div>
                            </div>
                            <div class="form-group">
                                <label for="fecNac">Fecha Nacimiento: </label> 
                                <input type="date" class="form-control" id="fecNac" name="fecNac" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Contraseña: </label> 
                                <input type="password" class="form-control form-control-lg" id="password" name="password" required>
                                <div class="invalid-feedback">Ingrese una contraseña.</div>
                            </div>
                            <div class="form-group">
                                <label for="confirmacion:">Confirmación Contraseña: </label> 
                                <input type="password" class="form-control form-control-lg" id="confirmacion" name="confirmacion" required>
                                <div class="invalid-feedback">Ingrese la misma contraseña.</div>
                            </div>
                            <p>Elija el tipo de Usuario:<br>
                                <input type="radio" id="prop" name="tipoUsuario" value="Proponente" checked> <label for="prop">Proponente</label>
                                <input type="radio" id="cola" name="tipoUsuario" value="Colaborador"> <label for="cola">Colaborador</label>
                            </p>
                        </div>
                        <div class="col">
                            <div id="datosProponente" class="collapse mt-4 p-5 bg-secondary text-bg-color rounded">
                                <div class="form-group">
                                    <label for="direccion">Dirección: </label>
                                    <input type="text" class="form-control" id="direccion" name="direccion">
                                    <div class="invalid-feedback">Ingrese una dirección.</div>
                                </div>
                                <div class="form-group">
                                    <label for="bio">Biografía: </label> 
                                    <input type="text" class="form-control" id="bio" name="bio" placeholder="(Opcional)">
                                </div>
                                <div class="form-group">
                                    <label for="sitioWeb">Sitio Web: </label> 
                                    <input type="text" class="form-control" id="sitioWeb" name="sitioWeb" placeholder="(Opcional)">
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <c:if test="${not empty error}">
                        <p style="color: red">${error}</p>
                    </c:if>
                </form>
            </div>
        </div>
            <%--
//            <h1>Ver lista de Usuarios</h1>
//            <p>Para ver los datos de los usuarios cargados haga click en el botón</p>
//            <form action="altaUsuario" method="GET">
//                <button type="submit"> Mostrar usuarios </button>
//            </form>
//        </div>
            --%>
            <!-- Latest compiled JavaScript -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
            <script>
            const prop = document.getElementById('prop');
            const cola = document.getElementById('cola');
            const collapse = new bootstrap.Collapse('#datosProponente', {toggle: true});
            
            prop.addEventListener('change', () => {
                if(prop.checked){
                    collapse.show();
                }
            });
            
            cola.addEventListener('change', () => {
                if(cola.checked){
                    collapse.hide();
                }
            });
        </script>
    </body>
</html>
