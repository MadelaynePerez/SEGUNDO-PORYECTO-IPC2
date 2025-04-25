/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ana
 */
public class ConvertidorFecha {
     public java.sql.Date ConvertirFecha(String fechaStr){
        if (fechaStr == null) {
            return null;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(fechaStr, formato);
        return Date.valueOf(localDate);
    }
    
    public Date ObtenerFechaActual(){
        java.util.Date utilDate = new java.util.Date();
        return new java.sql.Date(utilDate.getTime());
    }
    public java.sql.Date ConvertirFechaDos(String fechaStr){
        if (fechaStr == null) {
            return null;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(fechaStr, formato);
        return Date.valueOf(localDate);
    }
}
