/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import java.sql.SQLException;
import modelo.Evento;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.Actividad;
import modelo.Articulo;
import modelo.Responsable;
import modeloDAO.ArticuloAdminDAO;
import modelo.TipoArticulo;

/**
 *
 * @author kuroy
 */
public class formModEvento extends javax.swing.JPanel {
    public DefaultTableModel tableModelAct;
    public DefaultTableModel tableModelArt;
    public Evento eventoActual;
    public ArrayList<modelo.Articulo> articulos;
    public ArrayList<modelo.Actividad> actividades;
    /**
     * Creates new form formModEvento2
     */
    public formModEvento() {
        initComponents();
        this.cmbMontaje.addItem("");
        this.eventoActual = new modelo.Evento();
        this.articulos = new ArrayList<>();
        this.actividades = new ArrayList<>();
        this.tableModelAct = (DefaultTableModel)this.tableActividades.getModel();
        this.tableModelArt = (DefaultTableModel)this.tableArticulo.getModel();
    }

    public void initListener(java.awt.event.ActionListener e, javax.swing.event.TableModelListener t){
        this.tableModelArt.addTableModelListener(t);
        this.tableModelAct.addTableModelListener(t);
        this.cmbMontaje.addActionListener(e);
        this.btnAddAct.addActionListener(e);
        this.btnAddArt.addActionListener(e);
        this.btnBus.addActionListener(e);
        this.btnDelAct.addActionListener(e);
        this.btnDelArt.addActionListener(e);
        this.btnCancelar.addActionListener(e);
        this.btnGuardar.addActionListener(e);
        this.btnVolver.addActionListener(e);
    }
    
    public void clear(){
        this.tableModelAct.setRowCount(0);
        this.tableModelArt.setRowCount(0);
        this.articulos.clear();
        this.actividades.clear();
        this.eventoActual = new modelo.Evento();
        this.txtAbonar.setText("");
        this.txtDoc.setText("");
        this.txtHoraInicio.setText("");
        this.lblMontoAbonado.setText("");
        this.lblPrecioTotal.setText("");
        this.txtAbonar.setText("");
        this.txtEstado.setText("");
        this.txtFecha.setText("");
        this.txtDir.setText("");
        this.lblMontoFalt.setText("");
        this.cmbMontaje.setSelectedIndex(0);
        this.lblPrecioMontaje.setText("");
        this.txtDoc.setEditable(Boolean.TRUE);
    }
    
    private void removeItems(){
        for(Integer i=this.cmbMontaje.getItemCount()-1; i>0; i--){
            this.cmbMontaje.removeItemAt(i);
        }
        this.resetTablas();
    }
    
    //Base de datos
    private void addAllArticulo(ArrayList<Articulo> articulos){
        this.articulos = articulos;
        for(Articulo articulo : articulos){
            if(articulo.getTipo().equals(TipoArticulo.MONTAJE)){
                this.cmbMontaje.setSelectedIndex(this.index(articulo.getNombre()));
                this.lblPrecioMontaje.setText(""+articulo.getCosto());
            }else{
                Integer precio = this.getPrecio(articulo.getNombre());
                articulo.setCosto(precio);
                Object data[] = {articulo.getNombre(), articulo.getCantidad(), articulo.getCosto()};
                this.tableModelArt.addRow(data);
            }
        }
    }
    
    private void addAllActividad(ArrayList<Actividad> actividades){
        this.actividades = actividades;
        for(Actividad actividad : actividades){
            Object data[] = {actividad.getNombre(), actividad.getHorario().toString(), actividad.getDescripcion()};
            this.tableModelAct.addRow(data);
        }
    }
    //
    
    //frmAddArticulo
    public void addArticulosTabla(Object obj){
        ArrayList<Articulo> articulosNuevo = (ArrayList<Articulo>)obj;
        for(Articulo articulo : articulosNuevo){
            if(!this.articuloExistenteTabla(articulo)){
                this.articulos.add(articulo);
                Object data[] = {articulo.getNombre(), articulo.getCantidad(), articulo.getCosto()};
                this.tableModelArt.addRow(data);
                this.precioTotal();
            }           
        }
    }
    
