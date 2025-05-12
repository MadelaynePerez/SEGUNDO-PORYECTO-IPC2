/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Services;

import com.mycompany.salondebelleza.DatosDB.QueryAnuncio;
import com.mycompany.salondebelleza.Modelos.Anuncio;

/**
 *
 * @author Ana
 */
public class AnuncioService {

    private final QueryAnuncio queryAnuncio = new QueryAnuncio();

    public boolean crearAnuncio(Anuncio anuncio) {
        return this.queryAnuncio.crear(anuncio);
    }

    public boolean eliminarAnuncio(int id_anuncio) {
        return this.queryAnuncio.EliminarAnuncio(id_anuncio);
    }

    public boolean modificarAnuncio(Anuncio anuncio) {
        return this.queryAnuncio.modificarDatosAnuncio(anuncio);
    }

    public boolean inactivarActivarAnuncio(int id_anuncio, boolean estado) {
        if (estado== true) {
            return this.queryAnuncio.activarAnuncio(id_anuncio);
        } else {
            return this.queryAnuncio.inactivarAnuncio(id_anuncio);
        }

    }
}
