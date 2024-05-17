<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="modelo.TipoUsuario"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ProductivityTrack - Departamentos</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <section class="p-3">
            <article class="mb-3">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addDep">
                    Añadir departamento
                </button>

                <!-- Modal -->
                <div class="modal fade" id="addDep" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="addModalLabel">Añadir Departamento</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="/TrabajoFinal/admin/Departamentos" method="POST">
                                    <div class="mb-3">
                                        <label for="nombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control" name="nombre" id="nombre">
                                    </div>
                                    <input type="hidden" name="añadir" value="true"/>
                                    <div class="d-flex justify-content-end gap-3">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                        <button type="submit" class="btn btn-primary">Añadir departamento</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </article>
            <article>
                <table id="tablaDep" class="table align-items-center mb-0">
                    <thead class="align-bottom">
                        <tr>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nombre</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Jefe departamento</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Empleados</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="departamento" items="${departamentos}">
                            <tr>
                                <td class="align-middle text-center">
                                    <div class="d-flex px-2 py-1 justify-content-center">
                                        <span class="text-sm">${departamento.nombre}</span>
                                    </div>
                                </td>
                                <td class="align-middle text-center">
                                    <c:forEach var="empleado" items="${departamento.empleados}">
                                        <c:if test="${empleado.cargo eq 'JEFEDEPARTAMENTO' or empleado.cargo eq 'JEFE'}">
                                            <span class="text-secondary text-xs font-weight-bold">${empleado.nombre} (${empleado.cargo})</span>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td class="align-middle text-center">
                                    <c:forEach var="empleado" varStatus="ultimoEmpleado" items="${departamento.empleados}">
                                        <span class="text-secondary text-xs font-weight-bold">${empleado.nombre}</span>
                                        <c:if test="${not ultimoEmpleado.last}">
                                            ,
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nombre</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Jefe departamento</th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Empleados</th>
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
                $('#tablaDep').DataTable({
                    "searching": true,
                    "paging": true,
                    "lengthMenu": [5, 10, 25, 50],
                    "pageLength": 10,
                    "language": {
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
