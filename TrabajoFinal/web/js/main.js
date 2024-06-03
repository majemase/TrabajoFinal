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

function verDep(departmentId) {
    $.ajax({
        type: 'POST',
        url: '/TrabajoFinal/admin/verDep',
        data: {id: departmentId},
        success: function (response) {
            $("#nombreEdit").val(response.nombre);
            $("#jefeDep-" + response.jefeDepartamento.id).prop('selected', true);
            response.empleados.forEach(function (empleado) {
                $("#empleadoDep-" + empleado.id).prop('selected', true);
            });
            $("#idDep").val(response.id);
        },
        error: function (xhr, status, error) {
            console.error('Error en la solicitud: ' + error);
        }
    });
}

function confirmaDel(departmentId) {
    Swal.fire({
        title: "¿Seguro?",
        text: "¿Desea eliminar el departamento?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: 'POST',
                url: '/TrabajoFinal/admin/Departamentos',
                data: {id: departmentId, eliminar: "eliminar"},
                success: function (response) {
                    location.reload();
                },
                error: function (xhr, status, error) {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "No se ha podido eliminar el departamento"
                    });
                }
            });
        }
    });
}