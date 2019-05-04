/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import modelo.*;
import util.MyException;
/**
 *
 * @author kuroy
 */
public class Cardinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("LOL");
//        try {
//            Evento e = new Evento();
//            e.agregarActividad(new Actividad("Karaoke", new Hora(0, 0, 0), 29000));
//            e.agregarActividad(new Actividad("Karaoke", new Hora(0, 0, 0), 29000));
//        } catch (MyException ex) {
//            System.out.println(ex.getMessage());
//        }
        Hora h1 = new Hora(0, 0, 0);
        Hora h2 = new Hora(0, 0, 0);
        System.out.println("Afirmacion : "+h1.equals(h2));
    }
    
}
