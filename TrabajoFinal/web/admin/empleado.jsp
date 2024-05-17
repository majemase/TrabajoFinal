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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <section class="p-3">
            <article class="mb-3">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addEmpleado">
                    Añadir empleado
                </button>

                <!-- Modal -->
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
                                        <label for="dni" class="form-label">DNI</label>
                                        <input type="text" class="form-control" name="dni" id="dni">
                                    </div>
                                    <div class="mb-3">
                                        <label for="pass" class="form-label">Contraseña</label>
                                        <input type="password" class="form-control" name="pass" id="pass">
                                    </div>
                                    <div class="mb-3">
                                        <label for="cargo">Cargo</label>
                                        <select name="cargo" id="cargo" class="form-select" aria-label="Cargo">
                                            <c:forEach var="cargo" items="${cargos}">
                                                <option value="${cargo}">${cargo}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="dep">Departamento</label>
                                        <select id="dep" name="dep" class="form-select" aria-label="Departamento">
                                            <c:forEach var="dep" items="${departamentos}">
                                                <option value="${dep.id_departamento}">${dep.nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="tipoUsu">Tipo usuario</label>
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
            </article>
            <article>
                <table id="tablaEmpleados" class="table align-items-center mb-0">
                    <thead>
                        <tr>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nombre</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">DNI</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Contraseña</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Cargo</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Departamento</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">TipoUsuario</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="empleado" items="${empleados}">
                            <tr>
                                <td class="align-middle text-center">
                                    <div class="d-flex px-2 py-1 justify-content-center">
                                        <span class="text-sm">${empleado.nombre}</span>
                                    </div>
                                </td>
                                <td class="align-middle text-center">
                                    <span class="text-secondary text-xs font-weight-bold">
                                        ${empleado.dni}
                                    </span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class="text-secondary text-xs font-weight-bold">
                                        ${empleado.pass}
                                </td>
                                <td class="align-middle text-center">
                                    <span class="text-secondary text-xs font-weight-bold">
                                        ${empleado.cargo}
                                    </span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class="text-secondary text-xs font-weight-bold">
                                        ${empleado.departamento.nombre}
                                    </span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class="text-secondary text-xs font-weight-bold">
                                        ${empleado.tipoUsuario}
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nombre</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">DNI</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Contraseña</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Cargo</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Departamento</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">TipoUsuario</th>
                        </tr>
                    </tfoot>
                </table>
            </article>
        </section>
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
                    </body>
            </html>
