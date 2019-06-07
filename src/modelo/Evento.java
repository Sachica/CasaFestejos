/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
/**
 *
 * @author kuroy
 */
public class Evento{
    private Responsable responsable;
    private String direccion_evento;
    private Fecha fecha_celebracion;
    private Integer monto_abonado;
    private Integer monto_total;
    private Estado estado_pago;
    private Estado estado;
	
    public Evento(){
        this.responsable = null;
	this.direccion_evento = null;
	this.fecha_celebracion = null;
        this.monto_total = 0;
        this.monto_abonado = 0;
        this.estado = Estado.ACTIVO;
        this.estado_pago = Estado.NO_PAGO;
    }
	
    public Evento(Fecha fecha_celebracion, String direccion_evento, Responsable responsable, Integer monto_abonado, Integer montoTotal, Estado estado){
        this.responsable = responsable;
	this.direccion_evento = direccion_evento;
	this.fecha_celebracion = fecha_celebracion;
        this.monto_abonado = monto_abonado;
        this.monto_total = montoTotal;
        this.estado = estado;
        this.actualizarEstado();
    }
    
    public void abonar(Integer monto){
        this.monto_abonado += monto;
        this.actualizarEstado();
    }
    
    private void actualizarEstado(){
        this.estado_pago = monto_abonado>=monto_total ? Estado.PAGO : Estado.NO_PAGO;
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
        return this.getEstado_pago().equals(Estado.PAGO) ? getMonto_total() : getMonto_abonado();
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getID(){
        return this.getResponsable().getCedula();
    }
}
