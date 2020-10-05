package mx.com.pakar.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas.canaan
 */
public class PerfilDTO implements Serializable {
    
    private String idPerfil;
    private List<AplicacionDTO> aplicaciones;

    public PerfilDTO() {
        aplicaciones = new ArrayList<AplicacionDTO>();
    }
    
    @Override
    public String toString(){
        return idPerfil;
    }
    
    @Override
    public boolean equals(Object o){
        if( o instanceof PerfilDTO ){
            return idPerfil.equals( ((PerfilDTO)o).idPerfil );
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.idPerfil != null ? this.idPerfil.hashCode() : 0);
        return hash;
    }
    
    public List<AplicacionDTO> getAplicaciones() {
        return aplicaciones;
    }

    public void setAplicaciones(List<AplicacionDTO> aplicaciones) {
        this.aplicaciones = aplicaciones;
    }

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

}
