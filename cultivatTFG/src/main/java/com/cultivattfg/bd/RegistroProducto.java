/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cultivattfg.bd;

import com.cultivattfg.bd.classesBD.ProductosBD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mireia
 */
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
        //int id_categoria = con.obtenerIdCategoriaProductor(id_productor);
        //get categoria from form
        
        producto.setId_categoria(Integer.parseInt(request.getParameter("categoria")));
        producto.setId_productor(id_productor);
        producto.setNombre(request.getParameter("nombreProducto"));
        producto.setStock(Integer.parseInt(request.getParameter("stock")));
        producto.setUnidad(request.getParameter("unidadMedida"));
        producto.setPrecio_unitario(Integer.parseInt(request.getParameter("precio")));
        producto.setIngredientes(request.getParameter("ingredientes"));
        
        
        if(con.registroProductoBD(producto)){
            sesion.setAttribute("SUCCESS", "Producte afegit correctament");
            response.sendRedirect("./userAccess_component/mod_productos.jsp");
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
