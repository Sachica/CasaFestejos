/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modeloDAO.ArticuloAdminDAO;
import util.TipoArticulo;
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

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == vista.frmArticulo.btnCargar) {
            this.cargarDatosDeBase();
        }

        if (e.getSource() == vista.frmArticulo.btnAgregar) {
            try {
                modelo.ArticuloAdmin articulo = this.getArticulo();
                ArticuloAdminDAO.guardar(articulo, Controlador.getConnection());
                util.SistemaImagen.guardarImagen(URLImagen, articulo.getNombre());
                vista.frmArticulo.clear();
                this.cargarDatosDeBase();
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
        }

        if (e.getSource() == vista.frmArticulo.btnActualizar) {
            try {
                modelo.ArticuloAdmin articulo = this.getArticulo();
                ArticuloAdminDAO.actualizar(articulo, Controlador.getConnection());
                util.SistemaImagen.guardarImagen(URLImagen, articulo.getNombre());
                vista.frmArticulo.clear();
                util.SistemaImagen.actualizarImagenes();
                this.cargarDatosDeBase();
            } catch (SQLException err) {
            }
        }

        if (e.getSource() == vista.frmArticulo.btnEliminar) {
            try {
                modelo.ArticuloAdmin articulo = this.getArticulo();
                ArticuloAdminDAO.eliminar(articulo, Controlador.getConnection());
                util.SistemaImagen.eliminarImagen(articulo.getNombre());
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
                Object nombre = vista.frmArticulo.tblArt.getValueAt(row, 1);
                Object tipo = vista.frmArticulo.tblArt.getValueAt(row, 2);
                Object precio = vista.frmArticulo.tblArt.getValueAt(row, 3);
                this.cargarArticulo(new Object[]{id, nombre, tipo, precio});
                vista.frmArticulo.lblImagen.setIcon(util.SistemaImagen.getImagen(nombre.toString(), vista.frmArticulo.lblImagen.getMaximumSize()));
            }
        }

        if (me.getSource() == vista.frmArticulo.lblImagen) {
            this.getFile();
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

    private void insertarDatos(java.util.ArrayList<modelo.ArticuloAdmin> articulos) {
        for (modelo.ArticuloAdmin articulo : articulos) {
            Object data[] = {articulo.getId(), articulo.getNombre(), articulo.getTipo(), articulo.getPrecio()};
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

    private modelo.ArticuloAdmin getArticulo() {
        Integer id = Integer.parseInt(vista.frmArticulo.txtId.getText());
        String nombre = vista.frmArticulo.txtActNombre.getText();
        String tipo = vista.frmArticulo.txtActTipo.getText();
        Integer precio = Integer.parseInt(vista.frmArticulo.txtActPrecio.getText());

        return new modelo.ArticuloAdmin(id, TipoArticulo.getTipoString(tipo), nombre, precio);
    }
}
