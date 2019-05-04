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
public class Actividad{
    private String nombre;
    private Hora horario;
    private Integer costo;
	
    public Actividad(){
    	this.nombre = "";
    	this.horario = null;
    	this.costo = 0;
    }
	
    public Actividad(String nombre, Hora hora, Integer costo){
	this.nombre = nombre;
    	this.horario = hora;
	this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Hora getHorario() {
        return horario;
    }

    public void setHorario(Hora horario) {
        this.horario = horario;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
    
    public String toString(){
        return "Nombre: "+this.getNombre()+"\t Horario: "+this.getHorario();
    }
}
