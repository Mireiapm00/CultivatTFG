/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cultivattfg.bd;

import com.cultivattfg.bd.classesBD.Usuario;
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
public class ModificarDatosUsuario extends HttpServlet {

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
        
        Usuario usuario = new Usuario();
        
        int id_usuario = con.obtenerIdUsuarioBD((String)sesion.getAttribute("user"));
        boolean productor = con.comprobarUsuarioProductorBD((String)sesion.getAttribute("user"), request.getParameter("pass"));
        
        usuario.setId_usuario(id_usuario);
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setPassword(request.getParameter("pass"));
        usuario.setTelefono(Integer.parseInt(request.getParameter("telefono")));
        usuario.setActivo(true);
        usuario.setProductor(productor);
        
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellidos(request.getParameter("apellidos"));
        usuario.setDomicilio(request.getParameter("domicilio"));
        usuario.setPoblacion(request.getParameter("poblacion"));
        usuario.setProvincia(request.getParameter("provincia"));
        if(!"".equals(request.getParameter("codigopostal")))
            usuario.setCp(Integer.parseInt(request.getParameter("codigopostal")));
        
        
        if(con.modificarUsuarioBD(usuario)){
            sesion.setAttribute("SUCCESS", "Dades del usuari actualitzades");
            
            if(usuario.isProductor())
                response.sendRedirect("./userAccess_component/op_productor.jsp");
            else{
                response.sendRedirect("./userAccess_component/op_user.jsp");
            }
        }
        else{
            sesion.setAttribute("FAILED", "Error al actualitzar");
            response.sendRedirect("./userAccess_component/mod_infoUsuario.jsp");
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
