package com.scpakar.scpakarweb.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo.martinez
 */
public class Prueba {

    public static void main(String[] args) {
        try {
            String dato = "";
            dato = AESCrypt.encryptMD5("1234");
            System.out.println("Encripta: "+dato);
            System.out.println("Decripta: "+ AESCrypt.decrypt(dato));
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
