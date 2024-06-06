/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import entidades.Empleado;
import entidades.Tareas;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import utilidades.Email;
import utilidades.Utilidades;

/**
 *
 * @author majemase
 */
public class modeloEmail {

    public static void enviaEmail(List<Tareas> listTar) {
        for (Tareas tarea : listTar) {
            for (Empleado empleado : tarea.getEmpleados()) {
                if (tarea.getFecha_fin() == null && fueraTiempo(tarea.getFecha())) {
                    String to = empleado.getEmail();
                    String subject = "Tarea retrasada desde el dia " + tarea.getFecha();
                    String text = "Debe realizar la tarea puesta la fecha " + tarea.getFecha() + " aqui esta la descripción de la tarea: " + tarea.getDescripcion();
                    String from = "marcos.segura.manuel.jesus@iescamas.es";
                    String password = "Marcossegura03!";
                    Email email = new Email();
                    email.setTo(to);
                    email.setSubject(subject);
                    email.setText(text);
                    email.setFrom(from);
                    Utilidades u = new Utilidades();
                    try {
                        u.enviarEmail(email, password);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    private static boolean fueraTiempo(Date fechaCreacion) {
        Date fechaActual = new Date();
        long diferenciaEnMilisegundos = fechaActual.getTime() - fechaCreacion.getTime();
        long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
        return diferenciaEnDias >= 10;
    }

}
