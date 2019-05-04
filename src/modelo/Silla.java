/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author kuroy
 */
public class Silla{
    private String tipo;
    private Integer cantidad;
    private Integer costo;
    public Silla(){
    }

    public Silla(String tipo, Integer cantidad, Integer costo) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCosto() {
        return costo*cantidad;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
    
}
