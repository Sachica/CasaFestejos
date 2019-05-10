/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
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
    //Conexion conexion;
    ResponsableServicio servicio;
    RegistroPersona registro;
    Principal principal;
    Administrador administrador;
    CajaAdministrador caja;
    Comidas_Bebidas comida_bebida;
    Confirmar confirmar;
    ControlAdministrador control;
    Reservas reserva;
    Sillas_Mesas silla_mesa;
    TipoDeMontaje montaje;

    public Controlador() throws SQLException, ClassNotFoundException {
        
        persona = new Responsable();
        //conexion = new Conexion();
        servicio = new ResponsableServicio();
        
        registro = new RegistroPersona();
        registro.InicializarListener(this);
        principal = new Principal();
        principal.InicializarListener6(this);
        administrador = new Administrador();
        administrador.InicializarListener7(this);
        caja = new CajaAdministrador();
        caja.InicializarListener10(this);
        comida_bebida = new Comidas_Bebidas();
        comida_bebida.InicializarListener4(this);
        confirmar = new Confirmar();
        confirmar.InicializarListener5(this);
        control = new ControlAdministrador();
        control.InicializarListener9(this);
        reserva = new Reservas();
        reserva.InicializarListener8(this);
        silla_mesa = new Sillas_Mesas();
        silla_mesa.InicializarListener3(this);
        montaje = new TipoDeMontaje();
        montaje.InicializarListener2(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==registro.btnGuardar){
            
            /*String identificacion = registro.txtDocumento.getText();
            String cedula = identificacion.substring(identificacion.length()-3);*/
            persona = new Responsable(registro.txtNombre.getText(), registro.txtApellido.getText(),registro.txtDocumento.getText(), registro.txtTelefono.getText(), registro.txtEmail.getText());
            
           /* try{
                servicio.Guardar(conexion.getCnx(), persona);
                
            }catch(SQLException ex){
                System.out.print(ex.getMessage());
                
            }
        }*/
        
        if(e.getSource()==registro.btnLimpiar){
            
            registro.Limpiar();
        }
        
        if(e.getSource()==registro.btnIngresar){
            
            registro.setVisible(false);
            montaje.setVisible(true);
        }
        
        if(e.getSource()==montaje.btnContinuar){
            
            montaje.setVisible(false);
            silla_mesa.setVisible(true);
        }
        
        if(e.getSource()==silla_mesa.btnContinuar){
            
            silla_mesa.setVisible(false);
            comida_bebida.setVisible(true);
        }
        
        if(e.getSource()==comida_bebida.btnContinuar){
            
            comida_bebida.setVisible(false);
            confirmar.setVisible(true);
        }
        
        if(e.getSource()==confirmar.btnConfirmar){
            
            JOptionPane.showMessageDialog(null, "Operación Relizada Correctamente");
        }
    }
    
    /*public static void main(String[] args) throws SQLException, ClassNotFoundException{
        Controlador c = new Controlador();
        c.registro.setVisible(true);
        try {
            System.out.println("");
            Evento e = new Evento();
            e.agregarActividad(new Actividad("Karaoke", new Hora(1, 1, 1), 2000));
            e.agregarActividad(new Actividad("Karaoke", new Hora(1, 1, 1), 2000));
        } catch (MyException e) {
        }
    }*/
   }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Controlador m = new Controlador();
        m.registro.setVisible(true);
    
    }
}
