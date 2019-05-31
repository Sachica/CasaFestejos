/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vista.*;
/**
 *
 * @author kuroy
 */
public class Controlador implements ActionListener, MouseListener {
    private Vista vista;
    private ControladorRegistro controladorRegistro;
    private ControladorInicio controladorInicio;
    private ControladorCliente controladorCliente;
    private ControladorEvento controladorEvento;
    private ControladorArticulo controladorArticulo;
    private ControladorAddArticulo controladorAddArticulo;
    private ControladorModEvento controladorModEvento;
    
    public Controlador(){
        vista = new Vista();
        vista.initListeners(this, this);
        controladorRegistro = new ControladorRegistro(vista);
        controladorInicio = new ControladorInicio(vista);
        controladorCliente = new ControladorCliente(vista);
        controladorEvento = new ControladorEvento(vista);
        controladorArticulo = new ControladorArticulo(vista);  
        controladorAddArticulo = new ControladorAddArticulo(vista);
        controladorModEvento = new ControladorModEvento(vista);
        this.initComponents();
    }
    
    private void initComponents(){
        vista.cambiarPanel(vista.frmRegistro, vista.frmInicio);
        vista.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        controladorInicio.actionPerformed(e);
        controladorRegistro.actionPerformed(e);
        controladorCliente.actionPerformed(e);
        controladorArticulo.actionPerformed(e);
        controladorAddArticulo.actionPerformed(e);
        controladorEvento.actionPerformed(e);
        controladorModEvento.actionPerformed(e);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        controladorArticulo.mouseClicked(me);
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    public static void main(String[] args){
//        modelo.ArticuloCliente a = new modelo.ArticuloCliente(2, util.TipoArticulo.MONTAJE, "Cumpleaños", 1, 10000);
//        try{
//            modeloDAO.ArticuloClienteDAO.guardar(a, servicio.Conexion.getConnection());
//        }catch(java.sql.SQLException e){
//        }
        Controlador m = new Controlador();       
    }

}
