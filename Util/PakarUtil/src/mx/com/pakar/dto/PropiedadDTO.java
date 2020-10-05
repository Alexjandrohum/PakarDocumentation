package mx.com.pakar.dto;

import java.io.Serializable;

/**
 *
 * @author nicolas.canaan
 */
public class PropiedadDTO implements Serializable {

    private String clave;
    private String valor;

    @Override
    public String toString(){
        return clave + ": " + valor;
    }

    @Override
    public boolean equals(Object o){
        if( o instanceof PropiedadDTO ){
            return clave.equals( ((PropiedadDTO)o).clave );
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.clave != null ? this.clave.hashCode() : 0);
        return hash;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
