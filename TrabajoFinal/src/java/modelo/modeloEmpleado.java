/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.EmpleadoJpaController;
import dao.exceptions.NonexistentEntityException;
import entidades.Departamento;
import entidades.Empleado;
import entidades.Tareas;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static void editarEmpleado(Long id, String pass) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        Empleado e = ejc.findEmpleado(id);
        String passCod;
        try {
            passCod = codificar(pass);
            e.setPass(passCod);
            ejc.edit(e);
            emf.close();
        } catch (Exception ex) {
            Logger.getLogger(modeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editarEmpleado(Long id, String nombre, String email, String pass, String cargo, Long dep, String tipoUsu) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
            EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
            Empleado e = ejc.findEmpleado(id);
            e.setNombre(nombre);
            e.setEmail(email);
            String passCod = codificar(pass);
            e.setPass(passCod);
            Cargo carg = Cargo.EMPLEADO;
            switch (cargo) {
                case "JEFE":
                    carg = Cargo.JEFE;
                    break;
                case "JEFEDEPARTAMENTO":
                    carg = Cargo.JEFEDEPARTAMENTO;
                    break;
                case "EMPLEADO":
                    carg = Cargo.EMPLEADO;
                    break;
            }
            Departamento departamento = modeloDepartamento.buscarDepartamentoId(dep);
            e.setCargo(carg);
            e.setDepartamento(departamento);
            TipoUsuario tipUsu = TipoUsuario.EMPLEADO;
            switch (tipoUsu) {
                case "ADMINISTRADOR":
                    tipUsu = TipoUsuario.ADMINISTRADOR;
                    break;
                case "EMPLEADO":
                    tipUsu = TipoUsuario.EMPLEADO;
                    break;
            }
            e.setTipoUsuario(tipUsu);
            try {
                ejc.edit(e);
                emf.close();
            } catch (Exception ex) {
                Logger.getLogger(modeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(modeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public static void delEmp(Long id_empleado) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        try {
            ejc.destroy(id_empleado);
            emf.close();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(modeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Empleado verEmpleado(Long id_empleado) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        Empleado e = ejc.findEmpleado(id_empleado);
        emf.close();
        return e;
    }
}
