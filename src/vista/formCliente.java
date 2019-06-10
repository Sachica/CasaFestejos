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
public class formCliente extends javax.swing.JPanel {
    public static final String MODIFICAR = "Modificar";
    public static final String GUARDAR_CAMBIOS = "Guardar cambios";
    /**
     * Creates new form formCliente
     */
    public formCliente() {
        initComponents();
    }

    public void initListeners(java.awt.event.ActionListener e){
        this.btnBuscar.addActionListener(e);
        this.btnModificar.addActionListener(e);
        this.btnVolver.addActionListener(e);
        this.btnClean.addActionListener(e);
        this.btnRegis.addActionListener(e);
    }
    
    public Boolean puedeModificar(){
        return !this.txtCedula.getText().isEmpty();
    }
    
    public void deshabilitar(){
        this.txtCedula.setEditable(false);
    }
    
    public void habilitar(){
        this.txtCedula.setEditable(true);

    }
    
    public void clean(){
        this.txtCedula.setText("");
        this.txtCed.setText("");
        this.txtEmail.setText("");
        this.txtApe.setText("");
        this.txtNom.setText("");
        this.txtTel.setText("");
        this.txtCedula.setEditable(true);
        this.btnModificar.setText(formCliente.MODIFICAR);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        txtApe = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        txtCed = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtCedula = new javax.swing.JTextField();
        btnClean = new javax.swing.JButton();
        btnRegis = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificicaciones del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        setMaximumSize(new java.awt.Dimension(440, 300));
        setMinimumSize(new java.awt.Dimension(412, 300));
        setPreferredSize(new java.awt.Dimension(430, 300));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cedula");

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido");

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Telefono");

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("E-mail");

        txtEmail.setBackground(new java.awt.Color(0, 0, 51));
        txtEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        txtEmail.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtEmail.setSelectedTextColor(new java.awt.Color(51, 51, 255));
        txtEmail.setSelectionColor(new java.awt.Color(255, 255, 255));

        txtTel.setBackground(new java.awt.Color(0, 0, 51));
        txtTel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTel.setForeground(new java.awt.Color(255, 255, 255));
        txtTel.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTel.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTel.setSelectedTextColor(new java.awt.Color(51, 51, 255));
        txtTel.setSelectionColor(new java.awt.Color(255, 255, 255));

        txtApe.setBackground(new java.awt.Color(0, 0, 51));
        txtApe.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtApe.setForeground(new java.awt.Color(255, 255, 255));
        txtApe.setCaretColor(new java.awt.Color(255, 255, 255));
        txtApe.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtApe.setSelectedTextColor(new java.awt.Color(51, 51, 255));
        txtApe.setSelectionColor(new java.awt.Color(255, 255, 255));

        txtNom.setBackground(new java.awt.Color(0, 0, 51));
        txtNom.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNom.setForeground(new java.awt.Color(255, 255, 255));
        txtNom.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNom.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNom.setSelectedTextColor(new java.awt.Color(51, 51, 255));
        txtNom.setSelectionColor(new java.awt.Color(255, 255, 255));

        txtCed.setBackground(new java.awt.Color(0, 0, 51));
        txtCed.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCed.setForeground(new java.awt.Color(255, 255, 255));
        txtCed.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCed.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtCed.setSelectedTextColor(new java.awt.Color(51, 51, 255));
        txtCed.setSelectionColor(new java.awt.Color(255, 255, 255));

        btnModificar.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setFocusable(false);

        btnVolver.setBackground(new java.awt.Color(255, 255, 255));
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setText("Volver");
        btnVolver.setFocusable(false);

        btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscar.setText("Buscar");
        btnBuscar.setFocusable(false);

        txtCedula.setBackground(new java.awt.Color(0, 0, 51));
        txtCedula.setForeground(new java.awt.Color(255, 255, 255));
        txtCedula.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCedula.setSelectionColor(new java.awt.Color(255, 255, 255));

        btnClean.setBackground(new java.awt.Color(255, 255, 255));
        btnClean.setForeground(new java.awt.Color(0, 0, 0));
        btnClean.setText("Limpiar");
        btnClean.setFocusable(false);

        btnRegis.setBackground(new java.awt.Color(255, 255, 255));
        btnRegis.setForeground(new java.awt.Color(0, 0, 0));
        btnRegis.setText("Registrar");
        btnRegis.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApe, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCed)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClean)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtCed, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApe, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnVolver)
                    .addComponent(btnClean)
                    .addComponent(btnRegis))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnClean;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegis;
    public javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JTextField txtApe;
    public javax.swing.JTextField txtCed;
    public javax.swing.JTextField txtCedula;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtNom;
    public javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
