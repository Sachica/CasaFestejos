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
public class Bufet {
    private ArrayList<Comida> comidas;
    private ArrayList<Bebida> bebidas;

    public Bufet() {
        comidas = new ArrayList<>();
        bebidas = new ArrayList<>();
    }
    
    public void agregarComida(Comida comida){
        comidas.add(comida);
    }
    
    public void agregarBebida(Bebida bebida){
        bebidas.add(bebida);
    }

    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }

    public ArrayList<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Bebida> bebidas) {
        this.bebidas = bebidas;
    }
    
    public Integer obtenerCostoBufet(){
        Integer costo = 0;
        for(Comida comida : comidas){
            costo += comida.getCosto();
        }
        
        for(Bebida bebida : bebidas){
            costo += bebida.getCosto();
        }
        
        return costo;
    }
    
    public String toStirng(){
        String comidaString = "";
        String bebidaString = "";
        for(Comida comida : comidas){
            comidaString += comida.toString()+"\n";
        }
        
        for(Bebida bebida : bebidas){
            bebidaString += bebida.toString()+"\n"; 
        }
        
        return "Comidas: \n"+comidaString+"\n Bebidas: \n"+bebidaString;
    }
}
