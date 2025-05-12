/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Services;

import com.mycompany.salondebelleza.DatosDB.QueryServicio;
import com.mycompany.salondebelleza.Modelos.Servicio;

/**
 *
 * @author Ana
 */
public class ServicioService {
      private final QueryServicio queryServicio = new QueryServicio();

    public boolean crearAnuncio(Servicio servicio) {
        return this.queryServicio.crear(servicio);
    }

    public boolean eliminarServicio(int id_servicio) {
        return this.queryServicio.EliminarServicio(id_servicio);
    }

    public boolean modificarServicio(Servicio servicio) {
        return this.queryServicio.modificarDatosServicio(servicio);
    }

    public boolean inactivarActivarServicio(int idServicio, boolean estado) {
       return this.queryServicio.activarOInactivarServicio(idServicio, estado);
        

    }
}
