/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import util.MyException;
import modelo.*;
import servicio.*;
import vista.*;
/**
 *
 * @author kuroy
 */
public class Controlador implements ActionListener {
    
    Responsable persona;
    Conexion conexion;
    PersonaServicio servicio;
    Principal principal;

    public Controlador() throws SQLException, ClassNotFoundException {
        
        persona = new Responsable();
        conexion = new Conexion();
        servicio = new PersonaServicio();
        principal = new Principal();
        principal.InicializarListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==principal.btnGuardar){
            
            /*String identificacion = principal.txtDocumento.getText();
            String id = identificacion.substring(identificacion.length()-3);
            persona = new Responsable(principal.txtNombre.getText(), principal.txtApellido.getText(), principal.txtDocumento.getText(), Integer.parseInt(principal.txtTelefono.getText()), Integer.parseInt(principal.txtEdad.getText()));
            */
            try{
                servicio.Guardar(conexion.getCnx(), persona);
                
            }catch(SQLException ex){
                System.out.print(ex.getMessage());
                
            }
        }
        
        if(e.getSource()==principal.btnCancelar){
            
            principal.txtNombre.setText("");
            principal.txtApellido.setText("");
            principal.txtDocumento.setText("");
            principal.txtTelefono.setText("");
            principal.txtEdad.setText("");
        }
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Controlador c = new Controlador();
        c.principal.setVisible(true);
        try {
            System.out.println("");
            Evento e = new Evento();
            e.agregarActividad(new Actividad("Karaoke", new Hora(1, 1, 1), 2000));
            e.agregarActividad(new Actividad("Karaoke", new Hora(1, 1, 1), 2000));
        } catch (MyException e) {
        }
    }
}
