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

function muestraCantidadTotalCarrito(){
    document.getElementById('carrito').innerHTML = " (" + devuelveCantidadTotalCarrito() + ")";
}

function mostrarCarritoCollapse(){
    
    var html = "";
    var p;
    
    console.log(carrito);
    
    for(p in carrito){
        
        html += " " + carrito[p].nombre + " ";
        html += " " + carrito[p].cantidad + " ";
        html += " " + carrito[p].precio_unitario + " €/" + carrito[p].unidad + " ";
        html += " Total: " + carrito[p].cantidad * carrito[p].precio_unitario + " €";
        html += "</div>";
    }
    
    document.getElementById('carritoCollapse').innerHTML = html;
}

function anadirProductoCarrito(id_producto, nombre, precio_unitario, unidad){

    var p, encontrado = 0;
    for(p in carrito){
        if(carrito[p].id_producto === id_producto) {
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
    mostrarCarritoCollapse();
}


