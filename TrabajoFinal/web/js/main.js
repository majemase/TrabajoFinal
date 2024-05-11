/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

window.onload = function () {
    if (localStorage.getItem("error") !== null) {
        document.getElementById("error").innerHTML = localStorage.getItem("error");
        localStorage.removeItem("error");
    }
}

function validarLogin(event) {
    let dni = document.getElementById("dni").value;
    let pass = document.getElementById("pass").value;
    let patronDni = /^\d{8}[A-Z]$/;

    if (dni.trim() === "" || !patronDni.test(dni) || pass.trim() === "") {
        document.getElementById("error").innerHTML = "Nombre o contraseña incorrectos";
        localStorage.setItem("error", "Nombre o contraseña incorrectos");
        event.preventDefault();
    }
}