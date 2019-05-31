/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import servicio.Conexion;
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
                modelo.Responsable res = this.getResponsable();
                if (res != null) {
                    this.cargarTablas(res);
                }
            } catch (NumberFormatException err) {
                //this.mostrarMensajes();
            } catch (SQLException err) {
                //this.mostrarMensajes();
                System.out.println(err.getMessage());
            }
        }

        if(e.getSource() == vista.frmModEvento.cmbMontaje){
            try{
                vista.frmModEvento.lblPrecioMontaje.setText(this.getPrecio(vista.frmModEvento.cmbMontaje.getSelectedItem().toString()));
            }catch(SQLException err){
            }
        }
        
        if (e.getSource() == vista.frmModEvento.btnCancelar) {
            vista.cambiarPanel(vista.frmModEvento, vista.frmInicio);
            vista.frmModEvento.clear();
        }
    }

    private void cargarTablas(modelo.Responsable res) throws SQLException {
        modelo.ArticuloCliente articuloCliente = new modelo.ArticuloCliente();
        articuloCliente.setId(res.getCedula());
        vista.frmModEvento.addAllArticulo(modeloDAO.ArticuloClienteDAO.buscar(articuloCliente, Conexion.getConnection()));
        modelo.Actividad actividad = new modelo.Actividad();
        actividad.setId(res.getCedula());
        vista.frmModEvento.addAllActividad(modeloDAO.ActividadDAO.buscar(actividad, Conexion.getConnection()));
    }

    private modelo.Responsable getResponsable() {
        modelo.Responsable r = new modelo.Responsable();
        Integer doc = Integer.parseInt(vista.frmModEvento.txtDoc.getText());
        r.setCedula(doc);
        try {
            return modeloDAO.ResponsableDAO.buscar(r, Conexion.getConnection());
        } catch (SQLException e) {
        }

        return null;
    }
    
    private String getPrecio(String name) throws SQLException{
        modelo.ArticuloAdmin articulo = new modelo.ArticuloAdmin();
        articulo.setNombre(name);
        articulo = modeloDAO.ArticuloAdminDAO.buscarPorNombre(articulo, Conexion.getConnection());
        if(articulo != null){
            return ""+articulo.getPrecio();
        }
        return "";
    }
}
