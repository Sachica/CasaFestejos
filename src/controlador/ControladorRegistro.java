/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import modelo.Responsable;
import modeloDAO.ResponsableDAO;
import servicio.Conexion;
import vista.Vista;

/**
 *
 * @author kuroy
 */
public class ControladorRegistro{
    private final Vista vista;
    
    public ControladorRegistro(Vista vista){
        this.vista = vista;
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e){
        if(e.getSource() == vista.frmRegistro.btnRegistrar){
            try {
                ResponsableDAO.guardar(this.getResponsable(), Conexion.getConnection());
                vista.frmRegistro.clean();
            } catch (SQLException er) {
                System.out.println(er.getMessage());
            }
        }
        
        if(e.getSource() == vista.frmRegistro.btnCont){
            vista.cambiarPanel(vista.frmRegistro, vista.frmEvento);
        }
        
        if(e.getSource() == vista.frmRegistro.btnVolver){
            vista.cambiarPanel(vista.frmRegistro, vista.frmInicio);
        }
    }
    
    private Responsable getResponsable(){
        String nombre = vista.frmRegistro.txtNom.getText();
        String apellido = vista.frmRegistro.txtApe.getText();
        Integer cedula = Integer.parseInt(vista.frmRegistro.txtCed.getText());
        String telefono = vista.frmRegistro.txtTel.getText();
        String email = vista.frmRegistro.txtEmail.getText();
        
        return new Responsable(nombre, apellido, cedula, telefono, email);
    }
}
