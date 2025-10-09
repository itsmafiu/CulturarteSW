/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function() {
    let timer;

    $("#titulo").on("input", function() {
        clearTimeout(timer);
        const titulo = $(this).val().trim();

        if (titulo === "") {
            $("#errorTitulo").hide();
            return;
        }

        timer = setTimeout(function() {$.ajax({url: "SvValidarTitulo", method: "GET", data: { titulo: titulo }, success: function(respuesta) {
                    if (respuesta === "existe") {
                        $("#errorTitulo").show();
                    } else {
                        $("#errorTitulo").hide();
                    }
                },
                error: function() {
                    console.error("Error al verificar el título.");
                }
            });
        }, 500);
    });

    $("form").on("submit", function(e) {
        if ($("#errorTitulo").is(":visible")) {
            e.preventDefault();
            alert("El título ingresado ya existe. Por favor, elija otro.");
        }
    });
});
