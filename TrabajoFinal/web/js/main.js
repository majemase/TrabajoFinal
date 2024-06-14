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

function validarTarea(event) {
    event.preventDefault();
    document.getElementById('descError').textContent = '';
    document.getElementById('fechaError').textContent = '';
    document.getElementById('empleadoError').textContent = '';
    const descripcion = document.getElementById('desc').value;
    const fecha = document.getElementById('fecha').value;
    const listaEmpleado = document.querySelector('select[name="listaEmpleado"]');
    let hayErrores = false;
    const fechaActual = new Date();
    const fechaSeleccionada = new Date(fecha);
    if (fechaSeleccionada > fechaActual) {
        document.getElementById('fechaError').textContent = 'La fecha no puede ser anterior de la fecha actual.';
        hayErrores = true;
    }
    if (listaEmpleado.selectedOptions.length === 0) {
        document.getElementById('empleadoError').textContent = 'Debe seleccionar al menos un empleado.';
        hayErrores = true;
    }
    if (!hayErrores) {
        event.target.submit();
    }
}

function validarMaterial(event) {
    event.preventDefault();
    document.getElementById('nombreError').textContent = '';
    document.getElementById('precioError').textContent = '';
    document.getElementById('stockError').textContent = '';
    const nombre = document.getElementById('nombre').value;
    const precio = document.getElementById('precio').value;
    const stock = document.getElementById('stock').value;
    let hayErrores = false;
    const nombreRegex = /^[a-zA-Z0-9\s]+$/;
    if (!nombreRegex.test(nombre)) {
        document.getElementById('nombreError').textContent = 'El nombre no puede contener caracteres extraños.';
        hayErrores = true;
    }
    if (isNaN(precio) || precio <= 0) {
        document.getElementById('precioError').textContent = 'El precio debe ser un número positivo.';
        hayErrores = true;
    }
    if (isNaN(stock) || stock <= 0) {
        document.getElementById('stockError').textContent = 'El stock debe ser un número positivo.';
        hayErrores = true;
    }
    if (!hayErrores) {
        event.target.submit();
    }
}

function validarMaterialEdit(event) {
    event.preventDefault();
    document.getElementById('nombreEditError').textContent = '';
    document.getElementById('precioEditError').textContent = '';
    document.getElementById('stockEditError').textContent = '';
    const nombre = document.getElementById('nombreEdit').value;
    const precio = document.getElementById('precioEdit').value;
    const stock = document.getElementById('stockEdit').value;
    let hayErrores = false;
    const nombreRegex = /^[a-zA-Z0-9\s]+$/;
    if (!nombreRegex.test(nombre)) {
        document.getElementById('nombreEditError').textContent = 'El nombre no puede contener caracteres extraños.';
        hayErrores = true;
    }
    if (isNaN(precio) || precio <= 0) {
        document.getElementById('precioEditError').textContent = 'El precio debe ser un número positivo.';
        hayErrores = true;
    }
    if (isNaN(stock) || stock <= 0) {
        document.getElementById('stockEditError').textContent = 'El stock debe ser un número positivo.';
        hayErrores = true;
    }
    if (!hayErrores) {
        event.target.submit();
    }
}

function validarEmpleado(event) {
    event.preventDefault();
    document.getElementById('nombreError').textContent = '';
    document.getElementById('emailError').textContent = '';
    document.getElementById('passError').textContent = '';
    document.getElementById('cargoError').textContent = '';
    document.getElementById('depError').textContent = '';
    document.getElementById('tipoUsuError').textContent = '';
    const nombre = document.getElementById('nombre').value;
    const email = document.getElementById('email').value;
    const pass = document.getElementById('pass').value;
    const cargo = document.getElementById('cargo').value;
    const dep = document.getElementById('dep').value;
    const tipoUsu = document.getElementById('tipoUsu').value;
    let hayErrores = false;
    const nombreRegex = /^[a-zA-ZáéíóúÁÉÍÓÚüÜ\s]+$/;
    if (!nombreRegex.test(nombre)) {
        document.getElementById('nombreError').textContent = 'El nombre solo puede contener letras y espacios.';
        hayErrores = true;
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        document.getElementById('emailError').textContent = 'El email no es válido.';
        hayErrores = true;
    }
    if (pass.length < 5) {
        document.getElementById('passError').textContent = 'La contraseña debe tener al menos 5 caracteres.';
        hayErrores = true;
    }
    if (!hayErrores) {
        event.target.submit();
    }
}

function validarDepartamento(event) {
    event.preventDefault();
    document.getElementById('nombreError').textContent = '';
    const nombreDepartamento = document.getElementById('nombre').value;
    let hayErrores = false;
    const nombreDepartamentoRegex = /^[a-zA-Z\s]+$/;
    if (!nombreDepartamentoRegex.test(nombreDepartamento)) {
        document.getElementById('nombreError').textContent = 'El nombre del departamento solo puede contener letras y espacios.';
        hayErrores = true;
    }
    if (!hayErrores) {
        event.target.submit();
    }
}

