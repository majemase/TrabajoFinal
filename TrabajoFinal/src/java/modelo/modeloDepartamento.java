/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.DepartamentoJpaController;
import entidades.Departamento;
import entidades.Empleado;
import java.util.List;
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
}
