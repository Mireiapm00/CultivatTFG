<%-- 
    Document   : mod_productes
    Created on : 27-jul-2021, 19:19:08
    Author     : mireia
--%>

<%@page import="com.cultivattfg.bd.classesBD.CategoriasBD"%>
<%@page import="com.cultivattfg.bd.classesBD.ProductosBD"%>
<%@page import="java.util.List"%>
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
            AccesoBD con = AccesoBD.getInstance();
            int id_usuario = con.obtenerIdUsuarioBD(usuarioActual);
            int id_productor = con.obtenerIdProductorBD(id_usuario);
            
            List<CategoriasBD> categories = con.obtenerCategoriasBD();                    
            CategoriasBD cat;
            List<ProductosBD> productos = con.obtenerProductosPorProductor(id_productor);
    %>
            
            <div class="d-flex justify-content-center">
                <div>
                    <p class="bold h3 mt-2 p-2">Gestió dels productes de <b><%=usuarioActual%></b></p>
                </div>
            </div>
            <div class="d-flex justify-content-center mb-2">
                <div class="mx-4">
                    <table class="table  table-hover table-responsive caption-top mx-auto">
                        <caption>
                            <button type="button" class="btn btn-outline-dark me-5" onclick="Cargar('userAccess_component/op_productor.jsp', 'cuerpo')"><i class="fa fa-arrow-left"></i></button>
                            <button type="button" class="btn btn-outline-dark float-end" onclick="Cargar('userAccess_component/add_producto.jsp','cuerpo')"><i class="fa fa-plus"></i> Afegir Producte</button>
                        </caption>
                        <thead>
                            <tr class="text-center">
                                <td class="fw-bold px-4 pt-4">Nom del producte</td>
                                <td class="fw-bold px-4 pt-4">Stock</td>
                                <td class="fw-bold px-4 pt-4">Unitat</td>
                                <td class="fw-bold px-4 pt-4">Preu unitari</td>
                                <td class="fw-bold px-4 pt-4">Ingredients</td>
                                <td class="fw-bold px-4 pt-4">Categoria</td>
                                <td class="fw-bold px-4 pt-4">Accions</td>
                            </tr>
                        </thead>
                        <tbody>
                    <%
                        for(ProductosBD p: productos){
                    %>
                        <tr class="text-center">
                            <td class="px-4"><%=p.getNombre()%></td>
                            <td class="px-4"><%=p.getStock()%></td>
                            <td class="px-4"><%=p.getUnidad()%></td>
                            <td class="px-4"><%= p.getPrecio_unitario()%></td>
                            <td class="px-4"><%=p.getIngredientes()%></td>
                            <td>
                            <%
                                    
                                if(p.getId_categoria() == 1){
                                    cat = categories.get(0);
                            %>
                                    <%=cat.getNombre()%>
                            <%
                                }
                                else if(p.getId_categoria() == 2){
                                    cat = categories.get(1);
                            %>
                                    <%=cat.getNombre()%>
                            <%
                                }
                                else if(p.getId_categoria() == 3){
                                    cat = categories.get(2);
                            %>
                                    <%=cat.getNombre()%>
                            <%
                                }
                                else if(p.getId_categoria() == 4){
                                    cat = categories.get(3);
                            %>
                                    <%=cat.getNombre()%>
                            <%
                                }
                                else if(p.getId_categoria() == 5){
                                    cat = categories.get(4);
                            %>
                                    <%=cat.getNombre()%>
                            <%
                                }
                            %>
                            </td>
                            <td>
                                <div class="d-flex">
                                    <div class="me-2">
                                        <form name="form1" method="post" onsubmit="ProcesarForm(this,'ModificarProducto','cuerpo'); return false">
                                            <input type="text" name="id_producto" hidden value="<%=p.getId_producto()%>"/>
                                            <input type="submit" class="btn btn-dark" value="Modificar"/>
                                        </form>
                                    </div>
                                    <div>
                                        <form name="form2" method="post" onsubmit="ProcesarForm(this,'EliminarProducto','cuerpo'); return false">
                                            <input type="text" name="id_producto" hidden value="<%=p.getId_producto()%>"/>
                                            <input type="submit" class="btn btn-dark" value="Eliminar"/>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                        </tbody>
                    </table>
                </div>
            </div>
    <%
        }
    %>
    </body>
</html>
