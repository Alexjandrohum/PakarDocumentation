package mx.com.pakar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.dto.ModuloDTO;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author nicolas.canaan
 */
public class ControlSesionDAO {

    public List<ModuloDTO> selectModulos(String idPerfil, String idAplicacion, Connection con){
        List<ModuloDTO> menu = new ArrayList<ModuloDTO>();
        try {
            String query = "SELECT m.id_modulo, m.nombre "
                    + "FROM modulo AS m "
                    + "INNER JOIN perfil_modulo AS p "
                    + "ON m.id_modulo = p.id_modulo "
                    + "WHERE m.id_aplicacion = ? "
                    + "AND p.id_perfil = ? "
                    + "ORDER BY m.orden";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, idAplicacion);
            st.setString(2, idPerfil);
            ResultSet rs = st.executeQuery();
            while( rs.next() ){
                ModuloDTO modulo = new ModuloDTO();
                modulo.setNombre( rs.getString("nombre") );
                modulo.setIdModulo( rs.getString("id_modulo") );
                menu.add( modulo );
            }
            Factory.close(rs);
            Factory.close(st);
        } catch (SQLException ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } finally {
            return menu;
        }
    }
    
    public String selectTienda(String tiendaOU, Connection con){
        String tienda = "HO";
        try {
            String query = "SELECT almacen FROM saup_tienda "
                    + "WHERE ? LIKE '%'+ruta_ad +'%' AND almacen <> 'NA'";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, tiendaOU);
            ResultSet rs = st.executeQuery();
            if( rs.next() ){
                tienda = rs.getString("almacen");
            }
            Factory.close(rs);
            Factory.close(st);
        } catch (SQLException ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } finally {
            return tienda;
        }
    }
    
}
