/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.salondebelleza.Servlets;

import com.mycompany.salondebelleza.DatosDTO.ClienteDTO;
import com.mycompany.salondebelleza.DatosDTO.EmpleadoDTO;
import com.mycompany.salondebelleza.Modelos.Cliente;
import com.mycompany.salondebelleza.Modelos.Empleado;
import com.mycompany.salondebelleza.Modelos.Rol;
import com.mycompany.salondebelleza.Modelos.Usuario;
import com.mycompany.salondebelleza.Services.ClienteService;
import com.mycompany.salondebelleza.Services.EmpleadoService;
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
@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/EmpleadoServlet"})
public class EmpleadoServlet extends HttpServlet {

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
            out.println("<title>Servlet EmpleadoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmpleadoServlet at " + request.getContextPath() + "</h1>");
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
       try {

            JsonUtil<EmpleadoDTO> jsonUtil = new JsonUtil<>();
            EmpleadoDTO empleadoDTO = jsonUtil.JsonAObjeto(request, EmpleadoDTO.class);

            Usuario usuario = new Usuario();
            usuario.setNombre(empleadoDTO.getNombre());
            usuario.setCorreo(empleadoDTO.getCorreo());
            usuario.setTelefono(empleadoDTO.getTelefono());
            usuario.setDireccion(empleadoDTO.getDireccion());
            usuario.setPassword(empleadoDTO.getPassword());
            usuario.setDescripcion(empleadoDTO.getDescripcion());
            usuario.setRol(new Rol(4, "Empleado"));

            Empleado empleadoo = new Empleado(
                    0,
                    0,
                    empleadoDTO.getEspecialidad(),
                    empleadoDTO.getFotoPerfil()
                    
            );

            boolean resultado = new EmpleadoService().crearEmpleado(usuario, empleadoo);

            if (resultado) {
                JsonUtil<String> mensajeJson = new JsonUtil<>();
                mensajeJson.EnviarJson(response, "empleado Creado");
            } else {
                JsonUtil<String> mensajeJson = new JsonUtil<>();
                mensajeJson.EnviarJson(response, "error al crear empleado");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
            new JsonUtil<>().EnviarJson(response, "Error interno: " + e.getMessage());
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

}
