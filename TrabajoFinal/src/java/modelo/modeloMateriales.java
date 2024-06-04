/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.MaterialesJpaController;
import dao.exceptions.NonexistentEntityException;
import entidades.Materiales;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author majemase
 */
public class modeloMateriales {

    final static String PU = "TrabajoFinalPU";

    public static void añadirMateriales(String nombre, double precio, int stock, boolean aprobado) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        MaterialesJpaController mjc = new MaterialesJpaController(emf);
        Materiales m = new Materiales();
        m.setNombre(nombre);
        m.setPrecio(precio);
        m.setStock(stock);
        m.setAprobado(aprobado);
        mjc.create(m);
        emf.close();
    }

    public static List<Materiales> listarMateriales() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        MaterialesJpaController mjc = new MaterialesJpaController(emf);
        return mjc.findMaterialesEntities();
    }

    public static void delMat(Long id_material) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        MaterialesJpaController mjc = new MaterialesJpaController(emf);
        try {
            mjc.destroy(id_material);
            emf.close();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(modeloMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
