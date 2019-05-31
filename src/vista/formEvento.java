/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.SQLException;
import servicio.Conexion;
import util.TipoArticulo;

/**
 *
 * @author kuroy
 */
public class formEvento extends javax.swing.JPanel {

    private Integer precioTotal;

    /**
     * Creates new form formEvento
     */
    public formEvento() {
        initComponents();
        this.precioTotal = 0;
        this.cmbMontaje.addItem("");
        this.habilitar(Boolean.FALSE);
    }

    public void initListeners(java.awt.event.ActionListener e) {
        this.cmbMontaje.addActionListener(e);
        this.btnAddActividad.addActionListener(e);
        this.btnCancelar.addActionListener(e);
        this.btnConfirmar.addActionListener(e);
        this.btnBus.addActionListener(e);
        this.btnCalcular.addActionListener(e);
        this.btnVolver.addActionListener(e);
        this.btnAddArticulo.addActionListener(e);
    }

    public void precioArticulos(java.util.ArrayList<modelo.ArticuloCliente> articulos) {
        for (modelo.ArticuloCliente articulo : articulos) {
            precioTotal += articulo.getCosto();
            articulo.setId(Integer.parseInt(txtDoc.getText()));
        }

    }

    public void calcularPrecio() {
        Integer x = Integer.parseInt(!lblPrecioMontaje.getText().isEmpty() ? lblPrecioMontaje.getText() : "0");
        this.txtPrecioTotal.setText("$ " + (x + precioTotal));
    }

    public void habilitar(Boolean x) {
        this.txtDoc.setEditable(!x);
        this.panelMontaje.setVisible(x);
        this.btnAddArticulo.setVisible(x);
        this.panelConfirm.setVisible(x);
        this.panelActiv.setVisible(x);
    }

    public void addArticulo(modelo.ArticuloAdmin articulo) {
        if (articulo.getTipo().equals(TipoArticulo.MONTAJE)) {
            this.cmbMontaje.addItem(articulo.getNombre());
        }
    }

    private void removeItems() {
        for (Integer i = this.cmbMontaje.getItemCount() - 1; i > 0; i--) {
            this.cmbMontaje.removeItemAt(i);
        }
    }

