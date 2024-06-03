/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import modelo.Estado;

/**
 *
 * @author majemase
 */
@Entity
public class Tareas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_tarea;
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Date fecha;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToMany
    @JoinTable(
            name = "tarea_empleado",
            joinColumns = @JoinColumn(name = "id_tarea"),
            inverseJoinColumns = @JoinColumn(name = "id_empleado")
    )
    private List<Empleado> empleados;
    @ManyToMany
    @JoinTable(
            name = "tarea_material",
            joinColumns = @JoinColumn(name = "id_tarea"),
            inverseJoinColumns = @JoinColumn(name = "id_material")
    )
    private List<Materiales> materiales;
    @OneToMany
    @JoinColumn(name = "id_tarea")
    private List<Gastos> gastos;

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Materiales> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Materiales> materiales) {
        this.materiales = materiales;
    }

    public List<Gastos> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gastos> gastos) {
        this.gastos = gastos;
    }

    public Long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Long id_tarea) {
        this.id_tarea = id_tarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_tarea != null ? id_tarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_tarea fields are not set
        if (!(object instanceof Tareas)) {
            return false;
        }
        Tareas other = (Tareas) object;
        if ((this.id_tarea == null && other.id_tarea != null) || (this.id_tarea != null && !this.id_tarea.equals(other.id_tarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tareas[ id=" + id_tarea + " ]";
    }

}
