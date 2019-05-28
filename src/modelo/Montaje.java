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
public class Montaje {
    private String tipoMontaje;
    private Integer costo;

    public Montaje() {
    }

    public Montaje(String tipoMontaje, Integer costo) {
        this.tipoMontaje = tipoMontaje;
        this.costo = costo;
    }

    public String getTipoMontaje() {
        return tipoMontaje;
    }

    public void setTipoMontaje(String tipoMontaje) {
        this.tipoMontaje = tipoMontaje;
    }

    public Integer getCosto() {
        return this.costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }   
}
