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
public class Platillo extends Comida {
    public Platillo() {
    }

    public Platillo(Integer id, String nombre, Integer costo, Integer cantidad) {
        super(id, nombre, costo, cantidad);
    }
}
