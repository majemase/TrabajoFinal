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
import modelo.TipoUsuario;
import modelo.modeloMateriales;

/**
 *
 * @author majemase
 */
@WebServlet(name = "Materiales", urlPatterns = {"/empleado/Materiales"})
public class Materiales extends HttpServlet {

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
        String vista = "/empleado/materiales.jsp";
        request.setAttribute("materiales", modeloMateriales.listarMateriales());
        if (request.getParameter("añadir") != null) {
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            if (TipoUsuario.ADMINISTRADOR.equals(((Empleado) request.getSession().getAttribute("usuario")).getTipoUsuario())) {
                modeloMateriales.añadirMateriales(nombre, precio, stock, true);
            } else {
                modeloMateriales.añadirMateriales(nombre, precio, stock, false);
            }
            response.sendRedirect(request.getContextPath() + "/empleado/Materiales");
            return;
        }
        if (request.getParameter("eliminar") != null) {
            modeloMateriales.delMat(parseLong(request.getParameter("id")));
        }
        if (request.getParameter("confirma") != null) {
            modeloMateriales.confirmaMat(parseLong(request.getParameter("id")));
        }
        if (request.getParameter("editar") != null) {
            modeloMateriales.editarMat(parseLong(request.getParameter("id")), request.getParameter("nombre"), request.getParameter("precio"), request.getParameter("stock"));
            response.sendRedirect(request.getContextPath() + "/empleado/Materiales");
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
