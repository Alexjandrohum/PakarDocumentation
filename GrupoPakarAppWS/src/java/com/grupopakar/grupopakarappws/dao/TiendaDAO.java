package com.grupopakar.grupopakarappws.dao;

import com.grupopakar.grupopakarappws.dto.ModuloDTO;
import com.grupopakar.grupopakarappws.dto.TiendaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author carlos.juarez
 */
public class TiendaDAO {

    public boolean validaLatitud(String latitud) {
        Double l = Double.valueOf(latitud);
        return (l > -90) && (l <= 90);
    }

    public boolean validaLongitud(String longitud) {
        Double l = Double.valueOf(longitud);
        return (l > -180) && (l <= 180);
    }                                                                                                          

    public List<TiendaDTO> getTiendasCercanas(String token, String lat, String lon, Connection con) {
        List<TiendaDTO> tiendas = new ArrayList<TiendaDTO>();
        String query = 
                  "SELECT "
                + "     TOP (SELECT CAST(valor AS INT) FROM aps.propiedad WHERE clave = 'max_tiendas') "
                + "     t.id_tienda, t.numero, t.clave_tienda, t.nombre, t.ciudad, t.tipo_tienda, ISNULL(t.supervisor, 'N/A') supervisor,   "
                + "     aps.calcula_distancia(?, ?, t.id_tienda) AS distancia   "
                + "FROM aps.tienda t WITH(NOLOCK)   "
                + "INNER JOIN aps.usuario_tienda ut WITH(NOLOCK) ON t.id_tienda = ut.id_tienda   "
                + "INNER JOIN aps.usuario u WITH(NOLOCK) ON ut.id_usuario = u.id_usuario "
                + "WHERE u.token_usuario = ? "
                + "ORDER BY distancia ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, lat);
            ps.setString(2, lon);
            ps.setString(3, token);
            rs = ps.executeQuery();
            while (rs.next()) {
                TiendaDTO t = new TiendaDTO();
                t.setIdTienda(rs.getInt("id_tienda"));
                t.setNumeroTienda(rs.getString("numero"));
                t.setCveTienda(rs.getString("clave_tienda"));
                t.setNombreTienda(rs.getString("nombre"));
                t.setCiudad(rs.getString("ciudad"));
                t.setTipoTienda(rs.getString("tipo_tienda"));
                t.setSupervisor(rs.getString("supervisor"));
                tiendas.add(t);
            }
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return tiendas;
    }

    public List<TiendaDTO> getTiendasCoincidentes(String token, String desc, Connection con) {
        List<TiendaDTO> tiendas = new ArrayList<TiendaDTO>();
        String query = 
                  "SELECT \n"
                + "	t.id_tienda, t.numero, t.clave_tienda, t.nombre, t.ciudad, t.tipo_tienda, ISNULL(t.supervisor, 'N/A') supervisor \n"
                + "FROM aps.tienda t WITH(NOLOCK) \n"
                + "INNER JOIN aps.usuario_tienda ut WITH(NOLOCK) ON t.id_tienda = ut.id_tienda \n"
                + "WHERE (t.numero LIKE ? OR t.clave_tienda LIKE ? OR nombre LIKE ? OR ciudad LIKE ?) \n"
                + "AND (ut.id_usuario = (SELECT id_usuario FROM aps.usuario WHERE token_usuario = ? )) \n"
                + "ORDER BY t.nombre ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + desc + "%");
            ps.setString(2, "%" + desc + "%");
            ps.setString(3, "%" + desc + "%");
            ps.setString(4, "%" + desc + "%");
            ps.setString(5, token);
            rs = ps.executeQuery();
            while (rs.next()) {
                TiendaDTO t = new TiendaDTO();
                t.setIdTienda(rs.getInt("id_tienda"));
                t.setNumeroTienda(rs.getString("numero"));
                t.setCveTienda(rs.getString("clave_tienda"));
                t.setNombreTienda(rs.getString("nombre"));
                t.setCiudad(rs.getString("ciudad"));
                t.setTipoTienda(rs.getString("tipo_tienda"));
                t.setSupervisor(rs.getString("supervisor"));
                tiendas.add(t);
            }
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return tiendas;
    }
    
    public List<ModuloDTO> getModulos(String numeroControl, Connection con) {
        List<ModuloDTO> modulos = new ArrayList<ModuloDTO>();
        String query = "SELECT u.id_usuario, up.id_perfil, m.id_modulo, m.nombre, ISNULL(m.ruta_img,'') AS icono  "
                + "FROM aps.modulo m  "
                + "INNER JOIN aps.perfil_modulo pm ON m.id_modulo = pm.id_modulo  "
                + "INNER JOIN aps.usuario_perfil up ON pm.id_perfil = up.id_perfil  "
                + "INNER JOIN aps.usuario u ON up.id_usuario = u.id_usuario  "
                + "WHERE u.numero_control = ?  "
                + "ORDER BY m.orden";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, numeroControl);
            rs = ps.executeQuery();
            while (rs.next()) {
                ModuloDTO m = new ModuloDTO();
                m.setIdUsuario(rs.getInt("id_usuario"));
                m.setIdPerfil(rs.getInt("id_perfil"));
                m.setIdModulo(rs.getInt("id_modulo"));
                m.setNombreModulo(rs.getString("nombre"));
                m.setIcono(rs.getString("icono"));
                modulos.add(m);
            }
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(rs);
            Factory.close(ps);

        }
        return modulos;
    }
}
