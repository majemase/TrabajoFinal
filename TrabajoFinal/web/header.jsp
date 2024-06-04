<header>
    <nav class="navbar navbar-expand-lg bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand d-flex align-items-center text-white" href="http://localhost:8080/TrabajoFinal/empleado/MenuPrincipal">
                <img src="../assets/imagenes/logo.png" alt="Logo" width="75" class="d-inline-block align-text-top">
                ProductivityTrack
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link text-white" href="/TrabajoFinal/empleado/MenuPrincipal">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="/TrabajoFinal/empleado/Tareas">Tareas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="/TrabajoFinal/empleado/Materiales">Materiales</a>
                        </li>
                        <c:if test="${usuario.tipoUsuario eq TipoUsuario.ADMINISTRADOR}">
                            <li class="nav-item">
                                <a class="nav-link text-white" href="/TrabajoFinal/admin/Empleados">Empleados</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="/TrabajoFinal/admin/Departamentos">Departamentos</a>
                            </li>
                        </c:if>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Cuenta
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end bg-dark">
                                <li>
                                    <!-- Button trigger modal -->
                                    <button type="button" class="dropdown-item text-white bg-dark" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        <i class="fa-solid fa-gears"></i> Ajustes
                                    </button>
                                </li>
                                <li><a class="dropdown-item text-white bg-dark" href="../CerrarSesion"><i class="fa-solid fa-right-from-bracket"></i> Cerrar sesi�n</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content bg-dark text-white">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Ajustes de usuario</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="/TrabajoFinal/empleado/MenuPrincipal" method="POST">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="nombreAjustes" class="form-label">Nombre</label>
                        <input type="text" class="form-control bg-secondary text-white" value="${usuario.nombre}" name="nombreAjustes" id="nombreAjustes">
                    </div>
                    <div class="mb-3">
                        <label for="passAjustes" class="form-label">Contrase�a</label>
                        <input type="password" class="form-control bg-secondary text-white" value="${usuario.pass}" name="passAjustes" id="passAjustes">
                    </div>
                    <div class="mb-3">
                        <label for="cargoAjustes" class="form-label">Cargo</label>
                        <select name="cargoAjustes" id="cargoAjustes" class="form-select bg-secondary text-white" aria-label="Cargo">
                            <c:forEach var="cargo" items="${cargos}">
                                <option value="${cargo}" <c:if test="${usuario.cargo eq cargo}">selected</c:if>>${cargo}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="depAjustes" class="form-label">Departamento</label>
                        <select id="depAjustes" name="depAjustes" class="form-select bg-secondary text-white" aria-label="Departamento">
                            <c:forEach var="dep" items="${departamentos}">
                                <option value="${dep.id_departamento}" <c:if test="${usuario.departamento eq departamento}">selected</c:if>>${dep.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="text-warning">
                    ${error}
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="idEmpleado" id="idEmpleado" value="${empleado}">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="button" name="editarAj" class="btn btn-primary">Aplicar cambios</button>
                </div>
            </form>
        </div>
    </div>
</div>