<%-- 
    Document   : resguardo
    Created on : 04-ago-2021, 16:38:52
    Author     : mirei
--%>

<%@page import="com.cultivattfg.bd.classesBD.Usuario"%>
<%@page import="com.cultivattfg.bd.AccesoBD"%>
<%@page import="java.util.List"%>
<%@page import="com.cultivattfg.bd.classesBD.ProductosBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>&nbsp;</title>
    </head>
    <body>
<%
    String usuarioActual = (String)session.getAttribute("user");
    if(usuarioActual == null) { //usuario no registrado
        response.sendRedirect("userAccess.jsp");
    }
    else {
        List<ProductosBD> carrito = (List<ProductosBD>) session.getAttribute("carrito");
        AccesoBD con = new AccesoBD();
        Usuario infoUsuario = con.obtenerUsuarioBD(usuarioActual);
        boolean ok = false;
%>
        <div class="d-flex justify-content-center">
            <p class="fw-bold h3 mt-2 mb-3 text-center p-2">Resum de la comanda</p>
        </div>
        <div class="container w-50">
            <div class="d-flex justify-content-start my-2">
                <button type="button" class="btn rounded-pill bg-light text-dark">
                    Hola&nbsp;<i class="fa fa-user"></i> <u class="fw-bold"><%=infoUsuario.getUsuario()%></u>
                </button>
            </div>
            <div class="d-flex align-items-center my-2">
        <%
            if(infoUsuario.getNombre()!= null && infoUsuario.getApellidos() != null){
                ok = true;
        %>
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <p class="card-text"><%=infoUsuario.getNombre()%> <%=infoUsuario.getApellidos()%></p>
                        <p class="card-text"><%=infoUsuario.getTelefono()%></p>
                        <p class="card-text"><%=infoUsuario.getEmail()%></p>
                    </div>
                </div>
                <div>
                    <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/mod_infoUsuario.jsp', 'cuerpo')">Afegir dades</button>
                </div>
        <%
            }
            else{
                session.setAttribute("from", "resguardo");
        %>            
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/mod_infoUsuario.jsp', 'cuerpo')">Afegir dades</button>
                    </div>
                </div>
        
        <%
            }
        %>
            </div>
        </div>
        <!-- MOSTRAR LA INFO DE CARRITO Y BOTON DE CONTINUAR PROCESO ACTIVO SI OK == TRUE -->    
            
            
            
<%
    }
%>
    </body>
</html>
