<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio Sesión</title>
            <!-- Latest compiled and minified CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            input[type=text]{
                width: 50%;
            }
            input[type=password]{
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
            <div class="mt-4 p-5 bg-info text-bg-color rounded"> <%--Margin-Top-4(/5) | Padding(espacio interno)-5 | Background-(color) | text-background-color(se adapta solo) | borde redondeado--%>
                <div class="row">
                    <div class="col-md-8">
                        <h1>Inicio Sesión</h1>
                        <form action="inicioSesion" class="needs-validation" method="POST">
                            <div class="form-group">
                                <label for="usuario">Nick/Email: </label>
                                <input type="text" class="form-control form-control-lg" id="usuario" name="usuario" required>
                                <div class="invalid-feedback">Ingrese un nick o mail.</div>
                            </div>
                            <div class="form-group">
                                <label for="password">Contraseña: </label> 
                                <input type="password" class="form-control form-control-lg" id="password" name="password" required>
                                <div class="invalid-feedback">Ingrese una contraseña.</div>
                            </div>
                            <br>
                            <div class="form-group">
                                <button type="submit" id="entrar" class="btn btn-primary">Enviar</button>
                                <c:if test="${not empty error}">
                                    <label for="entrar" style="color: red">${error}</p>
                                </c:if>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-4">
                        <div id="datosProponente" class="mt-4 p-5 bg-secondary text-bg-color rounded">
                            <h3>¿No tienes usuario?</h3>
                            <h4>Registrate aquí!</h4>
                            <form action="altaUsuario" method="GET">
                                <button type="submit" class="btn btn-success">Registrarse</button>
                            </form>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
