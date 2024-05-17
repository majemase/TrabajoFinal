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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <section class="p-3">
            <article>

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
