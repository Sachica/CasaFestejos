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
public class Bebida extends Comida{
    public Bebida() {
    }

    public Bebida(Integer id, String nombre, Integer costo, Integer cantidad) {
        super(id, nombre, costo, cantidad);
    }
}
