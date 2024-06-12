<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="modelo.TipoUsuario"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ProductivityTrack - Materiales</title>
        <link rel="icon" href="../assets/imagenes/logo.png">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://kit.fontawesome.com/05663c91b1.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../assets/css/main.css"/>
    </head>
    <body class="bg-light">
        <%@ include file="../header.jsp" %>
        <section class="container py-3">
            <div class="row justify-content-center">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <h1 class="text-azul">Listado de materiales</h1>
                </div>
            </div>

            <div class="row justify-content-center mt-3">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <button type="button" class="btn btn-primary bg-azul-oscuro" data-bs-toggle="modal" data-bs-target="#addDep">
                        <i class="fa-solid fa-plus"></i> Añadir material
                    </button>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-5 d-flex justify-content-center align-items-center">
                    <h5 class="text-azul">Manten y comprueba el inventario de tu empresa:</h5>
                </div>
            </div>
            <!-- Modal Añadir Materiales -->
            <div class="modal fade" id="addDep" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-azul" id="addModalLabel">Añadir Materiales</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="../empleado/Materiales" method="POST" onsubmit="validarMaterial(event)">
                                <div class="mb-3">
                                    <label for="nombre" class="form-label text-azul">Nombre</label>
                                    <input type="text" class="form-control text-azul" name="nombre" id="nombre" required>
                                    <div id="nombreError" class="text-danger"></div>
                                </div>
                                <div class="mb-3">
                                    <label for="precio" class="form-label text-azul">Precio</label>
                                    <input type="number" step="0.01" class="form-control text-azul" name="precio" id="precio" required>
                                    <div id="precioError" class="text-danger"></div>
                                </div>
                                <div class="mb-3">
                                    <label for="stock" class="form-label text-azul">Stock</label>
                                    <input type="number" class="form-control text-azul" name="stock" id="stock" required>
                                    <div id="stockError" class="text-danger"></div>
                                </div>
                                <input type="hidden" name="añadir" value="true"/>
                                <div class="d-flex justify-content-end gap-3">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-primary bg-azul-oscuro">Añadir Material</button>
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
                            <table id="tablaMateriales" class="table table-striped table-bordered align-items-center mb-0">
                                <thead>
                                    <tr>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Nombre</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Precio</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Stock</th>
                                            <c:if test="${usuario.tipoUsuario eq 'ADMINISTRADOR'}">
                                            <th class="text-center text-uppercase text-azul font-weight-bold">Opciones</th>
                                            </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="material" items="${materiales}">
                                        <c:if test="${material.aprobado == true || usuario.tipoUsuario eq 'ADMINISTRADOR'}">
                                            <tr>
                                                <td class="align-middle text-center">
                                                    <div class="d-flex px-2 py-1 justify-content-center">
                                                        <span class="text-sm">${material.nombre}</span>
                                                    </div>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-light text-black text-xs font-weight-bold">
                                                        ${material.precio}
                                                    </span>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-light text-black text-xs font-weight-bold">
                                                        ${material.stock}
                                                    </span>
                                                </td>
                                                <c:if test="${usuario.tipoUsuario eq 'ADMINISTRADOR'}">
                                                    <td class="align-middle text-center">
                                                        <c:if test="${material.aprobado eq false}">
                                                            <button type="button" onclick="confirMat(${material.id_material})" class="btn btn-success btn-sm">
                                                                <i class="fa-solid fa-check"></i>
                                                            </button>
                                                        </c:if>
                                                        <button type="button" onclick="verMat(${material.id_material})" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editarMat">
                                                            <i class="fa-solid fa-pen-to-square"></i>
                                                        </button>
                                                        <button type="button" onclick="confirmaDelMat(${material.id_material})" class="btn btn-danger btn-sm">
                                                            <i class="fa-solid fa-trash"></i>
                                                        </button>
                                                    </td>
                                                </c:if>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Nombre</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Precio</th>
                                        <th class="text-center text-uppercase text-azul font-weight-bold">Stock</th>
                                            <c:if test="${usuario.tipoUsuario eq 'ADMINISTRADOR'}">
                                            <th class="text-center text-uppercase text-azul font-weight-bold">Opciones</th>
                                            </c:if>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </article>
        </section>
        <!-- Modal Editar Material -->
        <div class="modal fade" id="editarMat" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="editarModalLabel">Editar Material</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="Materiales" method="POST" onsubmit="validarMaterialEdit(event)">
                            <div class="mb-3">
                                <label for="nombreEdit" class="form-label">Nombre</label>
                                <input type="text" class="form-control" name="nombre" id="nombreEdit">
                                <div id="nombreEditError" class="text-danger"></div>
                            </div>
                            <div class="mb-3">
                                <label for="precioEdit" class="form-label">Precio</label>
                                <input type="number" step="0.01" class="form-control" name="precio" id="precioEdit">
                                <div id="precioEditError" class="text-danger"></div>
                            </div>
                            <div class="mb-3">
                                <label for="stockEdit" class="form-label">Stock</label>
                                <input type="number" class="form-control" name="stock" id="stockEdit">
                                <div id="stockEditError" class="text-danger"></div>
                            </div>
                            <input type="hidden" name="id" id="idMaterialEdit" value=""/>
                            <div class="d-flex justify-content-end gap-3">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" value="editar" name="editar" class="btn btn-primary">Editar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
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
                                $('#tablaMateriales').DataTable({
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
