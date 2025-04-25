/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Services;

import com.mycompany.salondebelleza.Modelos.Rol;
import com.mycompany.salondebelleza.Modelos.Usuario;
import com.mycompany.salondebelleza.DatosDB.QueryUsuario;
import java.util.List;

/**
 *
 * @author Ana
 */
public class UsuarioService {

    private final QueryUsuario QueryUsuario = new QueryUsuario();

    public boolean CrearUsuario(String nombre, String correo, String telefono, String direccion, String password, int idRol, String descripcion) {
        Usuario user = new Usuario(-1,nombre, correo, telefono, direccion, password, new Rol(idRol, ""), descripcion,  true);
        return this.QueryUsuario.crear(user);
    }

    public boolean DesactivarUsuario(int userId) {
        return this.QueryUsuario.inactivarUsuario(userId);
    }

    public List<Usuario> ListarUsuarios() {
        return this.QueryUsuario.listarUsuarios();
    }

    public boolean CambiarRolDeUsuario(int rolId, int userId) {
        return this.QueryUsuario.CambiarRol(userId, rolId);
    }
    public boolean modificardatosUsuario(Usuario usuario) {
        return this.QueryUsuario.modificarDatosUsuario(usuario);
    }
}
