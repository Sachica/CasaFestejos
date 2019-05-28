/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.Vista;

/**
 *
 * @author kuroy
 */
public class ControladorInicio implements java.awt.event.ActionListener{
    private Vista vista;

    public ControladorInicio(Vista vista) {
        this.vista  = vista;
    }
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == vista.frmInicio.btnRegis){
            vista.cambiarPanel(vista.frmInicio, vista.frmRegistro);
        }
        
        if(e.getSource() == vista.frmInicio.btnCrearEvento){
            vista.cambiarPanel(vista.frmInicio, vista.frmEvento);
        }
    }
    
}
