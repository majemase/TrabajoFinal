/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.MaterialesJpaController;
import entidades.Materiales;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author majemase
 */
public class modeloMateriales {

    final static String PU = "TrabajoFinalPU";

    public static void añadirMateriales(String nombre, Long precio, int stock) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        MaterialesJpaController mjc = new MaterialesJpaController(emf);
        Materiales m = new Materiales();
        m.setNombre(nombre);
        m.setPrecio(precio);
        m.setStock(stock);
        mjc.create(m);
        emf.close();
    }
}
