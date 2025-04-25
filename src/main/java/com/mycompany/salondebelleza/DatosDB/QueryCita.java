/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.DatosDB;

import com.mycompany.salondebelleza.Modelos.Cita;
import com.mycompany.salondebelleza.Modelos.Coneccion;
import com.mycompany.salondebelleza.Modelos.Rol;
import com.mycompany.salondebelleza.Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class QueryCita {
      public boolean crear(Cita cita) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Usuario usuarioExistente = encontrarCitaPorId(cita.getIdCita());
        try {
            if (usuarioExistente != null) {
                return false;

            }
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO cita (id_cliente, id_empleado, id_servicio, fecha, hora) VALUES (?, ?, ?, ?, ?)";

            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, cita.getIdCliente());
            pstmt.setInt(2, cita.getIdEmpleado());
            pstmt.setInt(3, cita.getIdServicio());
            pstmt.setDate(4, cita.getFecha());  
            pstmt.setTime(5, cita.getHora());   
            pstmt.setString(6, cita.getEstado().name());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (Exception e) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
      public Usuario encontrarCitaPorId(int idcita) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT idUsuario, nombre, correo, idRol, password, direccion, telefono, descripcion, activo FROM usuario WHERE nombre = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idcita);
            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                int idUsuario = resultado.getInt("idUsuario");
                String nombre = resultado.getString("nombre");
                String correo = resultado.getString("correo");
                int idRol = resultado.getInt("idRol");
                String password = resultado.getString("password");
                String direccion = resultado.getString("direccion");
                String telefono = resultado.getString("telefono");
                String descripcion = resultado.getNString("descripcion");
                boolean activo = resultado.getBoolean("activo");

                return new Usuario(idUsuario, nombre, correo, password, direccion, telefono, new Rol(idRol, ""), descripcion, activo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
