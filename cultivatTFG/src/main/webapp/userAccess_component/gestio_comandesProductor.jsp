<%-- 
    Document   : gestio_comandesProductor
    Created on : 07-ago-2021, 14:56:32
    Author     : mirei
--%>

<%@page import="com.cultivattfg.bd.AccesoBD"%>
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
            AccesoBD con = new AccesoBD();
            
%>
            <p class="fw-bold h3 mt-2 mb-3 text-center p-2">Gestió de comandes</p>
            
            <div class="d-flex justify-content-center mb-2">
                
                
                
                
                
                
            </div>
<%
        }
%>
    </body>
</html>
