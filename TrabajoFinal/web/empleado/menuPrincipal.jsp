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
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <section class="p-6">
            <div class="block w-fit p-6 mb-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100">
                <h5 class="text-lg font-bold tracking-tight text-gray-900">Puntos de productividad ${usuario.puntos_productividad}</h5>
            </div>
            <article>
                <table id="tablaTareas" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>DNI</th>
                            <th>Contraseña</th>
                            <th>Cargo</th>
                            <th>Departamento</th>
                            <th>Tipo usuario</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tarea" items="${tareas}">
                            <tr class="bg-white border-b">
                                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                                    ${tarea.descripcion}
                                </th>
                                <td class="px-6 py-4">
                                    <fmt:formatDate value="${tarea.fecha_inicio}" pattern="dd-MM-yyyy"/>
                                </td>
                                <td class="px-6 py-4">
                                    <fmt:formatDate value="${tarea.fecha_fin}" pattern="dd-MM-yyyy"/>
                                </td>
                                <td class="px-6 py-4">
                                    ${tarea.estado}
                                </td>
                                <td class="px-6 py-4">
                                    <c:if test="${tarea.estado == Estado.NO_REALIZADO}">
                                        <a href="" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 focus:outline-none">Empezar</a>
                                    </c:if>
                                    <c:if test="${tarea.estado == Estado.PROCESO}">
                                        <a href="" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 focus:outline-none">Terminar</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Nombre</th>
                            <th>DNI</th>
                            <th>Contraseña</th>
                            <th>Cargo</th>
                            <th>Departamento</th>
                            <th>Tipo usuario</th>
                        </tr>
                    </tfoot>
                </table>
            </article>
        </section>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                $('#tablaTareas').DataTable();
            });
        </script>
    </body>
</html>
