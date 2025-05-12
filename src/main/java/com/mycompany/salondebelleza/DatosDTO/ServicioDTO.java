/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.DatosDTO;

/**
 *
 * @author Ana
 */
public class ServicioDTO {

    private int idServicio;
    private String nombre;
    private String descripcion;
    private double precio;
    private int duracion;
    private boolean estado;
    private String rutaCatalogo;

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getRutaCatalogo() {
        return rutaCatalogo;
    }

    public void setRutaCatalogo(String rutaCatalogo) {
        this.rutaCatalogo = rutaCatalogo;
    }

    public ServicioDTO(int idServicio, String nombre, String descripcion, double precio, int duracion, boolean estado, String rutaCatalogo) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracion = duracion;
        this.estado = estado;
        this.rutaCatalogo = rutaCatalogo;
    }
    
    
}
