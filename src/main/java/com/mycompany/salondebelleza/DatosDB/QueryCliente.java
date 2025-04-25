/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.DatosDB;

import com.mycompany.salondebelleza.Modelos.Cliente;
import com.mycompany.salondebelleza.Modelos.Coneccion;
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
public class QueryCliente {

    public boolean crear(Usuario usuario, Cliente cliente) {
        Connection connection = null;
        PreparedStatement pstmtUsuario = null;
        PreparedStatement pstmtCliente = null;
        EncriptarContrasenia encriptado = new EncriptarContrasenia();
        ResultSet generatedKeys = null;

        try {
            connection = Coneccion.getConnection();

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

            String sqlCliente = "INSERT INTO cliente (idUsuario, dpi, hobbi, gusto) VALUES (?, ?, ?, ?)";
            pstmtCliente = connection.prepareStatement(sqlCliente);

            pstmtCliente.setInt(1, idUsuario);
            pstmtCliente.setString(2, cliente.getDpi());
            pstmtCliente.setString(3, cliente.getHobbi());
            pstmtCliente.setString(4, cliente.getGusto());

            int filasInsertadasCliente = pstmtCliente.executeUpdate();

            if (filasInsertadasCliente > 0) {
                connection.commit();
                return false;
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
            if (pstmtCliente != null) {
                try {
                    pstmtCliente.close();
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

    public boolean modificarDatocliente(Usuario usuario, Cliente cliente) {
        Connection connection = null;
        PreparedStatement pstmtUsuario = null;
        PreparedStatement pstmtCliente = null;

        try {
            connection = Coneccion.getConnection();
            connection.setAutoCommit(false);

            String sqlUsuario = "UPDATE usuario SET nombre = ?, correo = ?, telefono = ?, direccion = ?, idRol = ?, descripcion = ?, activo = ? WHERE idUsuario = ?";
            pstmtUsuario = connection.prepareStatement(sqlUsuario);
            pstmtUsuario.setString(1, usuario.getNombre());
            pstmtUsuario.setString(2, usuario.getCorreo());
            pstmtUsuario.setString(3, usuario.getTelefono());
            pstmtUsuario.setString(4, usuario.getDireccion());
            pstmtUsuario.setInt(5, usuario.getRol().getIdRol());
            pstmtUsuario.setString(6, usuario.getDescripcion());
            pstmtUsuario.setBoolean(7, usuario.isActivo());
            pstmtUsuario.setInt(8, usuario.getIdUsuario());

            int filasUsuario = pstmtUsuario.executeUpdate();

            String sqlCliente = "UPDATE cliente SET dpi = ?, hobbi = ?, gusto = ? WHERE idUsuario = ?";
            pstmtCliente = connection.prepareStatement(sqlCliente);
            pstmtCliente.setString(1, cliente.getDpi());
            pstmtCliente.setString(2, cliente.getHobbi());
            pstmtCliente.setString(3, cliente.getGusto());
            pstmtCliente.setInt(4, usuario.getIdUsuario());

            int filasCliente = pstmtCliente.executeUpdate();

            if (filasUsuario > 0 && filasCliente > 0) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                if (pstmtUsuario != null) {
                    pstmtUsuario.close();
                }
                if (pstmtCliente != null) {
                    pstmtCliente.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

}
