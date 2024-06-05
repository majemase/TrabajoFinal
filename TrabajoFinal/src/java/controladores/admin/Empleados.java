/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.admin;

import entidades.Departamento;
import java.io.IOException;
import static java.lang.Long.parseLong;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cargo;
import modelo.TipoUsuario;
import modelo.modeloCargo;
import modelo.modeloDepartamento;
import modelo.modeloEmpleado;
import modelo.modeloTipoUsuario;

/**
 *
 * @author majemase
 */
@WebServlet(name = "Empleados", urlPatterns = {"/admin/Empleados"})
public class Empleados extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        String vista = "/admin/empleado.jsp";
        request.setAttribute("tipoUsus", TipoUsuario.values());
        request.setAttribute("cargos", Cargo.values());
        request.setAttribute("departamentos", modeloDepartamento.listaDepartamento());
        request.setAttribute("empleados", modeloEmpleado.listaEmpleado());
        if (request.getParameter("añadir") != null) {
            if (request.getParameter("nombre") != null || request.getParameter("email") != null
                    || request.getParameter("pass") != null || request.getParameter("dep") != null
                    || request.getParameter("cargo") != null || request.getParameter("tipoUsu") != null) {
                String nombre = request.getParameter("nombre");
                String email = request.getParameter("email");
                String pass = request.getParameter("pass");
                try {
                    String passCod = modeloEmpleado.codificar(pass);
                    Departamento departamento = modeloDepartamento.buscarDepartamentoId(parseLong(request.getParameter("dep")));
                    Cargo cargo = modeloCargo.buscarCargoStr(request.getParameter("cargo"));
                    TipoUsuario tipoUsu = modeloTipoUsuario.buscarTipoUsuStr(request.getParameter("tipoUsu"));
                    modeloEmpleado.añadirEmpleado(nombre, email, passCod, departamento, cargo, tipoUsu);
                    response.sendRedirect(request.getContextPath() + "/admin/Empleados");
                    return;
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    throw new ServletException("Error al codificar la contraseña", e);
                }
            }
        }
        if (request.getParameter("eliminar") != null) {
            modeloEmpleado.delEmp(parseLong(request.getParameter("id")));
        }
        if (request.getParameter("editar") != null) {
            modeloEmpleado.editarEmpleado(parseLong(request.getParameter("id")), request.getParameter("nombre"), request.getParameter("email"), request.getParameter("pass"), request.getParameter("cargo"), request.getParameter("dep"), request.getParameter("tipoUsu"));
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
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
