/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cultivattfg.bd;

import com.cultivattfg.bd.classesBD.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mireia
 */
public class RegistroUsuarioProductor extends HttpServlet {

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
        ProductoresBD productor = new ProductoresBD();
        String nombreProyecto;
        
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setPassword(request.getParameter("pass"));
        usuario.setTelefono(Integer.parseInt(request.getParameter("telefono")));
        usuario.setActivo(true);
        usuario.setProductor(true);
        
        nombreProyecto = request.getParameter("nombreProyecto");
        
        productor.setSello_spg("SPGE-" + nombreProyecto.replace(" ", ""));
        productor.setId_categoria(Integer.parseInt(request.getParameter("categoriaProyecto")));
        productor.setNombre(request.getParameter("nombreProyecto"));
        productor.setTelefono(Integer.parseInt(request.getParameter("telefonoProyecto")));
        productor.setEmail(request.getParameter("emailProyecto"));
        productor.setUbicacion(request.getParameter("ubicacionProyecto"));
        productor.setWeb(request.getParameter("webProyecto"));
        productor.setReparto(request.getParameter("repartoProyecto"));
        productor.setDescripcion(request.getParameter("descProyecto"));
        
        if(con.registroUsuarioBD(usuario)){
            sesion.setAttribute("user", usuario.getUsuario());
            
            productor.setId_usuario(con.obtenerIdUsuarioBD(usuario.getUsuario()));
            
            if(con.registroUsuarioProductorBD(productor)){
                sesion.setAttribute("SUCCESS", "Usuari productor registrat");
                response.sendRedirect("./userAccess_component/op_productor.jsp");
            }
            else {
                sesion.setAttribute("ERROR", "Error al registrar-se com a productor. Consulta amb l'administrador.");
                response.sendRedirect("./userAccess_component/op_productor.jsp");
            }
        }
        else{
            sesion.setAttribute("ERROR", "Error al registrar-se. Consulta amb l'administrador.");
            response.sendRedirect("./userAccess_component/userAccess.jsp");
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
