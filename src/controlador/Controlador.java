/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.*;
/**
 *
 * @author kuroy
 */
public class Controlador implements ActionListener {
    public Vista vista;
    public Controlador(){
        vista = new Vista();
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args){
        Controlador m = new Controlador();
        m.vista.cambiarPanel(m.vista.frmRegistro, m.vista.frmEvento);
    }
}
