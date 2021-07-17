<%-- 
    Document   : projects
    Created on : 16-jul-2021, 18:05:24
    Author     : mireia
--%>

<%@page import="com.cultivattfg.bd.classesBD.CategoriasBD"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" import="com.cultivattfg.bd.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>&nbsp;</title>
    </head>
    <body>
        <%
            AccesoBD con = AccesoBD.getInstance();
            List<CategoriasBD> categories = con.obtenerCategoriasBD();
            
        %>
        <p class="bold h2 text-center mt-3 mb-2 p-2">Projectes</p>
        
        <div class="d-flex">
            <div>
        <%
            for(CategoriasBD cat: categories){
                String nombre = cat.getNombre();
        %>
            <button type="button" class="btn btn-outline-dark btn-sm"><%=nombre%></button>        
        <%        
            }
        %>
            </div>
             
        </div>
    </body>
</html>
