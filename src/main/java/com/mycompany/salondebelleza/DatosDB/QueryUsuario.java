/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.DatosDB;

import com.mycompany.salondebelleza.Modelos.Cliente;
import com.mycompany.salondebelleza.Modelos.Coneccion;
import com.mycompany.salondebelleza.Modelos.Usuario;
import com.mycompany.salondebelleza.Modelos.Rol;
import com.mycompany.salondebelleza.Utils.EncriptarContrasenia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class QueryUsuario {

    public boolean crear(Usuario usuario) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        EncriptarContrasenia encriptado = new EncriptarContrasenia();
        Usuario usuarioExistente = encontrarPorNombre(usuario.getNombre());
        try {
            if (usuarioExistente != null) {
                return false;

            }
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO usuario (nombre, correo, telefono, direccion, password, idRol, activo, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getTelefono());
            pstmt.setString(4, usuario.getDireccion());
            pstmt.setString(5, encriptado.contraseniaEncriptada(usuario.getPassword()));
            pstmt.setInt(6, usuario.getRol().getIdRol());
            pstmt.setBoolean(7, true);
            pstmt.setString(8, usuario.getDescripcion());

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

    public Usuario encontrarPorNombre(String nombreUsuario) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT idUsuario, nombre, correo, idRol, password, direccion, telefono, descripcion, activo FROM usuario WHERE nombre = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nombreUsuario);
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

    public Usuario login(String username, String password) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT r.idRol, u.idUsuario FROM usuario u "
                    + "JOIN rol r ON u.idRol = r.idRol "
                    + "WHERE u.correo = ? AND u.password = ? AND u.activo = true";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getInt("idUsuario"), username, "", "", "", "", new Rol(rs.getInt("idRol"), ""), "", true);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        return null;
    }

    public boolean inactivarUsuario(int id_usuario) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "UPDATE usuario SET activo = false WHERE idUsuario = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id_usuario);

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

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT u.idUsuario, u.nombre, u.activo, u.idRol, r.nombre AS nombreRol, "
                    + "u.correo, u.telefono, u.direccion, u.descripcion "
                    + "FROM usuario u "
                    + "INNER JOIN rol r ON u.idRol = r.idRol";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setDescripcion(rs.getString("descripcion"));
                usuario.setRol(new Rol(rs.getInt("idRol"), rs.getString("rol.nombreRol")));
                usuarios.add(usuario);
            }

            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        return new ArrayList<>();
    }

    public boolean CambiarRol(int idUsuario, int nuevoIdRol) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "UPDATE usuario SET  idRol = ? WHERE idUsuario = ?";
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, nuevoIdRol);
            pstmt.setInt(2, idUsuario);

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

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT u.idUsuario, u.nombre, u.correo, u.telefono, u.direccion, "
                    + " u.idRol, u.activo, u.descripcion, r.nombre AS nombreRol "
                    + "FROM usuario u "
                    + "JOIN rol r ON u.idRol = r.idRol "
                    + "WHERE u.idUsuario = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuario.setDescripcion(rs.getString("descripcion"));
                usuario.setRol(new Rol(rs.getInt("idRol"), rs.getString("nombreRol")));

            }

            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            } catch (Exception e) {
            }
        }
        return null;
    }

    public boolean modificarDatosUsuario(Usuario usuario) {
        Connection connection = null;
        PreparedStatement pstmtUsuario = null;
        PreparedStatement pstmtCliente = null;

        try {
            connection = Coneccion.getConnection();
            connection.setAutoCommit(false);

            String sqlUsuario = "UPDATE usuario SET nombre = ?, correo = ?, telefono = ?, direccion = ?,  descripcion = ?, activo = ? WHERE idUsuario = ?";
            pstmtUsuario = connection.prepareStatement(sqlUsuario);
            pstmtUsuario.setString(1, usuario.getNombre());
            pstmtUsuario.setString(2, usuario.getCorreo());
            pstmtUsuario.setString(3, usuario.getTelefono());
            pstmtUsuario.setString(4, usuario.getDireccion());
            
            pstmtUsuario.setString(5, usuario.getDescripcion());
            pstmtUsuario.setBoolean(6, usuario.isActivo());
            pstmtUsuario.setInt(7, usuario.getIdUsuario());

            int filasUsuario = pstmtUsuario.executeUpdate();

            if (filasUsuario > 0) {
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
