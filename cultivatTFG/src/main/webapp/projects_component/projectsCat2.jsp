<%-- 
    Document   : projectsCat2
    Created on : 18-jul-2021, 21:20:35
    Author     : mirei
--%>

<%@page import="com.cultivattfg.bd.classesBD.ProductoresBD"%>
<%@page import="com.cultivattfg.bd.classesBD.CategoriasBD"%>
<%@page import="com.cultivattfg.bd.AccesoBD"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>&nbsp;</title>
        <link href="./projects_component/projects.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%
            List<String> catRoutes = Arrays.asList("projects_component/projects.jsp", 
                                                    "projects_component/projectsCat1.jsp",
                                                    "projects_component/projectsCat2.jsp",
                                                    "projects_component/projectsCat3.jsp",
                                                    "projects_component/projectsCat4.jsp");
            AccesoBD con = AccesoBD.getInstance();
            List<CategoriasBD> categories = con.obtenerCategoriasBD();                    
                        
            List<ProductoresBD> productores = con.obtenerProductoresPorCategoria(3);
        %>
        <div class="container">
            <p class="bold h2 text-center mt-2 mb-3 p-2">Projectes</p>
        
            <div class="d-flex justify-content-center">
            <%
                int i = 0;
                for(CategoriasBD cat: categories){
                    String nombre = cat.getNombre();
                    String route = catRoutes.get(i);
            %>
            <button type="button" class="btn btn-outline-dark me-4" onclick="Cargar('<%=route%>', 'cuerpo')"><%=nombre%></button>        
            <%      
                    i++;
                }
            %>
            </div>
        </div>
            
        <div class="d-flex justify-content-center my-3">
    <%
        int j = 0;
        for(ProductoresBD prod : productores){
            if(j < 3){
    %>
                <div>
                    <div class="card mx-4 mt-4" style="width: 15rem;">
                        <img src="<%=prod.getImgroute()%>" class="card-img" alt="Detall productor <%=prod.getNombre()%>">
                        <div class="card-img-overlay">
                            <h5 class="card-title text-white"></h5>
                        </div>
                        <!--<div class="card-body">
                            <p class="card-text"><%=prod.getNombre()%> - <%=prod.getUbicacion()%></p>
                        </div>-->
                    </div>
                    <button type="button" class="btn btn-dark mx-4 mt-1 mb-4" style="width: 15rem;" data-toggle="modal" data-target="#modalCenter<%=prod.getId_productor()%>">+ Info</button>
                </div>
                <div class="modal fade" id="modalCenter<%=prod.getId_productor()%>" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle<%=prod.getId_productor()%>" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered  modal-dialog-scrollable modal-xl" role="document">
                        <div class="modal-content">
                            <div class="modal-header bg-dark text-white">
                                <h5 class="modal-title" id="modalCenterTitle<%=prod.getId_productor()%>"><b><%=prod.getNombre()%></b></h5>
                                <button type="button" class="close btn-outline-dark" data-dismiss="modal" aria-label="Close">
                                    <i class="fa fa-times"></i>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="d-flex justify-content-center">
                                    <div class="mx-4">
                                        <img src="<%=prod.getImgroute()%>" class="rounded img-fluid" alt="Detall productor">
                                    </div>
                                    <div class="d-flex flex-column mx-4">
                                        
                                        <div class="mb-2"><b>Segell:</b> <%=prod.getSello_spg()%></div>
                                        
                                        <div><b>Descripció del projecte:</b></div>
                                        <div class="mb-2"><%=prod.getDescripcion()%></div>
                                        
                                        <div><i class="fa fa-map-marker"></i> <%=prod.getUbicacion()%></div>
                                        <hr>
                                        
                                        <div class="mt-2"><b>Repartiment: </b></div>
                                        <div class="mb-2"><i class="fa fa-truck"></i> <%=prod.getReparto()%></div>
                                        
                                        <div><b>Contacte:</b></div>
                                        <div class="mb-2"><i class="fa fa-phone"></i> <%=prod.getTelefono()%></div>
                                        <div class="mb-2"><i class="fa fa-envelope"></i> <%=prod.getEmail()%></div>
                                        <div class="mb-2"><i class="fa fa-globe"></i> <a href="<%=prod.getWeb()%>" target="_blank"><%=prod.getWeb()%></a></div>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>           
                            
    <%
            }
            else {
    %>
        </div>
        <div class="d-flex justify-content-center my-3">
            <div>
                <div class="card mx-4 mt-4" style="width: 15rem;">
                    <img src="<%=prod.getImgroute()%>" class="card-img" alt="Detall productor <%=prod.getNombre()%>">
                    <div class="card-img-overlay">
                        <h5 class="card-title text-white"></h5>
                    </div>
                    <!--<div class="card-body">
                        <p class="card-text"><%=prod.getNombre()%> - <%=prod.getUbicacion()%></p>
                    </div>-->
                </div>
                <button type="button" class="btn btn-dark mx-4 mt-1 mb-4" style="width: 15rem;" data-toggle="modal" data-target="#modalCenter<%=prod.getId_productor()%>">+ Info</button>
            </div>
            <div class="modal fade" id="modalCenter<%=prod.getId_productor()%>" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle<%=prod.getId_productor()%>" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered  modal-dialog-scrollable modal-xl" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modalCenterTitle<%=prod.getId_productor()%>"><b><%=prod.getNombre()%></b></h5>
                            <button type="button" class="close btn-light" data-dismiss="modal" aria-label="Close">
                                <i class="fa fa-times"></i>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="d-flex justify-content-center">
                                <div class="mx-4">
                                    <img src="<%=prod.getImgroute()%>" class="rounded img-fluid" alt="Detall productor">
                                </div>
                                <div class="d-flex flex-column mx-4">

                                    <div class="mb-2"><b>Segell:</b> <%=prod.getSello_spg()%></div>

                                    <div><b>Descripció del projecte:</b></div>
                                    <div class="mb-2"><%=prod.getDescripcion()%></div>

                                    <div><i class="fa fa-map-marker"></i> <%=prod.getUbicacion()%></div>
                                    <hr>

                                    <div class="mt-2"><b>Repartiment: </b></div>
                                    <div class="mb-2"><i class="fa fa-truck"></i> <%=prod.getReparto()%></div>

                                    <div><b>Contacte:</b></div>
                                    <div class="mb-2"><i class="fa fa-phone"></i> <%=prod.getTelefono()%></div>
                                    <div class="mb-2"><i class="fa fa-envelope"></i> <%=prod.getEmail()%></div>
                                    <div class="mb-2"><i class="fa fa-globe"></i> <a href="<%=prod.getWeb()%>" target="_blank"><%=prod.getWeb()%></a></div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    <%
                j = 0;
            }
            j++;
        }
    %>
        </div>
        
    </body>
</html>
