/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import util.Estado;
/**
 *
 * @author kuroy
 */
public class Evento{
    private String nombre;
    private Responsable responsable;
    private String direccion_evento;
    private Fecha fecha_celebracion;
    private Integer monto_abonado;
    private Integer monto_total;
    private Estado estado_pago;
	
    public Evento(){
        this.nombre = "";
        this.responsable = null;
	this.direccion_evento = null;
	this.fecha_celebracion = null;
        this.monto_abonado = 0;
        this.estado_pago = Estado.NO_PAGO;
    }
	
    public Evento(String nombre, Fecha fecha_celebracion, String direccion_evento, Responsable responsable, Integer montoInicial, Integer montoTotal){
	this.nombre = nombre;
        this.responsable = responsable;
	this.direccion_evento = direccion_evento;
	this.fecha_celebracion = fecha_celebracion;
        this.monto_abonado = montoInicial;
        this.monto_total = montoTotal;
        this.actualizarEstado();
    }
    
    public void abonar(Integer monto){
        this.monto_abonado += monto;
        this.actualizarEstado();
    }
    
    private void actualizarEstado(){
        this.estado_pago = monto_abonado>=monto_total ? Estado.PAGO : Estado.NO_PAGO;
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

    public String getDireccion_evento() {
        return direccion_evento;
    }

    public void setDireccion_evento(String direccion_evento) {
        this.direccion_evento = direccion_evento;
    }
    
    public Fecha getFecha_celebracion() {
        return fecha_celebracion;
    }

    public void setFecha_celebracion(Fecha fecha_celebracion) {
        this.fecha_celebracion = fecha_celebracion;
    }
    
    public Integer getMonto_abonado() {
        return monto_abonado;
    }
    
    public Integer getMonto_total(){
        return this.monto_total;
    }
    
    public Estado getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(Estado estado_pago) {
        this.estado_pago = estado_pago;
    }
    
    public Integer getID(){
        return this.getResponsable().getCedula();
    }
}
