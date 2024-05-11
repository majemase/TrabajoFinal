/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Tareas;
import java.util.ArrayList;
import java.util.List;
import entidades.Gastos;
import entidades.Materiales;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author majemase
 */
public class MaterialesJpaController implements Serializable {

    public MaterialesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materiales materiales) {
        if (materiales.getTareas() == null) {
            materiales.setTareas(new ArrayList<Tareas>());
        }
        if (materiales.getGastos() == null) {
            materiales.setGastos(new ArrayList<Gastos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tareas> attachedTareas = new ArrayList<Tareas>();
            for (Tareas tareasTareasToAttach : materiales.getTareas()) {
                tareasTareasToAttach = em.getReference(tareasTareasToAttach.getClass(), tareasTareasToAttach.getId_tarea());
                attachedTareas.add(tareasTareasToAttach);
            }
            materiales.setTareas(attachedTareas);
            List<Gastos> attachedGastos = new ArrayList<Gastos>();
            for (Gastos gastosGastosToAttach : materiales.getGastos()) {
                gastosGastosToAttach = em.getReference(gastosGastosToAttach.getClass(), gastosGastosToAttach.getId());
                attachedGastos.add(gastosGastosToAttach);
            }
            materiales.setGastos(attachedGastos);
            em.persist(materiales);
            for (Tareas tareasTareas : materiales.getTareas()) {
                tareasTareas.getMateriales().add(materiales);
                tareasTareas = em.merge(tareasTareas);
            }
            for (Gastos gastosGastos : materiales.getGastos()) {
                Materiales oldMaterialOfGastosGastos = gastosGastos.getMaterial();
                gastosGastos.setMaterial(materiales);
                gastosGastos = em.merge(gastosGastos);
                if (oldMaterialOfGastosGastos != null) {
                    oldMaterialOfGastosGastos.getGastos().remove(gastosGastos);
                    oldMaterialOfGastosGastos = em.merge(oldMaterialOfGastosGastos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materiales materiales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiales persistentMateriales = em.find(Materiales.class, materiales.getId_material());
            List<Tareas> tareasOld = persistentMateriales.getTareas();
            List<Tareas> tareasNew = materiales.getTareas();
            List<Gastos> gastosOld = persistentMateriales.getGastos();
            List<Gastos> gastosNew = materiales.getGastos();
            List<Tareas> attachedTareasNew = new ArrayList<Tareas>();
            for (Tareas tareasNewTareasToAttach : tareasNew) {
                tareasNewTareasToAttach = em.getReference(tareasNewTareasToAttach.getClass(), tareasNewTareasToAttach.getId_tarea());
                attachedTareasNew.add(tareasNewTareasToAttach);
            }
            tareasNew = attachedTareasNew;
            materiales.setTareas(tareasNew);
            List<Gastos> attachedGastosNew = new ArrayList<Gastos>();
            for (Gastos gastosNewGastosToAttach : gastosNew) {
                gastosNewGastosToAttach = em.getReference(gastosNewGastosToAttach.getClass(), gastosNewGastosToAttach.getId());
                attachedGastosNew.add(gastosNewGastosToAttach);
            }
            gastosNew = attachedGastosNew;
            materiales.setGastos(gastosNew);
            materiales = em.merge(materiales);
            for (Tareas tareasOldTareas : tareasOld) {
                if (!tareasNew.contains(tareasOldTareas)) {
                    tareasOldTareas.getMateriales().remove(materiales);
                    tareasOldTareas = em.merge(tareasOldTareas);
                }
            }
            for (Tareas tareasNewTareas : tareasNew) {
                if (!tareasOld.contains(tareasNewTareas)) {
                    tareasNewTareas.getMateriales().add(materiales);
                    tareasNewTareas = em.merge(tareasNewTareas);
                }
            }
            for (Gastos gastosOldGastos : gastosOld) {
                if (!gastosNew.contains(gastosOldGastos)) {
                    gastosOldGastos.setMaterial(null);
                    gastosOldGastos = em.merge(gastosOldGastos);
                }
            }
            for (Gastos gastosNewGastos : gastosNew) {
                if (!gastosOld.contains(gastosNewGastos)) {
                    Materiales oldMaterialOfGastosNewGastos = gastosNewGastos.getMaterial();
                    gastosNewGastos.setMaterial(materiales);
                    gastosNewGastos = em.merge(gastosNewGastos);
                    if (oldMaterialOfGastosNewGastos != null && !oldMaterialOfGastosNewGastos.equals(materiales)) {
                        oldMaterialOfGastosNewGastos.getGastos().remove(gastosNewGastos);
                        oldMaterialOfGastosNewGastos = em.merge(oldMaterialOfGastosNewGastos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = materiales.getId_material();
                if (findMateriales(id) == null) {
                    throw new NonexistentEntityException("The materiales with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiales materiales;
            try {
                materiales = em.getReference(Materiales.class, id);
                materiales.getId_material();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiales with id " + id + " no longer exists.", enfe);
            }
            List<Tareas> tareas = materiales.getTareas();
            for (Tareas tareasTareas : tareas) {
                tareasTareas.getMateriales().remove(materiales);
                tareasTareas = em.merge(tareasTareas);
            }
            List<Gastos> gastos = materiales.getGastos();
            for (Gastos gastosGastos : gastos) {
                gastosGastos.setMaterial(null);
                gastosGastos = em.merge(gastosGastos);
            }
            em.remove(materiales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materiales> findMaterialesEntities() {
        return findMaterialesEntities(true, -1, -1);
    }

    public List<Materiales> findMaterialesEntities(int maxResults, int firstResult) {
        return findMaterialesEntities(false, maxResults, firstResult);
    }

    private List<Materiales> findMaterialesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materiales.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Materiales findMateriales(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materiales.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materiales> rt = cq.from(Materiales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
