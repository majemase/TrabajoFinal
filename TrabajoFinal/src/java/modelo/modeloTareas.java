/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.TareasJpaController;
import entidades.Empleado;
import entidades.Tareas;
import static java.lang.Long.parseLong;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author majemase
 */
public class modeloTareas {

    final static String PU = "TrabajoFinalPU";

    public static void añadirTarea(String desc, String[] empleados, String fechaStr) throws ParseException {
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
}
