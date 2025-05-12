/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.DatosDB;

import com.mycompany.salondebelleza.Modelos.Anuncio;
import com.mycompany.salondebelleza.Modelos.Coneccion;
import com.mycompany.salondebelleza.Modelos.Usuario;
import com.mycompany.salondebelleza.Utils.EncriptarContrasenia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class QueryAnuncio {

    public boolean crear(Anuncio anuncio) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            connection = Coneccion.getConnection();
            String sql = "INSERT INTO anuncio (id_Usuario, tipo, contenido, url, duracion_dias, fecha_inicio, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, anuncio.getIdUsuario());
            pstmt.setString(2, anuncio.getTipo());
            pstmt.setString(3, anuncio.getContenido());
            pstmt.setString(4, anuncio.getUrl());
            pstmt.setInt(5, anuncio.getDuracionDias());
            pstmt.setDate(6, anuncio.getFechaInicio());
            pstmt.setBoolean(7, anuncio.isEstado());

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

    public boolean modificarDatosAnuncio(Anuncio anuncio) {
        Connection connection = null;
        PreparedStatement pstmtAnuncio = null;

        try {
            connection = Coneccion.getConnection();
            

            String sqlAnuncio = "UPDATE anuncio SET tipo = ?, contenido = ?, url = ?, duracion_dias = ?, fecha_inicio = ? WHERE id_anuncio = ?";

            pstmtAnuncio = connection.prepareStatement(sqlAnuncio);
            pstmtAnuncio.setString(1, anuncio.getTipo());
            pstmtAnuncio.setString(2, anuncio.getContenido());
            pstmtAnuncio.setString(3, anuncio.getUrl());
            pstmtAnuncio.setInt(4, anuncio.getDuracionDias());
            pstmtAnuncio.setDate(5, anuncio.getFechaInicio());
            pstmtAnuncio.setInt(6, anuncio.getIdAnuncio());

            int filasActualizadas = pstmtAnuncio.executeUpdate();

            return filasActualizadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmtAnuncio != null) {
                try {
                    pstmtAnuncio.close();
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

    public boolean EliminarAnuncio(int id_anuncio) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "DELETE FROM anuncio WHERE id_anuncio = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id_anuncio);

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

    public boolean inactivarAnuncio(int id_anuncio) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "UPDATE anuncio SET estado = false WHERE id_anuncio = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id_anuncio);

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

    public boolean activarAnuncio(int id_anuncio) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "UPDATE anuncio SET estado = true WHERE id_anuncio = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id_anuncio);

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
