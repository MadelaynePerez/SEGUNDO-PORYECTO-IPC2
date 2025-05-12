/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.DatosDTO;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Ana
 */
public class CitaDTO {
     private int idCita;
    private int idCliente;
    private int idEmpleado;
    private int idServicio;
    private String fecha;
    private String hora;
    private int duracion;

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    private EstadoCita estado;

    public enum EstadoCita {
        PENDIENTE, COMPLETADA, CANCELADA
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public CitaDTO(int idCita, int idCliente, int idEmpleado, int idServicio, String fecha, String hora, EstadoCita estado) {
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idServicio = idServicio;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }
}
