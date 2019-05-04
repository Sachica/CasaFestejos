/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;

/**
 *
 * @author kuroy
 */
public class MyException extends Exception{

    public MyException(String err) {
        super(err);
        JOptionPane.showMessageDialog(null, super.getMessage(), "Operacion Fallida!", JOptionPane.ERROR_MESSAGE);
    }
    
}
