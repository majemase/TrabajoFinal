/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.admin;

import entidades.Departamento;
import entidades.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Long.parseLong;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cargo;
import modelo.modeloDepartamento;

/**
 *
 * @author majemase
 */
@WebServlet(name = "mod", urlPatterns = {"/admin/verDep"})
public class verDep extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long departamentoId = Long.parseLong(request.getParameter("id"));
        Departamento departamento = modeloDepartamento.verDepartamento(departamentoId);

        // Construir el JSON manualmente
        String jsonDepartamento = "{";
        jsonDepartamento += "\"id\": \"" + departamento.getId_departamento() + "\",";
        jsonDepartamento += "\"nombre\": \"" + departamento.getNombre() + "\",";
        jsonDepartamento += "\"jefeDepartamento\": null,"; // Inicialmente establecemos al jefe de departamento como null

// Agregar la lista de empleados
        jsonDepartamento += "\"empleados\": [";
        List<Empleado> empleados = departamento.getEmpleados();
        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            jsonDepartamento += "{";
            jsonDepartamento += "\"id\": \"" + empleado.getId_empleado() + "\",";
            jsonDepartamento += "\"nombre\": \"" + empleado.getNombre() + "\",";
            jsonDepartamento += "\"cargo\": \"" + empleado.getCargo() + "\"";
            jsonDepartamento += "}";
            if (i < empleados.size() - 1) {
                jsonDepartamento += ",";
            }
        }
        jsonDepartamento += "]";

        jsonDepartamento += "}";

// Buscar al jefe de departamento
        for (Empleado empleado : empleados) {
            if (empleado.getCargo() == Cargo.JEFEDEPARTAMENTO) {
                // Si encontramos al jefe de departamento, lo agregamos al JSON
                jsonDepartamento = jsonDepartamento.replace("\"jefeDepartamento\": null",
                        "\"jefeDepartamento\": {\"id\": \"" + empleado.getId_empleado() + "\", \"nombre\": \"" + empleado.getNombre() + "\"}");
                break;
            }
        }

        // Enviar el JSON como respuesta al cliente
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonDepartamento);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
