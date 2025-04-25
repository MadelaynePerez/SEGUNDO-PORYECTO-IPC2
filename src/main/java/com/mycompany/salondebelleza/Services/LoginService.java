/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Services;

import com.mycompany.salondebelleza.DatosDB.QueryUsuario;
import com.mycompany.salondebelleza.Modelos.Usuario;
import com.mycompany.salondebelleza.Utils.EncriptarContrasenia;

/**
 *
 * @author Ana
 */
public class LoginService {
     public Usuario Login(String username, String password){
        QueryUsuario queryUsuario = new QueryUsuario();
        EncriptarContrasenia encriptador = new EncriptarContrasenia();
        return queryUsuario.login(username, encriptador.contraseniaEncriptada(password));
    }
}
