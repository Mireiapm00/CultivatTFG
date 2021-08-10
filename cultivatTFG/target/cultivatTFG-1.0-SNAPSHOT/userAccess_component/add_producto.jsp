<%-- 
    Document   : add_producto
    Created on : 28-jul-2021, 14:11:56
    Author     : mireia
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
            response.sendRedirect("userAccess.jsp");
        }
        else {
            AccesoBD con = AccesoBD.getInstance();
            int id_usuario = con.obtenerIdUsuarioBD(usuarioActual);
            int id_productor = con.obtenerIdProductorBD(id_usuario);
            int id_categoria = con.obtenerIdCategoriaProductor(id_productor);
    %>
    <form name="form1" method="post" enctype="multipart/form-data"  action="RegistroProducto" ><!--  onsubmit="ProcesarForm(this, 'RegistroProducto', 'cuerpo'); return false" -->
            <div class="container w-50 mt-1">
                <p class="fw-bold h3 text-center p-2">Afegir Producte</p>
                <div class="form-floating mb-3">
                        <select class="form-select" id="floatingCategoria" aria-label="Selecciona una categoria" name="categoria" required>
                            <option value="1" <% if(id_categoria == 1){ %> selected <% } %> >Hortalisses i Fruita</option>
                            <option value="2" <% if(id_categoria == 2){ %> selected <% } %>>Ferments, Elaborats i Càtering</option>
                            <option value="3" <% if(id_categoria == 3){ %> selected <% } %>>Pa i productes de forn</option>
                            <option value="4" <% if(id_categoria == 4){ %> selected <% } %>>Mel</option>
                            <option value="5" <% if(id_categoria == 5){ %> selected <% } %>>Oli</option>
                        </select>
                        <label for="floatingCategoria">Categoria</label>
                    </div>    
                <div class="form-floating mb-3 text-center">
                    <input type="text" class="form-control" id="floatingNombreProducto" placeholder="NombreProducto" name="nombreProducto" required autofocus>
                    <label for="floatingNombreProducto">Nom del producte</label>
                </div>
                <div class="form-floating mb-3 text-center">
                    <input type="num" class="form-control" id="floatingStock" placeholder="Stock" name="stock" required>
                    <label for="floatingStock">Stock</label>
                </div>
                <div class="form-floating mb-3 text-center">
                    <input type="text" class="form-control" id="floatingUnitat" placeholder="UnitatMesura" name="unidadMedida" required>
                    <label for="floatingUnitat">Unitat de Mesura</label>
                </div>
                <div class="form-floating mb-3 text-center">
                    <input type="num" class="form-control" id="floatingPrecio" placeholder="Precio" name="precio" required>
                    <label for="floatingPrecio">Preu unitari</label>
                </div>
                <div class="form-floating mb-3 text-center">
                    <textarea class="form-control" id="floatingIngredientes" placeholder="Ingredientes" name="ingredientes" style="height: 100px" required></textarea>
                    <label for="floatingIngredientes">Ingredients</label>
                </div>
                <div class="mb-3">
                    <label for="floatingImage">Selecciona una imatge</label>
                    <input type="file" class="form-control" id="floatingImage" name="floatingImage" size="50" required/>
                </div>
                <div class="mb-2 text-center">
                    <button type="submit" class="btn btn-dark me-4">Afegir Producte</button>
                    <button type="button" class="btn btn-outline-dark" onclick="Cargar('./userAccess_component/mod_productos.jsp', 'cuerpo')">Cancelar</button>
                </div>
            </div>
        </form>
    <%
        }
    %>
    </body>
</html>
