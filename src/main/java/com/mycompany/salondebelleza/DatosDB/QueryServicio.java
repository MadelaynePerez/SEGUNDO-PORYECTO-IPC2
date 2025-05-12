/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.DatosDB;

import com.mycompany.salondebelleza.Modelos.Coneccion;
import com.mycompany.salondebelleza.Modelos.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class QueryServicio {

    public boolean crear(Servicio servicio) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            connection = Coneccion.getConnection();
            String sql = "INSERT INTO servicio (nombre, descripcion, precio, duracion, estado, ruta_catalogo) VALUES (?, ?, ?, ?, ?, ?)";

            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, servicio.getNombre());
            pstmt.setString(2, servicio.getDescripcion());
            pstmt.setDouble(3, servicio.getPrecio());
            pstmt.setInt(4, servicio.getDuracion());
            pstmt.setBoolean(5, servicio.isEstado());
            pstmt.setString(6, servicio.getRutaCatalogo());

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

    public boolean modificarDatosServicio(Servicio servicio) {
        Connection connection = null;
        PreparedStatement pstmtServicio = null;

        try {
            connection = Coneccion.getConnection();

            String sqlServicio = "UPDATE servicio SET nombre = ?, descripcion = ?, precio = ?, duracion = ?,  ruta_catalogo = ? WHERE id_servicio = ?";

            pstmtServicio = connection.prepareStatement(sqlServicio);
            pstmtServicio.setString(1, servicio.getNombre());
            pstmtServicio.setString(2, servicio.getDescripcion());
            pstmtServicio.setDouble(3, servicio.getPrecio()); 
            pstmtServicio.setInt(4, servicio.getDuracion());
             pstmtServicio.setString(5, servicio.getRutaCatalogo());
            pstmtServicio.setInt(6, servicio.getIdServicio());

            int filasActualizadas = pstmtServicio.executeUpdate();

            return filasActualizadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmtServicio != null) {
                try {
                    pstmtServicio.close();
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
     public boolean EliminarServicio(int idServicio) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "DELETE FROM servicio WHERE id_servicio = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idServicio);

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
     public boolean activarOInactivarServicio(int idServicio, boolean estado) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "UPDATE servicio SET estado = ? WHERE id_servicio = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setBoolean(1, estado);
            pstmt.setInt(2, idServicio);

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
