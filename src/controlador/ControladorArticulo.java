/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import modeloDAO.ArticuloDAO;
import servicio.Conexion;
import util.TipoArticulo;
import vista.Vista;

/**
 *
 * @author kuroy
 */
public class ControladorArticulo {

    private Vista vista;
    
    public ControladorArticulo(Vista vista) {
        this.vista = vista;
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == vista.frmArticulo.btnCargar){
            try{
                vista.frmArticulo.tableModel.setRowCount(0);
                this.insertarDatos(ArticuloDAO.getAll(Conexion.getConnection()));
            }catch(SQLException er){
            }
        }
        
        if(e.getSource() == vista.frmArticulo.btnAgregar){
            try{
                ArticuloDAO.guardar(this.getArticulo(), Conexion.getConnection());
                vista.frmArticulo.clear();
            }catch(SQLException err){
            }
        }
        
        if(e.getSource() == vista.frmArticulo.btnActualizar){
            try{
                ArticuloDAO.actualizar(this.getArticulo(), Conexion.getConnection());
                
            }catch(SQLException err){
            }
        }
        
        if(e.getSource() == vista.frmArticulo.btnEliminar){
            try{
                ArticuloDAO.eliminar(this.getArticulo(), Conexion.getConnection());
                vista.frmArticulo.clear();
            }catch(SQLException err){
            }
        }
    }
    
    public void mouseClicked(java.awt.event.MouseEvent me) {
        if(vista.frmArticulo.tblArt.getSelectedRow()==-1){
            return;
        }
        Integer row = vista.frmArticulo.tblArt.getSelectedRow();
        Object id = vista.frmArticulo.tblArt.getValueAt(row, 0);
        Object nombre = vista.frmArticulo.tblArt.getValueAt(row, 1);
        Object tipo = vista.frmArticulo.tblArt.getValueAt(row, 2);
        Object precio = vista.frmArticulo.tblArt.getValueAt(row, 3);
        this.cargarArticulo(new Object[]{id, nombre, tipo, precio});
    }
    
    private void cargarArticulo(Object data[]) {
        vista.frmArticulo.txtId.setText(data[0].toString());
        vista.frmArticulo.txtActNombre.setText(data[1].toString());
        vista.frmArticulo.txtActTipo.setText(data[2].toString());
        vista.frmArticulo.txtActPrecio.setText(data[3].toString());
    }
    
    private void insertarDatos(java.util.ArrayList<modelo.Articulo> articulos){
        for(modelo.Articulo articulo : articulos){
            Object data[] = {articulo.getId(), articulo.getNombre(), articulo.getTipo(), articulo.getPrecio()};
            vista.frmArticulo.tableModel.addRow(data);
        }
    }
    
    private modelo.Articulo getArticulo(){
        Integer id = Integer.parseInt(vista.frmArticulo.txtId.getText());
        String nombre = vista.frmArticulo.txtActNombre.getText();
        String tipo = vista.frmArticulo.txtActTipo.getText();
        Integer precio = Integer.parseInt(vista.frmArticulo.txtActPrecio.getText());
        
        return new modelo.Articulo(id, TipoArticulo.getTipoArticulo(tipo), nombre, precio);
    }
}
