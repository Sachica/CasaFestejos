/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author kuroy
 */
public class Hora extends Thread{   
    private Integer hour;
    private Integer minute;
    private Integer second;

    public Hora() {
        LocalDateTime c = LocalDateTime.now();
        this.hour = c.getHour();
        this.minute = c.getMinute();
        this.second = c.getSecond();
    }

    public Hora(Integer hour, Integer minute, Integer second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    
    /**
     * Meotod que recibe otra hora y la compara con this para comprobar si this 
     * es mayor a other, de ser asi retorna true de lo contrario false
     * @param other
     * @return 
     */
    public Boolean esMayor(Hora other){
        if(this.hour>other.getHour()){
            return true;
        }else if(this.hour==other.getHour() && this.getMinute()>other.getMinute()){
            return true;
        }else if(this.hour==other.getHour() && this.getMinute()==other.getMinute() && this.getSecond()>other.getSecond()){
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
        return this.hour==other.getHour() && this.getMinute()==other.getMinute() && this.getSecond()==other.getSecond();
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }
    
    public String toString(){ 
        String hora = this.hour<10 ? "0"+this.hour : ""+this.hour;
        String minuto = this.minute<10 ? "0"+this.minute : ""+this.minute;
        String segundo = this.second<10 ? "0"+this.second : ""+this.second;
        return hora+":"+minuto+":"+segundo;
    }
    
    public static void main(String[] args) {
        Hora h1 = new Hora(0, 0, 0);
        Hora h2 = new Hora(0, 0, 0);
        System.out.println("Afirmacion : "+h1.equals(h2));
    }
}

