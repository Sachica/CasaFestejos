/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
import util.Estado;
import util.MyException;
import util.MontajeFactory;
/**
 *
 * @author kuroy
 */
public class Evento{
    private String nombre;
    private Responsable responsable;
    private Integer num_asistentes;
    private String direccion_evento;
    private ArrayList<Actividad> cronograma_actividades;
    private Montaje montaje;
    private Bufet bufet;
    private Fecha fecha_celebracion;
    private Integer monto_abonado;
    private Estado estado_pago;
	
    public Evento(){
        this.nombre = "";
        this.responsable = null;
	this.num_asistentes = 0;
	this.direccion_evento = null;
	this.cronograma_actividades = new ArrayList<>();
	this.fecha_celebracion = null;
        this.montaje = null;
	this.bufet = null;
        this.monto_abonado = 0;
        this.estado_pago = Estado.NO_PAGO;
    }
	
    public Evento(String nombre, Fecha fecha_celebracion, Integer num_asistentes, String direccion_evento, Montaje montaje, Responsable responsable, Integer montoInicial){
	this.nombre = nombre;
        this.responsable = responsable;
	this.num_asistentes = num_asistentes;
	this.direccion_evento = direccion_evento;
	this.cronograma_actividades = new ArrayList<>();
	this.fecha_celebracion = fecha_celebracion;
        this.montaje = montaje;
	this.bufet = new Bufet();
        this.monto_abonado = montoInicial;
        this.estado_pago = Estado.NO_PAGO;
    }

    public void agregarActividad(Actividad actividad) throws MyException{
        for(Actividad comparador : cronograma_actividades){
            if(comparador.getHorario().equals(actividad.getHorario())){
                throw new MyException("Ya existe una actividada a esta hora");
            }
        }
    	this.cronograma_actividades.add(actividad);
    }
    
    public void abonarMonto(Integer monto){
        monto_abonado += monto;       
    }
    
    public void actualizarEstado(){
        if(monto_abonado>=this.getMontoTotal()){
            this.setEstado_pago(Estado.PAGO);
        }else{
            this.setEstado_pago(Estado.NO_PAGO);
        }
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
    
    public Integer getNum_asistentes() {
        return num_asistentes;
    }

    public void setNum_asistentes(Integer num_asistentes) {
        this.num_asistentes = num_asistentes;
    }

    public String getDireccion_evento() {
        return direccion_evento;
    }

    public void setDireccion_evento(String direccion_evento) {
        this.direccion_evento = direccion_evento;
    }

    public ArrayList<Actividad> getCronograma_actividades() {
        return cronograma_actividades;
    }

    public void setCronograma_actividades(ArrayList<Actividad> cronograma_actividades) {
        this.cronograma_actividades = cronograma_actividades;
    }
    
    public Fecha getFecha_celebracion() {
        return fecha_celebracion;
    }

    public void setFecha_celebracion(Fecha fecha_celebracion) {
        this.fecha_celebracion = fecha_celebracion;
    }

    public Montaje getMontaje() {
        return montaje;
    }

    public void setMontaje(String tipo) {
        Montaje m = MontajeFactory.crearMontaje(tipo);
        this.montaje.setTipoMontaje(m.getTipoMontaje());
        this.montaje.setCosto(m.getCosto());
    }
    
    public Bufet getBufet() {
        return bufet;
    }

    public void setBufet(Bufet bufet) {
        this.bufet = bufet;
    }

    public Integer getMonto_abonado() {
        return monto_abonado;
    }
    
    public Estado getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(Estado estado_pago) {
        this.estado_pago = estado_pago;
    }
    
    public Integer getMontoTotal() {
        Integer bufetValor = this.getBufet().getCosto();
        Integer montajeValor = this.getMontaje().getCosto();
        return bufetValor+montajeValor;
    }
    
    public String mostraCronograma(){
        String actividades = "Actividades: \n";
        for(Actividad actividad : cronograma_actividades){
            actividades += actividad.toString()+"\n";
        }
        return actividades;
    }  
}
