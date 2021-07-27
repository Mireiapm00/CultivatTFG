/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cultivattfg.bd;

import com.cultivattfg.bd.classesBD.ProductoresBD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mirei
 */
public class ModificarDatosProductor extends HttpServlet {

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
        
        ProductoresBD productor = new ProductoresBD();
        
        int id_usuario = con.obtenerIdUsuarioBD((String)sesion.getAttribute("user"));
        int id_productor = con.obtenerIdProductorBD(id_usuario);
        
        productor.setId_productor(id_productor);
        productor.setId_usuario(id_usuario);
        productor.setSello_spg(request.getParameter("sellospge"));
        productor.setNombre(request.getParameter("nombre"));
        productor.setId_categoria(Integer.parseInt(request.getParameter("categoriaProyecto")));
        productor.setTelefono(Integer.parseInt(request.getParameter("telefono")));
        productor.setEmail(request.getParameter("emailProyecto"));
        productor.setUbicacion(request.getParameter("ubicacionProyecto"));
        productor.setWeb(request.getParameter("webProyecto"));
        productor.setReparto(request.getParameter("repartoProyecto"));
        productor.setDescripcion(request.getParameter("descProyecto"));
        
        if(con.modificarProductorBD(productor)){
            sesion.setAttribute("SUCCESS", "Dades del productor actualitzades");
            
            response.sendRedirect("./userAccess_component/op_productor.jsp");

        }
        else{
            sesion.setAttribute("FAILED", "Error al actualitzar");
            response.sendRedirect("./userAccess_component/mod_infoProductor.jsp");
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
