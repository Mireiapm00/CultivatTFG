<%-- 
    Document   : gestio_comandesProductor
    Created on : 07-ago-2021, 14:56:32
    Author     : mirei
--%>

<%@page import="java.util.List"%>
<%@page import="com.cultivattfg.bd.classesBD.*"%>
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
            response.sendRedirect("./userAccess_component/userAccess.jsp");
        }
        else {
            AccesoBD con = new AccesoBD();
            Usuario infoUsuario = con.obtenerUsuarioBD(usuarioActual);
            
            if(infoUsuario.isProductor()){
                ProductoresBD productor = con.obtenerProductorBD(infoUsuario.getId_usuario());
                List<DetalleBD> detallesPedido = con.obtenerDetallesPedidosPorProductor(productor.getId_productor());
                
%>
            <p class="fw-bold h3 mt-2 mb-3 text-center p-2">Gestió de comandes de <span class="fw-bold rounded-pill bg-light text-dark p-2"><%=usuarioActual%></span> </p>
            
            <div class="d-flex justify-content-center mb-2">
                <div class="mx-4">
                    <table class="table table-hover table-responsive caption-top mx-auto">
                        <caption>
                            <button type="button" class="btn btn-outline-dark me-5" onclick="Cargar('userAccess_component/op_productor.jsp', 'cuerpo')"><i class="fa fa-arrow-left"></i></button>
                            <button type="button" class="btn btn-outline-dark float-end" onclick="Cargar('shop_component/shop.jsp', 'cuerpo')"><i class="fa fa-shopping-basket" aria-hidden="true"></i> Tenda</button>
                        </caption>
                        <thead>
                            <tr class="text-center">
                                <td class="fw-bold px-4 pt-4">Número de comanda</td>
                                <td class="fw-bold px-4 pt-4">Nom del producte</td>
                                <td class="fw-bold px-4 pt-4">Preu unitari</td>
                                <td class="fw-bold px-4 pt-4">Unitats</td>
                                <td class="fw-bold px-4 pt-4">Total</td>
                                <td class="fw-bold px-4 pt-4">Contacte Usuari</td>
                                <td class="fw-bold px-4 pt-4">Estat comanda</td>
                            </tr>
                        </thead>
                        
<%
                    if(detallesPedido.size() != 0){
                        for(DetalleBD d : detallesPedido){
                            ProductosBD producto = con.obtenerProducto(d.getId_producto());
                            PedidosBD pedido = con.obtenerPedido(d.getId_pedido());
                            Usuario user = con.obtenerContactoUsuario(pedido.getId_usuario());
%>
                            <tr class="text-center">
                                <td class="px-4"><%=d.getId_pedido()%></td>
                                <td class="px-4"><%=producto.getNombre()%></td>
                                <td class="px-4"><%=d.getPrecio_unitario()%></td>
                                <td class="px-4"><%=d.getUnidades()%>&nbsp;<%=producto.getUnidad()%></td>
                                <td class="px-4"><%=d.getUnidades()*d.getPrecio_unitario()%> €</td>
                                <td class="px-4"><button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#contactoUsuarioModal<%=pedido.getId_usuario()%>"><i class="fa fa-user" aria-hidden="true"></i> <%=user.getUsuario()%></button></td>
                                <td class="px-4"><%=pedido.getEstado()%> &nbsp;<button type="button" class="btn rounded-pill btn-dark" data-bs-toggle="modal" data-bs-target="#modificarEstadoPedido<%=d.getId_pedido()%>"><i class="fa fa-pencil-square-o"></i></button></td>
                            </tr>
                            
                            <div class="modal fade" id="contactoUsuarioModal<%=pedido.getId_usuario()%>" tabindex="-1" aria-labelledby="contactoUsuarioModal<%=pedido.getId_usuario()%>" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header bg-dark text-white">
                                            <h5 class="modal-title" id="contactoUsuarioModal<%=pedido.getId_usuario()%>">Contacte Usuari <%=user.getUsuario()%></h5>
                                            <button type="button" class="btn-close bg-white" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="d-flex flex-column mx-4">
                                                <div class="mb-2">
                                                    <i class="fa fa-address-card" aria-hidden="true"></i>&nbsp;&nbsp;
                                                    <%=user.getUsuario()%>
                                                </div>
                                                <div class="mb-2">
                                                    <i class="fa fa-phone" aria-hidden="true"></i>&nbsp;&nbsp;
                                                    <%=user.getTelefono()%>
                                                </div>
                                                <div class="mb-2">
                                                    <i class="fa fa-envelope" aria-hidden="true"></i>&nbsp;&nbsp;
                                                    <%=user.getEmail()%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        
                                                
                            <div class="modal fade" id="modificarEstadoPedido<%=d.getId_pedido()%>" tabindex="-1" aria-labelledby="modificarEstadoPedido<%=d.getId_pedido()%>" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header bg-dark text-white">
                                            <h5 class="modal-title" id="modificarEstadoPedido<%=d.getId_pedido()%>">Modificar estat de la comanda</h5>
                                            <button type="button" class="btn-close bg-white" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <input type="number" id="id_pedido" value="<%=d.getId_pedido()%>" hidden>
                                            </div>
                                            <div class="form-group">
                                                <select id="selectedEstado" class="form-select" aria-label="select">
                                                    <option value="Encarregat" <% if(pedido.getEstado().equals("Encarregat")){ %> selected <% } %> >Encarregat</option>
                                                    <option value="En espera per produccio" <% if(pedido.getEstado().equals("En espera per produccio")){ %> selected <% } %> >En espera per producció</option>
                                                    <option value="Preparant la comanda" <% if(pedido.getEstado().equals("Preparant la comanda")){ %> selected <% } %>>Preparant comanda</option>
                                                    <option value="Llest per a recollir" <% if(pedido.getEstado().equals("Llest per a recollir")){ %> selected <% } %>>Llest per a recollir</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-dark me-4" data-bs-dismiss="modal" onclick="ProcesarEstadoPedido('CambiarEstadoPedido', 'cuerpo'); return false">Modificar Estat</button> 
                                        </div>
                                    </div>
                                </div>
                            </div>
<%
                        }
                    }
                    else {
%>
                        <tr class="text-center">
                            <td colspan="6" class="px-4"><img src="./img/logos/nocomandes.png" alt="No comandes"></td>
                        </tr>
<%
                    }             
%>  
                        
                    </table>
                </div>
            </div>
                
                
<%
            }
        }
%>
    </body>
</html>
