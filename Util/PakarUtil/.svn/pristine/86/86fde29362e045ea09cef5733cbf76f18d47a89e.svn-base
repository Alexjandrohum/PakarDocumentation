package mx.com.pakar.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author nicolas.canaan
 */
public class Cliente{

    public static String envia(String servidor, int puerto, String mensaje){
        String resultado = "";
        try{
            Socket cliente = new Socket( servidor, puerto );
            ObjectOutputStream oos = new ObjectOutputStream( cliente.getOutputStream() );
            oos.writeUTF( mensaje );
            oos.close();
            cliente.close();
        }catch(IOException ex){
            resultado = ex.getMessage();
        }catch(Exception ex){
            resultado = ex.getMessage();
        }finally{
            return resultado;
        }
    }
    
}
