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
import java.sql.*;
import java.util.ArrayList;
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
        
        return ok;
    }
    
    public boolean registroProductoBD(ProductosBD producto){
        abrirConexionBD();
        boolean ok = false;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "INSERT INTO productos (id_categoria, id_productor, nombre, stock, unidad, precio_unitario, ingredientes)"
                    + " VALUES( " + producto.getId_categoria() + ", " + producto.getId_productor() + ", \"" + producto.getNombre() + "\", "
                    + producto.getStock() + ", \"" + producto.getUnidad() + "\", " + producto.getPrecio_unitario() + ", \"" 
                    + producto.getIngredientes() + "\")";
            
            s.executeUpdate(con);
            ok = true;

        }catch(SQLException e){
            System.out.println("Error al insertar en la BBDD. ");
        }
        
        return ok;
    }
    
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
        
        
        return id_categoria;
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
        
        return ok;
        
    }
            
    public List<ProductoresBD> obtenerProductoresPorCategoria(int id_categoria){
        
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
            }
        }
        catch(SQLException e) {
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
        return producto;
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
                productos.add(producto);
            }
        }
        catch(SQLException e) {
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
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
        
        return ok;
    }
    
    
    /*
    public List<UsuarioBD> obtenerUsuariosGestionBD(){
        abrirConexionBD();
        
        ArrayList<UsuarioBD> usuarios = new ArrayList<>();
        UsuarioBD usuario;
        
        ResultSet resultados = null;
        
        try{
            String con;
            Statement s = conexionBD.createStatement();
            
            con = "SELECT usuario, password FROM usuarios";
            resultados = s.executeQuery(con);
            
            while(resultados.next()){
                usuario = new UsuarioBD();
                usuario.setPassword(resultados.getString("password"));
                usuario.setUsuario(resultados.getString("usuario"));
                usuarios.add(usuario);
            }
        }
        catch(Exception e){
            System.out.println("Error ejecutando la consulta a la BBDD.");
        }
        
        return usuarios;
    }
    */
    /*
    boolean comprobarUsuarioAdminBD(String usuario, String clave) {
        abrirConexionBD();
        
        ResultSet resultados = null;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM administracion WHERE usuario='" + usuario + "' and password='" + clave + "'";
            resultados = s.executeQuery(con);
            if(resultados.next()){
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception e){
            System.out.println("No se ha completado la petición");
            return false;
        }
    }
    */
    
    /*
    int obtenerStockProductoBD(int id) throws SQLException {
        abrirConexionBD();
        int stock = 0;
        
        ResultSet resultados = null;
        try{
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT stock FROM productos WHERE id_producto=" + id + ";";
            resultados = s.executeQuery(con);
        }
        catch (Exception e){
            System.out.println("Error al completar la consulta");
        }
        
        if(resultados != null){
            resultados.next();
            stock = resultados.getInt("stock");
        }
        
        return stock;
    }
    */
    /*
    public boolean registrarPedidoBD(int idUsuario, String fecha, float importe_total, String direccion_envio, 
            String poblacion, int cp, String tipo_pago, String tarjeta, String estado) {
        boolean ok = false;
        abrirConexionBD();
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "INSERT INTO pedidos(usuario, fecha, importe, direccion_envio, poblacion, codigo_postal, tipo_pago, numero_tarjeta, estado)"
                    + " VALUES (" + idUsuario + ",STR_TO_DATE('" + fecha + "', '%d/%m/%Y')," + importe_total + ", \"" + direccion_envio + "\", \"" 
                    + poblacion + "\", " + cp + ", \"" + tipo_pago + "\" ,\"" + tarjeta + "\", \"" + estado + "\");";
            s.executeUpdate(con);
            ok = true;
        }
        catch(SQLException e){
            System.out.println("Error al insertar en la BBDD");
        }
        
        return ok;
    }
    */
    /*
    public boolean registrarDetallePedidoBD(int id_pedido, int id_producto, int unidades, float precio_unitario){
        boolean ok = false;
        abrirConexionBD();
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "INSERT INTO detalles_pedido(id_pedido, id_producto, unidades, precio_unitario)"
                    + "VALUES (" + id_pedido + ", " + id_producto + ", " + unidades + ", " + precio_unitario + ");";
            s.executeUpdate(con);
            ok = true;
        }
        catch(SQLException e){
            System.out.println("Error al insertar en la BBDD");
        }
        
        return ok;
        
        
    }
    */
    /*
    public void actualizarStockProductoBD(int id, int stock) {
        abrirConexionBD();
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "UPDATE productos SET stock = " + stock + " WHERE id_producto=" + id + ";";
            s.executeUpdate(con);
        }
        catch(Exception e){
            System.out.println("Error al actualizar la BBDD");
        }        
    }
    */
    /*
    public ResultSet obtenerPedidosUsuarioBD(int id){
        abrirConexionBD();

        ResultSet resultados = null;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT id_pedido, fecha, importe, estado FROM pedidos WHERE usuario=" + id + ";";
            resultados = s.executeQuery(con);
        }
        catch(Exception e){
            System.out.println("Error al completar la consulta");
        }
                
        return resultados;
    }
    */
    
    /* Esta function no se utiliza... */
    /*
    public ResultSet obtenerDetallesPedidoBD(int id_pedido){
        abrirConexionBD();

        ResultSet resultados = null;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM detalles_pedido WHERE id_pedido=" + id_pedido + ";";
            resultados = s.executeQuery(con);
        }
        catch(Exception e){
            System.out.println("Error al completar la consulta");
        }
                
        return resultados;
        
    }
    */
    /*
    public boolean eliminarPedidoUsuarioBD(String estado, int id_pedido){
        abrirConexionBD();
        boolean ok =false;
        ResultSet resultados = null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "UPDATE pedidos SET estado=\"" + estado + "\" WHERE id_pedido=" + id_pedido + ";";
            s.executeUpdate(con);
            if(estado.equals("Cancelado")){
                con = "SELECT id_producto, unidades FROM detalles_pedido dp INNER JOIN pedidos p ON dp.id_pedido = p.id_pedido WHERE p.id_pedido=" + id_pedido + ";";
                resultados = s.executeQuery(con);
                while(resultados.next()){
                    list.add(resultados.getInt("id_producto"));
                    list.add(resultados.getInt("unidades"));
                }
                
                for(int i = 0; i < list.size(); i = i + 2 ){
                    con = "UPDATE productos SET stock = stock + " + list.get(i + 1)
                            + " WHERE id_producto= " + list.get(i) + ";";
                    s.executeUpdate(con);
                }
            }
            ok = true;

        }
        catch(Exception e){
            System.out.println("Error al actualizar la BBDD");
        }
        
        return ok;
    }
    */
    /*
    public ResultSet obtenerUltimoPedidoBD() {
        abrirConexionBD();

        ResultSet resultados = null;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM pedidos ORDER BY id_pedido desc LIMIT 1;";
            resultados = s.executeQuery(con);
        }
        catch(Exception e){
            System.out.println("Error al completar la consulta");
        }
                
        return resultados;
    }
    */
    /*
    int obtenerIdUltimoPedidoBD() throws SQLException {
        abrirConexionBD();

        int id_pedido = 0;
        ResultSet resultados = null;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT id_pedido FROM pedidos ORDER BY id_pedido desc LIMIT 1;";
            resultados = s.executeQuery(con);
        }
        catch(Exception e){
            System.out.println("Error al completar la consulta");
        }
        
        if(resultados != null){
            resultados.next();
            id_pedido = resultados.getInt("id_pedido");
        }
                
        return id_pedido;
    }
    */
}


