/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import javax.swing.event.TableModelEvent;
import modelo.*;
import modeloDAO.*;
import java.util.ArrayList;
import vista.Vista;

/**
 *
 * @author kuroy
 */
public class ControladorModEvento {

    private Vista vista;

    public ControladorModEvento(Vista vista) {
        this.vista = vista;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == vista.frmModEvento.btnBus) {
            try {
                vista.frmModEvento.cargarTablaBaseDeDatos();
                vista.frmModEvento.cargarEventoBaseDeDatos();
                vista.frmModEvento.txtDoc.setEditable(false);
            } catch (NumberFormatException err) {
                //this.mostrarMensajes();
                System.out.println("Num " + err.getMessage());
            } catch (SQLException err) {
                //this.mostrarMensajes();
                System.out.println("SQL " + err.getMessage());
            }
        }

        if (e.getSource() == vista.frmModEvento.btnDelArt) {
            Integer row = vista.frmModEvento.tableArticulo.getSelectedRow();
            vista.frmModEvento.articulos.remove((int) row);
            vista.frmModEvento.tableModelArt.removeRow(row);
            vista.frmModEvento.precioTotal();
        }

        if (e.getSource() == vista.frmModEvento.btnDelAct) {
            Integer row = vista.frmModEvento.tableActividades.getSelectedRow();
            vista.frmModEvento.tableModelAct.removeRow(row);
            vista.frmModEvento.actividades.remove((int) row);
        }

        if (e.getSource() == vista.frmModEvento.btnAddArt) {
            Integer id = Integer.parseInt(vista.frmModEvento.txtDoc.getText());
            vista.frmAddArticulo.setCambio(Boolean.FALSE, id);
            vista.cambiarPanel(vista.frmModEvento, vista.frmAddArticulo);
        }

        if (e.getSource() == vista.frmModEvento.btnAddAct) {
            vista.frmAddActividad.setCambio(Boolean.FALSE);
            vista.cambiarPanel(vista.frmModEvento, vista.frmAddActividad);
        }

        if (e.getSource() == vista.frmModEvento.cmbMontaje) {
            try {
                if (!vista.frmModEvento.cmbMontaje.getSelectedItem().equals("")) {
                    Integer costo = Integer.parseInt(this.getPrecio(vista.frmModEvento.cmbMontaje.getSelectedItem().toString()));
                    vista.frmModEvento.lblPrecioMontaje.setText("" + costo);
                    String nombre = vista.frmModEvento.cmbMontaje.getSelectedItem().toString();
                    Integer id = Integer.parseInt(vista.frmModEvento.txtDoc.getText());
                    vista.frmModEvento.cambiarMontaje(new Articulo(id, nombre, 1, costo, TipoArticulo.MONTAJE));
                    vista.frmModEvento.precioTotal();
                }
            } catch (SQLException err) {
            }
        }

        if (e.getSource() == vista.frmModEvento.btnGuardar) {
            try {
                ArticuloClienteDAO.addAll(this.agregarSeguroArticulo(vista.frmModEvento.articulos), Controlador.getConnection());
                ActividadDAO.addAll(this.agregarSeguroActividad(vista.frmModEvento.actividades), Controlador.getConnection());
                Evento evento = this.getEvento();
                if (!vista.frmModEvento.txtAbonar.getText().trim().isEmpty() && !vista.frmModEvento.lblAbonar.getText().equals("Cambio")) {
                    Integer value = Integer.parseInt(vista.frmModEvento.txtAbonar.getText());
                    evento.abonar(value);
                }
                EventoDAO.actualizar(Controlador.getConnection(), evento);
                vista.cambiarPanel(vista.frmModEvento, vista.frmInicio);
                vista.frmModEvento.clear();
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
        }

        if (e.getActionCommand().equals("Cancelar evento")) {
            Evento evento = this.getEvento();
            evento.setEstado(modelo.Estado.CANCELADO);
            try {
                EventoDAO.actualizar(Controlador.getConnection(), evento);
            } catch (SQLException err) {
            }
            vista.cambiarPanel(vista.frmModEvento, vista.frmInicio);
            vista.frmModEvento.clear();
            vista.frmModEvento.txtDoc.setEditable(true);
        }

        if (e.getActionCommand().equals("Activar evento")) {
            Evento evento = this.getEvento();
            evento.setEstado(modelo.Estado.ACTIVO);
            try {
                EventoDAO.actualizar(Controlador.getConnection(), evento);
            } catch (SQLException err) {
            }
            vista.cambiarPanel(vista.frmModEvento, vista.frmInicio);
            vista.frmModEvento.clear();
        }

        if (e.getSource() == vista.frmModEvento.btnVolver) {
            vista.cambiarPanel(vista.frmModEvento, vista.frmInicio);
            vista.frmModEvento.clear();
        }
    }

