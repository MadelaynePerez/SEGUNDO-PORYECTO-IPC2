/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Modelos;

/**
 *
 * @author Ana
 */
public class Empleado {
    private int idEmpleado;
    private int idUsuario;
    private String especialidad;
    private String fotoPerfil;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Empleado(int idEmpleado, int idUsuario, String especialidad, String fotoPerfil) {
        this.idEmpleado = idEmpleado;
        this.idUsuario = idUsuario;
        this.especialidad = especialidad;
        this.fotoPerfil = fotoPerfil;
    }
    
}
