/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
/**
 *
 * @author kuroy
 */
public class Evento{
    private String nombre;
    private Integer num_asistentes;
    private String direccion_evento;
    private ArrayList<Actividad> cronograma_actividades;
    private Silla sillas[];
    private ArrayList<Comida> comidas;
    private Fecha fecha_celebracion;
	
    public Evento(){
        this.nombre = "";
	this.num_asistentes = 0;
	this.direccion_evento = null;
	this.cronograma_actividades = null;
	this.fecha_celebracion = null;
	this.sillas = null;
	this.comidas = new ArrayList<>();
    }
	
    public Evento(String nombre, Fecha fecha_celebracion, Integer num_asistentes, String direccion_evento){
	this.nombre = nombre;
	this.num_asistentes = num_asistentes;
	this.direccion_evento = direccion_evento;
	this.cronograma_actividades = new ArrayList<>();
	this.fecha_celebracion = fecha_celebracion;
	this.sillas = new Silla[num_asistentes];
	this.comidas = new ArrayList<>();
    }
	
    public void agregarComida(Comida comida){
   	this.comidas.add(comida);
    }
	
    public void agregarCronograma(Actividad actividad){
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

    public Silla[] getSillas() {
        return sillas;
    }

    public void setSillas(Silla[] sillas) {
        this.sillas = sillas;
    }

    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }

    public Fecha getFecha_celebracion() {
        return fecha_celebracion;
    }

    public void setFecha_celebracion(Fecha fecha_celebracion) {
        this.fecha_celebracion = fecha_celebracion;
    }
    
}
