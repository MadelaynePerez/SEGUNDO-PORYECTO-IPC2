/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.salondebelleza.Servlets;

import com.mycompany.salondebelleza.DatosDTO.AnuncioDTO;
import com.mycompany.salondebelleza.Modelos.Anuncio;
import com.mycompany.salondebelleza.Modelos.Usuario;
import com.mycompany.salondebelleza.Services.AnuncioService;
import com.mycompany.salondebelleza.Utils.JsonUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ana
 */
@WebServlet(name = "AnuncioServlet", urlPatterns = {"/AnuncioServlet"})
public class AnuncioServlet extends HttpServlet {

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
            out.println("<title>Servlet AnuncioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AnuncioServlet at " + request.getContextPath() + "</h1>");
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
        JsonUtil<AnuncioDTO> requestJson = new JsonUtil<>();
        AnuncioDTO datosEntrada = requestJson.JsonAObjeto(request, AnuncioDTO.class);

        int idUsuario = datosEntrada.getIdUsuario();
        String tipo = datosEntrada.getTipo();
        String contenido = datosEntrada.getContenido();
        String url = datosEntrada.getUrl();
        int duracionDias = datosEntrada.getDuracionDias();
        LocalDate localFechaInicio = LocalDate.parse(datosEntrada.getFechaInicio());
        java.sql.Date fechaInicio = java.sql.Date.valueOf(localFechaInicio);
        
        Anuncio anuncio = new Anuncio(0, idUsuario, tipo, contenido, url, duracionDias, fechaInicio, true);
        AnuncioService crearAnuncioService = new AnuncioService();

        boolean resultado = crearAnuncioService.crearAnuncio(anuncio);
        if (resultado) {
            responseJson.EnviarJson(response, "Anuncio Creado");
        } else {
            responseJson.EnviarJson(response, "Error al crear el anuncio");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonUtil<String> responseJson = new JsonUtil<>();
        JsonUtil<AnuncioDTO> requestJson = new JsonUtil<>();
        AnuncioDTO datosEntrada = requestJson.JsonAObjeto(request, AnuncioDTO.class);

        int idAnuncio = datosEntrada.getIdAnuncio();

        AnuncioService eliminarAnuncioService = new AnuncioService();

        boolean resultado = eliminarAnuncioService.eliminarAnuncio(idAnuncio);
        if (resultado) {
            responseJson.EnviarJson(response, " anuncio eliminado correctamente");
        } else {
            responseJson.EnviarJson(response, "error  al eliminar al anuncio");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonUtil<String> responseJson = new JsonUtil<>();
        JsonUtil<AnuncioDTO> requestJson = new JsonUtil<>();
        AnuncioDTO datosEntrada = requestJson.JsonAObjeto(request, AnuncioDTO.class);

        int idanuncio = datosEntrada.getIdAnuncio();
        String tipo = datosEntrada.getTipo();
        String contenido = datosEntrada.getContenido();
        String url = datosEntrada.getUrl();
        int duracionDias = datosEntrada.getDuracionDias();
        LocalDate localFechaInicio = LocalDate.parse(datosEntrada.getFechaInicio());
        java.sql.Date fechaInicio = java.sql.Date.valueOf(localFechaInicio);

        Anuncio anuncio = new Anuncio(idanuncio, 0, tipo, contenido, url, duracionDias, fechaInicio, true);
        AnuncioService modificarAnuncioService = new AnuncioService();

        boolean resultado = modificarAnuncioService.modificarAnuncio(anuncio);
        if (resultado) {
            responseJson.EnviarJson(response, "Anuncio Modificado");
        } else {
            responseJson.EnviarJson(response, "Error al modificar anuncio");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
