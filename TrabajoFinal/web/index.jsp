<%-- 
    Document   : index
    Created on : 13 abr 2024, 12:21:44
    Author     : majemase
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="js/tailwindConfig.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.css" rel="stylesheet" />
        <title>Index</title>
    </head>
    <body>
        <header>
            <nav class="bg-red-500 p-5 flex gap-10">
                <c:if test="${empty usuario}">
                    <button data-modal-target="default-modal" data-modal-toggle="default-modal" type="button">
                        Inicia sesion
                    </button>
                </c:if>
                <c:if test="${not empty usuario}">
                    <a href="admin/gestionarEmpleados.jsp">Gestionar empleados</a>
                    <a href="admin/gestionarMateriales.jsp">Gestionar materiales</a>
                    <a href="empleado/gestionarTareas.jsp">Tareas</a>
                    <a href="empleado/gestionarProductividad.jsp">Consultar productividad</a>
                </c:if>
            </nav>
        </header>
        <!-- Main modal -->
        <div id="default-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
            <div class="relative p-4 w-full max-w-2xl max-h-full">
                <!-- Modal content -->
                <div class="relative bg-white rounded-lg shadow">
                    <!-- Modal header -->
                    <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t">
                        <h3 class="text-xl font-semibold text-gray-900">
                            Inicia sesion
                        </h3>
                        <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center" data-modal-hide="default-modal">
                            <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                            </svg>
                            <span class="sr-only">Close modal</span>
                        </button>
                    </div>
                    <!-- Modal body -->
                    <div class="p-4 md:p-5 space-y-4">
                        <form class="space-y-6" action="" method="POST">
                            <div>
                                <label for="inputUsu" class="block text-sm font-medium leading-6 text-gray-900">Usuario</label>
                                <div class="mt-2">
                                    <input id="inputUsu" name="usuario" type="text" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                                </div>
                            </div>

                            <div>
                                <div class="flex items-center justify-between">
                                    <label for="password" class="block text-sm font-medium leading-6 text-gray-900">Contraseña</label>
                                    <div class="text-sm">
                                        <a href="#" class="font-semibold text-indigo-600 hover:text-indigo-500"></a>
                                    </div>
                                </div>
                                <div class="mt-2">
                                    <input id="password" name="pass" type="password" autocomplete="current-password" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                                </div>
                            </div>
                            <div>
                                <button class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Inicia sesion</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <section>
            <article class="bg-blue-500 p-6">
                <div class="w-fit bg-purple-500 border-2 border-black">
                    <p class="p-6">Tu productividad: 00000000</p>
                </div>
                <div class="flex justify-center items-center">
                    <table class="border-2 border-black">
                        <caption>
                            Tareas del día
                        </caption>
                        <thead>
                            <tr>
                                <th class="border-r-2 border-black p-6">Tareas</th>
                                <th class="border-r-2 border-black p-6">Materiales</th>
                                <th class="border-r-2 border-black p-6">Localización</th>
                                <th class="p-6">Realizado</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                <c:if test="${not empty error}">
                    <div class="text-red-500">
                        ${error}
                    </div>
                </c:if>
            </article>
        </section>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.js"></script>
    </body>
</html>
