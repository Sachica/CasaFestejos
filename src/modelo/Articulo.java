/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.ImageIcon;

/**
 *
 * @author kuroy
 */
public class Articulo {
    private Integer id;
    private String nombre;
    private Integer cantidad;
    private Integer costo;
    private ImageIcon imagen;
    private TipoArticulo tipo;

    public Articulo() {
    }
    
    public Articulo(Integer id, String nombre, Integer cantidad, Integer costo, TipoArticulo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costo = costo;
        this.tipo = tipo;
    }

    public Articulo(Integer id, String nombre, Integer cantidad, Integer costo, ImageIcon imagen, TipoArticulo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costo = costo;
        this.imagen = imagen;
        this.tipo = tipo;
    }

    public void aumentarCantidad(Integer cantidad){
        this.cantidad += cantidad;
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

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public TipoArticulo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArticulo tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj){
        return this.getNombre().equals(((Articulo)obj).getNombre());
    }
    
    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", costo=" + costo + ", tipo=" + tipo + '}';
    }
    
}
