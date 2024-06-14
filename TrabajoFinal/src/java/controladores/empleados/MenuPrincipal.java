/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.empleados;

import entidades.Empleado;
import java.io.IOException;
import static java.lang.Long.parseLong;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.modeloDepartamento;
import modelo.modeloEmpleado;
import modelo.modeloTareas;

/**
 *
 * @author majemase
 */
@WebServlet(name = "MenuPrincipal", urlPatterns = {"/empleado/MenuPrincipal"})
public class MenuPrincipal extends HttpServlet {

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
        String vista = "/empleado/menuPrincipal.jsp";
        if (request.getSession() != null) {
            Empleado e = (Empleado) request.getSession().getAttribute("usuario");
            Long idEmpleado = e.getId_empleado();
            request.setAttribute("tareas", modeloEmpleado.listaTareasEmpleado(idEmpleado));
        }
        if (request.getParameter("editarAj") != null) {
            Long id = parseLong(request.getParameter("idEmpleado"));
            String pass = request.getParameter("passAj");
            if (pass.equals(pass)) {
                modeloEmpleado.editarEmpleado(id, pass);
            }
        }
        if (request.getParameter("estado") != null) {
            modeloTareas.editarEstado(parseLong(request.getParameter("id")), request.getParameter("estado"), request.getParameter("fecha"), request.getParameter("princ_fin"));
        }
        getServletContext().getRequestDispatcher(vista).forward(request, response);
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
