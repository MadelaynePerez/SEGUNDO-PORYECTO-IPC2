/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.salondebelleza.Servlets;

import com.mycompany.salondebelleza.DatosDTO.ServicioDTO;
import com.mycompany.salondebelleza.Modelos.Servicio;
import com.mycompany.salondebelleza.Services.ServicioService;
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
@WebServlet(name = "ServicioServlet", urlPatterns = {"/ServicioServlet"})
public class ServicioServlet extends HttpServlet {

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
            out.println("<title>Servlet ServicioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServicioServlet at " + request.getContextPath() + "</h1>");
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
        
        JsonUtil<String> responseJson = new JsonUtil<>();
        JsonUtil<ServicioDTO> requestJson = new JsonUtil<>();
        ServicioDTO datosEntrada = requestJson.JsonAObjeto(request, ServicioDTO.class);

        int idServicio = datosEntrada.getIdServicio();
        String nombre = datosEntrada.getNombre();
        String descripcion = datosEntrada.getDescripcion();
        double precio = datosEntrada.getPrecio();
        int duracion = datosEntrada.getDuracion();
        
        String rutaCatalogo = datosEntrada.getRutaCatalogo();
        
        Servicio servicio = new Servicio(0, nombre, descripcion, precio, duracion,  true, rutaCatalogo);
        ServicioService crearServicioService = new ServicioService();

        boolean resultado = crearServicioService.crearAnuncio(servicio);
        if (resultado) {
            responseJson.EnviarJson(response, "Servicio Creado");
        } else {
            responseJson.EnviarJson(response, "Error al crear el servicio");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         JsonUtil<String> responseJson = new JsonUtil<>();
        JsonUtil<ServicioDTO> requestJson = new JsonUtil<>();
        ServicioDTO datosEntrada = requestJson.JsonAObjeto(request, ServicioDTO.class);

        int idServicio = datosEntrada.getIdServicio();
       
        ServicioService eliminarServicioService = new ServicioService();

        boolean resultado = eliminarServicioService.eliminarServicio(idServicio);
        if (resultado) {
            responseJson.EnviarJson(response, "Servicio Eliminado");
        } else {
            responseJson.EnviarJson(response, "Error al eliminar el servicio");
        }
    
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         JsonUtil<String> responseJson = new JsonUtil<>();
        JsonUtil<ServicioDTO> requestJson = new JsonUtil<>();
        ServicioDTO datosEntrada = requestJson.JsonAObjeto(request, ServicioDTO.class);

        int idServicio = datosEntrada.getIdServicio();
        String nombre = datosEntrada.getNombre();
        String descripcion = datosEntrada.getDescripcion();
        double precio = datosEntrada.getPrecio();
        int duracion = datosEntrada.getDuracion();
        String rutaCatalogo = datosEntrada.getRutaCatalogo();
        
        Servicio servicio = new Servicio(idServicio, nombre, descripcion, precio, duracion,  true, rutaCatalogo);
        ServicioService crearServicioService = new ServicioService();

        boolean resultado = crearServicioService.modificarServicio(servicio);
        if (resultado) {
            responseJson.EnviarJson(response, "Servicio modificado");
        } else {
            responseJson.EnviarJson(response, "Error al modificar el servicio");
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
