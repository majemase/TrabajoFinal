/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.EmpleadoJpaController;
import entidades.Empleado;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author majemase
 */
public class modeloLogin {

    static final String PU = "TrabajoFinalPU";

    public static Empleado validarEmpleado(String dni, String pass) {
        Empleado e = null;
        EntityManagerFactory emf = new Persistence().createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        List<Empleado> empleados = ejc.findEmpleadoEntities();
        boolean encontrado = false;
        for (int i = 0; i < empleados.size() && !encontrado; i++) {
            Empleado actual = empleados.get(i);
            if (actual.getDni().equals(dni)) {
                encontrado = true;
                if (actual.getPass().equals(pass)) {
                    e = actual;
                }
            }
        }
        emf.close();
        return e;
    }

}
