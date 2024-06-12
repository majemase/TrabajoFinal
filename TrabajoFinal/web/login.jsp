<%-- 
    Document   : login.jsp
    Created on : 7 may 2024, 17:27:50
    Author     : majemase
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ProductivityTrack - Login</title>
        <link rel="icon" href="assets/imagenes/logo.png">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="assets/css/main.css"/>
    </head>
    <body class="d-flex justify-content-center align-items-center vh-100 bg-azul">
        <div class="card shadow-sm p-4" style="max-width: 24rem;">
            <div class="card-body">
                <h5 class="card-title mb-4 text-azul">Bienvenido a ProductivityTrack</h5>
                <form id="formLogin" method="POST" onsubmit="validarLogin(event)">
                    <div class="mb-3">
                        <label for="email" class="form-label text-azul">Tu email</label>
                        <input type="email" name="email" id="email" class="form-control text-azul" placeholder="Escriba aqui su email" />
                    </div>
                    <div class="mb-3">
                        <label for="pass" class="form-label text-azul">Tu contraseña</label>
                        <input type="password" name="pass" id="pass" class="form-control text-azul" placeholder="Escriba aqui su contraseña" />
                    </div>
                    <div id="error" class="mb-3 text-danger">
                        ${error}
                    </div>
                    <button type="submit" class="btn bg-azul-oscuro text-white w-100">Iniciar sesión</button>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
