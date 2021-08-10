/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cultivattfg.bd;

import com.cultivattfg.bd.classesBD.ProductosBD;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author mireia
 */
@MultipartConfig(maxFileSize = 16177215)
public class RegistroProducto extends HttpServlet {

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
        AccesoBD con = new AccesoBD();
        HttpSession sesion = request.getSession(true);
        ProductosBD producto = new ProductosBD();
        
        String usuarioActual = (String)sesion.getAttribute("user");
        int id_usuario = con.obtenerIdUsuarioBD(usuarioActual);
        int id_productor = con.obtenerIdProductorBD(id_usuario);
        
        producto.setId_categoria(Integer.parseInt(request.getParameter("categoria")));
        producto.setId_productor(id_productor);
        producto.setNombre(request.getParameter("nombreProducto"));
        producto.setStock(Integer.parseInt(request.getParameter("stock")));
        producto.setUnidad(request.getParameter("unidadMedida"));
        producto.setPrecio_unitario(Float.parseFloat(request.getParameter("precio")));
        producto.setIngredientes(request.getParameter("ingredientes"));
        
        InputStream inputStream = null;
        
        Part filePart = request.getPart("floatingImage");
        
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            producto.setFoto(inputStream);
        }
        
        if(con.registroProductoBD(producto)){
            sesion.setAttribute("SUCCESS", "Producte afegit correctament");
            sesion.setAttribute("from", "registroProducto");
            response.sendRedirect("index.jsp");
            
        }
        else {
            sesion.setAttribute("ERROR", "Error al afegir el producte.");
            response.sendRedirect("./userAccess_component/mod_productos.jsp");
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
        processRequest(request, response);
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
