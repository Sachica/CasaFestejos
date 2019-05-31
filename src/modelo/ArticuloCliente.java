/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import util.TipoArticulo;

/**
 *
 * @author kuroy
 */
public class ArticuloCliente {
    private Integer id;
    private String nombre;
    private TipoArticulo tipoArticulo;
    private Integer cantidad;
    private Integer costo;

    public ArticuloCliente() {
    }

    public ArticuloCliente(Integer id, TipoArticulo tipoArticulo, String nombre, Integer cantidad, Integer costo) {
        this.id = id;
        this.nombre = nombre;
        this.tipoArticulo = tipoArticulo;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoArticulo getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticulo tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
    
}
