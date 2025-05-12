/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Modelos;

import java.sql.Date;

/**
 *
 * @author Ana
 */
public class Anuncio {
     private int idAnuncio;
    private int idUsuario;
    private String tipo;           
    private String contenido;
    private String url;
    private int duracionDias;
    private java.sql.Date fechaInicio;
    private boolean estado;
    private Usuario usuario;
    

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Anuncio(int idAnuncio, int idUsuario, String tipo, String contenido, String url, int duracionDias, Date fechaInicio, boolean estado) {
        this.idAnuncio = idAnuncio;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.contenido = contenido;
        this.url = url;
        this.duracionDias = duracionDias;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }
    
    
}
