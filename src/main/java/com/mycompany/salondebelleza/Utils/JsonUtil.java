/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salondebelleza.Utils;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ana
 */
public class JsonUtil<T> {

    private Gson gson = new Gson();

    public void EnviarJson(HttpServletResponse response, T object) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String res = gson.toJson(object);  
        java.io.PrintWriter out = response.getWriter();
        out.print(res);

    }

    public T JsonAObjeto(HttpServletRequest request, Class<T> classT) throws IOException {
        StringBuilder buffer = new StringBuilder();
        java.io.BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String body = buffer.toString();
        return gson.fromJson(body, classT);
    }

    public String getBody(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        java.io.BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

}
