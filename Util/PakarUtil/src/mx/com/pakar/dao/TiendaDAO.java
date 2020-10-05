package mx.com.pakar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.dto.TiendaDTO;
import mx.com.pakar.util.Factory;

/**
 *
 * @author nicolas.canaan
 */
public class TiendaDAO extends DAO{
    
    public List<TiendaDTO> select(Connection con){
        List<TiendaDTO> lista = new ArrayList<TiendaDTO>();
        try {
            String query = "SELECT id_tienda, nombre, numero, tipo FROM tienda_maestro WITH(NOLOCK) ORDER BY nombre";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while( rs.next() ){
                   TiendaDTO dto = new TiendaDTO();
                   dto.setIdTienda( rs.getString("id_tienda") );
                   dto.setNombre( rs.getString("nombre") );
                   dto.setNumero( rs.getString("numero") );
                   dto.setTipo( rs.getString("tipo") );
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
    
    public TiendaDTO select(String idTienda, Connection con){
        TiendaDTO tienda = new TiendaDTO();
        try {
            String query = "SELECT numero, nombre, tipo FROM tienda_maestro WITH(NOLOCK) WHERE id_tienda = ?";
            PreparedStatement ps = con.prepareStatement( query );
            ps.setString(1, idTienda);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                   tienda.setIdTienda( idTienda );
                   tienda.setNombre( rs.getString("nombre") );
                   tienda.setNumero( rs.getString("numero") );
                   tienda.setTipo( rs.getString("tipo") );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return tienda;
        }
    }
    
}
