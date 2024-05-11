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
import entidades.Departamento;
import entidades.Empleado;
import entidades.Tareas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author majemase
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getTareas() == null) {
            empleado.setTareas(new ArrayList<Tareas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento departamento = empleado.getDepartamento();
            if (departamento != null) {
                departamento = em.getReference(departamento.getClass(), departamento.getId_departamento());
                empleado.setDepartamento(departamento);
            }
            List<Tareas> attachedTareas = new ArrayList<Tareas>();
            for (Tareas tareasTareasToAttach : empleado.getTareas()) {
                tareasTareasToAttach = em.getReference(tareasTareasToAttach.getClass(), tareasTareasToAttach.getId_tarea());
                attachedTareas.add(tareasTareasToAttach);
            }
            empleado.setTareas(attachedTareas);
            em.persist(empleado);
            if (departamento != null) {
                departamento.getEmpleados().add(empleado);
                departamento = em.merge(departamento);
            }
            for (Tareas tareasTareas : empleado.getTareas()) {
                tareasTareas.getEmpleados().add(empleado);
                tareasTareas = em.merge(tareasTareas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getId_empleado());
            Departamento departamentoOld = persistentEmpleado.getDepartamento();
            Departamento departamentoNew = empleado.getDepartamento();
            List<Tareas> tareasOld = persistentEmpleado.getTareas();
            List<Tareas> tareasNew = empleado.getTareas();
            if (departamentoNew != null) {
                departamentoNew = em.getReference(departamentoNew.getClass(), departamentoNew.getId_departamento());
                empleado.setDepartamento(departamentoNew);
            }
            List<Tareas> attachedTareasNew = new ArrayList<Tareas>();
            for (Tareas tareasNewTareasToAttach : tareasNew) {
                tareasNewTareasToAttach = em.getReference(tareasNewTareasToAttach.getClass(), tareasNewTareasToAttach.getId_tarea());
                attachedTareasNew.add(tareasNewTareasToAttach);
            }
            tareasNew = attachedTareasNew;
            empleado.setTareas(tareasNew);
            empleado = em.merge(empleado);
            if (departamentoOld != null && !departamentoOld.equals(departamentoNew)) {
                departamentoOld.getEmpleados().remove(empleado);
                departamentoOld = em.merge(departamentoOld);
            }
            if (departamentoNew != null && !departamentoNew.equals(departamentoOld)) {
                departamentoNew.getEmpleados().add(empleado);
                departamentoNew = em.merge(departamentoNew);
            }
            for (Tareas tareasOldTareas : tareasOld) {
                if (!tareasNew.contains(tareasOldTareas)) {
                    tareasOldTareas.getEmpleados().remove(empleado);
                    tareasOldTareas = em.merge(tareasOldTareas);
                }
            }
            for (Tareas tareasNewTareas : tareasNew) {
                if (!tareasOld.contains(tareasNewTareas)) {
                    tareasNewTareas.getEmpleados().add(empleado);
                    tareasNewTareas = em.merge(tareasNewTareas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = empleado.getId_empleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId_empleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            Departamento departamento = empleado.getDepartamento();
            if (departamento != null) {
                departamento.getEmpleados().remove(empleado);
                departamento = em.merge(departamento);
            }
            List<Tareas> tareas = empleado.getTareas();
            for (Tareas tareasTareas : tareas) {
                tareasTareas.getEmpleados().remove(empleado);
                tareasTareas = em.merge(tareasTareas);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
