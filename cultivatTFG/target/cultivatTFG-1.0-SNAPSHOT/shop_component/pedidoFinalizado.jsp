<%-- 
    Document   : pedidoFinalizado
    Created on : 05-ago-2021, 19:40:40
    Author     : mireia
--%>

<%@page import="com.cultivattfg.bd.classesBD.ProductoresBD"%>
<%@page import="com.cultivattfg.bd.classesBD.DetalleBD"%>
<%@page import="java.util.ArrayList"%>
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
        String usuarioActual = (String)session.getAttribute("user");
        if(usuarioActual == null) { //usuario no registrado
            response.sendRedirect("./userAccess_component/userAccess.jsp");
        }
        else {
            AccesoBD con = new AccesoBD();
            ProductoresBD productor = new ProductoresBD();
            
            int id_pedido = con.obtenerIdUltimoPedido();
            List<DetalleBD> detallesPedido = con.obtenerDetallePorPedido(id_pedido);
            
    %>
    <div class="container w-75" onload="borrarCarrito()">
                <div class="d-flex justify-content-center mt-3 mb-4">
                    <img src="./img/logos/comandaReservada.png" class="img-fluid" alt="Comanda Reservada">
                </div>
                <div class="d-flex justify-content-center my-2">
                    <div class="alert alert-info" role="alert">
                        <p class="p-2 fw-bold">¡Hola <%=usuarioActual%>! Gràcies per la teua reserva.</p>
                        <p>Els productes encomanats són dels següents productors: </p>
                        <div class="d-flex">
    <%
                    for(DetalleBD detalle : detallesPedido){
                        productor = con.obtenerInfoProductorBD(detalle.getId_productor());
    %>
                            <div class="card mx-4" style="width: 14rem;">
                                <div class="card-body">
                                    <h5 class="card-title"><%=productor.getNombre()%></h5>
                                    <p class="card-text">
                                        <i class="fa fa-phone"></i> <%=productor.getTelefono()%><br>
                                        <i class="fa fa-envelope"></i> <%=productor.getEmail()%>
                                    </p>
                                </div>
                            </div>
    <%
                    }
    %>
                        </div>
                        <p class="mt-2 p-2 fw-bold">No oblides <span class="fw-bold rounded-pill bg-light text-dark p-2">contactar amb ells</span> i recollir la teua comanda!</p>
                        <p class="p-2">
                            Pots consultar més informació sobre cada productor 
                            <a href="#" onclick="borrarCarrito(); Cargar('./userAccess_component/gestio_comandes.jsp', 'cuerpo')" role="button" class="btn rounded-pill bg-light text-dark fw-bold">ACÍ</a>
                        </p>
                    </div>
                </div>    
                
                <div class="d-flex justify-content-center">
                    <button type="button" class="btn btn-dark me-4" onclick="borrarCarrito(); Cargar('shop_component/shop.jsp', 'cuerpo')"> <i class="fa fa-shopping-cart" aria-hidden="true"></i> Tornar a la tenda</button>
                    
                    <button type="button" class="btn btn-dark me-4" onclick="borrarCarrito(); Cargar('userAccess_component/userAcess.jsp', 'cuerpo')"> Menú d'usuari</button>
                    
                </div> 
            </div>
    
    <%
        }
    %>
        
    </body>
</html>
