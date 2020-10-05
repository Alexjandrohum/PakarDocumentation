package mx.com.pakar.dto;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import mx.com.pakar.util.Util;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

/**
 *
 * @author nicolas.canaan
 */
public class ControlSesionDTO implements Serializable {

    private String idUsuario;
    private String idPerfil;
    private String nombre;
    private String tienda;
    private String modulo;
    private List<ModuloDTO> menu;

    public ControlSesionDTO() {
    }

    public ControlSesionDTO(String idUsuario, String idPerfil, String nombre, String tienda) {
        this.idUsuario = idUsuario;
        this.idPerfil = idPerfil;
        this.nombre = nombre;
        this.tienda = tienda;
    }
    
    private void readObject(java.io.ObjectInputStream ois) {
        try {
            idUsuario = ois.readUTF();
            idPerfil = ois.readUTF();
            nombre = ois.readUTF();
            tienda = ois.readUTF();
        } catch (IOException ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        }
    }

    private void writeObject(java.io.ObjectOutputStream oos) {
        try {
            oos.writeUTF(idUsuario==null?"":idUsuario);
            oos.writeUTF(idPerfil==null?"":idPerfil);
            oos.writeUTF(nombre==null?"":nombre);
            oos.writeUTF(tienda==null?"":tienda);
        } catch (IOException ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        }
    }

    public boolean isValido(String idModulo){
        for( ModuloDTO m : menu ){
            if( m.getIdModulo().equals(idModulo) ){
                modulo = m.getNombre();
                return true;
            }
        }
        return false;
    }
    
    public MenuModel getMenuWeb(){
        MenuModel modelo = new DefaultMenuModel();
        for( ModuloDTO m : menu ){
            MenuItem item = new MenuItem();
            item.setUrl( m.getIdModulo()+".jsf" );
            item.setIcon( "ui-icon-triangle-1-e" );
            item.setValue( m.getNombre() );
            modelo.addMenuItem(item);
        }
        return modelo;
    }
    
    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
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

    public List<ModuloDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<ModuloDTO> menu) {
        this.menu = menu;
    }
    
}
