/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import modelo.Articulo;
import modelo.Responsable;
import modeloDAO.ActividadDAO;
import modeloDAO.ResponsableDAO;
import servicio.Conexion;
import util.MyException;
import vista.Vista;

/**
 *
 * @author kuroy
 */ 
public class ControladorEvento {
    
    private Vista vista;
    
    public ControladorEvento(Vista vista) {
        this.vista = vista;
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == vista.frmEvento.cmbMontaje) {
            Integer precio = articuloPrecio(vista.frmEvento.cmbMontaje.getSelectedItem().toString());
            if(precio!=-1){
                vista.frmEvento.jLabel.setText("$ " + precio);
            }else{
                vista.frmEvento.jLabel.setText("$");
            }  
        }
        
        if(e.getSource() == vista.frmEvento.btnAddArticulo){
            vista.cambiarPanel(vista.frmEvento, vista.frmAddArticulo);
        }
        
        if(e.getSource() == vista.frmEvento.btnBus){
            try{
                Responsable r = new Responsable();
                r.setCedula(Integer.parseInt(vista.frmEvento.txtDoc.getText()));
                r = ResponsableDAO.buscar(r, Conexion.getConnection());
                if(r!=null){
                    vista.frmEvento.habilitar(Boolean.TRUE);
                }else{
                    //this.mostrarMensaje();
                }
            }catch(NumberFormatException err){
                //this.mostrarMensaje();
            }catch(SQLException err){
                //this.mostrarMensaje();
            }
        }
        
        if(e.getSource() == vista.frmEvento.btnAddActividad){
            try{
                ActividadDAO.guardar(this.getActividad(), Conexion.getConnection());
            }catch(NumberFormatException er){
                //this.mostrarMensaje();
            }catch(SQLException er){
                //this.mostrarMensaje();
            }catch(MyException er){
                //this.mostrarMensaje();
            }catch(NullPointerException er){
                //this.mostrarMensaje();
            }
        }
        
        if(e.getSource() == vista.frmEvento.btnCalcular){
            vista.frmEvento.calcularPrecio();
        }
        
        if(e.getSource() == vista.frmEvento.btnConfirmar){
            //Guardar
        }
        
        if(e.getSource() == vista.frmEvento.btnVolver){
            vista.cambiarPanel(vista.frmEvento, vista.frmInicio);
        }
    }
    
    private modelo.Actividad getActividad() throws MyException{
        Integer id = Integer.parseInt(vista.frmEvento.txtDoc.getText());
        String nombre = vista.frmEvento.txtNomAct.getText();
        Integer hora = Integer.parseInt(vista.frmEvento.txtHoraAct.getText());
        Integer minuto = Integer.parseInt(vista.frmEvento.txtMinAct.getText());
        String descripcion = vista.frmEvento.txtDescrip.getText();
        
        return new modelo.Actividad(id, nombre, descripcion, new modelo.Hora(hora, minuto, 0));
    }
    
    private Integer articuloPrecio(String nombre) {
        try {
            Articulo articulo = new Articulo();
            articulo.setNombre(nombre);
            articulo = modeloDAO.ArticuloDAO.buscarPorNombre(articulo, servicio.Conexion.getConnection());            
            return articulo!=null ? articulo.getPrecio() : -1;
        } catch (SQLException e) {
        }
        return -1;
    }
}
