/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import util.MyException;
import modelo.*;
/**
 *
 * @author kuroy
 */
public class Controlador {
    public static void main(String[] args) {
        try {
            System.out.println("Test Consola");
            Evento e = new Evento();
            e.agregarActividad(new Actividad("Karaoke", new Hora(1, 1, 1), 2000));
            e.agregarActividad(new Actividad("Karaoke", new Hora(1, 1, 1), 2000));
        } catch (MyException e) {
        }
    }
}
