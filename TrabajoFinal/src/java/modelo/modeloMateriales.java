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
    
    public static void aņadirMateriales(String nombre, double precio, int stock, boolean aprobado) {
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
    
    public static void editarMat(Long materialId, String nombre, String precioStr, String stockStr) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        MaterialesJpaController mjc = new MaterialesJpaController(emf);
        Materiales m = mjc.findMateriales(materialId);
        m.setNombre(nombre);
        Double precio = Double.parseDouble(precioStr);
        m.setPrecio(precio);
        int stock = Integer.parseInt(stockStr);
        m.setStock(stock);
        try {
            mjc.edit(m);
            emf.close();
        } catch (Exception ex) {
            Logger.getLogger(modeloMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Materiales verMat(Long id_material) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        MaterialesJpaController mjc = new MaterialesJpaController(emf);
        Materiales m = mjc.findMateriales(id_material);
        emf.close();
        return m;
    }
    
    public static Materiales buscarMat(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        MaterialesJpaController mjc = new MaterialesJpaController(emf);
        return mjc.findMateriales(id);
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
    
    public static void confirmaMat(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        MaterialesJpaController mjc = new MaterialesJpaController(emf);
        Materiales m = mjc.findMateriales(id);
        m.setAprobado(true);
        try {
            mjc.edit(m);
            emf.close();
        } catch (Exception ex) {
            Logger.getLogger(modeloMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
