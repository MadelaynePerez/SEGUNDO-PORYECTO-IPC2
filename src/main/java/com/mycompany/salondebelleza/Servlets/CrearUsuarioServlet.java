/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.salondebelleza.Servlets;

import com.mycompany.salondebelleza.DatosDTO.ClienteDTO;
import com.mycompany.salondebelleza.Modelos.Cliente;
import com.mycompany.salondebelleza.Modelos.Rol;
import com.mycompany.salondebelleza.Modelos.Usuario;
import com.mycompany.salondebelleza.Services.ClienteService;
import com.mycompany.salondebelleza.Services.UsuarioService;
import com.mycompany.salondebelleza.Utils.JsonUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ana
 */
@WebServlet(name = "CrearUsuarioServlet", urlPatterns = {"/CrearUsuarioServlet"})
public class CrearUsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CrearUsuarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearUsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonUtil<Usuario> jsonUtil = new JsonUtil<>();

        Usuario datosEntrada = jsonUtil.JsonAObjeto(request, Usuario.class);

        String correo = datosEntrada.getCorreo();
        String password = datosEntrada.getPassword();
        String nombre = datosEntrada.getNombre();
        String telefono = datosEntrada.getTelefono();
        String direccion = datosEntrada.getDireccion();
        String descripcion = datosEntrada.getDescripcion();
        int rol = datosEntrada.getRol().getIdRol();

        UsuarioService crearUsuarioService = new UsuarioService();

        boolean resultado = crearUsuarioService.CrearUsuario(nombre, correo, telefono, direccion, password, rol, descripcion);

        if (resultado) {
            JsonUtil<String> mensajeJson = new JsonUtil<>();
            mensajeJson.EnviarJson(response, "Usuario Creado");
        } else {
            JsonUtil<String> errorJson = new JsonUtil<>();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            errorJson.EnviarJson(response, "Error al crear el usuario");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            JsonUtil<ClienteDTO> jsonUtil = new JsonUtil<>();
            ClienteDTO clienteDTO = jsonUtil.JsonAObjeto(request, ClienteDTO.class);

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(clienteDTO.getIdUsuario());
            usuario.setNombre(clienteDTO.getNombre());
            usuario.setCorreo(clienteDTO.getCorreo());
            usuario.setTelefono(clienteDTO.getTelefono());
            usuario.setDireccion(clienteDTO.getDireccion());
            usuario.setDescripcion(clienteDTO.getDescripcion());

            boolean resultado = new UsuarioService().modificardatosUsuario(usuario);

            if (resultado) {
                JsonUtil<String> mensajeJson = new JsonUtil<>();
                mensajeJson.EnviarJson(response, "usuario actualizado");
            } else {
                JsonUtil<String> mensajeJson = new JsonUtil<>();
                mensajeJson.EnviarJson(response, "error al actualizar usuario");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            new JsonUtil<>().EnviarJson(response, "Error interno: " + e.getMessage());
        }
    }

}
