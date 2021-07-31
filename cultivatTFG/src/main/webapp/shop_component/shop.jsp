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
        <title>&nbsp;</title>
    </head>
    <body>
        <div class="container">
            <script>
                muestraCantidadTotalCarrito();
            </script>
            
        <%
           String usuarioActual = (String)session.getAttribute("user");
           if(usuarioActual != null){
           }
        %>
        
        
        <%
            AccesoBD con = AccesoBD.getInstance();
            List<ProductosBD> productos = con.obtenerProductos();
        %>
            
            <div class="d-flex justify-content-center">
                <p class="fw-bold h3 mt-2 text-center p-2">Productes de l'SPG Ecollaures</p>
            </div>
            <div class="container w-75">
                <div class="d-flex justify-content-end me-2 mb-2">
                    
                    <a class="btn btn-dark rounded-pill fw-bold" data-bs-toggle="collapse" href="#collapseCarrito" role="button" aria-expanded="false" aria-controls="collapseCarrito">
                        Cistella <span id="carrito"></span>
                    </a>
                    
                    <!--<h5><span class="badge rounded-pill bg-dark"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Cistella<span id="carrito"></span></span></h5>-->
                </div>
                <div class="collapse" id="collapseCarrito">
                    <div class="card card-body" id="carritoCollapse">
                        <script>
                            mostrarCarritoCollapse();
                        </script>
                    </div>
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
                        <button type="button" class="btn btn-outline-dark mx-4 mt-1 mb-4" style="width: 13rem;" onclick="anadirProductoCarrito('<%=p.getId_producto()%>', '<%=p.getNombre()%>', '<%=p.getPrecio_unitario()%>')">Afegir a la Cistella</button>
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
                        <button type="button" class="btn btn-outline-dark mx-4 mt-1 mb-4" style="width: 13rem;" onclick="anadirProductoCarrito('<%=p.getId_producto()%>')">Afegir a la Cistella</button>
                    </div>
        <%          j = 0;
                }
                j++;
            }
        %>
            </div>
        </div>
        
    </body>
</html>
