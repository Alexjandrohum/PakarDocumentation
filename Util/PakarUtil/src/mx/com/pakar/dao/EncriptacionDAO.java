package mx.com.pakar.dao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncriptacionDAO extends DAO{

    private final String KEY = "0123456789abcdef0123456789abcdef";
    private final String ALGORITMO = "AES";
    private final int LONGITUD = 128;
    private final String CODIFICACION = "UTF-8";
    
    private String generaKey() {
        String key = "";
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITMO);
            keyGenerator.init(LONGITUD);
            SecretKey secretKey = keyGenerator.generateKey();
            key = HexToString(secretKey.getEncoded());
        }catch(NoSuchAlgorithmException ex){
            log.error( ex.getMessage() );
        }finally{
            return key;
        }
    }

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

    public String encripta(String cadena) {
        String encriptado = cadena;
        try{
            byte[] hex = StringToHex(KEY);
            SecretKeySpec secretKeySpec = new SecretKeySpec(hex, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(cadena.getBytes(CODIFICACION));
            encriptado = HexToString(encrypted);
        }catch(NoSuchAlgorithmException ex){
            log.error( ex.getMessage() );
        }catch(NoSuchPaddingException ex){
            log.error( ex.getMessage() );
        }catch(InvalidKeyException ex){
            log.error( ex.getMessage() );
        }catch(IllegalBlockSizeException ex){
            log.error( ex.getMessage() );
        }catch(BadPaddingException ex){
            log.error( ex.getMessage() );
        }catch(UnsupportedEncodingException ex){
            log.error( ex.getMessage() );
        } finally{
            return encriptado;
        }
    }
    
    public String desencriptar(String encriptado) {
        String desencriptado = encriptado;
        try{
            byte[] raw = StringToHex(KEY);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] original = cipher.doFinal(StringToHex(encriptado));
            desencriptado = new String(original);
        }catch(NoSuchAlgorithmException ex){
            log.error( ex.getMessage() );
        }catch(InvalidKeyException ex){
            log.error( ex.getMessage() );
        }catch(NoSuchPaddingException ex){
            log.error( ex.getMessage() );
        }catch(IllegalBlockSizeException ex){  
            log.error( ex.getMessage() );
        }catch(BadPaddingException ex){
            log.error( ex.getMessage() );
        } finally{
            return desencriptado;
        }
    }

}
