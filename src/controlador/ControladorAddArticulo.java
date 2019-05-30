/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.sql.SQLException;
import modelo.*;
import modeloDAO.ComidaDAO;
import servicio.Conexion;
import vista.Vista;
/**
 *
 * @author kuroy
 */
public class ControladorAddArticulo {
    private Vista vista;
    private java.util.ArrayList<Object> bebidas;
    private java.util.ArrayList<Object> platillos;
    private java.util.ArrayList<Object> sillas;
    private java.util.ArrayList<Object> mesas;
    
    public ControladorAddArticulo(Vista vista) {
        this.vista = vista;
        this.bebidas = new java.util.ArrayList<>();
        this.platillos = new java.util.ArrayList<>();
        this.sillas = new java.util.ArrayList<>();
        this.mesas = new java.util.ArrayList<>();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == vista.frmAddArticulo.btnFin){
            try{
                vista.frmEvento.precioArticulos(new Object[]{bebidas, platillos, sillas, mesas});
                ComidaDAO.addAll(bebidas, Conexion.getConnection());
                ComidaDAO.addAll(platillos, Conexion.getConnection());
                ComidaDAO.addAll(sillas, Conexion.getConnection());
                ComidaDAO.addAll(mesas, Conexion.getConnection());
            }catch(SQLException er){
            }           
            this.resetear();
            vista.frmAddArticulo.resetear();
            vista.cambiarPanel(vista.frmAddArticulo, vista.frmEvento);
        }
        
        if(e.getSource() == vista.frmAddArticulo.btnAddSilla){
            try{
                if(this.puedeAñadir(vista.frmAddArticulo.txtCantSilla, vista.frmAddArticulo.cmbSilla)){    
                    String nombre = vista.frmAddArticulo.cmbSilla.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantSilla.getText());
                    this.sillas.add(new Silla(0, nombre, cant, this.articuloPrecio(nombre)));
                }else{
                    //mostrarMensaje
                }
            }catch(NumberFormatException err){
                //mostrarMensaje
            }
        }
        
        if(e.getSource() == vista.frmAddArticulo.btnAddMesa){
            try{
                if(this.puedeAñadir(vista.frmAddArticulo.txtCantMesa, vista.frmAddArticulo.cmbMesa)){    
                    String nombre = vista.frmAddArticulo.cmbMesa.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantMesa.getText());
                    this.mesas.add(new Mesa(0, nombre, cant, this.articuloPrecio(nombre)));
                }else{
                    //mostrarMensaje
                }
            }catch(NumberFormatException err){
                //mostrarMensaje
            }
        }
        
        if(e.getSource() == vista.frmAddArticulo.btnAddBebida){
            try{
                if(this.puedeAñadir(vista.frmAddArticulo.txtCantBebida, vista.frmAddArticulo.cmbBebidas)){ 
                    String nombre = vista.frmAddArticulo.cmbBebidas.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantBebida.getText());
                    this.bebidas.add(new Bebida(0, nombre, cant, this.articuloPrecio(nombre)));
                }else{
                    //mostrarMensaje
                }
            }catch(NumberFormatException err){
                //mostrarMensaje
            }
        }
        
        if(e.getSource() == vista.frmAddArticulo.btnAddComida){
            try{
                if(this.puedeAñadir(vista.frmAddArticulo.txtCantComida, vista.frmAddArticulo.cmbComidas)){ 
                    String nombre = vista.frmAddArticulo.cmbComidas.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantComida.getText());
                    this.platillos.add(new Platillo(0, nombre, cant, this.articuloPrecio(nombre)));
                }else{
                    //mostrarMensaje
                }
            }catch(NumberFormatException err){
                //mostrarMensaje
            }
        }
        
        if (e.getSource() == vista.frmAddArticulo.cmbMesa) {
            Integer precio = articuloPrecio(vista.frmAddArticulo.cmbMesa.getSelectedItem().toString());
            if(precio!=-1){
                vista.frmAddArticulo.lblPrecioMesa.setText("$ " + precio);
            } 
            else{
                vista.frmAddArticulo.lblPrecioMesa.setText("$");
            }
        }
        
        if (e.getSource() == vista.frmAddArticulo.cmbSilla) {
            Integer precio = articuloPrecio(vista.frmAddArticulo.cmbSilla.getSelectedItem().toString());
            if(precio!=-1){
                vista.frmAddArticulo.lblPrecioSilla.setText("$ " + precio);
            }else{
                vista.frmAddArticulo.lblPrecioSilla.setText("$");
            }       
        }
        
        if (e.getSource() == vista.frmAddArticulo.cmbBebidas) {
            Integer precio = articuloPrecio(vista.frmAddArticulo.cmbBebidas.getSelectedItem().toString());
            if(precio!=-1){
                vista.frmAddArticulo.lblPrecioBebida.setText("$ " + precio);
            }else{
                vista.frmAddArticulo.lblPrecioBebida.setText("$");
            }
        }
        
        if (e.getSource() == vista.frmAddArticulo.cmbComidas) {
            Integer precio = articuloPrecio(vista.frmAddArticulo.cmbComidas.getSelectedItem().toString());
            if(precio!=-1){
                vista.frmAddArticulo.lblPrecioComida.setText("$ " + precio);
            }else{
                vista.frmAddArticulo.lblPrecioComida.setText("$");
            }  
        }       
    }
    
    private Boolean puedeAñadir(javax.swing.JTextField txt, javax.swing.JComboBox cmb){
        return !txt.getText().isEmpty() && Integer.parseInt(txt.getText())!=0 && cmb.getSelectedIndex()!=0;
    }
    
    private void resetear(){
        this.bebidas.clear();
        this.platillos.clear();
        this.mesas.clear();
        this.sillas.clear();
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
