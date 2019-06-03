/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
                    if(!vista.frmEvento.addActividad(this.getActividad())){
                        //Mostrar mensaje
                    }
                }else{
                    if(!vista.frmModEvento.addActividadesTable(this.getActividad())){
                        //Mostrar mensaje
                    }                    
                }
                vista.frmAddActividad.clearActividad();
            }catch(NumberFormatException er){
                //this.mostrarMensaje();
                System.out.println(er.getMessage());
            }catch(NullPointerException er){
                //this.mostrarMensaje();
                System.out.println(er.getMessage());
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

    private modelo.Actividad getActividad() {
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
        return new modelo.Actividad(id, nombre, descripcion, new modelo.Hora(hora, minuto, 0));
    }
    
}
