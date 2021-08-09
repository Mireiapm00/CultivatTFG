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
        <link href="./shop_component/shop.css" rel="stylesheet" type="text/css">
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
            <p class="h5 my-3 p-2 bd-highlight fw-bold text-center bg-light">Informació d'usuari <%=infoUsuario.getUsuario()%></p>
            
            <div class="d-flex align-items-center mb-4">
    <%
            if(infoUsuario.getNombre()!= null && infoUsuario.getApellidos() != null){
                session.setAttribute("from", "resguardo");
                ok = true;
    %>
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <p class="card-text"><%=infoUsuario.getNombre()%> <%=infoUsuario.getApellidos()%></p>
                        <p class="card-text"><%=infoUsuario.getTelefono()%></p>
                        <p class="card-text"><%=infoUsuario.getEmail()%></p>
                    </div>
                </div>
                <div class="mx-4">
                    <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/mod_infoUsuario.jsp', 'cuerpo')">Afegir dades</button>
                </div>
    <%
            }
            else{
                session.setAttribute("from", "resguardo");
    %>            
                <div class="card mx-4" style="width: 18rem;">
                    <div class="card-body">
                        <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/mod_infoUsuario.jsp', 'cuerpo')">Afegir dades</button>
                    </div>
                </div>
    <%
            }
    %>
            </div>
            
            <p class="h5 my-3 p-2 text-center fw-bold bg-light">Informació comanda</p>
            <table class="table table-responsive mb-4">
    <%
            float totalCarrito = 0, totalProd;
            
            for(ProductosBD p: carrito){
                totalProd = p.getStock() * p.getPrecio_unitario();
                totalCarrito += totalProd;
    %>
                <tr>
                    <td><%=p.getNombre()%></td>
                    <td><%=p.getStock()%> <%=p.getUnidad()%></td>
                    <td><%=p.getPrecio_unitario()%> €/<%=p.getUnidad()%></td>
                    <td><%=totalProd%> €</td>
                </tr>
    <%
            }
    %>
                <tfoot>
                    <tr>
                        <td colspan="2"></td>
                        <td class="fw-bold">Total: </td>
                        <td class="p-2 fw-bold"><%=totalCarrito%> €</td>
                    </tr>
                </tfoot>
            </table>
    <%
            if(ok){
                session.setAttribute("totalCarrito", totalCarrito);
    %>
                <div class=" d-flex justify-content-center align-items-center my-2">
                    <form method="post" onsubmit="ProcesarForm(this, 'TramitarPedido', 'cuerpo'); return false;">
                        <button type="submit" class="btn btn-dark me-4">Confirmar reserva</button>
                        <button type="button" class="btn btn-outline-dark" onclick="Cargar('./shop_component/shop.jsp', 'cuerpo');">Cancelar</button>
                    </form>
                    
                </div>
    <%
            }
    %>
        </div>
<%
    }
%>   

    </body>
</html>