    public Boolean addActividadesTable(Actividad actividad){
        if(!actividadExistente(actividad)){
            this.actividades.add(actividad);
            Object data[] = {actividad.getNombre(), actividad.getHorario().toString(), actividad.getDescripcion()};
            this.tableModelAct.addRow(data);
            return true;
        }
        
        return false;
    }
    
    private Boolean articuloExistenteTabla(Articulo articulo){
        Integer row = this.tableModelArt.getRowCount();
        for(Integer i=0; i<row; i++){
            String nombre = this.tableModelArt.getValueAt(i, 0).toString();
            if(nombre.equals(articulo.getNombre())){            
                Integer value = Integer.parseInt(this.tableModelArt.getValueAt(i, 1).toString());              
                this.articulos.get(i).aumentarCantidad(value);
                articulo.aumentarCantidad(value);
                this.precioTotal();                
                this.tableModelArt.setValueAt(articulo.getCantidad(), i, 1);
                return true;
            }
        }
        
        return false;
    }
    
    public Boolean actividadExistente(Actividad actividad){
        for(Actividad act : this.actividades){
            if(act.equals(actividad)){
                return true;
            }
        }
        
        return false;
    }
    //
    
    //Base de datos para montaje
    public void addArticulo(Articulo articulo){
        if(articulo.getTipo().equals(TipoArticulo.MONTAJE)){
            this.cmbMontaje.addItem(articulo.getNombre());
        }
    }
    
    public void cargarItemOpciones(){
        try{
            this.removeItems();
            ArrayList<Articulo> articulosOpciones = ArticuloAdminDAO.getAll(Controlador.getConnection());
            for(modelo.Articulo articulo : articulosOpciones){
                this.addArticulo(articulo);
            }
        }catch(SQLException e){
        }
    }
    //
    
    private Integer getPrecio(String name){
        Articulo articulo = new Articulo();
        articulo.setNombre(name);
        try{
            articulo = modeloDAO.ArticuloAdminDAO.buscarPorNombre(articulo, Controlador.getConnection());
        }catch(SQLException e){
        }
        return articulo!=null ? articulo.getCosto() : -1;
    }
    
    private Integer index(String name){
        for(Integer i=1 ; i<this.cmbMontaje.getItemCount(); i++){
            if(name.equals(this.cmbMontaje.getItemAt(i))){
                return i;
            }
        }
        return -1;
    }
    
    private void resetTablas(){
        this.tableModelAct.setRowCount(0);
        this.tableModelArt.setRowCount(0);
    }
    
    public void cargarEventoBaseDeDatos() throws SQLException, NumberFormatException, NullPointerException {
        Responsable responsable = new Responsable();
        responsable.setCedula(Integer.parseInt(this.txtDoc.getText()));
        
        this.eventoActual.setResponsable(responsable);
        this.eventoActual = modeloDAO.EventoDAO.buscar(Controlador.getConnection(), this.eventoActual);
        
        this.txtFecha.setText(this.eventoActual.getFecha_celebracion().toString());
        this.txtHoraInicio.setText(this.eventoActual.getFecha_celebracion().getHora().toString());
        this.lblMontoAbonado.setText(""+this.eventoActual.getMonto_abonado());
        this.lblPrecioTotal.setText(""+this.eventoActual.getMonto_total());
        this.lblMontoFalt.setText(" $   "+(this.eventoActual.getMonto_total()-this.eventoActual.getMonto_abonado()));
        if(this.eventoActual.getEstado().equals(modelo.Estado.ACTIVO)){
            this.btnCancelar.setText("Cancelar evento");
        }else{
            this.btnCancelar.setText("Activar evento");
        }
        this.txtEstado.setText(""+this.eventoActual.getEstado().toString());
        this.txtDir.setText(eventoActual.getDireccion_evento());
    }
    
    public void cargarTablaBaseDeDatos() throws SQLException, NumberFormatException, NullPointerException {
        this.resetTablas();
        
        Articulo articuloCliente = new modelo.Articulo();
        Integer cedula = Integer.parseInt(this.txtDoc.getText());
        articuloCliente.setId(cedula);
        this.addAllArticulo(modeloDAO.ArticuloClienteDAO.buscar(articuloCliente, Controlador.getConnection()));
        
        Actividad actividad = new Actividad();
        actividad.setId(cedula);
        this.addAllActividad(modeloDAO.ActividadDAO.buscar(actividad, Controlador.getConnection()));
        this.precioTotal();
    }
    
