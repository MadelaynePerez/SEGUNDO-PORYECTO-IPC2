/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Modelos;

/**
 *
 * @author Ana
 */
public class Cliente {
    private int idCliente;
    private int idUsuario;
    private String hobbi;
    private String gusto;
    private String dpi;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }
     public Cliente() {
    }
    
    public Cliente(int idCliente, int idUsuario, String hobbi, String gusto, String dpi) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.hobbi = hobbi;
        this.gusto = gusto;
        this.dpi = dpi;
    }

   
    
}
