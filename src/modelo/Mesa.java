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
public class Mesa extends Mobiliario{
    public Mesa(){
        super();
    }
    
    public Mesa(Integer id, String tipo, Integer cantidad, Integer costo){
        super(id, tipo, cantidad, costo);
    }
}
