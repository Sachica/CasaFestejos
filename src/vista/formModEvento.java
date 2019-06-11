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
        this.btnDel.addActionListener(e);
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
                Object data[] = {articulo.getNombre(), articulo.getCantidad(), articulo.getCosto()*articulo.getCantidad()};
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
                Object data[] = {articulo.getNombre(), articulo.getCantidad(), articulo.getCosto()*articulo.getCantidad()};
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
        for(int i=0; i<row; i++){
            String nombre = this.tableModelArt.getValueAt(i, 0).toString();
            if(nombre.equals(articulo.getNombre())){            
                Integer value = Integer.parseInt(this.tableModelArt.getValueAt(i, 1).toString());  
                this.actualizarArticulo(nombre, value+articulo.getCantidad());
                this.tableModelArt.setValueAt(value+articulo.getCantidad(), i, 1);
                this.precioTotal();  
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
                Object data[] = {articulo.getNombre(), articulo.getCantidad(), articulo.getCosto()*articulo.getCantidad()};
                this.tableModelArt.addRow(data);
            }
        }
        
        for(Actividad actividad : this.actividades){
            Object data[] = {actividad.getNombre(), actividad.getHorario().toString(), actividad.getDescripcion()};
            this.tableModelAct.addRow(data);
        }
    }
    
    public void actualizarArticulo(String nombre, Integer value){
        for(Articulo articulo : this.articulos){
            if(articulo.getNombre().equals(nombre)){
                articulo.setCantidad(value);
                break;
            }
        }
    }
    
    public void eliminarArticulo(String nombre){
        for(Articulo articulo : this.articulos){
            if(articulo.getNombre().equals(nombre)){
                this.articulos.remove(articulo);
                break;
            }
        }
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
            precioArticulos += articulo.getCosto()*articulo.getCantidad();
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
    
    public void eliminarMontaje(){
        int i = 0;
        for(Articulo articulo : this.articulos){
            if(articulo.getTipo().equals(TipoArticulo.MONTAJE)){
                this.articulos.remove(i);
                break;
            }
            i++;
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

        panelArticulo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableArticulo = new javax.swing.JTable();
        btnDelArt = new javax.swing.JButton();
        btnAddArt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtDoc = new javax.swing.JTextField();
        btnBus = new javax.swing.JButton();
        panelActividad = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableActividades = new javax.swing.JTable();
        btnDelAct = new javax.swing.JButton();
        btnAddAct = new javax.swing.JButton();
        panelInfo = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtAbonar = new javax.swing.JTextField();
        lblAbonar = new javax.swing.JLabel();
        lblMontoFalt = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblPrecioTotal = new javax.swing.JLabel();
        lblMontoAbonado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtHoraInicio = new javax.swing.JTextField();
        txtDir = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPrecioMontaje = new javax.swing.JLabel();
        cmbMontaje = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(743, 724));

        panelArticulo.setBackground(new java.awt.Color(51, 51, 51));
        panelArticulo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        panelArticulo.setForeground(new java.awt.Color(255, 255, 255));

        tableArticulo.setBackground(new java.awt.Color(255, 255, 255));
        tableArticulo.setForeground(new java.awt.Color(0, 0, 0));
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

        btnDelArt.setBackground(new java.awt.Color(255, 255, 255));
        btnDelArt.setForeground(new java.awt.Color(0, 0, 0));
        btnDelArt.setText("Eliminar");

        btnAddArt.setBackground(new java.awt.Color(255, 255, 255));
        btnAddArt.setForeground(new java.awt.Color(0, 0, 0));
        btnAddArt.setText("Añadir");

        javax.swing.GroupLayout panelArticuloLayout = new javax.swing.GroupLayout(panelArticulo);
        panelArticulo.setLayout(panelArticuloLayout);
        panelArticuloLayout.setHorizontalGroup(
            panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArticuloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddArt, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(btnDelArt, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelArticuloLayout.setVerticalGroup(
            panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(panelArticuloLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnDelArt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddArt))
        );

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Documento");

        txtDoc.setBackground(new java.awt.Color(0, 0, 51));
        txtDoc.setForeground(new java.awt.Color(255, 255, 255));

        btnBus.setBackground(new java.awt.Color(255, 255, 255));
        btnBus.setForeground(new java.awt.Color(0, 0, 0));
        btnBus.setText("Buscar");

        panelActividad.setBackground(new java.awt.Color(51, 51, 51));
        panelActividad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        panelActividad.setForeground(new java.awt.Color(255, 255, 255));

        tableActividades.setBackground(new java.awt.Color(255, 255, 255));
        tableActividades.setForeground(new java.awt.Color(0, 0, 0));
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

        btnDelAct.setBackground(new java.awt.Color(255, 255, 255));
        btnDelAct.setForeground(new java.awt.Color(0, 0, 0));
        btnDelAct.setText("Eliminar");

        btnAddAct.setBackground(new java.awt.Color(255, 255, 255));
        btnAddAct.setForeground(new java.awt.Color(0, 0, 0));
        btnAddAct.setText("Añadir");

        javax.swing.GroupLayout panelActividadLayout = new javax.swing.GroupLayout(panelActividad);
        panelActividad.setLayout(panelActividadLayout);
        panelActividadLayout.setHorizontalGroup(
            panelActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActividadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddAct, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(btnDelAct, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelActividadLayout.setVerticalGroup(
            panelActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelActividadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelActividadLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnDelAct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddAct)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInfo.setBackground(new java.awt.Color(51, 51, 51));
        panelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        btnVolver.setBackground(new java.awt.Color(255, 255, 255));
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setText("Volver");

        btnDel.setBackground(new java.awt.Color(255, 255, 255));
        btnDel.setForeground(new java.awt.Color(0, 0, 0));
        btnDel.setText("Eliminar evento");

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setText("Guardar cambios");

        txtAbonar.setBackground(new java.awt.Color(0, 0, 51));
        txtAbonar.setForeground(new java.awt.Color(255, 255, 255));

        lblAbonar.setForeground(java.awt.Color.white);
        lblAbonar.setText("Abonar");

        lblMontoFalt.setForeground(java.awt.Color.white);
        lblMontoFalt.setText(" $");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("$");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("$");

        lblPrecioTotal.setForeground(new java.awt.Color(255, 255, 255));

        lblMontoAbonado.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Monto abonado");

        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("Monto faltante");

        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("Precio total");

        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("Estado");

        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("Direccion");

        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Hora de inicio");

        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Fecha de celebracion");

        txtFecha.setBackground(new java.awt.Color(0, 0, 51));
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));

        txtHoraInicio.setBackground(new java.awt.Color(0, 0, 51));
        txtHoraInicio.setForeground(new java.awt.Color(255, 255, 255));

        txtDir.setBackground(new java.awt.Color(0, 0, 51));
        txtDir.setForeground(new java.awt.Color(255, 255, 255));

        txtEstado.setEditable(false);
        txtEstado.setBackground(new java.awt.Color(0, 0, 51));
        txtEstado.setForeground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAbonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMontoFalt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMontoAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(134, 134, 134)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVolver)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(lblPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMontoAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMontoFalt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAbonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnDel)
                    .addComponent(btnVolver)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBus))
                    .addComponent(panelActividad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelArticulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(btnBus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAddAct;
    public javax.swing.JButton btnAddArt;
    public javax.swing.JButton btnBus;
    public javax.swing.JButton btnDel;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lblAbonar;
    public javax.swing.JLabel lblMontoAbonado;
    public javax.swing.JLabel lblMontoFalt;
    public javax.swing.JLabel lblPrecioMontaje;
    public javax.swing.JLabel lblPrecioTotal;
    private javax.swing.JPanel panelActividad;
    private javax.swing.JPanel panelArticulo;
    private javax.swing.JPanel panelInfo;
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
