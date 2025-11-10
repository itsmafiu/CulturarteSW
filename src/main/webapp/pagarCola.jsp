<%@page import="WebServices.DataAporte"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="WebServices.DataPropuesta"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pagar Colaboración</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <%
            DataPropuesta p = (DataPropuesta) request.getSession().getAttribute("p");
            DataAporte DA = (DataAporte) request.getSession().getAttribute("DA");

            String imagen = "";

            if (p.getImagen().isBlank()) {
                imagen = "fotos/default.jpg";
            } else {
                imagen = p.getImagen();
            }


        %>
        <div class="container mt-4">
            <div class="mt-4 p-5 bg-dark-subtle text-bg-color rounded">

                <form action="SvPagarCola" class="needs-validation" method="POST">

                    <div class="row">
                        <div class="col mb-3">
                            <div class="mb-3">
                                <h1>Pagar Colaboración</h1> 
                            </div>

                            <!-- Selección de método de pago -->
                            <div class="m-2">
                                <div class="btn-group" role="group" aria-label="Método de pago">
                                    <input type="radio" class="btn-check" name="metodoPago" id="radioTarjeta" value="tarjeta" autocomplete="off" checked>
                                    <label class="btn btn-outline-primary" for="radioTarjeta">Tarjeta</label>

                                    <input type="radio" class="btn-check" name="metodoPago" id="radioTransferencia" value="transferencia" autocomplete="off">
                                    <label class="btn btn-outline-success" for="radioTransferencia">Transferencia</label>

                                    <input type="radio" class="btn-check" name="metodoPago" id="radioPaypal" value="paypal" autocomplete="off">
                                    <label class="btn btn-outline-danger" for="radioPaypal">PayPal</label>
                                </div>
                            </div>

                            <!-- Campos para Tarjeta -->
                            <div id="camposTarjeta" class="mt-3">
                                <label class="form-label d-block">Tipo de tarjeta:</label>
                                <div class="mb-3" role="group" aria-label="Tipo de tarjeta">
                                    <input type="radio" class="btn-check" name="tipoTarjeta" id="radioOca" value="OCA" autocomplete="off" checked>
                                    <label class="btn btn-outline-secondary" for="radioOca">OCA</label>

                                    <input type="radio" class="btn-check" name="tipoTarjeta" id="radioVisa" value="Visa" autocomplete="off">
                                    <label class="btn btn-outline-secondary" for="radioVisa">Visa</label>

                                    <input type="radio" class="btn-check" name="tipoTarjeta" id="radioMaster" value="MasterCard" autocomplete="off">
                                    <label class="btn btn-outline-secondary" for="radioMaster">MasterCard</label>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Número de tarjeta:</label>
                                    <input type="text" class="form-control" name="numeroTarjeta" placeholder="1234 5678 9012 3456">
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Fecha de expiración:</label>
                                        <input type="text" class="form-control" name="fechaExp" placeholder="MM/AA">
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">CVC:</label>
                                        <input type="text" class="form-control" name="cvc" placeholder="123">
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Nombre del titular:</label>
                                    <input type="text" class="form-control" name="nombreTitular" placeholder="Ej: Juan Pérez">
                                </div>
                            </div>

                            <!-- Campos para Transferencia -->
                            <div id="camposTransferencia" class="mt-3 d-none">
                                <div class="mb-3">
                                    <label class="form-label">Banco:</label>
                                    <input type="text" class="form-control" name="banco" placeholder="Nombre del banco">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Número de cuenta:</label>
                                    <input type="text" class="form-control" name="numeroCuenta" placeholder="123456 789012">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Nombre del titular:</label>
                                    <input type="text" class="form-control" name="nombreTitular" placeholder="Ej: Juan Pérez">
                                </div>
                            </div>

                            <!-- Campos para PayPal -->
                            <div id="camposPaypal" class="mt-3 d-none">
                                <div class="mb-3">
                                    <label class="form-label">Número de Cuenta PayPal:</label>
                                    <input type="email" class="form-control" name="cuentaPaypal" placeholder="1234 56 7890">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Nombre del titular:</label>
                                    <input type="text" class="form-control" name="nombreTitular" placeholder="Ej: Juan Pérez">
                                </div>
                            </div>

                            <button type="submit" class="btn btn-success mt-3">Confirmar pago</button>
                            <a href="SvCargaProp" class="btn btn-outline-secondary mt-3">Cancelar</a>
                        </div>

                        <!-- Columna lateral con la propuesta -->
                        <div class="col">
                            <div id="datosProponente" class="p-4 bg-secondary text-bg-color rounded">
                                <div class="col">
                                    <div class="card">
                                        <a href="SvInfoPropuesta?titulo=<%= URLEncoder.encode(p.getTitulo(), "UTF-8")%>">
                                            <img src="<%=imagen%>" 
                                                 alt="Imagen de la propuesta" 
                                                 class="img-fluid rounded shadow-sm mb-3"
                                                 style="height: 400px; object-fit: contain;">
                                        </a>
                                        <div class="card-body" style="max-height: 300px; overflow: hidden;">
                                            <h5 class="card-title text-center"><%= p.getTitulo()%></h5>
                                            <div class="text-center bg-secondary-subtle rounded">
                                                <a class="text-decoration-none"
                                                   href="SvPerfilUsuario?nickTarjeta=<%= p.getNickProponenteDe()%>&tipoTarjeta=Proponente">
                                                    by <%= p.getNickProponenteDe()%>
                                                </a>
                                            </div>
                                            <p class="card-text" style="display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; height: 100px;">
                                                <%= p.getDesc()%>
                                            </p>
                                            <p><b>Tu aporte:</b> <%= DA.getAporte()%></p>
                                            <p><b>Fecha de aporte:</b> <%= DA.getFechaStr()%></p>

                                        </div>
                                    </div>
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <%@include file="footer.jsp" %>
            <script>
                // Referencias a radios
                const radioTarjeta = document.getElementById('radioTarjeta');
                const radioTransferencia = document.getElementById('radioTransferencia');
                const radioPaypal = document.getElementById('radioPaypal');

                // Referencias a bloques de campos
                const camposTarjeta = document.getElementById('camposTarjeta');
                const camposTransferencia = document.getElementById('camposTransferencia');
                const camposPaypal = document.getElementById('camposPaypal');

                // Función para actualizar los campos visibles
                function actualizarCampos() {
                    camposTarjeta.classList.add('d-none');
                    camposTransferencia.classList.add('d-none');
                    camposPaypal.classList.add('d-none');

                    if (radioTarjeta.checked)
                        camposTarjeta.classList.remove('d-none');
                    else if (radioTransferencia.checked)
                        camposTransferencia.classList.remove('d-none');
                    else if (radioPaypal.checked)
                        camposPaypal.classList.remove('d-none');
                }

                // Escuchar cambios
                [radioTarjeta, radioTransferencia, radioPaypal].forEach(radio => {
                    radio.addEventListener('change', actualizarCampos);
                });
            </script>
    </body>
</html>
