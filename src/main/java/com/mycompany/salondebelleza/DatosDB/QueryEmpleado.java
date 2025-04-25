/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.DatosDB;

import com.mycompany.salondebelleza.Modelos.Cliente;
import com.mycompany.salondebelleza.Modelos.Coneccion;
import com.mycompany.salondebelleza.Modelos.Empleado;
import com.mycompany.salondebelleza.Modelos.Usuario;
import com.mycompany.salondebelleza.Utils.EncriptarContrasenia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ana
 */
public class QueryEmpleado {

    public boolean crear(Usuario usuario, Empleado empleado) {
        Connection connection = null;
        PreparedStatement pstmtUsuario = null;
        PreparedStatement pstmtEmpleado = null;
        EncriptarContrasenia encriptado = new EncriptarContrasenia();
        ResultSet generatedKeys = null;

        try {
            connection = Coneccion.getConnection();
            connection.setAutoCommit(false);

            String sqlUsuario = "INSERT INTO usuario (nombre, correo, telefono, direccion, password, idRol, descripcion) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmtUsuario = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);

            pstmtUsuario.setString(1, usuario.getNombre());
            pstmtUsuario.setString(2, usuario.getCorreo());
            pstmtUsuario.setString(3, usuario.getTelefono());
            pstmtUsuario.setString(4, usuario.getDireccion());
            pstmtUsuario.setString(5, encriptado.contraseniaEncriptada(usuario.getPassword()));
            pstmtUsuario.setInt(6, usuario.getRol().getIdRol());
            pstmtUsuario.setString(7, usuario.getDescripcion());

            int filasInsertadasUsuario = pstmtUsuario.executeUpdate();

            if (filasInsertadasUsuario == 0) {
                connection.rollback();
                return false;
            }

            generatedKeys = pstmtUsuario.getGeneratedKeys();
            int idUsuario = -1;
            if (generatedKeys.next()) {
                idUsuario = generatedKeys.getInt(1);
            }

            String sqlempleado = "INSERT INTO empleado (idUsuario, especialidad, foto_perfil) VALUES (?, ?, ?)";
            pstmtEmpleado = connection.prepareStatement(sqlempleado);

            pstmtEmpleado.setInt(1, idUsuario);
            pstmtEmpleado.setString(2, empleado.getEspecialidad());
            pstmtEmpleado.setString(3, empleado.getFotoPerfil());
            

            int filasInsertadasEmpleado = pstmtEmpleado.executeUpdate();

            if (filasInsertadasEmpleado > 0) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException ex) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            if (pstmtUsuario != null) {
                try {
                    pstmtUsuario.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (pstmtEmpleado != null) {
                try {
                    pstmtEmpleado.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
}
