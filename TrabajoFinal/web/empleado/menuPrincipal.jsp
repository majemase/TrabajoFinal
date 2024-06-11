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
<fmt:setBundle basename="internacionalizacion.menuPrincipal" var="menuPrincipal"/>
<fmt:setBundle basename="internacionalizacion.datatable" var="datatable"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ProductivityTrack - Menu Principal</title>
        <link rel="icon" href="../assets/imagenes/logo.png">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/05663c91b1.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../assets/css/main.css"/>
    </head>
    <body class="bg-light">
        <%@ include file="../header.jsp" %>
        <section class="container py-3">
            <!--<div class="row justify-content-center">
                <div class="col-md-4">
                    <div class="card text-center shadow-sm bg-azul-oscuro">
                        <div class="card-body text-white">
                            <h5 class="card-title">Puntos de Productividad</h5>
                            <h6 class="card-subtitle mb-2 fs-1 text-white">${usuario.puntos_productividad}</h6>
                        </div>
                    </div>
                </div>
            </div>-->
            <div class="row justify-content-center">
                <div class="col-md-5 d-flex justify-content-center align-items-center">
                    <h1 class="text-azul"><fmt:message key="Titulo" bundle="${menuPrincipal}" /></h1>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-7 d-flex justify-content-center align-items-center">
                    <h5 class="text-azul"><fmt:message key="Saludo" bundle="${menuPrincipal}" /> ${usuario.nombre} <fmt:message key="Subtitulo" bundle="${menuPrincipal}" /></h5>
                </div>
            </div>
            <article class="mt-4">
                <div class="card bg-azul text-azul shadow-sm">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="tablaTareas" class="table table-striped table-bordered align-items-center mb-0">
                                <thead>
                                    <tr class="text-azul">
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Descripcion" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Fecha" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Fecha_ini" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Fecha_fin" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Empleados" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Estado" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Opciones" bundle="${menuPrincipal}" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="tarea" items="${tareas}">
                                        <c:if test="${tarea.estado != 'REALIZADO'}">
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
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Descripcion" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Fecha" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Fecha_ini" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Fecha_fin" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Empleados" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Estado" bundle="${menuPrincipal}" /></th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold"><fmt:message key="Opciones" bundle="${menuPrincipal}" /></th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
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
                                                            $(document).ready(function () {
                                                                $('#tablaTareas').DataTable({
                                                                    "searching": true,
                                                                    "paging": true,
                                                                    "lengthMenu": [5, 10, 25, 50],
                                                                    "pageLength": 10,
                                                                    "language": {
                                                                        "search": "<fmt:message key='datatable.search' bundle="${datatable}"/>",
                                                                        "lengthMenu": "<fmt:message key='datatable.lengthMenu' bundle="${datatable}"/>",
                                                                        "zeroRecords": "<fmt:message key='datatable.zeroRecords' bundle="${datatable}"/>",
                                                                        "info": "<fmt:message key='datatable.info' bundle="${datatable}"/>",
                                                                        "infoEmpty": "<fmt:message key='datatable.infoEmpty' bundle="${datatable}"/>",
                                                                        "infoFiltered": "<fmt:message key='datatable.infoFiltered' bundle="${datatable}"/>",
                                                                        "paginate": {
                                                                            "first": "<fmt:message key='datatable.paginate.first' bundle="${datatable}"/>",
                                                                            "last": "<fmt:message key='datatable.paginate.last' bundle="${datatable}"/>",
                                                                            "next": "<fmt:message key='datatable.paginate.next' bundle="${datatable}"/>",
                                                                            "previous": "<fmt:message key='datatable.paginate.previous' bundle="${datatable}"/>"
                                                                        }
                                                                    }
                                                                });
                                                            });
        </script>
        <script src="../js/main.js"></script>
    </body>
</html>