    public void cargarTablaActuales() throws SQLException, NumberFormatException, NullPointerException {
        this.resetTablas();
        
        for(Articulo articulo : this.articulos){
            if(articulo.getTipo().equals(TipoArticulo.MONTAJE)){
                this.cmbMontaje.setSelectedItem(this.index(articulo.getNombre()));
                this.lblPrecioMontaje.setText(""+articulo.getCosto());
            }else{
                Object data[] = {articulo.getNombre(), articulo.getCantidad(), articulo.getCosto()};
                this.tableModelArt.addRow(data);
            }
        }
        
        for(Actividad actividad : this.actividades){
            Object data[] = {actividad.getNombre(), actividad.getHorario().toString(), actividad.getDescripcion()};
            this.tableModelAct.addRow(data);
        }
    }
    
    public void actualizarArticulo(Integer row, Integer value){
        this.articulos.get(row).setCantidad(value);
    }
    
    public void actualizarActividad(Integer row, Integer column, String value){
        if(column==0){
            this.actividades.get(row).setNombre(value);
        }else{
            this.actividades.get(row).setDescripcion(value);
        }
    }
    
    public Integer precioTotal(){
        Integer precioArticulos = 0;
        for(Articulo articulo : this.articulos){
            precioArticulos += articulo.getCosto();
        }
        this.lblPrecioTotal.setText(""+precioArticulos);
        this.lblMontoFalt.setText(" $   "+(precioArticulos-this.eventoActual.getMonto_abonado()));
        if((precioArticulos-this.eventoActual.getMonto_abonado())<=0){
            this.lblMontoFalt.setText(" $   0");
            this.lblAbonar.setText("Cambio");
            this.txtAbonar.setText(""+(precioArticulos-this.eventoActual.getMonto_abonado())*-1);
        }else{
            this.lblAbonar.setText("Abonar");
            this.txtAbonar.setText("");
        }
        return precioArticulos;
    }
    
