/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import util.SillaFactory;
import util.MesaFactory;
/**
 *
 * @author kuroy
 */
public class Montaje {
    private String tipoMontaje;
    private Silla sillas;
    private Mesa mesas;
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
    
    public Silla getSillas() {
        return sillas;
    }

    public void setSillas(String tipo, Integer cantidad) {
        this.sillas = SillaFactory.crearSillas(tipo, cantidad);
    }

    public Mesa getMesas() {
        return mesas;
    }

    public void setMesas(String tipo, Integer cantidad) {
        this.mesas = MesaFactory.crearMesas(tipo, cantidad);
    }

    public Integer getCosto() {
        Integer sillasValor = this.getSillas().getCosto();
        Integer mesasValor = this.getMesas().getCosto();
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
    
}
