<%-- 
    Document   : menuPrincipal
    Created on : 7 may 2024, 17:40:47
    Author     : majemase
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="modelo.TipoUsuario"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ProductivityTrack - Empleados</title>
        <link rel="icon" href="../assets/imagenes/logo.png">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://kit.fontawesome.com/05663c91b1.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../assets/css/main.css"/>
    </head>
    <body class="bg-light">
        <%@include file="../header.jsp" %>
        <section class="container p-3">
            <div class="row justify-content-center">
                <div class="col-md-5 d-flex justify-content-center align-items-center">
                    <h1 class="text-azul">Listado de empleados</h1>
                </div>
            </div>
            <div class="row justify-content-center mt-3">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <button type="button" class="btn btn-primary bg-azul-oscuro" data-bs-toggle="modal" data-bs-target="#addEmpleado">
                        <i class="fa-solid fa-plus"></i> Añadir empleado
                    </button>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-3 d-flex justify-content-center align-items-center">
                    <h5 class="text-azul">Empleados de la empresa:</h5>
                </div>
            </div>
            <!-- Modal Añadir Empleado -->
            <div class="modal fade" id="addEmpleado" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="addModalLabel">Añadir empleado</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/TrabajoFinal/admin/Empleados" method="POST">
                                <div class="mb-3">
                                    <label for="nombre" class="form-label">Nombre</label>
                                    <input type="text" class="form-control" name="nombre" id="nombre">
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" name="email" id="email">
                                </div>
                                <div class="mb-3">
                                    <label for="pass" class="form-label">Contraseña</label>
                                    <input type="password" class="form-control" name="pass" id="pass">
                                </div>
                                <div class="mb-3">
                                    <label for="cargo" class="form-label">Cargo</label>
                                    <select name="cargo" id="cargo" class="form-select" aria-label="Cargo">
                                        <c:forEach var="cargo" items="${cargos}">
                                            <option value="${cargo}">${cargo}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="dep" class="form-label">Departamento</label>
                                    <select id="dep" name="dep" class="form-select" aria-label="Departamento">
                                        <c:forEach var="dep" items="${departamentos}">
                                            <option value="${dep.id_departamento}">${dep.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="tipoUsu" class="form-label">Tipo usuario</label>
                                    <select id="tipoUsu" name="tipoUsu" class="form-select" aria-label="TipoUsu">
                                        <c:forEach var="tipoUsu" items="${tipoUsus}">
                                            <option value="${tipoUsu}">${tipoUsu}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="d-flex justify-content-end gap-3">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-primary" name="añadir" value="true">Añadir empleado</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <article class="mt-4">
                <div class="card bg-azul text-azul shadow-sm">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="tablaEmpleados" class="table table-striped table-bordered align-items-center mb-0">
                                <thead class="bg-azul">
                                    <tr>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Nombre</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Email</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Cargo</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Departamento</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">TipoUsuario</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="empleado" items="${empleados}">
                                        <tr>
                                            <td class="align-middle text-center">
                                                <span class="text-sm">${empleado.nombre}</span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <span class="text-light text-black text-xs font-weight-bold">${empleado.email}</span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <span class="text-light text-black text-xs font-weight-bold">${empleado.cargo}</span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <span class="text-light text-black text-xs font-weight-bold">${empleado.departamento.nombre}</span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <span class="text-light text-black text-xs font-weight-bold">${empleado.tipoUsuario}</span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <button type="button" onclick="verEmp(${empleado.id_empleado})" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editarEmpl">
                                                    <i class="fa-solid fa-pen-to-square"></i>
                                                </button>
                                                <button type="button" onclick="confirmaDelEmp(${empleado.id_empleado})" class="btn btn-danger btn-sm">
                                                    <i class="fa-solid fa-trash"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot class="bg-azul">
                                    <tr>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Nombre</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Email</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Cargo</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Departamento</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">TipoUsuario</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Opciones</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </article>
        </section>
        <!-- Modal Editar Empleado -->
        <div class="modal fade" id="editarEmpl" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5 text-dark" id="editarModalLabel">Editar Empleado</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="Empleados" method="POST">
                            <div class="mb-3">
                                <label for="nombreEdit" class="form-label">Nombre</label>
                                <input type="text" class="form-control" name="nombre" id="nombreEdit">
                            </div>
                            <div class="mb-3">
                                <label for="emailEdit" class="form-label">Email</label>
                                <input type="email" class="form-control" name="email" id="emailEdit">
                            </div>
                            <div class="mb-3">
                                <label for="passEdit" class="form-label">Contraseña</label>
                                <input type="password" class="form-control" name="pass" id="passEdit">
                            </div>
                            <div class="mb-3">
                                <label for="cargoEdit" class="form-label">Cargo</label>
                                <select name="cargo" id="cargoEdit" class="form-select" aria-label="Cargo">
                                    <c:forEach var="cargo" items="${cargos}">
                                        <option value="${cargo}" id="${cargo}Edit">${cargo}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="depEdit" class="form-label">Departamento</label>
                                <select id="depEdit" name="dep" class="form-select" aria-label="Departamento">
                                    <c:forEach var="dep" items="${departamentos}">
                                        <option value="${dep.id_departamento}" id="dep-${dep.id_departamento}">${dep.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="tipoUsuEdit" class="form-label">Tipo usuario</label>
                                <select id="tipoUsuEdit" name="tipoUsu" class="form-select" aria-label="TipoUsu">
                                    <c:forEach var="tipoUsu" items="${tipoUsus}">
                                        <option value="${tipoUsu}" id="${tipoUsu}Edit">${tipoUsu}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="d-flex justify-content-end gap-3">
                                <input type="hidden" id="idEmpleadoEdit" name="idEmpleado" value="" />
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-primary" name="editar" value="true">Guardar cambios</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.7/js/dataTables.bootstrap5.js"></script>
        <script>
                                                    $(document).ready(function () {
                                                        $('#tablaEmpleados').DataTable({
                                                            "searching": true, // Habilitar o deshabilitar el buscador
                                                            "paging": true, // Habilitar o deshabilitar la paginación
                                                            "lengthMenu": [5, 10, 25, 50], // Opciones para el número de registros por página
                                                            "pageLength": 10, // Número predeterminado de registros por página
                                                            "language": {// Personalizar el texto mostrado
                                                                "search": "Buscar:",
                                                                "lengthMenu": "Mostrar _MENU_ registros por página",
                                                                "zeroRecords": "No se encontraron resultados",
                                                                "info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
                                                                "infoEmpty": "Mostrando 0 a 0 de 0 registros",
                                                                "infoFiltered": "(filtrado de _MAX_ registros totales)",
                                                                "paginate": {
                                                                    "first": "<<",
                                                                    "last": ">>",
                                                                    "next": ">",
                                                                    "previous": "<"
                                                                }
                                                            }
                                                        });
                                                    });
        </script>
        <script src="../js/main.js"></script>
    </body>
</html>
