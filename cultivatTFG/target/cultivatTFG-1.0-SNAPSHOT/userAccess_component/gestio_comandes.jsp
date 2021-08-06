<%-- 
    Document   : gestio_comandes
    Created on : 27-jul-2021, 19:27:05
    Author     : mireia
--%>

<%@page import="java.util.List"%>
<%@page import="com.cultivattfg.bd.classesBD.*"%>
<%@page import="com.cultivattfg.bd.AccesoBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./userAccess_component/userAccess.css" rel="stylesheet" type="text/css">
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
            Usuario infoUsuario = con.obtenerUsuarioBD(usuarioActual);
            List<PedidosBD> pedidos = con.obtenerPedidosPorUsuario(infoUsuario.getId_usuario());
            
%>
            <p class="fw-bold h3 mt-2 mb-3 text-center p-2">Les meues comandes</p>
            
            <div class="d-flex justify-content-center mb-2">
                <div class="mx-4">
                    <table class="table table-hover table-responsive caption-top mx-auto">
                        <caption>
                            <button type="button" class="btn btn-outline-dark me-5" onclick="Cargar('userAccess_component/op_user.jsp', 'cuerpo')"><i class="fa fa-arrow-left"></i></button>
                            <button type="button" class="btn btn-outline-dark float-end" onclick="Cargar('shop_component/shop.jsp','cuerpo')"><i class="fa fa-shopping-basket" aria-hidden="true"></i> Tenda</button>
                        </caption>
                        <thead>
                            <tr class="text-center">
                                <td class="fw-bold px-4 pt-4">Número de comanda</td>
                                <td class="fw-bold px-4 pt-4">Data</td>
                                <td class="fw-bold px-4 pt-4">Import</td>
                                <td class="fw-bold px-4 pt-4">Estat</td>
                                <td class="fw-bold px-4 pt-4"></td>
                            </tr>
                        </thead>

                        <tbody>
<%
                    for(PedidosBD p : pedidos){
                        List<DetalleBD> detalles = con.obtenerDetallesPedidosPorIdPedido(p.getId_pedido());
                        

%>
                            <tr class="text-center">
                                <td class="px-4"><%=p.getId_pedido()%></td>
                                <td class="px-4"><%=p.getFecha()%></td>
                                <td class="px-4"><%=p.getImporte()%> €</td>
                                <td class="px-4"><%= p.getEstado()%></td>
                                <td class="px-4"><button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#detallePedidoModal<%=p.getId_pedido()%>"><i class="fa fa-plus" aria-hidden="true"></i> Detalls</button></td>
                            </tr>
                            
                            <div class="modal fade" id="detallePedidoModal<%=p.getId_pedido()%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            
                            <div class="modal-dialog modal-dialog-scrollable modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title fw-bold" id="detallePedidoModal<%=p.getId_pedido()%>">Detall comanda Nº <%=p.getId_pedido()%></h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="d-flex align-items-center justify-content-center">
<%
                                    int i = 0;
                                    for(DetalleBD d : detalles){
                                        ProductosBD producto = con.obtenerProducto(d.getId_producto());
                                        i++;
%>
                                        <div class="card mx-4" style="border: none">
                                            <img src="<%=producto.getImgroute()%>" alt="" class="img-thumbnail w-50">
                                            <div class="card-body">
                                                <h5 class="card-title fw-bold"><%=producto.getNombre()%></h5>
                                                <p class="card-text">
                                                    <i>(<%=producto.getPrecio_unitario()%> €/<%=producto.getUnidad()%>)</i><br>
                                                    <%=d.getUnidades()%> <%=producto.getUnidad()%>
                                                    <hr>
                                                    <p class="fw-bold text-center"><%=(d.getUnidades()*producto.getPrecio_unitario())%>€</p>
                                                </p>
                                            </div>
                                        </div>
<%
                                        if(i != detalles.size()){
%>                              
                                            <i class="fa fa-plus" aria-hidden="true"></i>
<%
                                        }
                                    }
%>
                                        </div>
                                    </div>
                                    <div class="modal-footer justify-content-center">
                                        <h5 class="fw-bold text-center border border-success rounded-pill p-3">Total comanda: <%=p.getImporte()%>€</h5>
                                    </div>
                                </div>
                            </div>
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
