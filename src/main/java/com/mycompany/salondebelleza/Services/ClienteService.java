/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Services;

import com.mycompany.salondebelleza.DatosDB.QueryCliente;
import com.mycompany.salondebelleza.Modelos.Cliente;
import com.mycompany.salondebelleza.Modelos.Usuario;

/**
 *
 * @author Ana
 */
public class ClienteService {
    
    private final QueryCliente queryCliente = new QueryCliente();
    public boolean crearCliente (Usuario usuario, Cliente cliente){
        return this.queryCliente.crear(usuario, cliente);
    } 
    public boolean modificarCliente(Usuario usuario, Cliente cliente){
        return this.queryCliente.modificarDatocliente(usuario, cliente);
    } 
}
