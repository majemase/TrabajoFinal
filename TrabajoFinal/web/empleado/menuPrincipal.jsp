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
    <body>
        <%@ include file="../header.jsp" %>
        <section class="p-3">
            <div class="card m-3 w-25">
                <h5 class="card-body d-flex justify-content-center align-items-center">Puntos de productividad ${usuario.puntos_productividad}</h5>
            </div>
            <article>
                <table id="tablaTareas" class="table align-items-center mb-0">
                    <thead>
                        <tr>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Descripcion</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha inicio</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha fin</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Empleados</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Opciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tarea" items="${tareas}">
                            <tr>
                                <td class="align-middle text-center">
                                    <div class="d-flex px-2 py-1 justify-content-center">
                                        <span class="text-sm">${tarea.descripcion}</span>
                                    </div>
                                </td>
                                <td class="align-middle text-center">
                                    <span class="text-secondary text-xs font-weight-bold">
                                        <fmt:formatDate value="${tarea.fecha}" pattern="dd-MM-yyyy"/>
                                    </span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class="text-secondary text-xs font-weight-bold">
                                        <fmt:formatDate value="${tarea.fecha_inicio}" pattern="dd-MM-yyyy"/>
                                    </span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class="text-secondary text-xs font-weight-bold">
                                        <fmt:formatDate value="${tarea.fecha_fin}" pattern="dd-MM-yyyy"/>
                                    </span>
                                </td>
                                <td class="align-middle text-center">
                                    <c:forEach var="empleado" varStatus="ultimoEmpleado" items="${tarea.empleados}">
                                        <span class="text-secondary text-xs font-weight-bold">
                                            ${empleado.nombre}
                                        </span>
                                        <c:if test="${not ultimoEmpleado.last}">
                                            ,
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td class="align-middle text-center">
                                    <!-- Button trigger modal -->
                                    <button type="button" onclick="verDep(${departamento.id_departamento})" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editarDep">
                                        <i class="fa-solid fa-pen-to-square"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Descripcion</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha inicio</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha fin</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Empleados</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Opciones</th>
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
                                            $('#tablaTareas').DataTable({
                                                "searching": true, // Habilitar o deshabilitar el buscador
                                                "paging": true, // Habilitar o deshabilitar la paginaci�n
                                                "lengthMenu": [5, 10, 25, 50], // Opciones para el n�mero de registros por p�gina
                                                "pageLength": 10, // N�mero predeterminado de registros por p�gina
                                                "language": {// Personalizar el texto mostrado
                                                    "search": "Buscar:",
                                                    "lengthMenu": "Mostrar _MENU_ registros por p�gina",
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