    public void cargar() {
        try {
            this.removeItems();
            java.util.ArrayList<modelo.ArticuloAdmin> articulos = modeloDAO.ArticuloAdminDAO.getAll(Conexion.getConnection());
            for (modelo.ArticuloAdmin articulo : articulos) {
                this.addArticulo(articulo);
            }
        } catch (SQLException e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMontaje = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbMontaje = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        lblPrecioMontaje = new javax.swing.JLabel();
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
        panelConfirm = new javax.swing.JPanel();
        txtPrecioTotal = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        btnCalcular = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtHoraEvt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtMinEvt = new javax.swing.JTextField();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        btnAddArticulo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtDoc = new javax.swing.JTextField();
        btnBus = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(402, 692));

        panelMontaje.setBackground(new java.awt.Color(51, 51, 51));
        panelMontaje.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo De Montaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        panelMontaje.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tipo De Montaje");

        cmbMontaje.setBackground(new java.awt.Color(255, 255, 255));
        cmbMontaje.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Precio");

        jLabel.setForeground(new java.awt.Color(255, 255, 255));
        jLabel.setText("$");

        lblPrecioMontaje.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelMontajeLayout = new javax.swing.GroupLayout(panelMontaje);
        panelMontaje.setLayout(panelMontajeLayout);
        panelMontajeLayout.setHorizontalGroup(
            panelMontajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMontajeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMontajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelMontajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbMontaje, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMontajeLayout.createSequentialGroup()
                        .addComponent(jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrecioMontaje, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelMontajeLayout.setVerticalGroup(
            panelMontajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMontajeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMontajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbMontaje)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMontajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPrecioMontaje, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelActiv.setBackground(new java.awt.Color(51, 51, 51));
        panelActiv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cronograma de actividades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        panelActiv.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nombre de la actividad");

        txtNomAct.setBackground(new java.awt.Color(0, 0, 51));
        txtNomAct.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNomAct.setForeground(new java.awt.Color(255, 255, 255));
        txtNomAct.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

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
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText(":");

        javax.swing.GroupLayout panelActivLayout = new javax.swing.GroupLayout(panelActiv);
        panelActiv.setLayout(panelActivLayout);
        panelActivLayout.setHorizontalGroup(
            panelActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActivLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(txtNomAct, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(panelActivLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(btnAddActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btnAddActividad)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        panelConfirm.setBackground(new java.awt.Color(51, 51, 51));
        panelConfirm.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Confirmación Del Evento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        panelConfirm.setForeground(new java.awt.Color(255, 255, 255));

        txtPrecioTotal.setEditable(false);
        txtPrecioTotal.setBackground(new java.awt.Color(0, 0, 51));
        txtPrecioTotal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPrecioTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecioTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar Evento");

        btnConfirmar.setBackground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setForeground(new java.awt.Color(0, 0, 0));
        btnConfirmar.setText("Confirmar Evento");

        btnCalcular.setBackground(new java.awt.Color(255, 255, 255));
        btnCalcular.setForeground(new java.awt.Color(0, 0, 0));
        btnCalcular.setText("Calcular precio total");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha de celebracion");

        jTextField1.setBackground(new java.awt.Color(0, 0, 51));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Monto inicial");

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Hora inicio");

        txtHoraEvt.setBackground(new java.awt.Color(0, 0, 51));
        txtHoraEvt.setForeground(new java.awt.Color(255, 255, 255));

        jLabel21.setBackground(new java.awt.Color(0, 0, 51));
        jLabel21.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText(":");

        txtMinEvt.setBackground(new java.awt.Color(0, 0, 51));
        txtMinEvt.setForeground(new java.awt.Color(255, 255, 255));

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelConfirmLayout = new javax.swing.GroupLayout(panelConfirm);
        panelConfirm.setLayout(panelConfirmLayout);
        panelConfirmLayout.setHorizontalGroup(
            panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfirmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfirmLayout.createSequentialGroup()
                        .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfirmLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoraEvt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtMinEvt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))
                            .addGroup(panelConfirmLayout.createSequentialGroup()
                                .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCalcular))
                                .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelConfirmLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(txtPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(panelConfirmLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(panelConfirmLayout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1))
                    .addGroup(panelConfirmLayout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmar)))
                .addContainerGap())
        );
        panelConfirmLayout.setVerticalGroup(
            panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfirmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcular)
                    .addComponent(txtPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHoraEvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinEvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnConfirmar))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnAddArticulo.setBackground(new java.awt.Color(255, 255, 255));
        btnAddArticulo.setForeground(new java.awt.Color(0, 0, 0));
        btnAddArticulo.setText("Añadir articulos");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Documento");

        txtDoc.setBackground(new java.awt.Color(0, 0, 51));
        txtDoc.setForeground(new java.awt.Color(255, 255, 255));

        btnBus.setBackground(new java.awt.Color(255, 255, 255));
        btnBus.setForeground(new java.awt.Color(0, 0, 0));
        btnBus.setText("Buscar");

        btnVolver.setBackground(new java.awt.Color(255, 255, 255));
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setText("Volver");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelMontaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelActiv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBus, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(btnAddArticulo)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(btnBus)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMontaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(btnAddArticulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelActiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAddActividad;
    public javax.swing.JButton btnAddArticulo;
    public javax.swing.JButton btnBus;
    public javax.swing.JButton btnCalcular;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnConfirmar;
    public javax.swing.JButton btnVolver;
    public javax.swing.JComboBox<String> cmbMontaje;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    public javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JLabel lblPrecioMontaje;
    private javax.swing.JPanel panelActiv;
    private javax.swing.JPanel panelConfirm;
    private javax.swing.JPanel panelMontaje;
    public javax.swing.JTextArea txtDescrip;
    public javax.swing.JTextField txtDoc;
    public javax.swing.JTextField txtHoraAct;
    public javax.swing.JTextField txtHoraEvt;
    public javax.swing.JTextField txtMinAct;
    private javax.swing.JTextField txtMinEvt;
    public javax.swing.JTextField txtNomAct;
    public javax.swing.JTextField txtPrecioTotal;
    // End of variables declaration//GEN-END:variables
}
