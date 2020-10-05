package mx.com.pakar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.dto.UsuarioDTO;
import mx.com.pakar.util.Factory;

/**
 *
 * @author nicolas.canaan
 */
public class UsuarioDAO extends DAO{

    public UsuarioDTO select(String idUsuario, String password, Connection con){
        UsuarioDTO usuario = new UsuarioDTO();
        try {
            String query = "SELECT id_usuario, nombre, id_perfil, id_tienda "
                    + "FROM usuario AS u WITH(NOLOCK) WHERE id_usuario = ? AND password = ? AND activo = '1' ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idUsuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                usuario.setIdUsuario( rs.getString("id_usuario") );
                usuario.setNombre( rs.getString("nombre") );
                usuario.setPerfil( new PerfilDAO().select(rs.getString("id_perfil"), con) );
                usuario.setTienda( new TiendaDAO().select(rs.getString("id_tienda"), con) );
                usuario.setActivo( true );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return usuario;
        }
    }
    
    public UsuarioDTO selectUsuario(String idUsuario, String password, Connection con){
        UsuarioDTO usuario = new UsuarioDTO();
        try {
            String query = "SELECT id_usuario, nombre, id_perfil, id_tienda, activo "
                    + "FROM usuario AS u WITH(NOLOCK) WHERE id_usuario = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idUsuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                usuario.setIdUsuario( rs.getString("id_usuario") );
                usuario.setNombre( rs.getString("nombre") );
                usuario.setPerfil( new PerfilDAO().select(rs.getString("id_perfil"), con) );
                usuario.setTienda( new TiendaDAO().select(rs.getString("id_tienda"), con) );
                usuario.setActivo( rs.getBoolean("activo") );
            }
            Factory.close(rs);
            Factory.close(ps);
        } catch (SQLException ex) {
            log.error( ex.getMessage() );
        } catch (Exception ex) {
            log.error( ex.getMessage() );
        } finally {
            return usuario;
        }
    }

    public List<UsuarioDTO> select(boolean activo, Connection con){
        List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
        try {
            String query = "SELECT id_usuario, nombre, id_perfil, id_tienda "
                    + "FROM usuario WITH(NOLOCK) WHERE activo = ? ORDER BY id_usuario";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setBoolean(1, activo);
            ResultSet rs = ps.executeQuery();
            TiendaDAO tiendaDao = new TiendaDAO();
            PerfilDAO perfilDao = new PerfilDAO();
            while(rs.next()){
                UsuarioDTO dto = new UsuarioDTO();
                dto.setIdUsuario( rs.getString("id_usuario") );
                dto.setNombre( rs.getString("nombre") );
                dto.setPerfil( perfilDao.select(rs.getString("id_perfil"), con) );
                dto.setTienda( tiendaDao.select(rs.getString("id_tienda"), con) );
                dto.setActivo( activo );
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
    
}