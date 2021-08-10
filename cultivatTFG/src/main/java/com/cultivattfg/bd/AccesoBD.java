/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mireia
 */
package com.cultivattfg.bd;

import com.cultivattfg.bd.classesBD.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AccesoBD {
    
    private static AccesoBD instanciaUnica = null;
    private Connection conexionBD = null;
    
    /* Garantiza que solo hay una instancia de esta clase */
    public static AccesoBD getInstance() {
        if(instanciaUnica == null){
            instanciaUnica = new AccesoBD();
        }
        return instanciaUnica;
    }
    
    /* Conexion a la BD */
    public AccesoBD() {
        abrirConexionBD();
    }
    
    public void abrirConexionBD() {
        if(conexionBD == null){
            String nombreConexionBD = "jdbc:mysql://localhost/ecollaures";
            
            try {
                //root y sin clave: usuario por defecto que crea XAMPP
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexionBD = DriverManager.getConnection(nombreConexionBD, "root", "");
            }
            catch (ClassNotFoundException | SQLException e){
                System.out.println("No se ha podido conectar a la BBDD");
            }
        }
    }
    
    public boolean comprobarAcceso() {
        abrirConexionBD();
        return conexionBD != null;
    }
    
    public void cerrarConexionBD() {
        if(conexionBD != null) {
            try {
                conexionBD.close();
                conexionBD = null;
            }
            catch(Exception e) {
                System.out.println("No se ha completado la desconexion");
            }
        }
    }
    
    boolean comprobarUsuarioBD(String user, String key) {
        abrirConexionBD();
        
        ResultSet resultados = null;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM `usuarios` WHERE usuario='" + user + "' and password='" + key + "'";
            resultados = s.executeQuery(con);
            if(resultados.next()){
                return true;
            }
            else {
                return false;
            }
        }
        catch(SQLException e){
            System.out.println("No se ha completado la petición");
            return false;
        }
    }
    
    boolean comprobarUsuarioProductorBD(String user, String key){
        abrirConexionBD();
        
        boolean productor = false;
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT productor FROM `usuarios` WHERE usuario='" + user + "' and password='" + key + "'";
            resultados = s.executeQuery(con);
            
            if(resultados.next()){
                productor = resultados.getBoolean("productor");
                return productor;
            }
            else {
                return false;
            }
        }
        catch (SQLException e){
            System.out.println("No se ha completado la petición");
            return false;
        }
    }
    
    public Usuario obtenerUsuarioBD(String usuario){
        abrirConexionBD();

        ResultSet resultados = null;
        Usuario user = new Usuario();
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM usuarios WHERE usuario=\"" + usuario + "\"";
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                user.setId_usuario(resultados.getInt("id_usuario"));
                user.setUsuario(resultados.getString("usuario"));
                user.setPassword(resultados.getString("password"));
                user.setEmail(resultados.getString("email"));
                user.setNombre(resultados.getString("nombre"));
                user.setApellidos(resultados.getString("apellidos"));
                user.setDomicilio(resultados.getString("domicilio"));
                user.setPoblacion(resultados.getString("poblacion"));
                user.setProvincia(resultados.getString("provincia"));
                user.setCp(resultados.getInt("cp"));
                user.setTelefono(resultados.getInt("telefono"));
                user.setProductor(resultados.getBoolean("productor"));
            }
        }
        catch(SQLException e){
            System.out.println("Error al completar la consulta");
        }
                
	cerrarConexionBD();
        return user;
    }
    
	public Usuario obtenerContactoUsuario(int id_usuario){
        abrirConexionBD();

        ResultSet resultados = null;
        Usuario user = new Usuario();
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT id_usuario, usuario, email, nombre, telefono FROM usuarios WHERE id_usuario=\"" + id_usuario + "\"";
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                user.setId_usuario(resultados.getInt("id_usuario"));
                user.setUsuario(resultados.getString("usuario"));
                user.setEmail(resultados.getString("email"));
                user.setNombre(resultados.getString("nombre"));
                user.setTelefono(resultados.getInt("telefono"));
            }
        }
        catch(SQLException e){
            System.out.println("Error al completar la consulta");
        }
        
        cerrarConexionBD();        
        return user;
    }
	
    public ProductoresBD obtenerProductorBD(int id_usuario){
        abrirConexionBD();

        ResultSet resultados = null;
        ProductoresBD productor = new ProductoresBD();
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM productores WHERE id_usuario = " + id_usuario + "";
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                productor.setId_productor(resultados.getInt("id_productor"));
                productor.setId_usuario(resultados.getInt("id_usuario"));
                productor.setId_categoria(resultados.getInt("id_categoria"));
                productor.setSello_spg(resultados.getString("sello_spg"));
                productor.setNombre(resultados.getString("nombre"));
                productor.setTelefono(resultados.getInt("telefono"));
                productor.setEmail(resultados.getString("email"));
                productor.setUbicacion(resultados.getString("ubicacion"));
                productor.setWeb(resultados.getString("web"));
                productor.setReparto(resultados.getString("reparto"));
                productor.setDescripcion(resultados.getString("descripcion"));
            }
        }
        catch(SQLException e){
            System.out.println("Error al completar la consulta");
        }
        
	cerrarConexionBD();
        return productor;
    }
    
    public ProductoresBD obtenerInfoProductorBD(int id_productor){
        abrirConexionBD();

        ResultSet resultados = null;
        ProductoresBD productor = new ProductoresBD();
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM productores WHERE id_productor = " + id_productor + "";
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                productor.setId_productor(resultados.getInt("id_productor"));
                productor.setId_usuario(resultados.getInt("id_usuario"));
                productor.setId_categoria(resultados.getInt("id_categoria"));
                productor.setSello_spg(resultados.getString("sello_spg"));
                productor.setNombre(resultados.getString("nombre"));
                productor.setTelefono(resultados.getInt("telefono"));
                productor.setEmail(resultados.getString("email"));
                productor.setUbicacion(resultados.getString("ubicacion"));
                productor.setWeb(resultados.getString("web"));
                productor.setReparto(resultados.getString("reparto"));
                productor.setDescripcion(resultados.getString("descripcion"));
            }
        }
        catch(SQLException e){
            System.out.println("Error al completar la consulta");
        }
        
	cerrarConexionBD();
        return productor;
    }
    
    
    public List<CategoriasBD> obtenerCategoriasBD(){
        abrirConexionBD();
        
        ArrayList<CategoriasBD> categorias = new ArrayList<>();
        CategoriasBD categoria;
        
        ResultSet resultados = null;
        
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT * FROM categorias";
            resultados = s.executeQuery(con);
            
            while(resultados.next()){
                categoria = new CategoriasBD();
                categoria.setId_categoria(resultados.getInt("id_categoria"));
                categoria.setNombre(resultados.getString("nombre"));
                categorias.add(categoria);
            }
        }
        catch(SQLException e){
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
	cerrarConexionBD();
        return categorias;
    }
    
    public boolean registroUsuarioBD(Usuario usuario) {
        abrirConexionBD();
        boolean ok = false;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "INSERT INTO usuarios (usuario, password, activo, email, telefono, productor)"
                    + " VALUES (\"" + usuario.getUsuario() + "\",\"" + usuario.getPassword() + "\"," + usuario.isActivo() + ",\"" 
                    + usuario.getEmail() + "\"," + usuario.getTelefono() + ", " + usuario.isProductor()+ ")";
            
            s.executeUpdate(con);
            ok = true;

        }catch(SQLException e){
            System.out.println("Error al insertar en la BBDD");
        }
        
	cerrarConexionBD();
        return ok;
    }
    
    public boolean registroProductoBD(ProductosBD producto){
        abrirConexionBD();
        
        boolean ok = false;
        
        try {
            
            String sql = "INSERT INTO productos (id_categoria, id_productor, nombre, stock, unidad, precio_unitario, ingredientes, foto)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement statement = conexionBD.prepareStatement(sql);
            statement.setInt(1, producto.getId_categoria());
            statement.setInt(2, producto.getId_productor());
            statement.setString(3, producto.getNombre());
            statement.setInt(4, producto.getStock());
            statement.setString(5, producto.getUnidad());
            statement.setFloat(6, producto.getPrecio_unitario());
            statement.setString(7, producto.getIngredientes());
            statement.setBlob(8, producto.getFoto());
            
            int rows = statement.executeUpdate();
            
            if(rows > 0)
                ok = true;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Error al insertar en la BBDD. ");
        }
        
	cerrarConexionBD();
        return ok;
    }
    
    /*
    public boolean registroProductoBD(ProductosBD producto){
        abrirConexionBD();
        
        boolean ok = false;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "INSERT INTO productos (id_categoria, id_productor, nombre, stock, unidad, precio_unitario, ingredientes, foto)"
                    + " VALUES( " + producto.getId_categoria() + ", " + producto.getId_productor() + ", \"" + producto.getNombre() + "\", "
                    + producto.getStock() + ", \"" + producto.getUnidad() + "\", " + producto.getPrecio_unitario() + ", \"" 
                    + producto.getIngredientes() + "\", " + producto.getFoto() + " )";
            
            s.executeUpdate(con);
            ok = true;

        }catch(SQLException e){
            System.out.println("Error al insertar en la BBDD. ");
        }
        
	cerrarConexionBD();
        return ok;
    }*/
    
    public int obtenerIdUsuarioBD(String usuario){
        abrirConexionBD();
        
        int id_usuario = 0;
        ResultSet resultados = null;
        
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT * FROM usuarios WHERE usuario = \"" + usuario + "\"";
            resultados = s.executeQuery(con);
            
            while(resultados.next()){
                id_usuario = resultados.getInt("id_usuario");
            }
        }
        catch(SQLException e){
            System.out.println("Error al consultar a la BBDD");
        }
        
	cerrarConexionBD();
        return id_usuario;
    }
    
    public int obtenerIdProductorBD(int id_usuario){
        abrirConexionBD();
        
        int id_productor = 0;
        ResultSet resultados = null;
        
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT id_productor FROM productores WHERE id_usuario = \"" + id_usuario + "\"";
            resultados = s.executeQuery(con);
            
            while(resultados.next()){
                id_productor = resultados.getInt("id_productor");
            }
        }
        catch(SQLException e){
            System.out.println("Error al consultar a la BBDD");
        }
        
	cerrarConexionBD();
        return id_productor;
    }
    
    public int obtenerIdCategoriaProductor(int id_productor){
        abrirConexionBD();
        int id_categoria = 0;
        ResultSet resultados = null;
        
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT id_categoria FROM productores WHERE id_productor = \"" + id_productor + "\"";
            resultados = s.executeQuery(con);
            
            while(resultados.next()){
                id_categoria = resultados.getInt("id_categoria");
            }
        }
        catch(SQLException e){
            System.out.println("Error al consultar a la BBDD");
        }
        
        cerrarConexionBD();
        return id_categoria;
    }
    
    public int obtenerStockProducto(int id_producto){
        abrirConexionBD();
        int stock = 0;
        
        ResultSet resultados = null;
        
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT stock FROM productos WHERE id_producto = " + id_producto;
            resultados = s.executeQuery(con);
            
            while(resultados.next()){
                stock = resultados.getInt("stock");
            }
        }
        catch(SQLException e){
            System.out.println("Error al consultar a la BBDD");
        }
        
	cerrarConexionBD();
        return stock;
    }
    
    public void modificarStockProductoBD(int id_producto, int stock) {
        abrirConexionBD();
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "UPDATE productos SET stock = " + stock + " WHERE id_producto=" + id_producto;
            s.executeUpdate(con);
        }
        catch(SQLException e){
            System.out.println("Error al actualizar la BBDD");
        }  
		
	cerrarConexionBD();
    }
    
	public boolean modificarEstadoPedido(int id_pedido, String estado){
        abrirConexionBD();
        boolean ok = false;
        
        try{
            String con;
            Statement s = conexionBD.createStatement();
            con = "UPDATE pedidos SET estado = \"" + estado + "\" WHERE id_pedido = " + id_pedido;
            s.executeUpdate(con);
            ok = true;
        }
        catch(SQLException e){
             System.out.println("Error al actualizar la BBDD");
        }
        
        cerrarConexionBD();
        return ok;
    }
	
    public boolean registroUsuarioProductorBD(ProductoresBD productor){
        abrirConexionBD();
        boolean ok = false;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "INSERT INTO productores (id_usuario, id_categoria, sello_spg, nombre, telefono, email, ubicacion, web, reparto, descripcion)"
                    + " VALUES (" + productor.getId_usuario() + ", " + productor.getId_categoria() + ", \"" + productor.getSello_spg() + "\", \"" 
                    + productor.getNombre() + "\", "  + productor.getTelefono() + ", \"" + productor.getEmail() + "\", \"" + productor.getUbicacion() + "\", \"" 
                    + productor.getWeb() + "\", \"" + productor.getReparto() + "\", \"" + productor.getDescripcion() + "\"" + ")";
            
            s.executeUpdate(con);
            ok = true;

        }catch(SQLException e){
            System.out.println("Error al insertar en la BBDD");
        }
        
	cerrarConexionBD();
        return ok;
    }
    
    public boolean registroPedidosBD(PedidosBD pedido){
        abrirConexionBD();
        boolean ok = false;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "INSERT INTO pedidos (id_usuario, fecha, importe, estado) VALUES "
                    + "(" + pedido.getId_usuario() + ", CURRENT_TIMESTAMP, " + pedido.getImporte() + ", \""
                    + pedido.getEstado() + "\" )";
            
            s.executeUpdate(con);
            ok = true;

        }catch(SQLException e){
            System.out.println("Error al insertar en la BBDD");
        }
        
	cerrarConexionBD();
        return ok;
    }
    
    public boolean registroDetallePedidosBD(DetalleBD detalle){
        abrirConexionBD();
        boolean ok = false;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "INSERT INTO detalle (id_pedido, id_producto, id_productor, unidades, precio_unitario) VALUES "
                    + "(" + detalle.getId_pedido() + ", " + detalle.getId_producto() + ", " + detalle.getId_productor()
                    + ", " + detalle.getUnidades() + ", " + detalle.getPrecio_unitario() + ")";
            
            s.executeUpdate(con);
            ok = true;

        }catch(SQLException e){
            System.out.println("Error al insertar en la BBDD");
        }
        
	cerrarConexionBD();
        return ok;
    }
    
    public List<PedidosBD> obtenerPedidosPorUsuario(int id_usuario){
        abrirConexionBD();
		ArrayList<PedidosBD> pedidos = new ArrayList<>();
        PedidosBD pedido;
        
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT * FROM pedidos WHERE id_usuario = " + id_usuario;
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                pedido = new PedidosBD();
                pedido.setId_pedido(resultados.getInt("id_pedido"));
                pedido.setId_usuario(resultados.getInt("id_usuario"));
                pedido.setFecha(resultados.getDate("fecha"));
                pedido.setImporte(resultados.getFloat("importe"));
                pedido.setEstado(resultados.getString("estado"));
                pedidos.add(pedido);
            }
        }
        catch(SQLException e) {
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
        cerrarConexionBD();
        return pedidos;
    }
    
    public List<DetalleBD> obtenerDetallesPedidosPorProductor(int id_productor){
        abrirConexionBD();
	ArrayList<DetalleBD> detalles = new ArrayList<>();
        DetalleBD detalle;
        
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT * FROM detalle WHERE id_productor = " + id_productor;
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                detalle = new DetalleBD();
                detalle.setId_pedido(resultados.getInt("id_pedido"));
                detalle.setId_producto(resultados.getInt("id_producto"));
                detalle.setId_productor(resultados.getInt("id_productor"));
                detalle.setUnidades(resultados.getInt("unidades"));
                detalle.setPrecio_unitario(resultados.getFloat("precio_unitario"));
                detalles.add(detalle);
            }
        }
        catch(SQLException e) {
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
	cerrarConexionBD();
        return detalles;
    }
    
    public int obtenerIdUltimoPedido(){
		abrirConexionBD();
        int id_pedido = 0;
        ResultSet resultados = null;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT id_pedido FROM pedidos ORDER BY id_pedido desc LIMIT 1;";
            resultados = s.executeQuery(con);
            
            while(resultados.next()){
                id_pedido = resultados.getInt("id_pedido");
            }
        }
        catch(SQLException e){
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
	cerrarConexionBD();
        return id_pedido;
    }
    
	public PedidosBD obtenerPedido(int id_pedido){
        abrirConexionBD();
        PedidosBD pedido = new PedidosBD();
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM pedidos WHERE id_pedido = " + id_pedido;
            resultados = s.executeQuery(con);
            
            while(resultados.next()){
                pedido.setId_pedido(resultados.getInt("id_pedido"));
                pedido.setId_usuario(resultados.getInt("id_usuario"));
                pedido.setFecha(resultados.getDate("fecha"));
                pedido.setImporte(resultados.getFloat("importe"));
                pedido.setEstado(resultados.getString("estado"));
            }
        }
        catch(SQLException e){
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
        cerrarConexionBD();
        return pedido;
        
    }
	
    public List<DetalleBD> obtenerDetallePorPedido(int id_pedido){
        abrirConexionBD();
		ArrayList<DetalleBD> detalles = new ArrayList<>();
        DetalleBD detalle;
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT * FROM detalle WHERE id_pedido = " + id_pedido;
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                detalle = new DetalleBD();
                detalle.setId_pedido(resultados.getInt("id_pedido"));
                detalle.setId_producto(resultados.getInt("id_producto"));
                detalle.setId_productor(resultados.getInt("id_productor"));
                detalle.setUnidades(resultados.getInt("unidades"));
                detalle.setPrecio_unitario(resultados.getFloat("precio_unitario"));
                detalles.add(detalle);
            }
        }
        catch(SQLException e) {
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
	cerrarConexionBD();
        return detalles;
    }
            
    public List<ProductoresBD> obtenerProductoresPorCategoria(int id_categoria){
        abrirConexionBD();
        ArrayList<ProductoresBD> productores = new ArrayList<>();
        ProductoresBD productor;
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT * FROM productores WHERE id_categoria = " + id_categoria + " ORDER BY nombre";
            
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                productor = new ProductoresBD();
                productor.setSello_spg(resultados.getString("sello_spg"));
                productor.setNombre(resultados.getString("nombre"));
                productor.setTelefono(resultados.getInt("telefono"));
                productor.setEmail(resultados.getString("email"));
                productor.setUbicacion(resultados.getString("ubicacion"));
                productor.setWeb(resultados.getString("web"));
                productor.setReparto(resultados.getString("reparto"));
                productor.setDescripcion(resultados.getString("descripcion"));
                productor.setImgroute(resultados.getString("imgroute"));
                productor.setId_productor(resultados.getInt("id_productor"));
                productor.setId_categoria(resultados.getInt("id_categoria"));
                productor.setId_usuario(resultados.getInt("id_usuario"));
                productores.add(productor);
            }
        }
        catch(SQLException e) {
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
	cerrarConexionBD();
        return productores;
    }
    
    public boolean modificarUsuarioBD(Usuario usuario){
        abrirConexionBD();
        
        boolean ok = false;
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "UPDATE usuarios SET usuario = \"" + usuario.getUsuario() + "\", password = \"" + usuario.getPassword()
                    + "\", activo = " + usuario.isActivo() + ", email = \"" + usuario.getEmail() + "\", nombre = \"" + usuario.getNombre()
                    + "\", apellidos = \"" + usuario.getApellidos() + "\", domicilio = \"" + usuario.getDomicilio() + "\", poblacion = \""
                    + usuario.getPoblacion() + "\", provincia = \"" + usuario.getProvincia() + "\", cp = " + usuario.getCp() + ", telefono = " 
                    + usuario.getTelefono() + ", productor = " + usuario.isProductor()                 
                    + " WHERE id_usuario = " + usuario.getId_usuario() + ";"; 
            
            s.executeUpdate(con);
            ok = true;
        }catch(SQLException e){
            System.out.println("Error al ejecutar la consulta.");
        }
        
	cerrarConexionBD();
        return ok;
    }
    
    public boolean modificarProductorBD(ProductoresBD productor){
        abrirConexionBD();
        
        boolean ok = false;
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "UPDATE productores SET id_categoria = " + productor.getId_categoria() + ", sello_spg = \""
                    + productor.getSello_spg() + "\", nombre = \"" + productor.getNombre() + "\", telefono = \""
                    + productor.getTelefono() + "\", email = \"" + productor.getEmail() + "\", ubicacion = \""
                    + productor.getUbicacion() + "\", web = \"" + productor.getWeb() + "\", reparto = \""
                    + productor.getReparto() + "\", descripcion = \"" + productor.getDescripcion() + "\""
                    + " WHERE id_productor = " + productor.getId_productor() + ";"; 
            
            s.executeUpdate(con);
            ok = true;
        }catch(SQLException e){
            System.out.println("Error al ejecutar la consulta.");
        }
        
	cerrarConexionBD();
        return ok;
    }
    
    public boolean modificarProductoBD(ProductosBD producto){
        abrirConexionBD();
        boolean ok = false;
        
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "UPDATE productos SET nombre = \"" + producto.getNombre() + "\", stock = " + producto.getStock()
                    + ", unidad = \"" + producto.getUnidad() + "\", precio_unitario = " + producto.getPrecio_unitario() 
                    + ", ingredientes = \"" + producto.getIngredientes() + "\" WHERE id_producto = " + producto.getId_producto() + ";"; 
            
            s.executeUpdate(con);
            ok = true;
        }catch(SQLException e){
            System.out.println("Error al ejecutar la consulta.");
        }
        
	cerrarConexionBD();
        return ok;
    }
    
    public ProductosBD obtenerProducto(int id_producto){
        abrirConexionBD();
        
        ProductosBD producto = new ProductosBD();
        
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT * FROM productos WHERE id_producto = " + id_producto;
            
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                producto.setId_producto(resultados.getInt("id_producto"));
                producto.setId_categoria(resultados.getInt("id_categoria"));
                producto.setId_productor(resultados.getInt("id_productor"));
                producto.setNombre(resultados.getString("nombre"));
                producto.setStock(resultados.getInt("stock"));
                producto.setUnidad(resultados.getString("unidad"));
                producto.setPrecio_unitario(resultados.getFloat("precio_unitario"));
                producto.setIngredientes(resultados.getString("ingredientes"));
               
                
                Blob blob = resultados.getBlob("foto");
                InputStream inputStream = blob.getBinaryStream();
                producto.setFoto(inputStream);
                
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                
                while((bytesRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0, bytesRead);
                }
                
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                
                inputStream.close();
                outputStream.close();
                
                producto.setImgroute(base64Image);
                
            }
        }
        catch(SQLException | IOException ex) {
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
	cerrarConexionBD();
        return producto;
    }
    
    public List<ProductosBD> obtenerProductos(){
        abrirConexionBD();
        
        ArrayList<ProductosBD> productos = new ArrayList<>();
        
        ProductosBD producto;
        
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT * FROM productos ";
            
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                producto = new ProductosBD();
                producto.setId_producto(resultados.getInt("id_producto"));
                producto.setId_categoria(resultados.getInt("id_categoria"));
                producto.setId_productor(resultados.getInt("id_productor"));
                producto.setNombre(resultados.getString("nombre"));
                producto.setStock(resultados.getInt("stock"));
                producto.setUnidad(resultados.getString("unidad"));
                producto.setPrecio_unitario(resultados.getFloat("precio_unitario"));
                producto.setIngredientes(resultados.getString("ingredientes"));
                
                Blob blob = resultados.getBlob("foto");
                InputStream inputStream = blob.getBinaryStream();
                producto.setFoto(inputStream);
                
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                
                while((bytesRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0, bytesRead);
                }
                
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                
                inputStream.close();
                outputStream.close();
                
                producto.setImgroute(base64Image);
                
                productos.add(producto);
            }
        }
        catch(SQLException | IOException ex) {
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
		
	cerrarConexionBD();
        return productos;
    }
    
    public List<ProductosBD> obtenerProductosPorProductor(int id_productor) {
        abrirConexionBD();
        
        ArrayList<ProductosBD> productos = new ArrayList<>();
        
        ProductosBD producto;
        
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT * FROM productos WHERE id_productor = " + id_productor;
            
            resultados = s.executeQuery(con);
            
            while (resultados.next()){
                producto = new ProductosBD();
                producto.setId_producto(resultados.getInt("id_producto"));
                producto.setId_categoria(resultados.getInt("id_categoria"));
                producto.setId_productor(resultados.getInt("id_productor"));
                producto.setNombre(resultados.getString("nombre"));
                producto.setStock(resultados.getInt("stock"));
                producto.setUnidad(resultados.getString("unidad"));
                producto.setPrecio_unitario(resultados.getFloat("precio_unitario"));
                producto.setIngredientes(resultados.getString("ingredientes"));
                
                Blob blob = resultados.getBlob("foto");
                InputStream inputStream = blob.getBinaryStream();
                producto.setFoto(inputStream);
                
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                
                while((bytesRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0, bytesRead);
                }
                
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                
                inputStream.close();
                outputStream.close();
                
                producto.setImgroute(base64Image);
                
                productos.add(producto);
            }
        }
        catch(SQLException | IOException ex) {
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
		
	cerrarConexionBD();
        return productos;
    }
    
    public boolean eliminarProducto(int id_producto){
        abrirConexionBD();
        boolean ok = false;
        
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "DELETE FROM productos WHERE id_producto = " + id_producto; 
            
            s.executeUpdate(con);
            ok = true;
        }catch(SQLException e){
            System.out.println("Error al ejecutar la consulta.");
        }
        
	cerrarConexionBD();
        return ok;
    }
}