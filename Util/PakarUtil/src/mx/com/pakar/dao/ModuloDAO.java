package mx.com.pakar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.dto.ModuloDTO;
import mx.com.pakar.util.Factory;

/**
 *
 * @author nicolas.canaan
 */
public class ModuloDAO extends DAO{

    public ModuloDTO select(String idModulo, Connection con){
        ModuloDTO modulo = new ModuloDTO();
        try {
            String query = "SELECT nombre, orden FROM modulo WITH(NOLOCK) WHERE id_modulo = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idModulo);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                modulo.setIdModulo( idModulo );
                modulo.setNombre( rs.getString("nombre") );
                modulo.setOrden( rs.getInt("orden") );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return modulo;
        }
    }
    
    public List<ModuloDTO> selectAplicacion(String idAplicacion, Connection con){
        List<ModuloDTO> lista = new ArrayList<ModuloDTO>();
        try {
            String query = "SELECT id_modulo, nombre, orden FROM modulo WITH(NOLOCK) WHERE id_aplicacion = ? "
                    + "ORDER BY orden";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idAplicacion);
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                ModuloDTO dto = new ModuloDTO();
                dto.setIdModulo( rs.getString( "id_modulo" ) );
                dto.setNombre( rs.getString("nombre") );
                dto.setOrden( rs.getInt("orden") );
                lista.add( dto );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return lista;
        }
    }
    
    public List<ModuloDTO> selectAplicacion(String idAplicacion, String idPerfil, Connection con){
        List<ModuloDTO> lista = new ArrayList<ModuloDTO>();
        try {
            String query = "SELECT m.id_modulo, m.nombre, m.orden FROM perfil_modulo AS pm WITH(NOLOCK) "
                    + "INNER JOIN modulo AS m WITH(NOLOCK) ON pm.id_modulo = m.id_modulo "
                    + "WHERE m.id_aplicacion = ? AND pm.id_perfil = ? ORDER BY m.orden";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idAplicacion);
            ps.setString(2, idPerfil);
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                ModuloDTO dto = new ModuloDTO();
                dto.setIdModulo( rs.getString( "id_modulo" ) );
                dto.setNombre( rs.getString("nombre") );
                dto.setOrden( rs.getInt("orden") );
                lista.add( dto );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return lista;
        }
    }
    
}
