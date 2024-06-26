/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.DepartamentoJpaController;
import dao.TareasJpaController;
import dao.exceptions.NonexistentEntityException;
import entidades.Departamento;
import entidades.Empleado;
import entidades.Tareas;
import static java.lang.Long.parseLong;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author majemase
 */
public class modeloTareas {

    final static String PU = "TrabajoFinalPU";

    public static void aņadirTarea(String desc, String[] empleados, String fechaStr) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        TareasJpaController tjc = new TareasJpaController(emf);
        List<Empleado> listaEmpleados = new ArrayList<>();
        for (String empleado : empleados) {
            listaEmpleados.add(modeloEmpleado.buscarEmpleadoId(parseLong(empleado)));
        }
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = formato.parse(fechaStr);
        Tareas t = new Tareas();
        t.setDescripcion(desc);
        t.setEstado(Estado.NO_REALIZADO);
        t.setEmpleados(listaEmpleados);
        t.setFecha(fecha);
        tjc.create(t);
        emf.close();
    }

    public static List<Map<String, Object>> grafica() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        TareasJpaController tjc = new TareasJpaController(emf);
        Map<Long, Map<String, Object>> tareasPorEmpleado = new HashMap<>();
        for (Tareas tarea : tjc.findTareasEntities()) {
            for (Empleado empleado : tarea.getEmpleados()) {
                Map<String, Object> datosEmpleado = tareasPorEmpleado.get(empleado.getId_empleado());
                if (datosEmpleado == null) {
                    datosEmpleado = new HashMap<>();
                    datosEmpleado.put("idEmpleado", empleado.getId_empleado());
                    datosEmpleado.put("nombre", empleado.getNombre());
                    datosEmpleado.put("numeroDeTareas", 0L);
                    tareasPorEmpleado.put(empleado.getId_empleado(), datosEmpleado);
                }
                datosEmpleado.put("numeroDeTareas", (Long) datosEmpleado.get("numeroDeTareas") + 1);
            }
        }

        return new ArrayList<>(tareasPorEmpleado.values());
    }

    public static void editarEstado(Long id, String estado, String fechaStr, String opciones) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        TareasJpaController tjc = new TareasJpaController(emf);
        Tareas t = tjc.findTareas(id);
        if ("proceso".equalsIgnoreCase(estado)) {
            t.setEstado(Estado.PROCESO);
        } else {
            t.setEstado(Estado.REALIZADO);
        }
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        try {
            Date fecha = formato.parse(fechaStr);
            if ("ini".equals(opciones)) {
                t.setFecha_inicio(fecha);
            } else {
                t.setFecha_fin(fecha);
            }
            tjc.edit(t);
            emf.close();

        } catch (Exception ex) {
            Logger.getLogger(modeloTareas.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Tareas> listarTareas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        TareasJpaController tjc = new TareasJpaController(emf);
        return tjc.findTareasEntities();
    }

    public static List<Tareas> listarTareasEmp(Long id_empleado) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        TareasJpaController tjc = new TareasJpaController(emf);
        List<Tareas> todasLasTareas = tjc.findTareasEntities();
        List<Tareas> tareasEmp = new ArrayList<>();
        for (Tareas tarea : todasLasTareas) {
            for (Empleado empleado : tarea.getEmpleados()) {
                if (empleado.getId_empleado().equals(id_empleado)) {
                    tareasEmp.add(tarea);
                    break;
                }
            }
        }
        emf.close();
        return tareasEmp;
    }

    public static List<Tareas> listarTareasDep(Long id_departamento) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        TareasJpaController tjc = new TareasJpaController(emf);
        DepartamentoJpaController djc = new DepartamentoJpaController(emf);
        Set<Tareas> tareasDepartamentoSet = new HashSet<>();
        Departamento departamento = djc.findDepartamento(id_departamento);
        List<Empleado> empleadosDepartamento = departamento.getEmpleados();
        for (Empleado empleado : empleadosDepartamento) {
            List<Tareas> tareasEmpleado = empleado.getTareas();
            tareasDepartamentoSet.addAll(tareasEmpleado);
        }
        emf.close();
        return new ArrayList<>(tareasDepartamentoSet);
    }

    public static void eliminarTarea(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        TareasJpaController tjc = new TareasJpaController(emf);
        try {
            tjc.destroy(id);

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(modeloTareas.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        emf.close();
    }
}
