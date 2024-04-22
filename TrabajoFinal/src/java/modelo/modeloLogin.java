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

    public static Empleado validarUsuario(String usuario, String pass) {
        Empleado u = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        List<Empleado> empleados = ejc.findEmpleadoEntities();
        boolean encontrado = false;
        for (int i = 0; i < empleados.size() && !encontrado; i++) {
            Empleado actual = empleados.get(i);
            if (actual.getUsuario().equals(usuario)) {
                encontrado = true;
                if (actual.getPass().equals(pass)) {
                    u = actual;
                }
            }
        }
        emf.close();
        return u;
    }
}
