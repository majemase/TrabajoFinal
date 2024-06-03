/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.EmpleadoJpaController;
import entidades.Departamento;
import entidades.Empleado;
import entidades.Tareas;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        emf.close();
        return tareasEmpleado;
    }

    public static List<Empleado> listaEmpleado() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        List<Empleado> listaEmpleado = ejc.findEmpleadoEntities();
        emf.close();
        return listaEmpleado;
    }

    public static void añadirEmpleado(String nombre, String email, String pass, Departamento departamento, Cargo cargo, TipoUsuario tipoUsu) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        Empleado e = new Empleado();
        e.setNombre(nombre);
        e.setPass(pass);
        e.setCargo(cargo);
        e.setEmail(email);
        e.setDepartamento(departamento);
        e.setTipoUsuario(tipoUsu);
        ejc.create(e);
        emf.close();
    }

    public static void editarEmpleado(Long id, String pass2) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        Empleado e = ejc.findEmpleado(id);
        e.setPass(pass2);
    }

    public static String codificar(String pass) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(hash[i] & 0xff);
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static Empleado buscarEmpleadoId(Long id_empleado) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        Empleado e = ejc.findEmpleado(id_empleado);
        emf.close();
        return e;
    }
}
