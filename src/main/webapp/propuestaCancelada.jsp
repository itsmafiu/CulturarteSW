<%-- 
    Document   : colaRegistrada
    Created on : 9 oct. 2025, 4:39:03 p. m.
    Author     : Luiano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="col text-center mt-5">
            <div class="text-center m-4 pb-5">
                <h1>Propuesta Cancelada</h1>
            </div>
            <div class="text-center mt-4 p-2">
                <h5 class="text-secondary">Lamentamos que hayas cancelado esta propuesta.</h5>
            </div>
            <div class="text-center btn bg-secondary-subtle rounded">
                <a href="SvCargaProp" class="text-decoration-none text-center">
                    Seguir explorando
                </a>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
