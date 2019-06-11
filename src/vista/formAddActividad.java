/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author kuroy
 */
public class formAddActividad extends javax.swing.JPanel {
    private Boolean cambio;
    /**
     * Creates new form frmAddActividad
     */
    public formAddActividad() {
        initComponents();
        this.cambio = true;
    }
    
    public void initListeners(java.awt.event.ActionListener e){
        this.btnAddActividad.addActionListener(e);
        this.btnFin.addActionListener(e);
    }
    
    public void clearActividad(){
        this.txtNomAct.setText("");
        this.txtHoraAct.setText("");
        this.txtMinAct.setText("");
        this.txtDescrip.setText("");
    }

    public Boolean getCambio() {
        return cambio;
    }

    public void setCambio(Boolean cambio) {
        this.cambio = cambio;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelActiv = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtNomAct = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescrip = new javax.swing.JTextArea();
        btnAddActividad = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtHoraAct = new javax.swing.JTextField();
        txtMinAct = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnFin = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(402, 275));

        panelActiv.setBackground(new java.awt.Color(51, 51, 51));
        panelActiv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Crear actividad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        panelActiv.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nombre de la actividad");

        txtNomAct.setBackground(new java.awt.Color(0, 0, 51));
        txtNomAct.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNomAct.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Descripcion");

        txtDescrip.setBackground(new java.awt.Color(0, 0, 51));
        txtDescrip.setColumns(20);
        txtDescrip.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDescrip.setForeground(new java.awt.Color(255, 255, 255));
        txtDescrip.setLineWrap(true);
        txtDescrip.setRows(5);
        jScrollPane1.setViewportView(txtDescrip);

        btnAddActividad.setBackground(new java.awt.Color(255, 255, 255));
        btnAddActividad.setForeground(new java.awt.Color(0, 0, 0));
        btnAddActividad.setText("Añadir actividad");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Horario");

        txtHoraAct.setBackground(new java.awt.Color(0, 0, 51));
        txtHoraAct.setForeground(new java.awt.Color(255, 255, 255));

        txtMinAct.setBackground(new java.awt.Color(0, 0, 51));
        txtMinAct.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setBackground(new java.awt.Color(0, 0, 51));
        jLabel18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText(":");

        btnFin.setBackground(new java.awt.Color(255, 255, 255));
        btnFin.setForeground(new java.awt.Color(0, 0, 0));
        btnFin.setText("Finalizar");

        javax.swing.GroupLayout panelActivLayout = new javax.swing.GroupLayout(panelActiv);
        panelActiv.setLayout(panelActivLayout);
        panelActivLayout.setHorizontalGroup(
            panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActivLayout.createSequentialGroup()
                .addGroup(panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelActivLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelActivLayout.createSequentialGroup()
                                .addGroup(panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelActivLayout.createSequentialGroup()
                                        .addComponent(txtHoraAct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtMinAct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNomAct, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelActivLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnAddActividad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFin)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelActivLayout.setVerticalGroup(
            panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActivLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomAct, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoraAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddActividad)
                    .addComponent(btnFin))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelActiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelActiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAddActividad;
    public javax.swing.JButton btnFin;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelActiv;
    public javax.swing.JTextArea txtDescrip;
    public javax.swing.JTextField txtHoraAct;
    public javax.swing.JTextField txtMinAct;
    public javax.swing.JTextField txtNomAct;
    // End of variables declaration//GEN-END:variables
}