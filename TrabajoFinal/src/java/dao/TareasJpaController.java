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
import entidades.Empleado;
import java.util.ArrayList;
import java.util.List;
import entidades.Materiales;
import entidades.Gastos;
import entidades.Tareas;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author majemase
 */
public class TareasJpaController implements Serializable {

    public TareasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tareas tareas) {
        if (tareas.getEmpleados() == null) {
            tareas.setEmpleados(new ArrayList<Empleado>());
        }
        if (tareas.getMateriales() == null) {
            tareas.setMateriales(new ArrayList<Materiales>());
        }
        if (tareas.getGastos() == null) {
            tareas.setGastos(new ArrayList<Gastos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleado> attachedEmpleados = new ArrayList<Empleado>();
            for (Empleado empleadosEmpleadoToAttach : tareas.getEmpleados()) {
                empleadosEmpleadoToAttach = em.getReference(empleadosEmpleadoToAttach.getClass(), empleadosEmpleadoToAttach.getId_empleado());
                attachedEmpleados.add(empleadosEmpleadoToAttach);
            }
            tareas.setEmpleados(attachedEmpleados);
            List<Materiales> attachedMateriales = new ArrayList<Materiales>();
            for (Materiales materialesMaterialesToAttach : tareas.getMateriales()) {
                materialesMaterialesToAttach = em.getReference(materialesMaterialesToAttach.getClass(), materialesMaterialesToAttach.getId_material());
                attachedMateriales.add(materialesMaterialesToAttach);
            }
            tareas.setMateriales(attachedMateriales);
            List<Gastos> attachedGastos = new ArrayList<Gastos>();
            for (Gastos gastosGastosToAttach : tareas.getGastos()) {
                gastosGastosToAttach = em.getReference(gastosGastosToAttach.getClass(), gastosGastosToAttach.getId());
                attachedGastos.add(gastosGastosToAttach);
            }
            tareas.setGastos(attachedGastos);
            em.persist(tareas);
            for (Empleado empleadosEmpleado : tareas.getEmpleados()) {
                empleadosEmpleado.getTareas().add(tareas);
                empleadosEmpleado = em.merge(empleadosEmpleado);
            }
            for (Materiales materialesMateriales : tareas.getMateriales()) {
                materialesMateriales.getTareas().add(tareas);
                materialesMateriales = em.merge(materialesMateriales);
            }
            for (Gastos gastosGastos : tareas.getGastos()) {
                Tareas oldTareaOfGastosGastos = gastosGastos.getTarea();
                gastosGastos.setTarea(tareas);
                gastosGastos = em.merge(gastosGastos);
                if (oldTareaOfGastosGastos != null) {
                    oldTareaOfGastosGastos.getGastos().remove(gastosGastos);
                    oldTareaOfGastosGastos = em.merge(oldTareaOfGastosGastos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tareas tareas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tareas persistentTareas = em.find(Tareas.class, tareas.getId_tarea());
            List<Empleado> empleadosOld = persistentTareas.getEmpleados();
            List<Empleado> empleadosNew = tareas.getEmpleados();
            List<Materiales> materialesOld = persistentTareas.getMateriales();
            List<Materiales> materialesNew = tareas.getMateriales();
            List<Gastos> gastosOld = persistentTareas.getGastos();
            List<Gastos> gastosNew = tareas.getGastos();
            List<Empleado> attachedEmpleadosNew = new ArrayList<Empleado>();
            for (Empleado empleadosNewEmpleadoToAttach : empleadosNew) {
                empleadosNewEmpleadoToAttach = em.getReference(empleadosNewEmpleadoToAttach.getClass(), empleadosNewEmpleadoToAttach.getId_empleado());
                attachedEmpleadosNew.add(empleadosNewEmpleadoToAttach);
            }
            empleadosNew = attachedEmpleadosNew;
            tareas.setEmpleados(empleadosNew);
            List<Materiales> attachedMaterialesNew = new ArrayList<Materiales>();
            for (Materiales materialesNewMaterialesToAttach : materialesNew) {
                materialesNewMaterialesToAttach = em.getReference(materialesNewMaterialesToAttach.getClass(), materialesNewMaterialesToAttach.getId_material());
                attachedMaterialesNew.add(materialesNewMaterialesToAttach);
            }
            materialesNew = attachedMaterialesNew;
            tareas.setMateriales(materialesNew);
            List<Gastos> attachedGastosNew = new ArrayList<Gastos>();
            for (Gastos gastosNewGastosToAttach : gastosNew) {
                gastosNewGastosToAttach = em.getReference(gastosNewGastosToAttach.getClass(), gastosNewGastosToAttach.getId());
                attachedGastosNew.add(gastosNewGastosToAttach);
            }
            gastosNew = attachedGastosNew;
            tareas.setGastos(gastosNew);
            tareas = em.merge(tareas);
            for (Empleado empleadosOldEmpleado : empleadosOld) {
                if (!empleadosNew.contains(empleadosOldEmpleado)) {
                    empleadosOldEmpleado.getTareas().remove(tareas);
                    empleadosOldEmpleado = em.merge(empleadosOldEmpleado);
                }
            }
            for (Empleado empleadosNewEmpleado : empleadosNew) {
                if (!empleadosOld.contains(empleadosNewEmpleado)) {
                    empleadosNewEmpleado.getTareas().add(tareas);
                    empleadosNewEmpleado = em.merge(empleadosNewEmpleado);
                }
            }
            for (Materiales materialesOldMateriales : materialesOld) {
                if (!materialesNew.contains(materialesOldMateriales)) {
                    materialesOldMateriales.getTareas().remove(tareas);
                    materialesOldMateriales = em.merge(materialesOldMateriales);
                }
            }
            for (Materiales materialesNewMateriales : materialesNew) {
                if (!materialesOld.contains(materialesNewMateriales)) {
                    materialesNewMateriales.getTareas().add(tareas);
                    materialesNewMateriales = em.merge(materialesNewMateriales);
                }
            }
            for (Gastos gastosOldGastos : gastosOld) {
                if (!gastosNew.contains(gastosOldGastos)) {
                    gastosOldGastos.setTarea(null);
                    gastosOldGastos = em.merge(gastosOldGastos);
                }
            }
            for (Gastos gastosNewGastos : gastosNew) {
                if (!gastosOld.contains(gastosNewGastos)) {
                    Tareas oldTareaOfGastosNewGastos = gastosNewGastos.getTarea();
                    gastosNewGastos.setTarea(tareas);
                    gastosNewGastos = em.merge(gastosNewGastos);
                    if (oldTareaOfGastosNewGastos != null && !oldTareaOfGastosNewGastos.equals(tareas)) {
                        oldTareaOfGastosNewGastos.getGastos().remove(gastosNewGastos);
                        oldTareaOfGastosNewGastos = em.merge(oldTareaOfGastosNewGastos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tareas.getId_tarea();
                if (findTareas(id) == null) {
                    throw new NonexistentEntityException("The tareas with id " + id + " no longer exists.");
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
            Tareas tareas;
            try {
                tareas = em.getReference(Tareas.class, id);
                tareas.getId_tarea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tareas with id " + id + " no longer exists.", enfe);
            }
            List<Empleado> empleados = tareas.getEmpleados();
            for (Empleado empleadosEmpleado : empleados) {
                empleadosEmpleado.getTareas().remove(tareas);
                empleadosEmpleado = em.merge(empleadosEmpleado);
            }
            List<Materiales> materiales = tareas.getMateriales();
            for (Materiales materialesMateriales : materiales) {
                materialesMateriales.getTareas().remove(tareas);
                materialesMateriales = em.merge(materialesMateriales);
            }
            List<Gastos> gastos = tareas.getGastos();
            for (Gastos gastosGastos : gastos) {
                gastosGastos.setTarea(null);
                gastosGastos = em.merge(gastosGastos);
            }
            em.remove(tareas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tareas> findTareasEntities() {
        return findTareasEntities(true, -1, -1);
    }

    public List<Tareas> findTareasEntities(int maxResults, int firstResult) {
        return findTareasEntities(false, maxResults, firstResult);
    }

    private List<Tareas> findTareasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tareas.class));
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

    public Tareas findTareas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tareas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTareasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tareas> rt = cq.from(Tareas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
