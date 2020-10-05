package mx.com.pakar.dto;

import java.io.Serializable;

/**
 *
 * @author nicolas.canaan
 */
public class UsuarioDTO implements Serializable {

    
    private String idUsuario;
    private String password;
    private String nombre;
    private PerfilDTO perfil;
    private TiendaDTO tienda;
    private boolean activo;

    public UsuarioDTO() {
        password = "";
        perfil = new PerfilDTO();
        tienda = new TiendaDTO();
        activo = true;
    }

    @Override
    public String toString(){
        return idUsuario+": "+tienda;
    }

    @Override
    public boolean equals(Object o){
        if( o != null && o instanceof UsuarioDTO ){
            return idUsuario.equals( ((UsuarioDTO)o).idUsuario );
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.idUsuario != null ? this.idUsuario.hashCode() : 0);
        return hash;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PerfilDTO getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDTO perfil) {
        this.perfil = perfil;
    }

    public TiendaDTO getTienda() {
        return tienda;
    }

    public void setTienda(TiendaDTO tienda) {
        this.tienda = tienda;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
