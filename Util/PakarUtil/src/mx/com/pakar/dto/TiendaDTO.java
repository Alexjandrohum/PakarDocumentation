package mx.com.pakar.dto;

import java.io.Serializable;

/**
 *
 * @author nicolas.canaan
 */
public class TiendaDTO implements Serializable {

    private String idTienda;
    private String numero;
    private String nombre;
    private String tipo;

    @Override
    public String toString(){
        return idTienda;
    }
    
    @Override
    public boolean equals(Object o){
        if( o instanceof TiendaDTO ){
            return idTienda.equals( ((TiendaDTO)o).idTienda );
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.idTienda != null ? this.idTienda.hashCode() : 0);
        return hash;
    }
    
    public String getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(String idTienda) {
        this.idTienda = idTienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
