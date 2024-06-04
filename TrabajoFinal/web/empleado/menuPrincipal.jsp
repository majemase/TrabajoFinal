<%-- 
    Document   : menuPrincipal
    Created on : 7 may 2024, 17:40:47
    Author     : majemase
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="modelo.Estado"%>
<%@page import="modelo.TipoUsuario"%>
<%@page import="modelo.Cargo"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ProductivityTrack - Menu Principal</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/05663c91b1.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-secondary">
        <%@ include file="../header.jsp" %>
        <section class="container py-3">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <div class="card text-center shadow-sm bg-dark">
                        <div class="card-body text-white">
                            <h5 class="card-title">Puntos de Productividad</h5>
                            <h6 class="card-subtitle mb-2 fs-1 text-white">${usuario.puntos_productividad}</h6>
                        </div>
                    </div>
                </div>
            </div>
            <article class="mt-4">
                <div class="card bg-dark text-white shadow-sm">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="tablaTareas" class="table table-striped table-bordered align-items-center mb-0">
                                <thead class="table-dark">
                                    <tr>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Descripción</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Fecha</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Fecha Inicio</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Fecha Fin</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Empleados</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Estado</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="tarea" items="${tareas}">
                                        <c:if test="${tarea.estado != 'REALIZADO'}">
                                            <tr>
                                                <td class="align-middle text-center">
                                                    <span class="text-sm">${tarea.descripcion}</span>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-black text-xs font-weight-bold">
                                                        <fmt:formatDate value="${tarea.fecha}" pattern="dd-MM-yyyy"/>
                                                    </span>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-black text-xs font-weight-bold">
                                                        <fmt:formatDate value="${tarea.fecha_inicio}" pattern="dd-MM-yyyy"/>
                                                    </span>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-black text-xs font-weight-bold">
                                                        <fmt:formatDate value="${tarea.fecha_fin}" pattern="dd-MM-yyyy"/>
                                                    </span>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <c:forEach var="empleado" varStatus="ultimoEmpleado" items="${tarea.empleados}">
                                                        <span class="text-black text-xs font-weight-bold">
                                                            ${empleado.nombre}
                                                        </span>
                                                        <c:if test="${not ultimoEmpleado.last}">, </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <c:if test="${tarea.estado eq 'REALIZADO'}">
                                                        <i class="fa-solid fa-check text-success"></i>
                                                    </c:if>
                                                    <c:if test="${tarea.estado eq 'NO_REALIZADO'}">
                                                        <i class="fa-solid fa-xmark text-danger"></i>
                                                    </c:if>
                                                    <c:if test="${tarea.estado eq 'PROCESO'}">
                                                        <i class="fa-solid fa-spinner text-warning"></i>
                                                    </c:if>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <button type="button" class="btn btn-success btn-sm">
                                                        <i class="fa-solid fa-check"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                                <tfoot class="table-dark">
                                    <tr>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Descripción</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Fecha</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Fecha Inicio</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Fecha Fin</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Empleados</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Estado</th>
                                        <th class="text-center text-uppercase text-light font-weight-bold">Opciones</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </article>
        </section>
    </section>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/2.0.7/js/dataTables.bootstrap5.js"></script>
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
</body>
</html>
