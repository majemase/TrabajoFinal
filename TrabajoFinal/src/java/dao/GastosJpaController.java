/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import entidades.Gastos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Tareas;
import entidades.Materiales;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author majemase
 */
public class GastosJpaController implements Serializable {

    public GastosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gastos gastos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tareas tarea = gastos.getTarea();
            if (tarea != null) {
                tarea = em.getReference(tarea.getClass(), tarea.getId_tarea());
                gastos.setTarea(tarea);
            }
            Materiales material = gastos.getMaterial();
            if (material != null) {
                material = em.getReference(material.getClass(), material.getId_material());
                gastos.setMaterial(material);
            }
            em.persist(gastos);
            if (tarea != null) {
                tarea.getGastos().add(gastos);
                tarea = em.merge(tarea);
            }
            if (material != null) {
                material.getGastos().add(gastos);
                material = em.merge(material);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gastos gastos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gastos persistentGastos = em.find(Gastos.class, gastos.getId());
            Tareas tareaOld = persistentGastos.getTarea();
            Tareas tareaNew = gastos.getTarea();
            Materiales materialOld = persistentGastos.getMaterial();
            Materiales materialNew = gastos.getMaterial();
            if (tareaNew != null) {
                tareaNew = em.getReference(tareaNew.getClass(), tareaNew.getId_tarea());
                gastos.setTarea(tareaNew);
            }
            if (materialNew != null) {
                materialNew = em.getReference(materialNew.getClass(), materialNew.getId_material());
                gastos.setMaterial(materialNew);
            }
            gastos = em.merge(gastos);
            if (tareaOld != null && !tareaOld.equals(tareaNew)) {
                tareaOld.getGastos().remove(gastos);
                tareaOld = em.merge(tareaOld);
            }
            if (tareaNew != null && !tareaNew.equals(tareaOld)) {
                tareaNew.getGastos().add(gastos);
                tareaNew = em.merge(tareaNew);
            }
            if (materialOld != null && !materialOld.equals(materialNew)) {
                materialOld.getGastos().remove(gastos);
                materialOld = em.merge(materialOld);
            }
            if (materialNew != null && !materialNew.equals(materialOld)) {
                materialNew.getGastos().add(gastos);
                materialNew = em.merge(materialNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = gastos.getId();
                if (findGastos(id) == null) {
                    throw new NonexistentEntityException("The gastos with id " + id + " no longer exists.");
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
            Gastos gastos;
            try {
                gastos = em.getReference(Gastos.class, id);
                gastos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gastos with id " + id + " no longer exists.", enfe);
            }
            Tareas tarea = gastos.getTarea();
            if (tarea != null) {
                tarea.getGastos().remove(gastos);
                tarea = em.merge(tarea);
            }
            Materiales material = gastos.getMaterial();
            if (material != null) {
                material.getGastos().remove(gastos);
                material = em.merge(material);
            }
            em.remove(gastos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gastos> findGastosEntities() {
        return findGastosEntities(true, -1, -1);
    }

    public List<Gastos> findGastosEntities(int maxResults, int firstResult) {
        return findGastosEntities(false, maxResults, firstResult);
    }

    private List<Gastos> findGastosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gastos.class));
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

    public Gastos findGastos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gastos.class, id);
        } finally {
            em.close();
        }
    }

    public int getGastosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gastos> rt = cq.from(Gastos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
