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
    private Integer id;
    private String nombre;
    private String descripcion;
    private Hora horario;
	
    public Actividad(){
    	this.nombre = "";
    	this.horario = null;
    }
	
    public Actividad(Integer id, String nombre, String descripcion, Hora hora){
        this.id = id;
	this.nombre = nombre;
        this.descripcion = descripcion;
    	this.horario = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Hora getHorario() {
        return horario;
    }

    public void setHorario(Hora horario) {
        this.horario = horario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String toString(){
        return "Nombre: "+this.getNombre()+"\t Horario: "+this.getHorario();
    }
}
