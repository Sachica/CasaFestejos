/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Articulo;
import modeloDAO.ArticuloAdminDAO;
import modelo.TipoArticulo;
import vista.Vista;

/**
 *
 * @author kuroy
 */
public class ControladorArticulo {

    private String URLImagen;
    private Vista vista;

    public ControladorArticulo(Vista vista) {
        this.URLImagen = "";
        this.vista = vista;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) throws IOException {
        if (e.getSource() == vista.frmArticulo.btnCargar) {
            this.cargarDatosDeBase();
        }

        if (e.getSource() == vista.frmArticulo.btnAgregar) {
            try {
                Articulo articulo = this.getArticulo();
                ArticuloAdminDAO.guardar(articulo, Controlador.getConnection());
                vista.frmArticulo.clear();
                this.cargarDatosDeBase();
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            } catch (IOException err){
                
            }
        }

        if (e.getSource() == vista.frmArticulo.btnActualizar) {
            try {
                Articulo articulo = this.getArticulo();
                ArticuloAdminDAO.actualizar(articulo, Controlador.getConnection());
                vista.frmArticulo.clear();
                this.cargarDatosDeBase();
            } catch (SQLException err) {
                
            } catch (IOException err){
                
            }
        }

        if (e.getSource() == vista.frmArticulo.btnEliminar) {
            try {
                Articulo articulo = this.getArticulo();
                ArticuloAdminDAO.eliminar(articulo, Controlador.getConnection());
                this.cargarDatosDeBase();
                vista.frmArticulo.clear();
            } catch (SQLException err) {
            }
        }

        if (e.getSource() == vista.frmArticulo.btnFin) {
            vista.frmArticulo.clear();
            vista.frmArticulo.tableModel.setRowCount(0);
            vista.frmAddArticulo.cargarItemOpciones();
            vista.frmEvento.cargarItemOpciones();
            vista.frmModEvento.cargarItemOpciones();
            vista.cambiarPanel(vista.frmArticulo, vista.frmInicio);
        }
    }

    public void mouseClicked(java.awt.event.MouseEvent me) {
        if (me.getSource() == vista.frmArticulo.tblArt) {
            if (vista.frmArticulo.tblArt.getSelectedRow() != -1) {
                Integer row = vista.frmArticulo.tblArt.getSelectedRow();
                Object id = vista.frmArticulo.tblArt.getValueAt(row, 0);
                Articulo articulo = new Articulo();
                articulo.setId(Integer.parseInt(id.toString()));
                try{
                    articulo = ArticuloAdminDAO.buscarPorID(articulo, Controlador.getConnection());
                }catch(SQLException e){
                }
                Object nombre = articulo.getNombre();
                Object tipo = articulo.getTipo();
                Object costo = articulo.getCosto();
                this.cargarArticulo(new Object[]{id, nombre, tipo, costo});
                vista.frmArticulo.lblImagen.setIcon(this.getImagen(articulo.getImagen()));
            }
        }

        if (me.getSource() == vista.frmArticulo.lblImagen) {
            this.getFile();
        }
    }

    public void mouseEntered(java.awt.event.MouseEvent e){
        if(e.getSource() == vista.frmArticulo.lblImagen){
            vista.frmArticulo.lblImagen.setBackground(Color.WHITE);
            vista.frmArticulo.lblImagen.setForeground(Color.BLACK);
        }
    }
    
    public void mouseExited(java.awt.event.MouseEvent e){
        if(e.getSource() == vista.frmArticulo.lblImagen){
            vista.frmArticulo.lblImagen.setBackground(new Color(51,51,51));
            vista.frmArticulo.lblImagen.setForeground(Color.WHITE);
        }
    }
    
    private void getFile() {
        JFileChooser file = new JFileChooser();
        file.setFileFilter(new FileNameExtensionFilter("Archivos JPG", "JPG"));
        file.showOpenDialog(vista);
        File archivo = file.getSelectedFile();
        if (archivo != null) {
            this.URLImagen = archivo.getAbsolutePath();
            vista.frmArticulo.cargarImagen(URLImagen);
        }
    }

    private void cargarArticulo(Object data[]) {
        vista.frmArticulo.txtId.setText(data[0].toString());
        vista.frmArticulo.txtActNombre.setText(data[1].toString());
        vista.frmArticulo.txtActTipo.setText(data[2].toString());
        vista.frmArticulo.txtActPrecio.setText(data[3].toString());
    }

    private void insertarDatos(ArrayList<Articulo> articulos) {
        for (Articulo articulo : articulos) {
            Object data[] = {articulo.getId(), articulo.getNombre(), articulo.getTipo(), articulo.getCosto()};
            vista.frmArticulo.tableModel.addRow(data);
        }
    }

    private void cargarDatosDeBase() {
        try {
            vista.frmArticulo.tableModel.setRowCount(0);
            this.insertarDatos(ArticuloAdminDAO.getAll(Controlador.getConnection()));
        } catch (SQLException er) {
        }
    }

    private Articulo getArticulo() {
        Integer id = Integer.parseInt(vista.frmArticulo.txtId.getText());
        String nombre = vista.frmArticulo.txtActNombre.getText();
        String tipo = vista.frmArticulo.txtActTipo.getText();
        Integer precio = Integer.parseInt(vista.frmArticulo.txtActPrecio.getText());
        ImageIcon imagen = new ImageIcon(URLImagen);

        return new Articulo(id, nombre, id, precio, imagen, TipoArticulo.getTipoString(tipo));
    }
    
    private ImageIcon getImagen(ImageIcon imagen){
        Dimension dim = vista.frmArticulo.lblImagen.getMaximumSize();
        return new ImageIcon(imagen.getImage().getScaledInstance((int)dim.getWidth()+20, (int)dim.getHeight(), Image.SCALE_DEFAULT));
    }
}
