/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.Actividad;
import modelo.Hora;
import vista.Vista;
/**
 *
 * @author kuroy
 */
public class ControladorAddActividad {
    private Vista vista;
    
    public ControladorAddActividad(Vista vista) {
        this.vista = vista;
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e){
        if(e.getSource() == vista.frmAddActividad.btnAddActividad){
            try{
                if(vista.frmAddActividad.getCambio()){                                   
                    if(vista.frmEvento.addActividad(this.getActividad())){
                        this.mostrarMensajes("Actividad guardada", Boolean.TRUE);
                    }else{
                        this.mostrarMensajes("Ya existe una actividad con el mismo nombre o horario guardada", Boolean.FALSE);
                    }
                }else{
                    if(!vista.frmModEvento.addActividadesTable(this.getActividad())){
                        this.mostrarMensajes("Actividad guardada", Boolean.TRUE);
                    }else{
                        this.mostrarMensajes("Ya existe una actividad con el mismo nombre o horario guardada", Boolean.FALSE);
                    }                  
                }
                vista.frmAddActividad.clearActividad();
            }catch(NumberFormatException er){
                this.mostrarMensajes("Caracteres invalidos", Boolean.FALSE);
            }
        }
        
        if(e.getSource() == vista.frmAddActividad.btnFin){
            if(vista.frmAddActividad.getCambio()){               
                    vista.cambiarPanel(vista.frmAddActividad, vista.frmEvento);
            }else{
                vista.cambiarPanel(vista.frmAddActividad, vista.frmModEvento);
                vista.frmAddActividad.setCambio(Boolean.TRUE);
            }
        }
    }

    private Actividad getActividad() {
        String nombre = vista.frmAddActividad.txtNomAct.getText();
        Integer id = 0;
        if(vista.frmAddActividad.getCambio()){
            id = Integer.parseInt(vista.frmEvento.txtDoc.getText());
        }else{
            id = Integer.parseInt(vista.frmModEvento.txtDoc.getText());
        }       
        Integer hora = Integer.parseInt(vista.frmAddActividad.txtHoraAct.getText());
        Integer minuto = Integer.parseInt(vista.frmAddActividad.txtMinAct.getText());
        String descripcion = vista.frmAddActividad.txtDescrip.getText();
        return new Actividad(id, nombre, descripcion, new Hora(hora, minuto, 0));
    }
    
    public void mostrarMensajes(String mensaje, Boolean x){
        String titulo = x ? "Operacion exitosa!" : "Operacion fallida!";
        Integer tipo = x ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;       
        JOptionPane.showMessageDialog(vista, mensaje, titulo, tipo);
    }
    
}
