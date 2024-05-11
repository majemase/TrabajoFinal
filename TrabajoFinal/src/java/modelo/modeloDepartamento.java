/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.DepartamentoJpaController;
import entidades.Departamento;
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
        return listaDepartamento;
    }
}