function validarEmpleadoEdit(event) {
    event.preventDefault();
    document.getElementById('nombreEditError').textContent = '';
    document.getElementById('emailEditError').textContent = '';
    document.getElementById('passEditError').textContent = '';
    document.getElementById('cargoEditError').textContent = '';
    document.getElementById('depEditError').textContent = '';
    document.getElementById('tipoUsuEditError').textContent = '';
    const nombre = document.getElementById('nombreEdit').value;
    const email = document.getElementById('emailEdit').value;
    const pass = document.getElementById('passEdit').value;
    const cargo = document.getElementById('cargoEdit').value;
    const dep = document.getElementById('depEdit').value;
    const tipoUsu = document.getElementById('tipoUsuEdit').value;
    let hayErrores = false;
    const nombreRegex = /^[a-zA-ZáéíóúÁÉÍÓÚüÜ\s]+$/;
    if (!nombreRegex.test(nombre)) {
        document.getElementById('nombreEditError').textContent = 'El nombre solo puede contener letras y espacios.';
        hayErrores = true;
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        document.getElementById('emailEditError').textContent = 'El email no es válido.';
        hayErrores = true;
    }
    if (pass.length < 5) {
        document.getElementById('passEditError').textContent = 'La contraseña debe tener al menos 5 caracteres.';
        hayErrores = true;
    }
    if (!hayErrores) {
        event.target.submit();
    }
}

function validarDepartamentoEdit(event) {
    event.preventDefault();
    document.getElementById('nombreEditError').textContent = '';
    const nombreDepartamento = document.getElementById('nombreEdit').value;
    let hayErrores = false;
    const nombreDepartamentoRegex = /^[a-zA-Z\s]+$/;
    if (!nombreDepartamentoRegex.test(nombreDepartamento)) {
        document.getElementById('nombreEditError').textContent = 'El nombre del departamento solo puede contener letras y espacios.';
        hayErrores = true;
    }
    if (!hayErrores) {
        event.target.submit();
    }
}

function verDep(departmentId) {
    $.ajax({
        type: 'POST',
        url: '/TrabajoFinal/admin/verDep',
        data: {id: departmentId},
        success: function (response) {
            $("#nombreEdit").val('');
            $("#jefeDep").val('');
            $("#empleadosDep").val([]);
            $("#nombreEdit").val(response.nombre);
            $("#jefeDep-" + response.jefeDepartamento.id).prop('selected', true);
            response.empleados.forEach(function (empleado) {
                $("#empleadoDep-" + empleado.id).prop('selected', true);
            });
            $("#idDep").val(response.id);
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "No se ha encontrado el departamento"
            });
        }
    });
}

function verEmp(empleadoId) {
    $.ajax({
        type: 'POST',
        url: '/TrabajoFinal/admin/verEmp',
        data: {id: empleadoId},
        success: function (response) {
            $('#idEmpleadoEdit').val(empleadoId)
            $('#nombreEdit').val(response.nombre);
            $('#emailEdit').val(response.email);
            $('#passEdit').val(response.pass);
            $('#' + response.cargo + 'Edit').prop('selected', true);
            $('#dep-' + response.dep).prop('selected', true);
            $('#' + response.tipoUsu + 'Edit').prop('selected', true);
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "No se ha encontrado el empleado"
            });
        }
    });
}

function confirmaDel(departmentId) {
    Swal.fire({
        title: "¿Desea eliminar el departamento?",
        text: "Se elimiaran tambien los empleados asocioados a ese departamento, asegurese de que el departamento este vacio.",
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

function confirmaDelEmp(empleadoId) {
    Swal.fire({
        title: "¿Seguro?",
        text: "¿Desea eliminar el empleado?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: 'POST',
                url: '/TrabajoFinal/admin/Empleados',
                data: {id: empleadoId, eliminar: "eliminar"},
                success: function (response) {
                    location.reload();
                },
                error: function (xhr, status, error) {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "No se ha podido eliminar el empleado"
                    });
                }
            });
        }
    });
}

function confirmaDelMat(materialId) {
    Swal.fire({
        title: "¿Seguro?",
        text: "¿Desea eliminar el material?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: 'POST',
                url: '/TrabajoFinal/empleado/Materiales',
                data: {id: materialId, eliminar: "eliminar"},
                success: function (response) {
                    location.reload();
                },
                error: function (xhr, status, error) {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "No se ha podido eliminar el material"
                    });
                }
            });
        }
    });
}

function confirmaDelTarea(tareaId) {
    Swal.fire({
        title: "¿Seguro?",
        text: "¿Desea eliminar la tarea?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: 'POST',
                url: '/TrabajoFinal/empleado/Tareas',
                data: {id: tareaId, eliminar: "eliminar"},
                success: function (response) {
                    location.reload();
                },
                error: function (xhr, status, error) {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "No se ha podido eliminar la tarea"
                    });
                }
            });
        }
    });
}

function confirMat(materialId) {
    $.ajax({
        type: 'POST',
        url: '/TrabajoFinal/empleado/Materiales',
        data: {id: materialId, confirma: "confirma"},
        success: function (response) {
            location.reload();
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "No se ha podido confirmar el material"
            });
        }
    });
}

function procesoTar(tareaId, estado, opciones) {
    var fechaPulsa = new Date().toISOString();
    $.ajax({
        type: 'POST',
        url: '/TrabajoFinal/empleado/MenuPrincipal',
        data: {id: tareaId, estado: estado, fecha: fechaPulsa, princ_fin: opciones},
        success: function (response) {
            location.reload();
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "No se ha podido cambiar el estado de la tarea"
            });
        }
    });
}

function verMat(materialId) {
    $.ajax({
        type: 'POST',
        url: '/TrabajoFinal/empleado/verMat',
        data: {id: materialId},
        success: function (response) {
            $('#idMaterialEdit').val(materialId)
            $('#nombreEdit').val(response.nombre);
            $('#precioEdit').val(response.precio);
            $('#stockEdit').val(response.stock);
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "No se ha encontrado el material"
            });
        }
    });
}