package com.scpakar.scpakarappws.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author pablo.martinez
 */
public class Cifrado {

    /**
     * Pass Phrase que se usa para cifrar y descifrar informacion.
     */
    private final String SECRET_PASSWD = "*D3veLopM3nt*";
    /**
     * Clase de la libreria Jasypt, que se utiliza para cifrado.
     */
    private final BasicTextEncryptor textEncryptor;

    /**
     * Constructor Inicializa la clase que encripta y desencripta los datos por
     * medio de la frase secreta <code>SECRET_PASSWD</code>.
     */
    public Cifrado() {
        textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(SECRET_PASSWD);
    }

    /**
     * Encripta una cadena de texto a partir de la frase secreta
     * <code>SECRET_PASSWD</code>.
     *
     * @param cadena Cadena de texto sin encriptar.
     * @return Cadena de texto encriptada.
     */
    public String cryptText(String cadena) {
        String myEncryptedText = "";
        if (cadena.compareTo("") != 0) {
            myEncryptedText = textEncryptor.encrypt(cadena);
        }
        return myEncryptedText;
    }

    /**
     * Desencripta una cadena de texto a partir de la frase secreta
     * <code>SECRET_PASSWD</code>.
     *
     * @param cadena Cadena de texto encriptada.
     * @return Cadena de texto desencriptada.
     */
    public String decryptText(String cadena) {
        String plainText = "";
        if (cadena.compareTo("") != 0) {
            plainText = textEncryptor.decrypt(cadena);
        }
        return plainText;
    }
}
