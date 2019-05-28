/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import modelo.Articulo;
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
                vista.frmEvento.lblPrecioMontaje.setText("$ " + precio);
            }else{
                vista.frmEvento.lblPrecioMontaje.setText("$");
            }  
        }
        
        if (e.getSource() == vista.frmEvento.cmbMesa) {
            Integer precio = articuloPrecio(vista.frmEvento.cmbMesa.getSelectedItem().toString());
            if(precio!=-1){
                vista.frmEvento.lblPrecioMesa.setText("$ " + precio);
            } 
            else{
                vista.frmEvento.lblPrecioMesa.setText("$");
            }
        }
        
        if (e.getSource() == vista.frmEvento.cmbSilla) {
            Integer precio = articuloPrecio(vista.frmEvento.cmbSilla.getSelectedItem().toString());
            if(precio!=-1){
                vista.frmEvento.lblPrecioSilla.setText("$ " + precio);
            }else{
                vista.frmEvento.lblPrecioSilla.setText("$");
            }       
        }
        
        if (e.getSource() == vista.frmEvento.cmbBebidas) {
            Integer precio = articuloPrecio(vista.frmEvento.cmbBebidas.getSelectedItem().toString());
            if(precio!=-1){
                vista.frmEvento.lblPrecioBebida.setText("$ " + precio);
            }else{
                vista.frmEvento.lblPrecioBebida.setText("$");
            }
        }
        
        if (e.getSource() == vista.frmEvento.cmbComidas) {
            Integer precio = articuloPrecio(vista.frmEvento.cmbComidas.getSelectedItem().toString());
            if(precio!=-1){
                vista.frmEvento.lblPrecioComida.setText("$ " + precio);
            }else{
                vista.frmEvento.lblPrecioComida.setText("$");
            }  
        }
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
