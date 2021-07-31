<%-- 
    Document   : op_user
    Created on : 11-jul-2021, 17:47:44
    Author     : mireia
--%>

<%@page contentType="text/html" import="com.cultivattfg.bd.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="./js/libCapas.js" type="text/javascript"></script>
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
                <p class="bold h5 mt-2 mb-3 text-center p-2">Benvingut usuari: <%=usuarioActual%></p>
                <div class="d-flex justify-content-center my-4">
                    <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/mod_infoUsuario.jsp', 'cuerpo')">Modificar dades Usuari</button>
                    <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('userAccess_component/gestio_comandes.jsp', 'cuerpo')">Les Meues Reserves</button>
                    
                    <form method="post" onsubmit="ProcesarForm(this, 'CerrarSesion', 'cuerpo');return false">
                        <input type="submit" class="btn btn-dark" val ue="Cerrar sesión">
                    </form>
                </div>
                
                
        <%
            }
        %>
    </body>
</html>
