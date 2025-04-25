/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.DatosDTO;

import com.mycompany.salondebelleza.Modelos.Rol;

/**
 *
 * @author Ana
 */
public class ClienteDTO {

    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private String password;
    private int rol;
    private String descripcion;
    private String dpi;
    private String hobbi;
    private String gusto;
    private int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getHobbi() {
        return hobbi;
    }

    public void setHobbi(String hobbi) {
        this.hobbi = hobbi;
    }

    public String getGusto() {
        return gusto;
    }

    public void setGusto(String gusto) {
        this.gusto = gusto;
    }

    public ClienteDTO( String nombre, String correo, String telefono, String direccion, String password, int rol, String descripcion, String dpi, String hobbi, String gusto) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.password = password;
        this.rol = rol;
        this.descripcion = descripcion;
        this.dpi = dpi;
        this.hobbi = hobbi;
        this.gusto = gusto;
    }

    public ClienteDTO() {
    }

}
