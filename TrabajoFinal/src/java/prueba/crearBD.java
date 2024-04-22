/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package prueba;

import dao.DepartamentoJpaController;
import dao.EmpleadoJpaController;
import dao.GastosJpaController;
import dao.MaterialesJpaController;
import dao.TareasJpaController;
import entidades.Departamento;
import entidades.Empleado;
import entidades.Gastos;
import entidades.Materiales;
import entidades.Tareas;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author majemase
 */
@WebServlet(name = "crearBD", urlPatterns = {"/crearBD"})
public class crearBD extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrabajoFinalPU");
        DepartamentoJpaController djc = new DepartamentoJpaController(emf);
        Departamento departamento = new Departamento();
        departamento.setNombre("administracion");
        djc.create(departamento);

        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        Empleado empleado = new Empleado();
        empleado.setDepartamento(departamento);
        empleado.setNombre("Jesus");
        empleado.setUsuario("majemase");
        empleado.setPass("majemaseAdmin");
        empleado.setPuntosProductividad(9999);
        empleado.setCargo("admin");
        ejc.create(empleado);

        TareasJpaController tjc = new TareasJpaController(emf);
        Tareas tarea = new Tareas();
        tarea.setDescripcion("Es una tarea de prueba");
        LocalDate fechaActual = LocalDate.now();
        tarea.setFechaInicio(fechaActual);
        tarea.setFechaFin(fechaActual);
        tarea.setEstado("Realizado");
        tjc.create(tarea);

        MaterialesJpaController mjc = new MaterialesJpaController(emf);
        Materiales material = new Materiales();
        material.setNombre("Material de prueba");
        material.setPrecio(10.0);
        material.setStock(100);
        mjc.create(material);

        GastosJpaController gjc = new GastosJpaController(emf);
        Gastos gasto = new Gastos();
        gasto.setTarea(tarea);
        gasto.setDescripcion("descripcion de los gastos (prueba)");
        gasto.setTransporte(0);
        gasto.setTotal_gastos(0);
        gjc.create(gasto);

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet crearBD</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>BD creada con datos de prueba</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
