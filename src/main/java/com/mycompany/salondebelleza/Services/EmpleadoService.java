/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Services;

import com.mycompany.salondebelleza.DatosDB.QueryCliente;
import com.mycompany.salondebelleza.DatosDB.QueryEmpleado;
import com.mycompany.salondebelleza.Modelos.Cliente;
import com.mycompany.salondebelleza.Modelos.Empleado;
import com.mycompany.salondebelleza.Modelos.Usuario;

/**
 *
 * @author Ana
 */
public class EmpleadoService {
     private final QueryEmpleado queryEmpleado = new QueryEmpleado();
    public boolean crearEmpleado (Usuario usuario, Empleado empleado){
        return this.queryEmpleado.crear(usuario, empleado);
    } 
}
