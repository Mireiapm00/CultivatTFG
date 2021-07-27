<%-- 
    Document   : userAccess
    Created on : 10-jul-2021, 20:16:45
    Author     : mireia
--%>

<%@page contentType="text/html" import="com.cultivattfg.bd.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="./js/libCapas.js" type="text/javascript"></script>
        <link href="./userAccess_component/userAccess.css" rel="stylesheet" type="text/css">
        <title>&nbsp;</title>
    </head>
    <body>
                
        <div class="container mt-5">
            
            <div class="row justify-content-center">
                <aside class="col-sm-4">
                    <div class="card">
                        <article class="card-body">
                            <button type="button" class="float-right btn btn-outline-dark btn-sm" onclick="Cargar('userAccess_component/registrarse.jsp', 'cuerpo')" >Registra't</button>
                            <h4 class="card-title text-center mb-4 mt-4">Iniciar Sessió</h4>
                            <hr>
            <%
                String mensaje = (String)session.getAttribute("ERROR");
                if(mensaje != null){
                    session.removeAttribute("ERROR");
            %>
                    <div class="alert alert-danger"><%=mensaje%></div>
            <%
                }
                String usuarioActual = (String)session.getAttribute("user");
                String productor = (String)session.getAttribute("productor");

                if(usuarioActual == null) { //usuario no registrado

            %>
                            <form name="usuarioLogin" method="post" onsubmit="ProcesarForm(this, 'LoginUsuario', 'cuerpo');return false">
                                <div class="form-group">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="inputUser"><i class="fa fa-user" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="user" placeholder="Usuari" aria-label="Usuari" aria-describedby="inputUser">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group mb-4">
                                        <span class="input-group-text" id="inputUserpass"><i class="fa fa-lock" aria-hidden="true"></i></span>
                                        <input type="password" class="form-control" name="key" placeholder="Contrasenya" aria-label="Contrasenya" aria-describedby="inputUserpass">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="mb-2 text-center">
                                        <input class="btn btn-dark" type="submit" value="Entrar">
                                    </div>
                                </div>
                                <p class="text-center"><a href="#" class="btn"><i>Clic ací per recordar contrasenya</i></a></p>
                               
                            </form>
                        </article>
                    </div>
                </aside>
            </div>
        </div>
        <%
            }
            else {
                if(productor == "true"){
                    response.sendRedirect("op_productor.jsp");
                }
                else {
                    response.sendRedirect("op_user.jsp");
                }
            }
        %>
    </body>
</html>
