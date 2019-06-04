/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import javax.swing.event.TableModelEvent;
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
                    Integer precio = Integer.parseInt(this.getPrecio(vista.frmModEvento.cmbMontaje.getSelectedItem().toString()));
                    vista.frmModEvento.lblPrecioMontaje.setText("" + precio);
                    String nombre = vista.frmModEvento.cmbMontaje.getSelectedItem().toString();
                    Integer id = Integer.parseInt(vista.frmModEvento.txtDoc.getText());
                    vista.frmModEvento.cambiarMontaje(new modelo.ArticuloCliente(id, util.TipoArticulo.MONTAJE, nombre, 1, precio));
                    vista.frmModEvento.precioTotal();
                }
            } catch (SQLException err) {
            }
        }

        if (e.getSource() == vista.frmModEvento.btnGuardar) {
            try {
                modeloDAO.ArticuloClienteDAO.addAll(this.agregarSeguroArticulo(vista.frmModEvento.articulos), Controlador.getConnection());
                modeloDAO.ActividadDAO.addAll(this.agregarSeguroActividad(vista.frmModEvento.actividades), Controlador.getConnection());
                modelo.Evento evento = this.getEvento();
                if (!vista.frmModEvento.txtAbonar.getText().trim().isEmpty() && !vista.frmModEvento.lblAbonar.getText().equals("Cambio")) {
                    Integer value = Integer.parseInt(vista.frmModEvento.txtAbonar.getText());
                    evento.abonar(value);
                }
                modeloDAO.EventoDAO.actualizar(Controlador.getConnection(), evento);
                vista.cambiarPanel(vista.frmModEvento, vista.frmInicio);
                vista.frmModEvento.clear();
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
        }

        if (e.getActionCommand().equals("Cancelar evento")) {
            modelo.Evento evento = this.getEvento();
            evento.setEstado(util.Estado.CANCELADO);
            try {
                modeloDAO.EventoDAO.actualizar(Controlador.getConnection(), evento);
            } catch (SQLException err) {
            }
            vista.cambiarPanel(vista.frmModEvento, vista.frmInicio);
            vista.frmModEvento.clear();
            vista.frmModEvento.txtDoc.setEditable(true);
        }

        if (e.getActionCommand().equals("Activar evento")) {
            modelo.Evento evento = this.getEvento();
            evento.setEstado(util.Estado.ACTIVO);
            try {
                modeloDAO.EventoDAO.actualizar(Controlador.getConnection(), evento);
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

    private modelo.Evento getEvento() {
        modelo.Fecha fecha_celebracion = this.getFecha();
        Integer monto_abonado = 0;
        monto_abonado = Integer.parseInt(vista.frmModEvento.lblMontoAbonado.getText());
        Integer monto_total = Integer.parseInt(vista.frmModEvento.lblPrecioTotal.getText());
        util.Estado estado = util.Estado.getEstado(vista.frmModEvento.txtEstado.getText());
        String direccion = vista.frmModEvento.txtDir.getText();

        return new modelo.Evento(fecha_celebracion, direccion, this.getResponsable(), monto_abonado, monto_total, estado);
    }

    private modelo.Fecha getFecha() throws NumberFormatException, ArrayIndexOutOfBoundsException {
        String fecha[] = vista.frmModEvento.txtFecha.getText().split("/");
        Integer dia = Integer.parseInt(fecha[0]);
        Integer mes = Integer.parseInt(fecha[1]);
        Integer año = Integer.parseInt(fecha[2]);
        String horario[] = vista.frmModEvento.txtHoraInicio.getText().split(":");
        Integer hora = Integer.parseInt(horario[0]);
        Integer minuto = Integer.parseInt(horario[1]);
        Integer segundo = Integer.parseInt(horario[2]);
        return new modelo.Fecha(año, mes, dia, new modelo.Hora(hora, minuto, segundo));
    }

    private modelo.Responsable getResponsable() {
        modelo.Responsable r = new modelo.Responsable();
        Integer doc = Integer.parseInt(vista.frmModEvento.txtDoc.getText());
        r.setCedula(doc);
        try {
            return modeloDAO.ResponsableDAO.buscar(r, Controlador.getConnection());
        } catch (SQLException e) {
        }

        return null;
    }

    private String getPrecio(String name) throws SQLException {
        modelo.ArticuloAdmin articulo = new modelo.ArticuloAdmin();
        articulo.setNombre(name);
        articulo = modeloDAO.ArticuloAdminDAO.buscarPorNombre(articulo, Controlador.getConnection());
        if (articulo != null) {
            return "" + articulo.getPrecio();
        }
        return "";
    }

    private java.util.ArrayList<modelo.ArticuloCliente> agregarSeguroArticulo(java.util.ArrayList<modelo.ArticuloCliente> articulos) {
        modelo.ArticuloCliente articulo = new modelo.ArticuloCliente();
        articulo.setId(Integer.parseInt(vista.frmModEvento.txtDoc.getText()));
        articulos.add(articulo);
        return articulos;
    }

    private java.util.ArrayList<modelo.Actividad> agregarSeguroActividad(java.util.ArrayList<modelo.Actividad> actividades) {
        modelo.Actividad actividad = new modelo.Actividad();
        actividad.setId(Integer.parseInt(vista.frmModEvento.txtDoc.getText()));
        actividades.add(actividad);
        return actividades;
    }
}
