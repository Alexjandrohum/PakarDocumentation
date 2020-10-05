package mx.com.pakar.dto;

import java.io.Serializable;

/**
 *
 * @author nicolas.canaan
 */
public class ModuloDTO implements Serializable {

    private String idModulo;
    private String nombre;
    private int orden;

    public ModuloDTO() {
    }

    public ModuloDTO(String idModulo) {
        this.idModulo = idModulo;
    }

    @Override
    public String toString(){
        return idModulo + ": " + nombre;
    }

    @Override
    public boolean equals(Object o){
        if( o instanceof ModuloDTO ){
            return idModulo.equals( ((ModuloDTO)o).idModulo );
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.idModulo != null ? this.idModulo.hashCode() : 0);
        return hash;
    }
    
    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

}
