/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cultivattfg.bd;

import com.cultivattfg.bd.classesBD.ProductosBD;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mireia
 */
public class ProcesarPedido extends HttpServlet {

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
        
        List <ProductosBD> carrito = new ArrayList();
        String indice;
        AccesoBD con = new AccesoBD();
        
        String carritoJSON = request.getParameter("carrito");
        JsonReader jsonReader = Json.createReader(new StringReader(carritoJSON));
        JsonObject jobj = jsonReader.readObject();
        
        if (jobj.size() > 0) {
            JsonParser jsonParser = Json.createParser(new StringReader(carritoJSON));
            while (jsonParser.hasNext()) {
                JsonParser.Event e = jsonParser.next();
                if (e == JsonParser.Event.KEY_NAME) {
                    {
                        indice = jsonParser.getString();
                        
                        if (!indice.equals("id_producto") && !indice.equals("nombre") && !indice.equals("cantidad") && !indice.equals("precio_unitario")
                                && !indice.equals("unidad")){
                            
                            JsonObject prod = jobj.getJsonObject(indice);
                            int id_producto = Integer.parseInt(prod.getString("id_producto"));
                            
                            ProductosBD producto = con.obtenerProducto(id_producto);
                            producto.setStock(Integer.parseInt(prod.get("cantidad").toString()));
                            carrito.add(producto);
                        }
                    }
                }
            }
        }
        
        HttpSession sesion = request.getSession(true);
        sesion.setAttribute("carrito", carrito);
        String usuario = (String) sesion.getAttribute("user");
        
        boolean ok = true;
        
        //Check if there is enough stock product in db
        for(ProductosBD p: carrito){
            int stockdb = con.obtenerStockProducto(p.getId_producto());
            if(p.getStock() > stockdb){
                ok = false;
            }
        }
        
        if(ok){
            if(usuario != null)
                response.sendRedirect("./shop_component/resguardo.jsp");
            else {
                sesion.setAttribute("from", "carrito");
                response.sendRedirect("./userAccess_component/userAccess.jsp");
            }
        }
        else {
            sesion.setAttribute("ERRORSTOCK", "Alguns productes de la cistella no estan disponibles");
            response.sendRedirect("./shop_component/shop.jsp");
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
