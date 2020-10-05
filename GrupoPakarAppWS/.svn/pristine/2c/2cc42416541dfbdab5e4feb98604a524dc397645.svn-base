/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupopakar.grupopakarappws.util;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import mx.com.pakar.util.Util;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author carlos.juarez
 */
public class Encriptacion {

    private final String KEY = "0123456789abcdef0123456789abcdef";
    private final String ALGORITMO_AES = "AES";
    private final String ALGORITMO_SHA1 = "SHA1";
    private final String CODIFICACION = "UTF-8";

    private String HexToString(byte[] arregloEncriptado) {
        String textoEncriptado = "";
        for (int i = 0; i < arregloEncriptado.length; i++) {
            int aux = arregloEncriptado[i] & 0xff;
            if (aux < 16) {
                textoEncriptado = textoEncriptado.concat("0");
            }
            textoEncriptado = textoEncriptado.concat(Integer.toHexString(aux));
        }
        return textoEncriptado;
    }

    private byte[] StringToHex(String encriptado) {
        byte[] enBytes = new byte[encriptado.length() / 2];
        for (int i = 0; i < enBytes.length; i++) {
            int index = i * 2;
            String aux = encriptado.substring(index, index + 2);
            int v = Integer.parseInt(aux, 16);
            enBytes[i] = (byte) v;
        }
        return enBytes;
    }

    public String encriptarAES(String cadena) {
        String encriptado = cadena;
        try {
            byte[] hex = StringToHex(KEY);
            SecretKeySpec secretKeySpec = new SecretKeySpec(hex, ALGORITMO_AES);
            Cipher cipher = Cipher.getInstance(ALGORITMO_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(cadena.getBytes(CODIFICACION));
            encriptado = HexToString(encrypted);
        } catch (Exception ex) {
            System.out.println("Error en Encriptacion.encripta: " + ex.getMessage());
        } finally {
            
        }
        return encriptado;
    }

    public String desencriptarAES(String encriptado) {
        String desencriptado = encriptado;
        try {
            byte[] raw = StringToHex(KEY);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITMO_AES);
            Cipher cipher = Cipher.getInstance(ALGORITMO_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] original = cipher.doFinal(StringToHex(encriptado));
            desencriptado = new String(original);
        } catch (Exception ex) {
            System.out.println("Error en Encriptacion.desencripta: " + ex.getMessage());
        } finally {
            
        }
        return desencriptado;
    }

    public String getHashSHA1(String valor) {
        String valorCodificado;
        valorCodificado = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITMO_SHA1);
            messageDigest.update(valor.getBytes());
            byte[] array = messageDigest.digest();
            valorCodificado = Hex.encodeHexString(array);
        } catch (Exception e) {
            System.out.println("Error en Encriptacion.Hash: " + e.getMessage());
        }
        return valorCodificado;
    }
    
    public String getEncodeBase64(String valor) {
        Base64 base64 = new Base64();
        String valorCodificado;
        valorCodificado = "";
        try {
            valorCodificado = base64.encodeToString(valor.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println("Error en Encriptacion.getEncodeBase64: " + e.getMessage());
        }
        return valorCodificado;
    }
    
    public String getEncodeBase64URL(String valor) {
        String valorCodificado;
        valorCodificado = "";
        try {
            valorCodificado = Base64.encodeBase64URLSafeString(valor.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println("Error en Encriptacion.getEncodeBase64: " + e.getMessage());
        }
        return valorCodificado;
    }
    
    public String getDecodeBase64(String valor) {
        String valorDecodificado;
        valorDecodificado = "";
        try {
            valorDecodificado = new String(Base64.decodeBase64(valor));
        } catch (Exception e) {
            System.out.println("Error en Encriptacion.getDecodeBase64: " + e.getMessage());
        }
        return valorDecodificado;
    }
    
    public String getTokenDeUsuario(String idUsuario, String usuarioDominio, String numeroDeControl, String anio, String mes){
        Encriptacion cripto = new Encriptacion();

        String jsonText = "";
        String jsonTextBase64 = "";
        String sessionToken = "";
        
        try {
            jsonText = "{\"idUsuario\":\"" + idUsuario + "\", \"usuarioDominio\":\"" + usuarioDominio + "\", \"numeroControl\":\"" + numeroDeControl + "\", \"anio\":\"" + anio + "\", \"mes\":\"" + mes + "\"}";
            System.out.println(jsonText);
            jsonTextBase64 = cripto.getEncodeBase64URL(jsonText);
            sessionToken = cripto.getHashSHA1(jsonTextBase64);
            
        } catch (Exception e) {
            Util.registraError(e);
        }
        cripto = null;
        return sessionToken;
    }

}
