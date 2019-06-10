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
public class ControladorInicio{
    private Vista vista;

    public ControladorInicio(Vista vista) {
        this.vista  = vista;
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {      
        if(e.getSource() == vista.frmInicio.btnModCliente){
            vista.cambiarPanel(vista.frmInicio, vista.frmCliente);
        }
        
        if(e.getSource() == vista.frmInicio.btnBuscarEvento){
            vista.cambiarPanel(vista.frmInicio, vista.frmModEvento);
        }
        
        if(e.getSource() == vista.frmInicio.btnCrearEvento){            
            vista.cambiarPanel(vista.frmInicio, vista.frmEvento);
        }
        
        if(e.getSource() == vista.frmInicio.btnUpdArt){
            vista.cambiarPanel(vista.frmInicio, vista.frmArticulo);
        }
    }
    
}
