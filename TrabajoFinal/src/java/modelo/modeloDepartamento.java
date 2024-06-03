/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.DepartamentoJpaController;
import dao.EmpleadoJpaController;
import dao.exceptions.NonexistentEntityException;
import entidades.Departamento;
import entidades.Empleado;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author majemase
 */
public class modeloDepartamento {

    final static String PU = "TrabajoFinalPU";

    public static List<Departamento> listaDepartamento() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        DepartamentoJpaController djc = new DepartamentoJpaController(emf);
        List<Departamento> listaDepartamento = djc.findDepartamentoEntities();
        emf.close();
        return listaDepartamento;
    }

    public static Departamento buscarDepartamentoId(Long id_departamento) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        DepartamentoJpaController djc = new DepartamentoJpaController(emf);
        Departamento d = djc.findDepartamento(id_departamento);
        emf.close();
        return d;
    }

    public static void añadirDep(String nombre) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
            DepartamentoJpaController djc = new DepartamentoJpaController(emf);
            Departamento d = new Departamento();
            d.setNombre(nombre);
            djc.create(d);
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Departamento verDepartamento(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        DepartamentoJpaController djc = new DepartamentoJpaController(emf);
        Departamento d = djc.findDepartamento(id);
        emf.close();
        return d;
    }

    public static void editarDep(String nombre, String[] empleados, String jefeDep, Long id) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        DepartamentoJpaController djc = new DepartamentoJpaController(emf);
        EmpleadoJpaController ejc = new EmpleadoJpaController(emf);
        Departamento d = djc.findDepartamento(id);
        List<Empleado> listEmpleados = new ArrayList<>();
        Empleado e;
        for (String empleado : empleados) {
            e = ejc.findEmpleado(parseLong(empleado));
            e.setDepartamento(d);
            e.setCargo(Cargo.EMPLEADO);
            ejc.edit(e);
            listEmpleados.add(e);
        }
        e = ejc.findEmpleado(parseLong(jefeDep));
        e.setCargo(Cargo.JEFEDEPARTAMENTO);
        listEmpleados.add(e);
        d.setNombre(nombre);
        d.setEmpleados(listEmpleados);
        try {
            djc.edit(d);
            emf.close();
        } catch (Exception ex) {
            Logger.getLogger(modeloDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void eliminarDepartamento(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        DepartamentoJpaController djc = new DepartamentoJpaController(emf);
        try {
            djc.destroy(id);
            emf.close();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(modeloDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
