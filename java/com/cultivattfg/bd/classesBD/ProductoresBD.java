/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cultivattfg.bd.classesBD;

/**
 *
 * @author mireia
 */
public class ProductoresBD {
    private int id_productor;
    private int id_usuario;
    private int id_categoria;
    private String sello_spg;
    private String nombre;
    private int telefono;
    private String email;
    private String ubicacion;
    private String web;
    private String reparto;
    private String descripcion;
    private String imgroute;

    public int getId_productor() {
        return id_productor;
    }

    public void setId_productor(int id_productor) {
        this.id_productor = id_productor;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getSello_spg() {
        return sello_spg;
    }

    public void setSello_spg(String sello_spg) {
        this.sello_spg = sello_spg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImgroute() {
        return imgroute;
    }

    public void setImgroute(String imgroute) {
        this.imgroute = imgroute;
    }
    
}
