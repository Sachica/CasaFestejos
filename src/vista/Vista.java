/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author kuroy
 */
public class Vista extends javax.swing.JFrame {
    public formEvento frmEvento;
    public formRegistro frmRegistro;
    public formInicio frmInicio;
    public formCliente frmCliente;
    public formArticulo frmArticulo;
    public formAddArticulo frmAddArticulo;
    public formModEvento frmModEvento;
    /**
     * Creates new form Vista
     */
    public Vista() {
        super("Casa de Festejos");
        initComponents();      
        super.setLocationRelativeTo(null);       
        frmEvento = new formEvento();
        frmRegistro = new formRegistro();
        frmInicio = new formInicio();
        frmCliente = new formCliente();
        frmArticulo = new formArticulo();
        frmAddArticulo = new formAddArticulo();
        frmModEvento = new formModEvento();
    }

    public void initListeners(java.awt.event.ActionListener e, java.awt.event.MouseListener m){
        this.frmEvento.initListeners(e);
        this.frmRegistro.initListeners(e);
        this.frmInicio.initListener(e);
        this.frmCliente.initListeners(e);
        this.frmArticulo.initListener(e, m);
        this.frmAddArticulo.initListeners(e);
        this.frmModEvento.initListener(e);
    }
    
    public void cambiarPanel(JPanel anterior, JPanel nuevo){
        Dimension s = nuevo.getMaximumSize();
        super.setSize((int)s.getWidth(), (int)s.getHeight()+40);
        super.setContentPane(nuevo);        
        super.setLocationRelativeTo(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
