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
import modeloDAO.*;
import modelo.TipoArticulo;
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
            if (precio != -1) {
                vista.frmEvento.lblPrecioMontaje.setText("" + precio);
                vista.frmEvento.calcularPrecio();
            } else {
                vista.frmEvento.lblPrecioMontaje.setText("");
            }
        }

        if (e.getSource() == vista.frmEvento.btnAddArticulo) {
            vista.frmAddArticulo.setCambio(Boolean.TRUE, (Integer.parseInt(vista.frmEvento.txtDoc.getText())));
            vista.cambiarPanel(vista.frmEvento, vista.frmAddArticulo);
        }

        if (e.getSource() == vista.frmEvento.btnBus) {
            try {
                Responsable responsable = new Responsable();
                responsable.setCedula(Integer.parseInt(vista.frmEvento.txtDoc.getText()));
                responsable = ResponsableDAO.buscar(responsable, Controlador.getConnection());
                if (responsable != null) {
                    vista.frmEvento.habilitar(Boolean.TRUE);
                } else {
                    this.mostrarMensajes("No se encuentra registrado", Boolean.FALSE);
                }
            } catch (NumberFormatException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            } catch (SQLException err) {
                this.mostrarMensajes("Error de busqueda a base de datos", Boolean.FALSE);
            }
        }

        if (e.getSource() == vista.frmEvento.btnAddActividad) {
            vista.cambiarPanel(vista.frmEvento, vista.frmAddActividad);
        }
        if (e.getSource() == vista.frmEvento.btnConfirmar) {
            try {
                Integer montoTotal = Integer.parseInt(vista.frmEvento.lblPrecioTotal.getText());
                Integer monto_inicial = Integer.parseInt(vista.frmEvento.txtMontoInicial.getText());
                String direccion_evento = vista.frmEvento.txtDir.getText();
                
                Evento evento = new Evento(this.getFecha(), direccion_evento, this.getResponsable(), monto_inicial, montoTotal, modelo.Estado.ACTIVO);
                EventoDAO.guardar(Controlador.getConnection(), evento);
                ArticuloClienteDAO.addAll(this.agregarSeguroArticulo(vista.frmEvento.articulos), Controlador.getConnection());
                ActividadDAO.addAll(this.agregarSeguroActividad(vista.frmEvento.actividades), Controlador.getConnection());
                this.guardarMontaje();                
                vista.frmEvento.clear();
                vista.cambiarPanel(vista.frmEvento, vista.frmInicio);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            } catch (SQLException err) {
                this.mostrarMensajes("Error de acceso a base de datos", Boolean.FALSE);
            }
        }

        if (e.getSource() == vista.frmEvento.btnVolver || e.getSource() == vista.frmEvento.btnCancelar) {
            vista.frmEvento.clear();
            vista.frmEvento.habilitar(Boolean.FALSE);
            vista.cambiarPanel(vista.frmEvento, vista.frmInicio);
        }
    }

    private Integer articuloPrecio(String nombre) {
        try {
            Articulo articulo = new Articulo();
            articulo.setNombre(nombre);
            articulo = ArticuloAdminDAO.buscarPorNombre(articulo, Controlador.getConnection());
            return articulo != null ? articulo.getCosto(): -1;
        } catch (SQLException e) {
        }
        return -1;
    }

    private Fecha getFecha() throws NumberFormatException, ArrayIndexOutOfBoundsException {
        String fecha[] = vista.frmEvento.txtFecha.getText().split("/");
        Integer dia = Integer.parseInt(fecha[0]);
        Integer mes = Integer.parseInt(fecha[1]);
        Integer año = Integer.parseInt(fecha[2]);

        Integer hora = Integer.parseInt(vista.frmEvento.txtHoraEvt.getText());
        Integer minuto = Integer.parseInt(vista.frmEvento.txtMinEvt.getText());

        return new modelo.Fecha(año, mes, dia, new Hora(hora, minuto, 0));
    }

    private void guardarMontaje() throws SQLException {
        if (!vista.frmEvento.lblPrecioMontaje.getText().equals("")) {
            Integer costo = Integer.parseInt(vista.frmEvento.lblPrecioMontaje.getText());
            String nombre = vista.frmEvento.cmbMontaje.getSelectedItem().toString();
            Integer id = Integer.parseInt(vista.frmEvento.txtDoc.getText());
            Articulo articulo = new Articulo(id, nombre, 1, costo, TipoArticulo.MONTAJE);
            ArticuloClienteDAO.guardar(articulo, Controlador.getConnection());
        }
    }

    private Responsable getResponsable() throws SQLException {
        Integer cedula = Integer.parseInt(vista.frmEvento.txtDoc.getText());
        Responsable responsable = new Responsable();
        responsable.setCedula(cedula);

        return ResponsableDAO.buscar(responsable, Controlador.getConnection());
    }
    
    private ArrayList<Articulo> agregarSeguroArticulo(ArrayList<Articulo> articulos){
        Articulo articulo = new Articulo();
        articulo.setId(Integer.parseInt(vista.frmEvento.txtDoc.getText()));
        articulos.add(articulo);
        return articulos;
    }
    
    private ArrayList<Actividad> agregarSeguroActividad(ArrayList<Actividad> actividades){
        Actividad actividad = new Actividad();
        actividad.setId(Integer.parseInt(vista.frmEvento.txtDoc.getText()));
        actividades.add(actividad);
        return actividades;
    }
    
    public void mostrarMensajes(String mensaje, Boolean x){
        String titulo = x ? "Operacion exitosa!" : "Operacion fallida!";
        Integer tipo = x ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;       
        JOptionPane.showMessageDialog(vista, mensaje, titulo, tipo);
    }
}
