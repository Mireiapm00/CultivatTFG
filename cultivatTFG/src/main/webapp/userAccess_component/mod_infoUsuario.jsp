<%-- 
    Document   : mod_infoUsuario
    Created on : 23-jul-2021, 19:41:36
    Author     : mireia
--%>

<%@page import="com.cultivattfg.bd.classesBD.Usuario"%>
<%@page import="com.cultivattfg.bd.*"%>
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
            AccesoBD con = new AccesoBD();
            Usuario infoUsuario = con.obtenerUsuarioBD(usuarioActual);
            String from = (String)session.getAttribute("from");
            
            if(from == "resguardo"){
    %>
                <div class="alert alert-info" role="alert">
                    <i class="fa fa-info-circle"></i> Per completar la comanda cal omplir els camps de <span class="fw-bold">Nom i Cognoms</span>
                </div>
    <%
            }
    %>
            <p class="bold h5 mt-2 mb-3 text-center p-2">Modificar dades de <b><%=infoUsuario.getUsuario()%></b></p>
    
            <form name="form1" method="post" onsubmit="ProcesarForm(this, 'ModificarDatosUsuario', 'cuerpo'); return false">
                <div class="container w-50 mt-4">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingUsuari" placeholder="Usuari" name="usuario" value="<%=infoUsuario.getUsuario()%>" required autofocus>
                        <label for="floatingUsuari">Usuari</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control form-control-sm" id="floatingEmail" placeholder="nom@example.com" name="email" value="<%=infoUsuario.getEmail()%>" required>
                        <label for="floatingEmail">Direcció email</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control form-control-sm" id="floatingPassword" placeholder="Password" name="pass" value="<%=infoUsuario.getPassword()%>" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                               title="Deu contindre al menys un numero, una lletra majúscula i minúscula amb 8 caràcters o més" required>
                        <label for="floatingPassword">Contrasenya</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control form-control-sm" id="floatingPassword2" placeholder="Password" name="pass2" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                               onchange="validar_clave()" title="Deu contindre al menys un numero, una lletra majúscula i minúscula amb 8 caràcters o més" required>
                        <label for="floatingPassword2">Repetir Contrasenya</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingNom" placeholder="Nom" name="nombre" 
                               <% if(infoUsuario.getNombre() != null){ %> value="<%=infoUsuario.getNombre()%>">
                               <% } else { %> value=""><%}%>
                        <label for="floatingNom">Nom</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingApellidos" placeholder="Cognoms" name="apellidos" 
                               <% if(infoUsuario.getApellidos() != null){ %> value="<%=infoUsuario.getApellidos()%>">
                               <% } else { %> value=""><%}%>
                        <label for="floatingApellidos">Cognoms</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingDomicilio" placeholder="Domicili" name="domicilio" 
                               <% if(infoUsuario.getDomicilio() != null){ %> value="<%=infoUsuario.getDomicilio()%>">
                               <% } else { %> value=""><%}%>
                        <label for="floatingDomicilio">Domicili</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingPoblacion" placeholder="Poblacio" name="poblacion" 
                               <% if(infoUsuario.getPoblacion() != null){ %> value="<%=infoUsuario.getPoblacion()%>">
                               <% } else { %> value=""><%}%>
                        <label for="floatingPoblacion">Població</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingProvincia" placeholder="Provincia" name="provincia" 
                               <% if(infoUsuario.getProvincia() != null){ %> value="<%=infoUsuario.getProvincia()%>">
                               <% } else { %> value=""><%}%>
                        <label for="floatingProvincia">Província</label>
                    </div>
                    <div class="form-floating mb-3">   
                        <input type="number" class="form-control" id="floatingCp" placeholder="Codipostal" name="codigopostal" 
                               <% if(infoUsuario.getCp() != 0){ %> value="<%=infoUsuario.getCp()%>">
                               <% } else { %> value=""><%}%>
                        <label for="floatingCp">Codi postal</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="tel" class="form-control" id="floatingTelefon" placeholder="Telefon" name="telefono" required pattern="[0-9]{9}" 
                               <% if(infoUsuario.getTelefono() != 0){ %> value="<%=infoUsuario.getTelefono()%>">
                               <% } else { %> value=""><%}%>
                        <label for="floatingTelefon">Telèfon</label>
                    </div>
                    
                    <div class="mb-2 text-center">
                        <input class="btn btn-dark me-4" type="submit" value="Modificar">
                        <input class="btn btn-outline-dark" type="reset" value="Cancelar"
                                <% if(from == "resguardo"){ session.removeAttribute("from"); %> onclick="Cargar('./shop_component/shop.jsp', 'cuerpo')"
                                <% } else if(infoUsuario.isProductor()){ %> onclick="Cargar('./userAccess_component/op_productor.jsp', 'cuerpo')"
                                <% } else { %> onclick="Cargar('./userAccess_component/op_user.jsp', 'cuerpo')" <% } %> >
                    </div>
                </div>
            </form>
    <%
        }
    %>
    </body>
</html>
