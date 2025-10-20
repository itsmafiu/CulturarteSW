
$(document).ready(function() {
    let timerNick;
    let timerCorreo;
    let timerPass;

    $("#nick").on("input", function() {
        clearTimeout(timerNick);
        const nick = $(this).val().trim();

        if (nick === "") {
            $("#errorNick").hide();
            return;
        }
        
//        if(nick.includes(" ")){
//            $("#errorNick").show();
//            return;
//        }else{
//            $("#errorNick").hide();
//        }
        
        timerNick = setTimeout(function() {$.ajax({url: "altaUsuario", method: "GET", data: { verificarNick: nick, tipoVerificarUsuario: "nick"}, 
                success: function(respuesta) {
                    if (respuesta === "existe") {
                        $("#errorNick").show();
                    } else {
                        $("#errorNick").hide();
                    }
                },
                error: function() {
                    console.error("Error al verificar el nick.");
                }
            });
        }, 500);
    });
    
    $("#correo").on("input", function() {
        clearTimeout(timerCorreo);
        const correo = $(this).val().trim();

        if (correo === "") {
            $("#errorCorreo").hide();
            return;
        }

        timerCorreo = setTimeout(function() {$.ajax({url: "altaUsuario", method: "GET", data: { verificarCorreo: correo, tipoVerificarUsuario: "correo"}, 
                success: function(respuesta) {
                    if (respuesta === "existe") {
                        $("#errorCorreo").text("Este correo ya está en uso.").show();
                    } else {
                        $("#errorCorreo").hide();
                    }
                },
                error: function() {
                    console.error("Error al verificar el correo.");
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
    });
});