package mx.com.pakar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.dto.PerfilDTO;
import mx.com.pakar.util.Factory;

/**
 *
 * @author nicolas.canaan
 */
public class PerfilDAO extends DAO{
    
    public PerfilDTO select(String idPerfil, Connection con){
        PerfilDTO perfil = new PerfilDTO();
        try {
            String query = "SELECT id_perfil FROM perfil WITH(NOLOCK) WHERE id_perfil = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idPerfil);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                perfil.setIdPerfil( idPerfil );
                perfil.setAplicaciones( new AplicacionDAO().selectAplicacion(idPerfil, con) );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return perfil;
        }
    }
    
    public List<PerfilDTO> select(Connection con){
        List<PerfilDTO> lista = new ArrayList<PerfilDTO>();
        try {
            String query = "SELECT id_perfil FROM perfil WITH(NOLOCK) ORDER BY id_perfil";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery( query );
            AplicacionDAO aplicacionDao = new AplicacionDAO();
            while( rs.next() ){
                PerfilDTO dto = new PerfilDTO();
                dto.setIdPerfil( rs.getString("id_perfil") );
                dto.setAplicaciones( aplicacionDao.selectAplicacion(dto.getIdPerfil(), con) );
                lista.add(dto);
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
    
    
}
