package mx.com.pakar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.dto.PropiedadDTO;
import mx.com.pakar.util.Factory;

/**
 *
 * @author nicolas.canaan
 */
public class PropiedadDAO extends DAO{
    
    public PropiedadDTO select(String clave, Connection con){
        PropiedadDTO propiedad = new PropiedadDTO();
        try {
            String query = "SELECT valor FROM propiedad WITH(NOLOCK) WHERE clave = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, clave);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                propiedad.setClave( clave );
                propiedad.setValor( rs.getString("valor") );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return propiedad;
        }
    }
    
    public List<PropiedadDTO> selectAplicacion(String idAplicacion, Connection con){
        List<PropiedadDTO> lista = new ArrayList<PropiedadDTO>();
        try {
            String query = "SELECT clave, valor FROM propiedad WITH(NOLOCK) WHERE id_aplicacion = ? ORDER BY clave";
            PreparedStatement ps = con.prepareStatement( query );
            ps.setString(1, idAplicacion);
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                PropiedadDTO dto = new PropiedadDTO();
                dto.setClave( rs.getString("clave") );
                dto.setValor( rs.getString("valor") );
                lista.add(dto);
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
    
    public String selectValor(String clave, Connection con){
        String valor = "";
        try {
            String query = "SELECT valor FROM propiedad WITH(NOLOCK) WHERE clave = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, clave);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                valor= rs.getString("valor");
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return valor;
        }
    }
    
    public int selectValorNumero(String clave, Connection con){
        int valor = 0;
        try {
            String query = "SELECT valor FROM propiedad WITH(NOLOCK) WHERE clave = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, clave);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                valor= rs.getInt("valor");
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return valor;
        }
    }
    
}
