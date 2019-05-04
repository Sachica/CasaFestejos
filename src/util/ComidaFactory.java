/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import modelo.Comida;
/**
 *
 * @author kuroy
 */
public class ComidaFactory {
    private static final String COMIDAS[] = {"PASTEL", "PASTEL DE BODA", "PERRO CALIENTE", "SANDWIWCH", "PINCHO DE FRUTA",
                                             "PINCHO DE CARNES", "NUGGETS DE POLLO", "PORCION DE PIZZA", "ENSALADA", "ARROZ CON LECHE", "GALLETAS",
                                             "FLAN", "NATILLA", "BUÑUELOS", "ROSQUILLAS"};
    private static final Integer COSTOS[] = {0, 0, 0, 0, 0, 0, 0, 0};
    
    public static Comida crearComida(String comando, Integer cantidad){
        for(Integer i=0; i<COMIDAS.length; i++){
            if(comando.equals(COMIDAS[i])){
                return new Comida(COMIDAS[i], COSTOS[i], cantidad);
            }
        }
    return null;
    }   
}
