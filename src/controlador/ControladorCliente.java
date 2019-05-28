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
import servicio.Conexion;
import vista.Vista;
/**
 *
 * @author kuroy
 */
public class ControladorCliente{
    private Vista vista;

    public ControladorCliente(Vista vista) {
        this.vista = vista;
        this.vista.frmCliente.deshabilitar();
    }
        
    public void actionPerformed(java.awt.event.ActionEvent e){
        if(e.getSource() == vista.frmCliente.btnBuscar){
            try{
                Integer cedula = Integer.parseInt(vista.frmCliente.txtCedula.getText());
                Responsable responsable = new Responsable();
                responsable.setCedula(cedula);
                responsable = ResponsableDAO.buscar(responsable, Conexion.getConnection());
                this.cargarResponsable(responsable);
            } catch (NumberFormatException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            } catch (SQLException err){
                this.mostrarMensajes("Error de conexion a la base de datos", Boolean.FALSE);
            } catch(NullPointerException err){
                this.mostrarMensajes("Cliente no registrado", Boolean.FALSE);
            }
        }
        
        if(e.getActionCommand().equals(vista.frmCliente.btnModificar.getText())){
            vista.frmCliente.btnModificar.setText(vista.frmCliente.GUARDAR_CAMBIOS);
            vista.frmCliente.habilitar();           
        }
        
        if(e.getActionCommand().equals(vista.frmCliente.btnModificar.getText())){
            vista.frmCliente.btnModificar.setText(vista.frmCliente.MODIFICAR);
            try{
                Integer cedula = Integer.parseInt(vista.frmCliente.txtCedula.getText());
                Responsable responsable = this.getResponsable();
                ResponsableDAO.actualizar(responsable, cedula, Conexion.getConnection());
                vista.frmCliente.deshabilitar();
                vista.frmCliente.clean();
                this.mostrarMensajes("Cliente actualizado exitosamente", Boolean.TRUE);
            } catch (NumberFormatException err) {
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            } catch (SQLException err){
                this.mostrarMensajes("Numero de cedula invalido, ya se encuentra un cliente registrado con este numero cedula", Boolean.FALSE);
            }          
        }
        
        if(e.getSource() == vista.frmCliente.btnVolver){
            vista.frmCliente.clean();
            vista.cambiarPanel(vista.frmCliente, vista.frmInicio);
        }
        
        if(e.getSource() == vista.frmCliente.btnClean){
            vista.frmCliente.clean();
        }
    }
    
    private void cargarResponsable(Responsable responsable){
        vista.frmCliente.txtCed.setText(""+responsable.getCedula());
        vista.frmCliente.txtNom.setText(responsable.getNombre());
        vista.frmCliente.txtApe.setText(responsable.getApellidos());
        vista.frmCliente.txtEmail.setText(responsable.getE_mail());
        vista.frmCliente.txtTel.setText(responsable.getTelefono());
    }
    
    private Responsable getResponsable(){
        String nombre = vista.frmCliente.txtNom.getText();
        String apellido = vista.frmCliente.txtApe.getText();
        Integer cedula = Integer.parseInt(vista.frmCliente.txtCed.getText());
        String telefono = vista.frmCliente.txtTel.getText();
        String email = vista.frmCliente.txtEmail.getText();
        
        return new Responsable(nombre, apellido, cedula, telefono, email);
    }
    
    public void mostrarMensajes(String mensaje, Boolean x){
        String titulo = x ? "Operacion exitosa!" : "Operacion fallida!";
        Integer tipo = x ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;       
        JOptionPane.showMessageDialog(vista, mensaje, titulo, tipo);
    }
}
