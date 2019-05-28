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
public class ControladorRegistro implements java.awt.event.ActionListener{
    private final Vista vista;
    
    public ControladorRegistro(Vista vista){
        this.vista = vista;
    }
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e){
        if(e.getSource() == vista.frmRegistro.btnRegistrar){
            try {
                ResponsableDAO.guardar(this.getResponsable(), Conexion.getConnection());
                vista.frmRegistro.clean();
            } catch (SQLException er) {
                System.out.println(er.getMessage());
            }
        }
        
        if(e.getSource() == vista.frmRegistro.txtCed){        
            try {
                Integer cedula = Integer.parseInt(vista.frmRegistro.txtCed.getText());
                Responsable responsable = new Responsable();
                responsable.setCedula(cedula);
                responsable = ResponsableDAO.buscar(responsable, Conexion.getConnection());
                this.cargarResponsable(responsable);
            } catch (NumberFormatException err) {
                System.out.println(err.getMessage());
            } catch (SQLException err){
                System.out.println(err.getMessage());
            } catch(NullPointerException err){
                System.out.println("No se encontro en la base de datos");
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
    
    private void cargarResponsable(Responsable responsable){
        vista.frmRegistro.txtNom.setText(responsable.getNombre());
        vista.frmRegistro.txtApe.setText(responsable.getApellidos());
        vista.frmRegistro.txtEmail.setText(responsable.getE_mail());
        vista.frmRegistro.txtTel.setText(responsable.getTelefono());
    }
}
