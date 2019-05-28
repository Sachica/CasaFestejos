/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import modelo.Responsable;

import vista.*;
import servicio.Conexion;
import modeloDAO.*;
/**
 *
 * @author kuroy
 */
public class Controlador implements ActionListener {
    private Vista vista;
    private ControladorRegistro controladorRegistro;
    private ControladorInicio controladorInicio;
    private ControladorCliente controladorCliente;
    
    public Controlador(){
        vista = new Vista();
        vista.initListeners(this);
        controladorRegistro = new ControladorRegistro(vista);
        controladorInicio = new ControladorInicio(vista);
        controladorCliente = new ControladorCliente(vista);
        vista.cambiarPanel(vista.frmRegistro, vista.frmInicio);
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        controladorInicio.actionPerformed(e);
        controladorRegistro.actionPerformed(e);
        controladorCliente.actionPerformed(e);
    }

    
    
    public static void main(String[] args){
        Controlador m = new Controlador();       
    }
}
