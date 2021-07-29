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

/**
 *
 * @author mirei
 */
public class ModificarProducto extends HttpServlet {

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
        
        int id_producto = Integer.parseInt(request.getParameter("id_producto"));
        
        ProductosBD producto = con.obtenerProducto(id_producto);
        
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>&nbsp;</title>");            
            out.println("</head>");
            
            
            out.println("<body>");
            out.println("<form name=\"form1\" method=\"post\" onsubmit=\"ProcesarForm(this, 'ModificarDatosProducto', 'cuerpo'); return false\">");
            out.println("<div class=\"container w-50 mt-4\">");
            
            out.println("<p class=\"fw-bold h5 text-center p-2\">Modificar producte " + producto.getNombre() + "</p>");
            
            out.println("<div class=\"form-floating mb-3 text-center\">\n" +
                        "<input type=\"text\" class=\"form-control\" id=\"floatingNombreProducto\" placeholder=\"NombreProducto\" name=\"nombreProducto\" value=\"" + producto.getNombre() + "\" required autofocus>\n" +
                        "<label for=\"floatingNombreProducto\">Nom del producte</label>\n" +
                        "</div>");
            
            out.println("<div class=\"form-floating mb-3 text-center\">\n" +
                        "<input type=\"num\" class=\"form-control\" id=\"floatingStock\" placeholder=\"Stock\" name=\"stock\" value=\"" + producto.getStock() + "\" required>\n" +
                        "<label for=\"floatingStock\">Stock</label>\n" +
                        "</div>");
            
            out.println("<div class=\"form-floating mb-3 text-center\">\n" +
                        "<input type=\"text\" class=\"form-control\" id=\"floatingUnitat\" placeholder=\"UnitatMesura\" name=\"unidadMedida\" value=\"" + producto.getUnidad() + "\" required>\n" +
                        "<label for=\"floatingUnitat\">Unitat de Mesura</label>\n" +
                        "</div>");
            
            out.println("<div class=\"form-floating mb-3 text-center\">\n" +
                        "<input type=\"num\" class=\"form-control\" id=\"floatingPrecio\" placeholder=\"Precio\" name=\"precio\" value=\"" + producto.getPrecio_unitario() + "\" required>\n" +
                        "<label for=\"floatingPrecio\">Preu unitari</label>\n" +
                        "</div>");
            
            out.println("<div class=\"form-floating mb-3 text-center\">\n" +
                        "<textarea class=\"form-control\" id=\"floatingIngredientes\" placeholder=\"Ingredientes\" name=\"ingredientes\" style=\"height: 100px\" required>" + producto.getIngredientes() + "</textarea>\n" +
                        "<label for=\"floatingIngredientes\">Ingredients</label>\n" +
                        "</div>");
            
            out.println("<div class=\"mb-2 text-center\">\n" +
                        "<input type='text' hidden name='id_producto' value='" + producto.getId_producto() + "'> " +
                        "<input class=\"btn btn-dark me-4\" type=\"submit\" value=\"Modificar\">" +
                        "</div>");
            
            out.println("</div>");
            out.println("</form>");
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
