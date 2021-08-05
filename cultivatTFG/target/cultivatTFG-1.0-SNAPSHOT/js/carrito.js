/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var carrito = null;
var canti = 0;  

function borrarCarrito(){
    localStorage.removeItem("carrito");
}

function actualizarCarrito(){
    localStorage.clear();
    localStorage.setItem("carrito", JSON.stringify(carrito));
}

function cargaInicialCarrito(){
    carrito = JSON.parse(localStorage.getItem("carrito"));
    canti = 0;
    
    if(carrito === null){
        carrito = new Object();
    }    
    else {
        var p;
        for(p in carrito){
            canti += carrito[p].cantidad;
        }
    }
}

function devuelveCantidadTotalCarrito(){
    var total = 0, p;
    for(p in carrito){
        total += carrito[p].cantidad;
    }
    return total;
}

function devuelvePrecioTotalCarrito(){
    var total = 0, p;
    for(p in carrito){
        total += carrito[p].precio_unitario * carrito[p].cantidad;
    }
    return total;
}

function muestraCantidadTotalCarrito(){
    document.getElementById('carrito').innerHTML = "(" + devuelveCantidadTotalCarrito() + ")";
}
function muestraPrecioTotalCarrito(){
    document.getElementById('totalPrecioCarrito').innerHTML = devuelvePrecioTotalCarrito() + "€";
}
function muestraTotalCarrito(){
    document.getElementById('totalCarrito').innerHTML = devuelvePrecioTotalCarrito() + "€";
}

function addCantidadCarritoByIdProducto(id_producto, unidad){
    var p;
    
    for(p in carrito){
        if(p === id_producto.toString()) {
            carrito[p].cantidad++;
            canti++; 
            document.getElementById(id_producto.toString()).innerHTML = carrito[p].cantidad + " " + unidad.toString();
        }
    }
    actualizarCarrito();
    muestraCantidadTotalCarrito();
    muestraPrecioTotalCarrito();
    muestraTotalCarrito();
}

function quitCantidadCarritoByIdProducto(row, id_producto, unidad){
    var p, i;
    
    for(p in carrito){
        if(p === id_producto.toString()) {
            carrito[p].cantidad--;
            canti--;
            document.getElementById(id_producto.toString()).innerHTML = carrito[p].cantidad + " " + unidad.toString();
            
            if(parseInt(carrito[p].cantidad) === 0){
                i = row.parentNode.parentNode.rowIndex;
                document.getElementById("carritoModal").deleteRow(i);
                delete carrito[p];
            }
        }
    }
    actualizarCarrito();
    muestraPrecioTotalCarrito();
    muestraCantidadTotalCarrito();
    muestraTotalCarrito();
}

function deleteProductoCarritoByIdProducto(row, id_producto){
    var p, i;
    
    for(p in carrito){
        if(p === id_producto.toString()) {
            canti -= carrito[p].cantidad;
            i = row.parentNode.parentNode.rowIndex;
            document.getElementById("carritoModal").deleteRow(i);
            delete carrito[p];
        }
    }
    actualizarCarrito();
    muestraPrecioTotalCarrito();
    muestraCantidadTotalCarrito();
    muestraTotalCarrito();
}

function mostrarContenidoCarrito(){
    
    var html = "";
    var p;
    
    for(p in carrito){
        html += "<tr id='Fila" + carrito[p].id_producto + "'>";
        html += "<td>" + carrito[p].nombre + "</td>";
        html += "<td class='text-center' id='" + carrito[p].id_producto + "'>" + carrito[p].cantidad + " " + carrito[p].unidad + "</td>";
        html += "<td class='text-center'>" + carrito[p].precio_unitario + " €/" + carrito[p].unidad + "</td>";
        html += "<td><i class='fa fa-plus-circle' role='button' onclick='addCantidadCarritoByIdProducto(" + carrito[p].id_producto + ", \"" + carrito[p].unidad + "\")'></i> <i class='fa fa-minus-circle ms-2' role='button' onclick='quitCantidadCarritoByIdProducto(this," + carrito[p].id_producto + ", \"" + carrito[p].unidad + "\")'></i></td>";
        html += "<td class='text-center'><i class='fa fa-trash' role='button' onclick='deleteProductoCarritoByIdProducto(this," + carrito[p].id_producto + ")'></i></td>";
        html += "</tr>";
    }
    
    return html;
}

function crearCarritoModal(){
    var html = "";
        
    html += "<tbody>";
    
    if(canti !== 0){
        html += mostrarContenidoCarrito();
        document.getElementById("modal-footer").hidden = false;
    }
    else {
        html += "<tr>";
        html += "<td class='text-center'><img src='./img/logos/cistellabuida.png' class='img-fluid' alt='Cistella buida'></td>";
        html += "</tr>"; 
        document.getElementById("modal-footer").hidden = true;
    }
    html += "</tbody>";
    
    document.getElementById("carritoModal").innerHTML = html;
    muestraPrecioTotalCarrito();
}

function anadirProductoCarrito(id_producto, nombre, precio_unitario, unidad){

    var p, encontrado = 0;
    for(p in carrito){
        if(p === id_producto) {
            encontrado = 1;
            carrito[p].cantidad++;
        }
    }

    if(encontrado === 0){
        var product = new Object();
        product.id_producto = id_producto;   
        product.cantidad = 1;
        product.nombre = nombre;
        product.precio_unitario = precio_unitario;
        product.unidad = unidad;
       
        carrito[id_producto] = product;
    }
    canti++;
    actualizarCarrito();
    
    document.getElementById('carrito').innerHTML = " (" + canti + ")";
    muestraPrecioTotalCarrito();
    muestraTotalCarrito();
    document.getElementById("buttonTramitar").addEventListener("onclick", "ProcesarCarrito(carrito,'ProcesarPedido','cuerpo')");
}


