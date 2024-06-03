/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.admin;

import entidades.Departamento;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Long.parseLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.modeloDepartamento;
import modelo.modeloEmpleado;

/**
 *
 * @author majemase
 */
@WebServlet(name = "Departamentos", urlPatterns = {"/admin/Departamentos"})
public class Departamentos extends HttpServlet {

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
        String vista = "/admin/departamento.jsp";
        request.setAttribute("departamentos", modeloDepartamento.listaDepartamento());
        request.setAttribute("empleados", modeloEmpleado.listaEmpleado());
        if (request.getParameter("añadir") != null) {
            if (request.getParameter("nombre") != null) {
                String nombre = request.getParameter("nombre");
                if (!nombre.trim().isEmpty()) {
                    modeloDepartamento.añadirDep(nombre);
                    response.sendRedirect(request.getContextPath() + "/admin/Departamentos");
                    return;
                }
            }
        }
        if (request.getParameter("editar") != null) {
            String nombre = request.getParameter("nombre");
            String[] empleados = request.getParameterValues("empleadosDep");
            try {
                modeloDepartamento.editarDep(nombre, empleados, request.getParameter("jefeDep"), parseLong(request.getParameter("idDep")));
            } catch (Exception ex) {
                Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("eliminar") != null) {
            modeloDepartamento.eliminarDepartamento(parseLong(request.getParameter("id")));
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
