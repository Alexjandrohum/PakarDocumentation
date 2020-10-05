package mx.com.pakar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.dto.AplicacionDTO;
import mx.com.pakar.util.Factory;

/**
 *
 * @author nicolas.canaan
 */
public class AplicacionDAO extends DAO{
    
    public AplicacionDTO select(String idAplicacion, Connection con){
        AplicacionDTO aplicacion = new AplicacionDTO();
        try {
            String query = "SELECT nombre, url FROM aplicacion WITH(NOLOCK) "
                    + "WHERE id_aplicacion = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idAplicacion);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                aplicacion.setIdAplicacion( idAplicacion );
                aplicacion.setNombre( rs.getString("nombre") );
                aplicacion.setUrl( rs.getString("url") );
                aplicacion.setModulos( new ModuloDAO().selectAplicacion(idAplicacion, con) );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return aplicacion;
        }
    }
    
    public List<AplicacionDTO> selectAplicacion(Connection con){
        List<AplicacionDTO> lista = new ArrayList<AplicacionDTO>();
        try {
            String query = "SELECT id_aplicacion, nombre, url FROM aplicacion WITH(NOLOCK) "
                    + "ORDER BY nombre";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery( query );
            while( rs.next() ){
                AplicacionDTO dto = new AplicacionDTO();
                dto.setIdAplicacion( rs.getString("id_aplicacion") );
                dto.setNombre( rs.getString("nombre") );
                dto.setUrl( rs.getString("url") );
                dto.setModulos( new ModuloDAO().selectAplicacion(dto.getIdAplicacion(), con) );
                lista.add( dto );
            }
            Factory.close(rs);
            Factory.close(st);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return lista;
        }
    }
    
    public AplicacionDTO select(String idAplicacion, String idPerfil, Connection con){
        AplicacionDTO aplicacion = new AplicacionDTO();
        try {
            String query = "SELECT nombre, url FROM aplicacion WITH(NOLOCK) "
                    + "WHERE id_aplicacion = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idAplicacion);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                aplicacion.setIdAplicacion( idAplicacion );
                aplicacion.setNombre( rs.getString("nombre") );
                aplicacion.setUrl( rs.getString("url") );
                aplicacion.setModulos( new ModuloDAO().selectAplicacion(idAplicacion, idPerfil, con) );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return aplicacion;
        }
    }
    
    public List<AplicacionDTO> selectAplicacion(String idPerfil, Connection con){
        List<AplicacionDTO> lista = new ArrayList<AplicacionDTO>();
        try {
            String query = "SELECT p.id_aplicacion, p.nombre, p.url FROM perfil_modulo AS pm WITH(NOLOCK) "
                    + "INNER JOIN modulo AS m WITH(NOLOCK) ON pm.id_modulo = m.id_modulo "
                    + "INNER JOIN aplicacion AS p WITH(NOLOCK) ON m.id_aplicacion = p.id_aplicacion "
                    + "WHERE pm.id_perfil = ? GROUP BY p.id_aplicacion, p.nombre, p.url ORDER BY p.nombre";
            PreparedStatement ps = con.prepareStatement( query );
            ps.setString(1, idPerfil);
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                AplicacionDTO dto = new AplicacionDTO();
                dto.setIdAplicacion( rs.getString("id_aplicacion") );
                dto.setNombre( rs.getString("nombre") );
                dto.setUrl( rs.getString("url") );
                dto.setModulos( new ModuloDAO().selectAplicacion(dto.getIdAplicacion(), idPerfil, con) );
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
