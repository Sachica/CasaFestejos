/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import util.MyException;
import java.time.LocalDateTime;

/**
 *
 * @author kuroy
 */
public class Hora{   
    private Integer hora;
    private Integer minuto;
    private Integer segundo;

    public Hora() {
        LocalDateTime c = LocalDateTime.now();
        this.hora = c.getHour();
        this.minuto = c.getMinute();
        this.segundo = c.getSecond();
    }

    public Hora(Integer hora, Integer minuto, Integer segundo) throws MyException{
        if((hora<0 || hora>23) || (minuto<0 || minuto>59) || (segundo<0 || segundo>59)){
            throw new MyException("Hora no permitida");
        }
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }
    
    
    /**
     * Meotod que recibe otra hora y la compara con this para comprobar si this 
     * es mayor a other, de ser asi retorna true de lo contrario false
     * @param other
     * @return 
     */
    public Boolean esMayor(Hora other){
        if(this.hora>other.getHora()){
            return true;
        }else if(this.hora==other.getHora() && this.getMinuto()>other.getMinuto()){
            return true;
        }else if(this.hora==other.getHora() && this.getMinuto()==other.getMinuto() && this.getSegundo()>other.getSegundo()){
            return true;
        }
        return false;
    }
    
    /**
     * Meotod que recibe otra hora y la compara con this para comprobar si this 
     * es menor a other, de ser asi retorna true de lo contrario false
     * @param other
     * @return 
     */
    public Boolean esMenor(Hora other){
        return !esMayor(other) && !equals(other);
    }
    
    /**
     * Meotod que recibe otra hora y la compara con this para comprobar si this 
     * es igual a other, de ser asi retorna true de lo contrario false
     * @param other
     * @return 
     */
    public Boolean equals(Hora other){
        return this.hora==other.getHora() && this.getMinuto()==other.getMinuto() && this.getSegundo()==other.getSegundo();
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public Integer getSegundo() {
        return segundo;
    }

    public void setSegundo(Integer segundo) {
        this.segundo = segundo;
    }
    
    public String toString(){ 
        String horaString = this.getHora()<10 ? "0"+this.getHora() : ""+this.getHora();
        String minutoString = this.getMinuto()<10 ? "0"+this.getMinuto() : ""+this.getMinuto();
        String segundoString = this.getSegundo()<10 ? "0"+this.getSegundo(): ""+this.getSegundo();
        return horaString+":"+minutoString+":"+segundoString;
    }
}

