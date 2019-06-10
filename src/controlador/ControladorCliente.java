/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modeloDAO.ResponsableDAO;
import modelo.Responsable;
import vista.Vista;

/**
 *
 * @author kuroy
 */
public class ControladorCliente {

    private Vista vista;

    public ControladorCliente(Vista vista) {
        this.vista = vista;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == vista.frmCliente.btnBuscar) {
            try {
                Integer cedula = Integer.parseInt(vista.frmCliente.txtCedula.getText());
                Responsable responsable = new Responsable();
                responsable.setCedula(cedula);
                responsable = ResponsableDAO.buscar(responsable, Controlador.getConnection());
                this.cargarResponsable(responsable);
                this.mostrarMensajes("Cliente encontrado exitosamente", Boolean.TRUE);
            } catch (NumberFormatException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            } catch (SQLException err) {
                this.mostrarMensajes("Error de conexion a la base de datos", Boolean.FALSE);
            } catch (NullPointerException err) {
                this.mostrarMensajes("Cliente no registrado", Boolean.FALSE);
            }
        }

        if (e.getSource()== vista.frmCliente.btnModificar && e.getActionCommand().equals(vista.frmCliente.MODIFICAR)) {
            if (vista.frmCliente.puedeModificar()) {
                vista.frmCliente.btnModificar.setText(vista.frmCliente.GUARDAR_CAMBIOS);
                vista.frmCliente.deshabilitar();
            } else {
                this.mostrarMensajes("No se ha buscado ningun cliente", Boolean.TRUE);
            }
        }

        if (e.getSource()== vista.frmCliente.btnModificar && e.getActionCommand().equals(vista.frmCliente.GUARDAR_CAMBIOS)) {
            try {
                Integer cedula = Integer.parseInt(vista.frmCliente.txtCedula.getText());
                Responsable responsable = this.getResponsable();
                ResponsableDAO.actualizar(responsable, cedula, Controlador.getConnection());
                vista.frmCliente.btnModificar.setText(vista.frmCliente.MODIFICAR);
                vista.frmCliente.clean();
                this.mostrarMensajes("Cliente actualizado exitosamente", Boolean.TRUE);
            } catch (NumberFormatException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            } catch (SQLException err) {
                this.mostrarMensajes("Numero de cedula invalido, ya se encuentra un cliente registrado con este numero cedula", Boolean.FALSE);
            }
        }

        if (e.getSource() == vista.frmCliente.btnRegis) {
            try {
                ResponsableDAO.guardar(this.getResponsable(), Controlador.getConnection());
                vista.frmCliente.clean();
                this.mostrarMensajes("Cliente registrado exitosamente", Boolean.TRUE);
            } catch (NumberFormatException er) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            } catch (SQLException er) {
                this.mostrarMensajes("Numero de cedula invalido, ya se encuentra un cliente registrado con este numero cedula", Boolean.FALSE);
            }
        }

        if (e.getSource() == vista.frmCliente.btnVolver) {
            vista.frmCliente.clean();
            vista.cambiarPanel(vista.frmCliente, vista.frmInicio);
        }

        if (e.getSource() == vista.frmCliente.btnClean) {
            vista.frmCliente.clean();
        }
    }

    private void cargarResponsable(Responsable responsable) {
        vista.frmCliente.txtCed.setText("" + responsable.getCedula());
        vista.frmCliente.txtNom.setText(responsable.getNombre());
        vista.frmCliente.txtApe.setText(responsable.getApellidos());
        vista.frmCliente.txtEmail.setText(responsable.getE_mail());
        vista.frmCliente.txtTel.setText(responsable.getTelefono());
    }

    private Responsable getResponsable() throws NumberFormatException {
        String nombre = vista.frmCliente.txtNom.getText();
        String apellido = vista.frmCliente.txtApe.getText();
        Integer cedula = Integer.parseInt(vista.frmCliente.txtCed.getText());
        String telefono = vista.frmCliente.txtTel.getText();
        String email = vista.frmCliente.txtEmail.getText();

        return new Responsable(nombre, apellido, cedula, telefono, email);
    }

    public void mostrarMensajes(String mensaje, Boolean x) {
        String titulo = x ? "Operacion exitosa!" : "Operacion fallida!";
        Integer tipo = x ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(vista, mensaje, titulo, tipo);
    }
}
