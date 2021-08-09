<%-- 
    Document   : op_productor
    Created on : 23-jul-2021, 14:38:59
    Author     : mireia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./userAccess_component/userAccess.css" rel="stylesheet" type="text/css">
        <title>&nbsp;</title>
    </head>
    <body>
    <%
        String mensaje = (String)session.getAttribute("ERROR");
        if(mensaje != null){
            session.removeAttribute("ERROR");
    %>
            <div class="alert alert-danger"><%=mensaje%></div>
    <%
        }
        String mensaje2 = (String)session.getAttribute("SUCCESS");
        if(mensaje2 != null){
            session.removeAttribute("SUCCESS");
    %>
            <div class="alert alert-success"><%=mensaje2%></div>
    <%
        }
        String usuarioActual = (String)session.getAttribute("user");
        if(usuarioActual == null) { //usuario no registrado
            response.sendRedirect("userAccess.jsp");
        }
        else {   
    %>
            <p class="fw-bold h3 mt-2 mb-3 text-center p-2">Benvingut productor: <%=usuarioActual%></p>
            
            <div class="d-flex justify-content-center my-4">
                <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/mod_infoUsuario.jsp', 'cuerpo')">Modificar dades Usuari</button>
                <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/mod_infoProductor.jsp', 'cuerpo')">Modificar dades Productor</button>
                <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/mod_productos.jsp', 'cuerpo')">Gestió de productes</button>
                <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/gestio_comandesProductor.jsp', 'cuerpo')">Gestió de comandes</button>

                <form method="post" onsubmit="ProcesarForm(this, 'CerrarSesion', 'cuerpo');return false">
                    <input type="submit" class="btn btn-dark" value="Tancar sesió">
                </form>
            </div>
            
    <%
        }
    %>
    </body>
</html>
