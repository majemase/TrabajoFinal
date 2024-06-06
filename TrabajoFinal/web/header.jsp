<header>
    <nav class="navbar navbar-expand-lg bg-azul">
        <div class="container-fluid">
            <a class="navbar-brand d-flex align-items-center text-azul" href="/TrabajoFinal/empleado/MenuPrincipal">
                <img src="../assets/imagenes/logo.png" alt="Logo" width="75" class="d-inline-block align-text-top">
                ProductivityTrack
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa-solid fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link text-azul" href="/TrabajoFinal/empleado/MenuPrincipal">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-azul" href="/TrabajoFinal/empleado/Tareas">Tareas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-azul" href="/TrabajoFinal/empleado/Materiales">Materiales</a>
                        </li>
                        <c:if test="${usuario.tipoUsuario eq TipoUsuario.ADMINISTRADOR}">
                            <li class="nav-item">
                                <a class="nav-link text-azul" href="/TrabajoFinal/admin/Empleados">Empleados</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-azul" href="/TrabajoFinal/admin/Departamentos">Departamentos</a>
                            </li>
                        </c:if>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-azul" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fa-solid fa-user"></i> Cuenta
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end bg-azul">
                                <li>
                                    <!-- Button trigger modal -->
                                    <button type="button" class="dropdown-item text-azul " data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        <i class="fa-solid fa-gears"></i> Ajustes
                                    </button>
                                </li>
                                <li><a class="dropdown-item text-azul" href="../CerrarSesion"><i class="fa-solid fa-right-from-bracket"></i> Cerrar sesión</a></li>
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
                        <label class="form-label">Nombre</label>
                        <p class="text-white">${usuario.nombre}</p>
                    </div>
                    <div class="mb-3">
                        <label for="passAj" class="form-label">Contraseña</label>
                        <input type="password" class="form-control bg-secondary text-white" value="${usuario.pass}" name="passAj" id="passAj">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Cargo </label>
                        <p class="text-white">${usuario.cargo}</p>
                    </div>
                </div>
                <div class="text-warning">
                    ${error}
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="idEmpleado" id="idEmpleado" value="${usuario.id_empleado}">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="submit" name="editarAj" class="btn btn-primary">Aplicar cambios</button>
                </div>
            </form>
        </div>
    </div>
</div>