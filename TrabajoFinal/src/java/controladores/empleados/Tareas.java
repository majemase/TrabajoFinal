/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.empleados;

import entidades.Departamento;
import entidades.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Long.parseLong;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cargo;
import modelo.modeloEmail;
import modelo.modeloEmpleado;
import modelo.modeloTareas;

/**
 *
 * @author majemase
 */
@WebServlet(name = "Tareas", urlPatterns = {"/empleado/Tareas"})
public class Tareas extends HttpServlet {

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
        String vista = "/empleado/tareas.jsp";
        request.setAttribute("empleados", modeloEmpleado.listaEmpleado());
        Empleado usuario = (Empleado) request.getSession().getAttribute("usuario");

        if (usuario.getCargo().equals(Cargo.JEFEDEPARTAMENTO)) {
            Departamento departamento = usuario.getDepartamento();
            request.setAttribute("tareas", modeloTareas.listarTareasDep(departamento.getId_departamento()));
        } else if (usuario.getCargo().equals(Cargo.JEFE)) {
            request.setAttribute("tareas", modeloTareas.listarTareas());
        } else {
            Long idEmpleado = usuario.getId_empleado();
            request.setAttribute("tareas", modeloTareas.listarTareasEmp(idEmpleado));
        }
        if (request.getParameter("añadir") != null) {
            String desc = request.getParameter("desc");
            String fecha = request.getParameter("fecha");
            String[] listaEmpleado = request.getParameterValues("listaEmpleado");
            try {
                modeloTareas.añadirTarea(desc, listaEmpleado, fecha);
            } catch (ParseException ex) {
                Logger.getLogger(Tareas.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect(request.getContextPath() + "/empleado/Tareas");
            return;
        }
        if (request.getParameter("eliminar") != null) {
            modeloTareas.eliminarTarea(parseLong(request.getParameter("id")));
        }
        if (request.getParameter("email") != null) {
            modeloEmail.enviaEmail(modeloTareas.listarTareas());
            response.sendRedirect(request.getContextPath() + "/empleado/Tareas");
            return;
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
