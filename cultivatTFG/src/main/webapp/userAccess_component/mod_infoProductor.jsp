<%-- 
    Document   : mod_infoProductor
    Created on : 23-jul-2021, 15:37:20
    Author     : mireia
--%>

<%@page import="com.cultivattfg.bd.classesBD.ProductoresBD"%>
<%@page import="com.cultivattfg.bd.classesBD.Usuario"%>
<%@page import="com.cultivattfg.bd.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>&nbsp;</title>
        <script type="text/javascript">
            function validar_clave(){
                var data = document.form1.pass.value;
                var data1 = document.form1.pass2.value;
                if(data === data1){
                    return true;
                }else{
                    alert("Las contraseñas deben de coincidir.");
                    return false;
                }
            }
        </script>
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
            
            ProductoresBD infoProductor = con.obtenerProductorBD(infoUsuario.getId_usuario());

            int id_categoria = infoProductor.getId_categoria();
            
    %>
    
    <p class="bold h3 mt-2 mb-3 text-center p-2">Modificar dades de <span class="fw-bold rounded-pill bg-light text-dark p-2"><%=infoUsuario.getUsuario()%></span> a <span class="fw-bold rounded-pill bg-light text-dark p-2"><%=infoProductor.getNombre()%></span></p>
    
            <form name="form1" method="post" onsubmit="ProcesarForm(this, 'ModificarDatosProductor', 'cuerpo'); return false">
                <div class="container w-50 mt-4">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingSelloSpge" placeholder="SelloSPGE" name="sellospge" value="<%=infoProductor.getSello_spg()%>" required autofocus>
                        <label for="floatingSelloSpge">Segell SPGE</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control form-control-sm" id="floatingNombre" placeholder="nomprojecte" name="nombre" value="<%=infoProductor.getNombre()%>" required>
                        <label for="floatingNombre">Nom Projecte</label>
                    </div>
                    <div class="form-floating mb-3">
                        <select class="form-select" id="floatingCategoriaProyecto" aria-label="Selecciona una categoria" name="categoriaProyecto" required>
                            <option value="1" <% if(id_categoria == 1){ %> selected <% } %> >Hortalisses i Fruita</option>
                            <option value="2" <% if(id_categoria == 2){ %> selected <% } %>>Ferments, Elaborats i Càtering</option>
                            <option value="3" <% if(id_categoria == 3){ %> selected <% } %>>Pa i productes de forn</option>
                            <option value="4" <% if(id_categoria == 4){ %> selected <% } %>>Mel</option>
                            <option value="5" <% if(id_categoria == 5){ %> selected <% } %>>Oli</option>
                        </select>
                        <label for="floatingCategoriaProyecto">Categoria del projecte</label>
                    </div>    
                    <div class="form-floating mb-3">
                        <input type="tel" class="form-control" id="floatingTelefon" placeholder="Telefon" name="telefono" required pattern="[0-9]{9}" value="<%=infoProductor.getTelefono()%>">
                        <label for="floatingTelefon">Telèfon</label>
                    </div>    
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control form-control-sm" id="floatingEmailProyecto" placeholder="nom@example.com" name="emailProyecto" value="<%=infoProductor.getEmail()%>" required>
                        <label for="floatingEmailProyecto">Direcció email</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingUbicacionProyecto" placeholder="UbicacionProyecto" name="ubicacionProyecto" value="<%=infoProductor.getUbicacion()%>" required>
                        <label for="floatingUbicacionProyecto">Ubicació del projecte</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingWebProyecto" placeholder="WebProjecto" name="webProyecto" value="<%=infoProductor.getWeb()%>" required>
                        <label for="floatingWebProyecto">Web del projecte</label>
                    </div>
                    <div class="form-floating mb-3">
                        <textarea class="form-control" id="floatingRepartoProyecto" placeholder="RepartoProyecto" name="repartoProyecto" style="height: 100px" required><%=infoProductor.getReparto()%></textarea>
                        <label for="floatingRepartoProyecto">Reparto</label>
                    </div>
                    <div class="form-floating mb-3">
                        <textarea class="form-control" id="floatingDescProyecto" placeholder="DescProyecto" name="descProyecto" style="height: 100px" required><%=infoProductor.getDescripcion()%></textarea>
                        <label for="floatingDescProyecto">Descripció del projecte</label>
                    </div>
                        
                    <div class="mb-2 text-center">
                        <input class="btn btn-dark me-4" type="submit" value="Modificar">
                        <input class="btn btn-outline-dark" type="reset" value="Cancelar" onclick="Cargar('./userAccess_component/op_productor.jsp', 'cuerpo')"/>
                    </div>
                </div>
            </form>
    <%
        }
    %>
    </body>
</html>
