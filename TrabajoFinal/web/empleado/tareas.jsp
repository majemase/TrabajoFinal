<%-- 
    Document   : menuPrincipal
    Created on : 7 may 2024, 17:40:47
    Author     : majemase
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="modelo.Estado"%>
<%@page import="modelo.TipoUsuario"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ProductivityTrack - Tareas</title>
        <link rel="icon" href="../assets/imagenes/logo.png">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/05663c91b1.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/highcharts-3d.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script src="https://code.highcharts.com/modules/export-data.js"></script>
        <script src="https://code.highcharts.com/modules/accessibility.js"></script>
        <link rel="stylesheet" href="../assets/css/main.css"/>
    </head>
    <body class="bg-light">
        <%@include file="../header.jsp" %>
        <section class="container p-3">
            <div class="row justify-content-center">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <h1 class="text-azul">Listado de tareas</h1>
                </div>
            </div>
            <div class="row justify-content-center mt-3">
                <div class="col-md-4 d-flex justify-content-center align-items-center gap-4">
                    <!-- Botón para activar modal -->
                    <button type="button" class="btn btn-primary bg-azul-oscuro" data-bs-toggle="modal" data-bs-target="#addDep">
                        <i class="fa-solid fa-plus"></i> Añadir tarea
                    </button>
                    <!-- Botón para mandar emails -->
                    <form action="../empleado/Tareas" method="POST">
                        <button type="submit" name="email" class="btn btn-primary bg-azul-oscuro">
                            <i class="fa-solid fa-paper-plane"></i> Mandar emails
                        </button>
                    </form>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-7 d-flex justify-content-center align-items-center">
                    <h5 class="text-azul">Comprueba las tareas que tienes realizadas, en proceso o no realizadas:</h5>
                </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="addDep" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-azul" id="addModalLabel">Añadir Tarea</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="../empleado/Tareas" onsubmit="validarTarea(event)" method="POST">
                                <div class="mb-3">
                                    <label for="desc" class="form-label text-azul">Descripción</label>
                                    <textarea id="desc" class="form-control text-azul" name="desc" rows="5" cols="10" required></textarea>
                                    <div id="descError" class="text-danger"></div>
                                </div>
                                <div class="mb-3">
                                    <label for="fecha" class="form-label text-azul">Fecha</label>
                                    <input type="date" class="form-control text-azul" name="fecha" id="fecha" required>
                                    <div id="fechaError" class="text-danger"></div>
                                </div>
                                <div class="mb-3">
                                    <label for="empleado" class="form-label text-azul">Empleados</label>
                                    <select class="form-select text-azul" name="listaEmpleado" multiple aria-label="Multiple select example" required>
                                        <c:forEach var="empleado" items="${empleados}">
                                            <option value="${empleado.id_empleado}">${empleado.nombre} - ${empleado.departamento.nombre}</option>
                                        </c:forEach>
                                    </select>
                                    <div id="empleadoError" class="text-danger"></div>
                                </div>
                                <input type="hidden" name="añadir" value="true"/>
                                <div class="d-flex justify-content-end gap-3">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-primary bg-azul-oscuro">Añadir Tarea</button>
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
                            <table id="tablaTareas" class="table table-striped table-bordered align-items-center mb-0">
                                <thead>
                                    <tr class="text-azul">
                                        <th class="text-center text-uppercase font-weight-bold">Descripción</th>
                                        <th class="text-center text-uppercase font-weight-bold">Fecha</th>
                                        <th class="text-center text-uppercase font-weight-bold">Fecha Inicio</th>
                                        <th class="text-center text-uppercase font-weight-bold">Fecha Fin</th>
                                        <th class="text-center text-uppercase font-weight-bold">Empleados</th>
                                        <th class="text-center text-uppercase font-weight-bold">Estado</th>
                                        <th class="text-center text-uppercase font-weight-bold">Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="tarea" items="${tareas}">
                                        <tr>
                                            <td class="align-middle text-center">
                                                <span class="text-dark">${tarea.descripcion}</span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <span class="text-dark text-xs font-weight-bold">
                                                    <fmt:formatDate value="${tarea.fecha}" pattern="dd-MM-yyyy"/>
                                                </span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <span class="text-dark text-xs font-weight-bold">
                                                    <fmt:formatDate value="${tarea.fecha_inicio}" pattern="dd-MM-yyyy HH:mm:ss"/>
                                                </span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <span class="text-dark text-xs font-weight-bold">
                                                    <fmt:formatDate value="${tarea.fecha_fin}" pattern="dd-MM-yyyy HH:mm:ss"/>
                                                </span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <c:forEach var="empleado" varStatus="ultimoEmpleado" items="${tarea.empleados}">
                                                    <span class="text-dark text-xs font-weight-bold">
                                                        ${empleado.nombre}
                                                    </span>
                                                    <c:if test="${not ultimoEmpleado.last}">, </c:if>
                                                </c:forEach>
                                            </td>
                                            <td class="align-middle text-center">
                                                <c:if test="${tarea.estado eq 'NO_REALIZADO'}">
                                                    <i class="fa-solid fa-xmark text-danger"></i>
                                                </c:if>
                                                <c:if test="${tarea.estado eq 'PROCESO'}">
                                                    <i class="fa-solid fa-spinner text-warning"></i>
                                                </c:if>
                                                <c:if test="${tarea.estado eq 'REALIZADO'}">
                                                    <i class="fa-solid fa-check text-success"></i>
                                                </c:if>
                                            </td>
                                            <td class="align-middle text-center">
                                                <c:if test="${tarea.estado eq 'PROCESO'}">
                                                    <button type="button" onclick="procesoTar(${tarea.id_tarea}, 'realizado', 'fin')" class="btn btn-success btn-sm">
                                                        <i class="fa-solid fa-check"></i>
                                                    </button>
                                                </c:if>
                                                <c:if test="${tarea.estado eq 'NO_REALIZADO'}">
                                                    <button type="button" onclick="procesoTar(${tarea.id_tarea}, 'proceso', 'ini')" class="btn btn-warning btn-sm">
                                                        <i class="fa-solid fa-play"></i>
                                                    </button>
                                                </c:if>
                                                <c:if test="${usuario.tipoUsuario eq 'ADMINISTRADOR' and (usuario.cargo eq 'JEFEDEPARTAMENTO' or usuario.cargo eq 'JEFE')}">
                                                    <button type="button" onclick="confirmaDelTarea(${tarea.id_tarea})" class="btn btn-danger btn-sm">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th class="text-center text-uppercase font-weight-bold">Descripción</th>
                                        <th class="text-center text-uppercase font-weight-bold">Fecha</th>
                                        <th class="text-center text-uppercase font-weight-bold">Fecha Inicio</th>
                                        <th class="text-center text-uppercase font-weight-bold">Fecha Fin</th>
                                        <th class="text-center text-uppercase font-weight-bold">Empleados</th>
                                        <th class="text-center text-uppercase font-weight-bold">Estado</th>
                                        <th class="text-center text-uppercase font-weight-bold">Opciones</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
                <!--<div class="d-flex justify-content-center align-items-center flex-column mt-3">
                    <div id="container" style="width: 600px; height: 400px;"></div>
                </div>-->
            </article>
        </section>
        <br><br><br><br>
        <footer class="bg-azul text-azul py-3 mt-4 fixed-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 text-center text-md-start">
                        <p class="mb-0">&copy; 2024 ProductivityTrack. Todos los derechos reservados.</p>
                    </div>
                    <div class="col-md-6 text-center text-md-end">
                        <a href="#" class="text-azul me-3">Política de Privacidad</a>
                        <a href="#" class="text-azul">Términos y Condiciones</a>
                    </div>
                </div>
            </div>
        </footer>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.7/js/dataTables.bootstrap5.js"></script>
        <script>
                                                        Highcharts.chart('container', {
                                                            chart: {
                                                                type: 'pie',
                                                                options3d: {
                                                                    enabled: true,
                                                                    alpha: 45
                                                                }
                                                            },
                                                            title: {
                                                                text: 'Tareas realizadas por los empleados',
                                                                align: 'left'
                                                            },
                                                            subtitle: {
                                                                text: '',
                                                                align: 'left'
                                                            },
                                                            plotOptions: {
                                                                pie: {
                                                                    innerSize: 100,
                                                                    depth: 45
                                                                }
                                                            },
                                                            series: [{
                                                                    name: 'Tareas',
                                                                    data: [
                                                                        ['Norway', 16],
                                                                        ['Germany', 12],
                                                                        ['USA', 8],
                                                                        ['Sweden', 8],
                                                                        ['Netherlands', 8],
                                                                        ['ROC', 6],
                                                                        ['Austria', 7],
                                                                        ['Canada', 4],
                                                                        ['Japan', 3]
                                                                    ]
                                                                }]
                                                        });
        </script>
        <script>
            $(document).ready(function () {
                $('#tablaTareas').DataTable({
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
