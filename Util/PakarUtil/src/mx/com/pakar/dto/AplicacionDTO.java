package mx.com.pakar.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas.canaan
 */
public class AplicacionDTO implements Serializable{

    private String idAplicacion;
    private String nombre;
    private String url;
    private List<ModuloDTO> modulos;

    public AplicacionDTO() {
        modulos = new ArrayList<ModuloDTO>();
    }
    
    public AplicacionDTO(String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    @Override
    public String toString(){
        return idAplicacion+": "+nombre;
    }

    @Override
    public boolean equals(Object o){
        if( o instanceof AplicacionDTO ){
            return idAplicacion.equals( ((AplicacionDTO)o).idAplicacion );
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.idAplicacion != null ? this.idAplicacion.hashCode() : 0);
        return hash;
    }
    
    public String getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public List<ModuloDTO> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloDTO> modulos) {
        this.modulos = modulos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
