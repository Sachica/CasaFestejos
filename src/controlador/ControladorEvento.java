/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import modelo.ArticuloAdmin;
import modelo.Evento;
import modelo.Responsable;
import modeloDAO.ActividadDAO;
import modeloDAO.ArticuloClienteDAO;
import modeloDAO.EventoDAO;
import modeloDAO.ResponsableDAO;
import util.TipoArticulo;
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
                Responsable r = new Responsable();
                r.setCedula(Integer.parseInt(vista.frmEvento.txtDoc.getText()));
                r = ResponsableDAO.buscar(r, Controlador.getConnection());
                if (r != null) {
                    vista.frmEvento.habilitar(Boolean.TRUE);
                } else {
                    //this.mostrarMensaje();
                }
            } catch (NumberFormatException err) {
                //this.mostrarMensaje();
            } catch (SQLException err) {
                //this.mostrarMensaje();
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
                
                Evento evento = new Evento(this.getFecha(), direccion_evento, this.getResponsable(), monto_inicial, montoTotal, util.Estado.ACTIVO);
                EventoDAO.guardar(Controlador.getConnection(), evento);
                ArticuloClienteDAO.addAll(this.agregarSeguroArticulo(vista.frmEvento.articulos), Controlador.getConnection());
                ActividadDAO.addAll(this.agregarSeguroActividad(vista.frmEvento.actividades), Controlador.getConnection());
                this.guardarMontaje();                
                vista.frmEvento.clear();
                vista.cambiarPanel(vista.frmEvento, vista.frmInicio);
            } catch (NumberFormatException err) {
                System.out.println(err.getMessage());
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            } catch (Exception err){
                System.out.println(err.getMessage());
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
            ArticuloAdmin articulo = new ArticuloAdmin();
            articulo.setNombre(nombre);
            articulo = modeloDAO.ArticuloAdminDAO.buscarPorNombre(articulo, Controlador.getConnection());
            return articulo != null ? articulo.getPrecio() : -1;
        } catch (SQLException e) {
        }
        return -1;
    }

    private modelo.Fecha getFecha() throws NumberFormatException {
        String fecha[] = vista.frmEvento.txtFecha.getText().split("/");
        Integer dia = Integer.parseInt(fecha[0]);
        Integer mes = Integer.parseInt(fecha[1]);
        Integer año = Integer.parseInt(fecha[2]);

        Integer hora = Integer.parseInt(vista.frmEvento.txtHoraEvt.getText());
        Integer minuto = Integer.parseInt(vista.frmEvento.txtMinEvt.getText());

        return new modelo.Fecha(año, mes, dia, new modelo.Hora(hora, minuto, 0));
    }

    private void guardarMontaje() throws SQLException {
        if (!vista.frmEvento.lblPrecioMontaje.getText().equals("")) {
            Integer precio = Integer.parseInt(vista.frmEvento.lblPrecioMontaje.getText());
            String nombre = vista.frmEvento.cmbMontaje.getSelectedItem().toString();
            Integer id = Integer.parseInt(vista.frmEvento.txtDoc.getText());
            modelo.ArticuloCliente articulo = new modelo.ArticuloCliente(id, TipoArticulo.MONTAJE, nombre, 1, precio);
            modeloDAO.ArticuloClienteDAO.guardar(articulo, Controlador.getConnection());
        }
    }

    private Responsable getResponsable() throws SQLException {
        Integer cedula = Integer.parseInt(vista.frmEvento.txtDoc.getText());
        Responsable responsable = new Responsable();
        responsable.setCedula(cedula);

        return ResponsableDAO.buscar(responsable, Controlador.getConnection());
    }
    
    private java.util.ArrayList<modelo.ArticuloCliente> agregarSeguroArticulo(java.util.ArrayList<modelo.ArticuloCliente> articulos){
        modelo.ArticuloCliente articulo = new modelo.ArticuloCliente();
        articulo.setId(Integer.parseInt(vista.frmEvento.txtDoc.getText()));
        articulos.add(articulo);
        return articulos;
    }
    
    private java.util.ArrayList<modelo.Actividad> agregarSeguroActividad(java.util.ArrayList<modelo.Actividad> actividades){
        modelo.Actividad actividad = new modelo.Actividad();
        actividad.setId(Integer.parseInt(vista.frmEvento.txtDoc.getText()));
        actividades.add(actividad);
        return actividades;
    }
}
