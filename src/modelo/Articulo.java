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
public class Articulo {
    private Integer id;
    private TipoArticulo tipo;
    private String nombre;
    private Integer precio;

    public Articulo() {
    }

    public Articulo(Integer id, TipoArticulo tipo, String nombre, Integer precio) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoArticulo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArticulo tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", precio=" + precio + '}';
    }
    
}
