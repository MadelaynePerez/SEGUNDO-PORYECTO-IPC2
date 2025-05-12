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
import java.time.LocalTime;
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
        ResultSet rs = null;

        try {
            if (cita == null) {
                return false;
            }

            connection = Coneccion.getConnection();

            String duracionSql = "SELECT duracion FROM servicio WHERE id_servicio = ?";
            pstmt = connection.prepareStatement(duracionSql);
            pstmt.setInt(1, cita.getIdServicio());
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                return false;
            }
            int duracionMin = rs.getInt("duracion");

            rs.close();
            pstmt.close();

            LocalTime horaInicio = cita.getHora().toLocalTime();
            LocalTime horaFin = horaInicio.plusMinutes(duracionMin);

            String sql = "SELECT c.hora, s.duracion "
                    + "FROM cita c "
                    + "JOIN servicio s ON c.id_servicio = s.id_servicio "
                    + "WHERE c.id_empleado = ? "
                    + "AND c.fecha = ? "
                    + "AND c.estado != 'Cancelada'";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, cita.getIdEmpleado());
            pstmt.setDate(2, cita.getFecha());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                LocalTime horaExistente = rs.getTime("hora").toLocalTime();
                int duracionExistente = rs.getInt("duracion");
                LocalTime finExistente = horaExistente.plusMinutes(duracionExistente);

                boolean traslape
                        = !horaFin.isBefore(horaExistente) && !horaInicio.isAfter(finExistente);

                if (traslape) {
                    return false;
                }
            }

            rs.close();
            pstmt.close();

            String insertSql = "INSERT INTO cita (id_cliente, id_empleado, id_servicio, fecha, hora, estado) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(insertSql);
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
                if (rs != null) {
                    rs.close();
                }
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

    public boolean cancelarCita(int idCita, int idCliente) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();

            String sql = "UPDATE cita SET estado = 'Cancelada' WHERE id_cita = ? AND id_cliente = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idCita);
            pstmt.setInt(2, idCliente);

            int filasActualizadas = pstmt.executeUpdate();

            return filasActualizadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

}