    public void cambiarMontaje(Articulo articuloMontaje){
        for(Articulo articulo : this.articulos){
            if(articulo.getTipo().equals(articuloMontaje.getTipo())){
                articulo.setNombre(articuloMontaje.getNombre());
                articulo.setCosto(articuloMontaje.getCosto());
                return;
            }
        }
        
        this.articulos.add(articuloMontaje);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableArticulo = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtDoc = new javax.swing.JTextField();
        btnBus = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableActividades = new javax.swing.JTable();
        txtHoraInicio = new javax.swing.JTextField();
        btnDelArt = new javax.swing.JButton();
        btnAddArt = new javax.swing.JButton();
        btnAddAct = new javax.swing.JButton();
        btnDelAct = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtAbonar = new javax.swing.JTextField();
        lblAbonar = new javax.swing.JLabel();
        lblMontoFalt = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPrecioMontaje = new javax.swing.JLabel();
        cmbMontaje = new javax.swing.JComboBox<>();
        btnVolver = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDir = new javax.swing.JTextField();
        lblPrecioTotal = new javax.swing.JLabel();
        lblMontoAbonado = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(738, 714));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Articulos"));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        tableArticulo.setBackground(new java.awt.Color(0, 0, 0));
        tableArticulo.setForeground(new java.awt.Color(255, 255, 255));
        tableArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cantidad", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableArticulo);
        if (tableArticulo.getColumnModel().getColumnCount() > 0) {
            tableArticulo.getColumnModel().getColumn(0).setResizable(false);
            tableArticulo.getColumnModel().getColumn(1).setResizable(false);
            tableArticulo.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Documento");

        txtDoc.setBackground(new java.awt.Color(0, 0, 51));
        txtDoc.setForeground(new java.awt.Color(255, 255, 255));

        btnBus.setBackground(new java.awt.Color(255, 255, 255));
        btnBus.setForeground(new java.awt.Color(0, 0, 0));
        btnBus.setText("Buscar");

        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Monto abonado");

        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Hora de inicio");

        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Fecha de celebracion");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Actividades"));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        tableActividades.setBackground(new java.awt.Color(0, 0, 0));
        tableActividades.setForeground(new java.awt.Color(255, 255, 255));
        tableActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Hora inicio", "Descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableActividades);
        if (tableActividades.getColumnModel().getColumnCount() > 0) {
            tableActividades.getColumnModel().getColumn(0).setResizable(false);
            tableActividades.getColumnModel().getColumn(1).setResizable(false);
            tableActividades.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtHoraInicio.setBackground(new java.awt.Color(0, 0, 51));
        txtHoraInicio.setForeground(new java.awt.Color(255, 255, 255));

        btnDelArt.setBackground(new java.awt.Color(255, 255, 255));
        btnDelArt.setForeground(new java.awt.Color(0, 0, 0));
        btnDelArt.setText("Eliminar");

        btnAddArt.setBackground(new java.awt.Color(255, 255, 255));
        btnAddArt.setForeground(new java.awt.Color(0, 0, 0));
        btnAddArt.setText("Añadir");

        btnAddAct.setBackground(new java.awt.Color(255, 255, 255));
        btnAddAct.setForeground(new java.awt.Color(0, 0, 0));
        btnAddAct.setText("Añadir");

        btnDelAct.setBackground(new java.awt.Color(255, 255, 255));
        btnDelAct.setForeground(new java.awt.Color(0, 0, 0));
        btnDelAct.setText("Eliminar");

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setText("Guardar cambios");

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar  evento");

        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("Monto faltante");

        txtAbonar.setBackground(new java.awt.Color(0, 0, 51));
        txtAbonar.setForeground(new java.awt.Color(255, 255, 255));

        lblAbonar.setForeground(java.awt.Color.white);
        lblAbonar.setText("Abonar");

        lblMontoFalt.setForeground(java.awt.Color.white);
        lblMontoFalt.setText(" $");

        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("Precio total");

        txtFecha.setBackground(new java.awt.Color(0, 0, 51));
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cambiar montaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Montaje");

        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Precio         $ ");

        lblPrecioMontaje.setForeground(java.awt.Color.white);

        cmbMontaje.setBackground(new java.awt.Color(255, 255, 255));
        cmbMontaje.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbMontaje, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPrecioMontaje, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMontaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioMontaje, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnVolver.setBackground(new java.awt.Color(255, 255, 255));
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setText("Volver");

        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("Estado");

        txtEstado.setEditable(false);
        txtEstado.setBackground(new java.awt.Color(0, 0, 51));
        txtEstado.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("Direccion");

        txtDir.setBackground(new java.awt.Color(0, 0, 51));
        txtDir.setForeground(new java.awt.Color(255, 255, 255));

        lblPrecioTotal.setForeground(new java.awt.Color(255, 255, 255));

        lblMontoAbonado.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("$");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("$");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAbonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHoraInicio)
                                    .addComponent(txtAbonar)
                                    .addComponent(lblMontoFalt, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                    .addComponent(txtFecha)
                                    .addComponent(txtDir)
                                    .addComponent(txtEstado)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPrecioTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblMontoAbonado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBus))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelArt, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(btnAddArt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVolver)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBus)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnDelArt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddArt)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnDelAct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddAct)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(txtFecha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(txtHoraInicio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(txtDir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPrecioTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMontoAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMontoFalt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAbonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnVolver))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAddAct;
    public javax.swing.JButton btnAddArt;
    public javax.swing.JButton btnBus;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnDelAct;
    public javax.swing.JButton btnDelArt;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnVolver;
    public javax.swing.JComboBox<String> cmbMontaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lblAbonar;
    public javax.swing.JLabel lblMontoAbonado;
    public javax.swing.JLabel lblMontoFalt;
    public javax.swing.JLabel lblPrecioMontaje;
    public javax.swing.JLabel lblPrecioTotal;
    public javax.swing.JTable tableActividades;
    public javax.swing.JTable tableArticulo;
    public javax.swing.JTextField txtAbonar;
    public javax.swing.JTextField txtDir;
    public javax.swing.JTextField txtDoc;
    public javax.swing.JTextField txtEstado;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtHoraInicio;
    // End of variables declaration//GEN-END:variables
}
