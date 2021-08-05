<%-- 
    Document   : shop
    Created on : 17-jul-2021, 23:23:06
    Author     : mireia
--%>

<%@page import="com.cultivattfg.bd.classesBD.ProductosBD"%>
<%@page import="java.util.List"%>
<%@page import="com.cultivattfg.bd.AccesoBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./shop_component/shop.css" rel="stylesheet" type="text/css">
        <title>&nbsp;</title>
    </head>
    <body>
        <div class="container">
            <script>
                muestraCantidadTotalCarrito();
                muestraTotalCarrito();
            </script>
        <%
            String mensaje = (String)session.getAttribute("ERRORSTOCK");
            if(mensaje != null){
                session.removeAttribute("ERRORSTOCK");
        %>
                <div class="alert alert-danger"><%=mensaje%></div>
        <%
            }

            String usuarioActual = (String)session.getAttribute("user");
            
            AccesoBD con = AccesoBD.getInstance();
            List<ProductosBD> productos = con.obtenerProductos();
        %>
            
            <div class="d-flex justify-content-center">
                <p class="fw-bold h3 mt-2 text-center p-2">Productes de l'SPG Ecollaures</p>
            </div>
            <div class="container w-75">
                <div class="d-flex justify-content-between me-2 mb-2">
                    <button type="button" class="btn btn-dark rounded-pill fw-bold" data-bs-toggle="modal" data-bs-target="#modalCarrito" onclick="crearCarritoModal()">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i> Cistella <span id="carrito"></span>
                        <i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;<span id="totalCarrito"></span>
                    </button>
            <%
                if(usuarioActual != null){
            %>
                    <button type="button" class="btn rounded-pill bg-light text-dark" onclick="Cargar('userAccess_component/op_user.jsp', 'cuerpo')">
                        Hola&nbsp;<i class="fa fa-user"></i> <u class="fw-bold"><%=usuarioActual%></u>
                    </button>
            <%   
                }
            %>
                </div>
            </div>
                
            <div class="d-flex justify-content-center">
        <%
            int j = 0;
            for(ProductosBD p: productos){
                if(j < 4){
        %>          
                    <div>
                        <div class="card mx-4 mt-4" style="width: 13rem;">
                            <img src="<%=p.getImgroute()%>" class="card-img w-100 text-center" alt="<%=p.getNombre()%>">
                            <div class="card-body">
                                <p class="card-text text-center">
                                    <span class="fw-bold"><%=p.getNombre()%></span><br>
                                    <%=p.getPrecio_unitario()%> €/<%=p.getUnidad()%>
                                </p>
                            </div>
                        </div>
                        <button type="button" class="btn btn-outline-dark mx-4 mt-1 mb-4" style="width: 13rem;" onclick="anadirProductoCarrito('<%=p.getId_producto()%>', '<%=p.getNombre()%>', '<%=p.getPrecio_unitario()%>', '<%=p.getUnidad()%>')"> Afegir a la Cistella <i class="fa fa-cart-plus" aria-hidden="true"></i></button>
                    </div>
        <%
                }
                else {
        %>
                    </div>
                    <div class="d-flex justify-content-center my-3">
                    <div>
                        <div class="card mx-4 mt-4" style="width: 13rem;">
                            <img src="<%=p.getImgroute()%>" class="card-img w-100" alt="Producte <%=p.getNombre()%>">
                            <div class="card-body">
                                <p class="card-text text-center">
                                    <span class="fw-bold"><%=p.getNombre()%></span><br>
                                    <%=p.getPrecio_unitario()%> €/<%=p.getUnidad()%>
                                </p>
                            </div>
                        </div>
                        <button type="button" class="btn btn-outline-dark mx-4 mt-1 mb-4" style="width: 13rem;" onclick="anadirProductoCarrito('<%=p.getId_producto()%>', '<%=p.getNombre()%>', '<%=p.getPrecio_unitario()%>', '<%=p.getUnidad()%>')"> Afegir a la Cistella <i class="fa fa-cart-plus" aria-hidden="true"></i></button>
                    </div>
        <%          j = 0;
                }
                j++;
            }
        %>
            </div>
        </div>
        
        <!-- MODAL CARRITO -->
        <div class="modal fade" id="modalCarrito" tabindex="-1" aria-labelledby="modalCarrito" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalCarrito"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Cistella</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <table id="carritoModal" class="table table-responsive table-borderless">
                        </table>
                    </div>
                    
                    <div class="modal-footer justify-content-between" id="modal-footer">
                        <div class="rounded-pill bg-light text-dark fw-bold ms-3 p-3">Total: &nbsp;<span id="totalPrecioCarrito"></span></div>
                        <button id="buttonTramitar" type="button" class="btn btn-dark me-4" data-bs-dismiss="modal" onclick="ProcesarCarrito(carrito,'ProcesarPedido','cuerpo')">Tramitar comanda</button>    
                    </div>
                </div>
            </div>
        </div>
            
    </body>
</html>
