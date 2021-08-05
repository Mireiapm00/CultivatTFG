<%-- 
    Document   : gestio_comandes
    Created on : 27-jul-2021, 19:27:05
    Author     : mirei
--%>

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
            response.sendRedirect("./userAccess_component/userAccess.jsp");
        }
        else {
            
            
    %>
            
            ISOOOOO
    
    <%
        }
    %>
        
    </body>
</html>
