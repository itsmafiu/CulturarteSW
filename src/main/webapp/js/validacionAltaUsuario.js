
$(document).ready(function() {
    let timerNick;
    let timerCorreo;
    let timerPass;

    $("#nick").on("input", function() {
        clearTimeout(timerNick);
        const nick = $(this).val().trim();

        if (nick === "") {
            $("#errorNick").hide();
            $("#correctoNick").hide();
            return;
        }
        
        timerNick = setTimeout(function() {
            $.ajax({url: "altaUsuario", method: "GET", data: { verificarNick: nick, tipoVerificarUsuario: "nick"}, dataType: "text",
                success: function(respuesta) {
                    respuesta = respuesta.trim();
                    if (respuesta === "existe") {
                        $("#errorNick").show();
                        $("#correctoNick").hide();
                    } else if(respuesta === "noexiste") {
                        $("#errorNick").hide();
                        $("#correctoNick").show();
                    } else {
                        console.warn("Respuesta no reconocida:", respuesta);
                    }
                },
                error: function(xhr, status, error) {
                    console.error("ERROR EJECUTADO");
                    console.error("Status:", status);
                    console.error("Error:", error);
                    console.error("Response:", xhr.responseText);
                    $("#errorNick").hide();
                    $("#correctoNick").hide();
                }
            });
        }, 500);
    });
    
    $("#correo").on("input", function() {
        clearTimeout(timerCorreo);
        const correo = $(this).val().trim();

        if (correo === "") {
            $("#errorCorreo").hide();
            $("#correctoCorreo").hide();
            return;
        }

        timerCorreo = setTimeout(function() {
            $.ajax({url: "altaUsuario", method: "GET", data: { verificarCorreo: correo, tipoVerificarUsuario: "correo"}, 
                success: function(respuesta) {
                    respuesta = respuesta.trim();
                    if (respuesta === "existe") {
                        $("#errorCorreo").show();
                        $("#correctoCorreo").hide();
                    } else if (respuesta === "noexiste"){
                        $("#errorCorreo").hide();
                        $("#correctoCorreo").show();
                    } else{
                        console.warn("Respuesta inesperada:", respuesta);
                    }
                },
                error: function() {
                    console.error("Error al verificar el correo.");
                    $("#errorCorreo").hide();
                    $("#correctoCorreo").hide();
                }
            });
        }, 500);
    });

    $("#confirmacion").on("input", function(){
        clearTimeout(timerPass);
        const pass = $("#password").val();
        const confirm = $(this).val();

        timerPass = setTimeout(function () {
            if (pass !== confirm) {
                $("#errorPass").show();
            } else {
                $("#errorPass").hide();
            }
        }, 500);
    });

    $("form").on("submit", function(e) {
        if ($("#errorNick").is(":visible")) {
            e.preventDefault();
            alert("El nick ingresado ya existe. Por favor, elija otro.");
        }
        if ($("#errorCorreo").is(":visible")) {
            e.preventDefault();
            alert("El correo ingresado ya existe. Por favor, elija otro.");
        }
        if ($("#errorPass").is(":visible")){
            e.preventDefault();
            alert("Las contraseñas no coinciden.");
        }
    });
});