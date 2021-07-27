<%-- 
    Document   : shop
    Created on : 17-jul-2021, 23:23:06
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
           if(usuarioActual != null){
        %>
            <h1>Hello World! <%=usuarioActual%></h1>
        <%
            }
        %>
        
        
        
    </body>
</html>
