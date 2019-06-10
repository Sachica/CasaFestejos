/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;
import modeloDAO.ArticuloAdminDAO;
import vista.Vista;

/**
 *
 * @author kuroy
 */
public class ControladorAddArticulo {
    private Vista vista;
    private ArrayList<Articulo> articulos;

    public ControladorAddArticulo(Vista vista) {
        this.vista = vista;
        this.articulos = new ArrayList<>();
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
            this.mostrarMensajes("Articulos añadidos al evento", Boolean.TRUE);
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
                    Integer costo = this.articuloPrecio(nombre);
                    this.resetSilla();
                    this.articulos.add(new Articulo(id, nombre, cant, costo, TipoArticulo.SILLA));
                    this.mostrarMensajes("Se ha añadido al evento", Boolean.TRUE);
                } else {
                    this.mostrarMensajes("No se ha elegido el articulo o cantidad nula", Boolean.FALSE);
                }
            } catch (NumberFormatException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            }
        }

        if (e.getSource() == vista.frmAddArticulo.btnAddMesa) {
            try {
                if (this.puedeAñadir(vista.frmAddArticulo.txtCantMesa, vista.frmAddArticulo.cmbMesa)) {
                    Integer id = vista.frmAddArticulo.getId();
                    String nombre = vista.frmAddArticulo.cmbMesa.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantMesa.getText());
                    Integer costo = this.articuloPrecio(nombre);
                    this.resetMesa();
                    this.articulos.add(new Articulo(id, nombre, cant, costo, TipoArticulo.MESA));
                    this.mostrarMensajes("Se ha añadido al evento", Boolean.TRUE);
                } else {
                    this.mostrarMensajes("No se ha elegido el articulo o cantidad nula", Boolean.FALSE);
                }
            } catch (NumberFormatException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            }
        }

        if (e.getSource() == vista.frmAddArticulo.btnAddBebida) {
            try {
                if (this.puedeAñadir(vista.frmAddArticulo.txtCantBebida, vista.frmAddArticulo.cmbBebidas)) {
                    Integer id = vista.frmAddArticulo.getId();
                    String nombre = vista.frmAddArticulo.cmbBebidas.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantBebida.getText());
                    Integer costo = this.articuloPrecio(nombre);
                    this.resetBebida();
                    this.articulos.add(new Articulo(id, nombre, cant, costo, TipoArticulo.BEBIDA));
                    this.mostrarMensajes("Se ha añadido al evento", Boolean.TRUE);
                } else {
                    this.mostrarMensajes("No se ha elegido el articulo o cantidad nula", Boolean.FALSE);
                }
            } catch (NumberFormatException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            }
        }

        if (e.getSource() == vista.frmAddArticulo.btnAddComida) {
            try {
                if (this.puedeAñadir(vista.frmAddArticulo.txtCantComida, vista.frmAddArticulo.cmbComidas)) {
                    Integer id = vista.frmAddArticulo.getId();
                    String nombre = vista.frmAddArticulo.cmbComidas.getSelectedItem().toString();
                    Integer cant = Integer.parseInt(vista.frmAddArticulo.txtCantComida.getText());
                    Integer costo = this.articuloPrecio(nombre);
                    this.resetComida();
                    this.articulos.add(new Articulo(id, nombre, cant, costo, TipoArticulo.PLATILLO));
                    this.mostrarMensajes("Se ha añadido al evento", Boolean.TRUE);
                } else {
                    this.mostrarMensajes("No se ha elegido el articulo o cantidad nula", Boolean.FALSE);
                }
            } catch (NumberFormatException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            }
        }

        if (e.getSource() == vista.frmAddArticulo.cmbMesa) {
            String nombreArticulo = vista.frmAddArticulo.cmbMesa.getSelectedItem().toString();
            Articulo articulo = this.getArticulo(nombreArticulo);
            if (articulo!=null) {
                vista.frmAddArticulo.addImagen(articulo.getImagen(), vista.frmAddArticulo.lblMesa);
                vista.frmAddArticulo.lblPrecioMesa.setText("" + articulo.getCosto());
            } else {
                vista.frmAddArticulo.lblPrecioMesa.setText("");
                vista.frmAddArticulo.lblMesa.setIcon(null);
            }
        }

        if (e.getSource() == vista.frmAddArticulo.cmbSilla) {
            String nombreArticulo = vista.frmAddArticulo.cmbSilla.getSelectedItem().toString();
            Articulo articulo = this.getArticulo(nombreArticulo);
            if (articulo!=null) {
                vista.frmAddArticulo.addImagen(articulo.getImagen(), vista.frmAddArticulo.lblSilla);
                vista.frmAddArticulo.lblPrecioSilla.setText("" + articulo.getCosto());
            } else {
                vista.frmAddArticulo.lblPrecioSilla.setText("");
                vista.frmAddArticulo.lblSilla.setIcon(null);
            }
        }

        if (e.getSource() == vista.frmAddArticulo.cmbBebidas) {
            String nombreArticulo = vista.frmAddArticulo.cmbBebidas.getSelectedItem().toString();
            Articulo articulo = this.getArticulo(nombreArticulo);
            if (articulo!=null) {
                vista.frmAddArticulo.addImagen(articulo.getImagen(), vista.frmAddArticulo.lblBebida);              
                vista.frmAddArticulo.lblPrecioBebida.setText("" + articulo.getCosto());
            } else {
                vista.frmAddArticulo.lblPrecioBebida.setText("");
                vista.frmAddArticulo.lblBebida.setIcon(null);
            }
        }

        if (e.getSource() == vista.frmAddArticulo.cmbComidas) {
            String  nombreArticulo = vista.frmAddArticulo.cmbComidas.getSelectedItem().toString();
            Articulo articulo = this.getArticulo(nombreArticulo);
            if (articulo!=null) {
                vista.frmAddArticulo.addImagen(articulo.getImagen(), vista.frmAddArticulo.lblComida);             
                vista.frmAddArticulo.lblPrecioComida.setText("" + articulo.getCosto());
            } else {
                vista.frmAddArticulo.lblPrecioComida.setText("");
                vista.frmAddArticulo.lblComida.setIcon(null);
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
            Articulo articulo = new Articulo();
            articulo.setNombre(nombre);
            articulo = ArticuloAdminDAO.buscarPorNombre(articulo, Controlador.getConnection());
            return articulo != null ? articulo.getCosto() : -1;
        } catch (SQLException e) {
        }
        return -1;
    }
    
    private Articulo getArticulo(String nombre){
        try {
            Articulo articulo = new Articulo();
            articulo.setNombre(nombre);
            articulo = ArticuloAdminDAO.buscarPorNombre(articulo, Controlador.getConnection());
            return articulo;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public void mostrarMensajes(String mensaje, Boolean x){
        String titulo = x ? "Operacion exitosa!" : "Operacion fallida!";
        Integer tipo = x ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;       
        JOptionPane.showMessageDialog(vista, mensaje, titulo, tipo);
    }
}
