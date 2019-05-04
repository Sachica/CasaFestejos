/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
import util.MyException;
/**
 *
 * @author kuroy
 */
public class Evento{
    private String nombre;
    private Integer num_asistentes;
    private String direccion_evento;
    private ArrayList<Actividad> cronograma_actividades;
    private Silla sillas;
    private Bufet bufet;
    private Fecha fecha_celebracion;
	
    public Evento(){
        this.nombre = "";
	this.num_asistentes = 0;
	this.direccion_evento = null;
	this.cronograma_actividades = null;
	this.fecha_celebracion = null;
	this.bufet = new Bufet();
    }
	
    public Evento(String nombre, Fecha fecha_celebracion, Integer num_asistentes, String direccion_evento){
	this.nombre = nombre;
	this.num_asistentes = num_asistentes;
	this.direccion_evento = direccion_evento;
	this.cronograma_actividades = new ArrayList<>();
	this.fecha_celebracion = fecha_celebracion;
	this.bufet = new Bufet();
    }

    public void agregarActividad(Actividad actividad) throws MyException{
        for(Actividad comparador : cronograma_actividades){
            if(comparador.getHorario().equals(actividad.getHorario())){
                throw new MyException("Ya existe una actividada a esta hora");
            }
        }
    	this.cronograma_actividades.add(actividad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Bufet getBufet() {
        return bufet;
    }

    public Silla getSillas() {
        return sillas;
    }

    public void setSillas(String tipo, Integer cantidad) {
        this.sillas = SillaFactory.crearSilla(tipo, cantidad);
    }
    
    public void setBufet(Bufet bufet) {
        this.bufet = bufet;
    }
    
    public String mostraCronograma(){
        String actividades = "Actividades: \n";
        for(Actividad actividad : cronograma_actividades){
            actividades += actividad.toString()+"\n";
        }
        return actividades;
    }  
}
