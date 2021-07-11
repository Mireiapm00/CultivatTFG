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
                            
            String usuarioActual = (String)session.getAttribute("user");
            if(usuarioActual == null) { //usuario no registrado
                response.sendRedirect("userAccess.jsp");
            }
            else {   
        %>
        <h4><%=usuarioActual%></h4>
        <form method="post" onsubmit="ProcesarForm(this, 'CerrarSesion', 'cuerpo');return false">
            <input type="submit" class="btn btn-dark" value="Cerrar sesión">
        </form>
        <%
            }
        %>
    </body>
</html>
