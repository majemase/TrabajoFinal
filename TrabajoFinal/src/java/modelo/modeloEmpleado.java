/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.EmpleadoJpaController;
import entidades.Empleado;
import entidades.Tareas;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author majemase
 */
public class modeloEmpleado {

    static final String PU = "TrabajoFinalPU";

    public static List<Tareas> listaTareasEmpleado(Long idEmpleado) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        Empleado empleado = ejc.findEmpleado(idEmpleado);
        List<Tareas> tareasEmpleado = empleado.getTareas();
        return tareasEmpleado;
    }

    public static List<Empleado> listaEmpleado() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        List<Empleado> listaEmpleado = ejc.findEmpleadoEntities();
        return listaEmpleado;
    }
}
