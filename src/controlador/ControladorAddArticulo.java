/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import modelo.*;
import vista.Vista;

/**
 *
 * @author kuroy
 */
public class ControladorAddArticulo {
    private Vista vista;
    private java.util.ArrayList<ArticuloCliente> articulos;

    public ControladorAddArticulo(Vista vista) {
        this.vista = vista;
        this.articulos = new java.util.ArrayList<>();
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == vista.frmAddArticulo.btnFin) {
            if (vista.frmAddArticulo.getCambio()) {
                vista.frmEvento.addArticulos(articulos.clone());
                vista.cambiarPanel(vista.frmAddArticulo, vista.frmEvento);
            } else {
                vista.frmModEvento.addArticulosTabla(articulos.clone());
                vista.cambiarPanel(vista.frmAddArticulo, vista.frmModEvento);              
            }
            vista.frmAddArticulo.setCambio(Boolean.TRUE, 0);
            vista.frmAddArticulo.resetear();
            this.resetear();
        }

        if (e.getSource() == vista.frmAddArticulo.btnAddSilla) {
            try {
                if (this.puedeAñadir(vista.frmAddArticulo.txtCantSilla, vista.frmAddArticulo.cmbSilla)) {
                    Integer id = vista.frmAddArticulo.getId();
                    String nombre = vista.frmAddArticulo.cmbSilla.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantSilla.getText());
                    this.resetSilla();
                    this.articulos.add(new ArticuloCliente(id, util.TipoArticulo.SILLA, nombre, cant, this.articuloPrecio(nombre)));
                } else {
                    //mostrarMensaje
                }
            } catch (NumberFormatException err) {
                //mostrarMensaje
            }
        }

        if (e.getSource() == vista.frmAddArticulo.btnAddMesa) {
            try {
                if (this.puedeAñadir(vista.frmAddArticulo.txtCantMesa, vista.frmAddArticulo.cmbMesa)) {
                    Integer id = vista.frmAddArticulo.getId();
                    String nombre = vista.frmAddArticulo.cmbMesa.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantMesa.getText());
                    this.resetMesa();
                    this.articulos.add(new ArticuloCliente(id, util.TipoArticulo.MESA, nombre, cant, this.articuloPrecio(nombre)));
                } else {
                    //mostrarMensaje
                }
            } catch (NumberFormatException err) {
                //mostrarMensaje
            }
        }

        if (e.getSource() == vista.frmAddArticulo.btnAddBebida) {
            try {
                if (this.puedeAñadir(vista.frmAddArticulo.txtCantBebida, vista.frmAddArticulo.cmbBebidas)) {
                    Integer id = vista.frmAddArticulo.getId();
                    String nombre = vista.frmAddArticulo.cmbBebidas.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantBebida.getText());
                    this.resetBebida();
                    this.articulos.add(new ArticuloCliente(id, util.TipoArticulo.BEBIDA, nombre, cant, this.articuloPrecio(nombre)));
                } else {
                    //mostrarMensaje
                }
            } catch (NumberFormatException err) {
                //mostrarMensaje
            }
        }

        if (e.getSource() == vista.frmAddArticulo.btnAddComida) {
            try {
                if (this.puedeAñadir(vista.frmAddArticulo.txtCantComida, vista.frmAddArticulo.cmbComidas)) {
                    Integer id = vista.frmAddArticulo.getId();
                    String nombre = vista.frmAddArticulo.cmbComidas.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantComida.getText());
                    this.resetComida();
                    this.articulos.add(new ArticuloCliente(id, util.TipoArticulo.PLATILLO, nombre, cant, this.articuloPrecio(nombre)));
                } else {
                    //mostrarMensaje
                }
            } catch (NumberFormatException err) {
                //mostrarMensaje
            }
        }

        if (e.getSource() == vista.frmAddArticulo.cmbMesa) {
            Integer precio = articuloPrecio(vista.frmAddArticulo.cmbMesa.getSelectedItem().toString());
            if (precio != -1) {
                vista.frmAddArticulo.lblPrecioMesa.setText("" + precio);
            } else {
                vista.frmAddArticulo.lblPrecioMesa.setText("");
            }
        }

        if (e.getSource() == vista.frmAddArticulo.cmbSilla) {
            Integer precio = articuloPrecio(vista.frmAddArticulo.cmbSilla.getSelectedItem().toString());
            if (precio != -1) {
                vista.frmAddArticulo.lblPrecioSilla.setText("" + precio);
            } else {
                vista.frmAddArticulo.lblPrecioSilla.setText("");
            }
        }

        if (e.getSource() == vista.frmAddArticulo.cmbBebidas) {
            Integer precio = articuloPrecio(vista.frmAddArticulo.cmbBebidas.getSelectedItem().toString());
            if (precio != -1) {
                vista.frmAddArticulo.lblPrecioBebida.setText("" + precio);
            } else {
                vista.frmAddArticulo.lblPrecioBebida.setText("");
            }
        }

        if (e.getSource() == vista.frmAddArticulo.cmbComidas) {
            Integer precio = articuloPrecio(vista.frmAddArticulo.cmbComidas.getSelectedItem().toString());
            if (precio != -1) {
                vista.frmAddArticulo.lblPrecioComida.setText("" + precio);
            } else {
                vista.frmAddArticulo.lblPrecioComida.setText("");
            }
        }
    }

    private void resetSilla() {
        vista.frmAddArticulo.cmbSilla.setSelectedIndex(0);
        vista.frmAddArticulo.lblPrecioSilla.setText("");
        vista.frmAddArticulo.txtCantSilla.setText("");
    }

    private void resetMesa() {
        vista.frmAddArticulo.cmbMesa.setSelectedIndex(0);
        vista.frmAddArticulo.lblPrecioMesa.setText("");
        vista.frmAddArticulo.txtCantMesa.setText("");
    }

    private void resetBebida() {
        vista.frmAddArticulo.cmbBebidas.setSelectedIndex(0);
        vista.frmAddArticulo.lblPrecioBebida.setText("");
        vista.frmAddArticulo.txtCantBebida.setText("");
    }

    private void resetComida() {
        vista.frmAddArticulo.cmbComidas.setSelectedIndex(0);
        vista.frmAddArticulo.lblPrecioComida.setText("");
        vista.frmAddArticulo.txtCantComida.setText("");
    }

    private Boolean puedeAñadir(javax.swing.JTextField txt, javax.swing.JComboBox cmb) {
        return !txt.getText().isEmpty() && Integer.parseInt(txt.getText()) != 0 && cmb.getSelectedIndex() != 0;
    }

    private void resetear() {
        this.articulos.clear();
    }

    private Integer articuloPrecio(String nombre) {
        try {
            ArticuloAdmin articulo = new ArticuloAdmin();
            articulo.setNombre(nombre);
            articulo = modeloDAO.ArticuloAdminDAO.buscarPorNombre(articulo, Controlador.getConnection());
            return articulo != null ? articulo.getPrecio() : -1;
        } catch (SQLException e) {
        }
        return -1;
    }
}