    public void tableChanged(TableModelEvent tme) {
        if (tme.getSource() == vista.frmModEvento.tableModelArt) {
            this.alterarTablaArticulos(tme);
        }

        if (tme.getSource() == vista.frmModEvento.tableModelAct) {           
            this.alterarTablaActividades(tme);
        }
    }

    private void alterarTablaArticulos(TableModelEvent tme) {
        Integer column = tme.getColumn();
        Integer row = tme.getFirstRow();
        try {
            if (column == 1) {
                String valueString = vista.frmModEvento.tableModelArt.getValueAt(row, column).toString();
                if (!valueString.equals("")) {
                    Integer valueInt = Integer.parseInt(valueString);
                    if (valueInt > 0) {
                        this.actualizarPrecio(row, valueInt);
                        vista.frmModEvento.actualizarArticulo(row, valueInt);
                        vista.frmModEvento.precioTotal();
                    } else {
                        throw new NumberFormatException();
                    }
                }
            }
        } catch (NumberFormatException e) {
            //this.mostrarMensaje();
            try {
                vista.frmModEvento.cargarTablaActuales();
            } catch (SQLException er) {
            }
        }
    }

    private void alterarTablaActividades(TableModelEvent tme) {
        Integer column = tme.getColumn();
        Integer row = tme.getFirstRow();
        if(column==-1)return;
        if (!vista.frmModEvento.tableModelAct.getValueAt(row, column).toString().trim().equals("")) {         
            String value = vista.frmModEvento.tableModelAct.getValueAt(row, column).toString().trim();
            vista.frmModEvento.actualizarActividad(row, column, value);
        }else{
            //this.mostrarMensaje();
            try {
                vista.frmModEvento.cargarTablaActuales();
            } catch (SQLException er) {
            }
        }
    }

    private void actualizarPrecio(Integer row, Integer cant) {
        try {
            Integer precio = Integer.parseInt(this.getPrecio(vista.frmModEvento.tableModelArt.getValueAt(row, 0).toString()));
            vista.frmModEvento.tableModelArt.setValueAt(precio * cant, row, 2);
        } catch (SQLException e) {
        }
    }

    private Evento getEvento() {
        Fecha fecha_celebracion = this.getFecha();
        Integer monto_abonado = 0;
        monto_abonado = Integer.parseInt(vista.frmModEvento.lblMontoAbonado.getText());
        Integer monto_total = Integer.parseInt(vista.frmModEvento.lblPrecioTotal.getText());
        modelo.Estado estado = modelo.Estado.getEstado(vista.frmModEvento.txtEstado.getText());
        String direccion = vista.frmModEvento.txtDir.getText();

        return new Evento(fecha_celebracion, direccion, this.getResponsable(), monto_abonado, monto_total, estado);
    }

    private Fecha getFecha() throws NumberFormatException, ArrayIndexOutOfBoundsException {
        String fecha[] = vista.frmModEvento.txtFecha.getText().split("/");
        Integer dia = Integer.parseInt(fecha[0]);
        Integer mes = Integer.parseInt(fecha[1]);
        Integer año = Integer.parseInt(fecha[2]);
        String horario[] = vista.frmModEvento.txtHoraInicio.getText().split(":");
        Integer hora = Integer.parseInt(horario[0]);
        Integer minuto = Integer.parseInt(horario[1]);
        Integer segundo = Integer.parseInt(horario[2]);
        return new Fecha(año, mes, dia, new Hora(hora, minuto, segundo));
    }

    private Responsable getResponsable() {
        Responsable responsable = new Responsable();
        Integer doc = Integer.parseInt(vista.frmModEvento.txtDoc.getText());
        responsable.setCedula(doc);
        try {
            return ResponsableDAO.buscar(responsable, Controlador.getConnection());
        } catch (SQLException e) {
        }

        return null;
    }

    private String getPrecio(String name) throws SQLException {
        Articulo articulo = new Articulo();
        articulo.setNombre(name);
        articulo = ArticuloAdminDAO.buscarPorNombre(articulo, Controlador.getConnection());
        if (articulo != null) {
            return "" + articulo.getCosto();
        }
        return "";
    }

    private ArrayList<Articulo> agregarSeguroArticulo(ArrayList<Articulo> articulos) {
        Articulo articulo = new Articulo();
        articulo.setId(Integer.parseInt(vista.frmModEvento.txtDoc.getText()));
        articulos.add(articulo);
        return articulos;
    }

    private ArrayList<Actividad> agregarSeguroActividad(ArrayList<Actividad> actividades) {
        Actividad actividad = new Actividad();
        actividad.setId(Integer.parseInt(vista.frmModEvento.txtDoc.getText()));
        actividades.add(actividad);
        return actividades;
    }
}
