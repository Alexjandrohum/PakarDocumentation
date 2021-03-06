package com.grupopakar.grupopakarappws.dao;

import com.grupopakar.grupopakarappws.dto.ActividadDTO;
import com.grupopakar.grupopakarappws.dto.InfoTareaDTO;
import com.grupopakar.grupopakarappws.dto.TareaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author alberto.quirino
 */
public class TareaDAO {

    public List<ActividadDTO> getActividades(int idTienda, Connection con) {
        List<ActividadDTO> lista = new ArrayList<>();
        String query = "SELECT tc.id_tipo_concepto, tc.nombre_concepto, "
                + "	r.id_tarea_revision AS idTarea, c.nombre_concepto AS nombreTarea, "
                + "	r.estado_tarea AS estatusTarea "
                + "FROM aps.tarea_revision AS r WITH(NOLOCK) "
                + "INNER JOIN aps.tarea_catalogo AS c WITH(NOLOCK) ON c.id_tarea_catalogo = r.id_tarea_revision "
                + "INNER JOIN aps.tipo_concepto AS tc WITH(NOLOCK) ON tc.id_tipo_concepto = c.id_tipo_concepto  "
                + "INNER JOIN aps.tienda AS t ON t.id_tienda = r.id_tienda  "
                + "INNER JOIN aps.usuario_tienda AS ut WITH(NOLOCK) ON ut.id_usuario = r.id_usuario and ut.id_tienda = r.id_tienda "
                + "WHERE r.id_tienda = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idTienda);
            rs = ps.executeQuery();
            while (rs.next()) {
                ActividadDTO dto = new ActividadDTO();
                dto.setIdGrupoTarea(rs.getInt("id_tipo_concepto"));
                dto.setGrupoTarea(rs.getString("nombre_concepto"));
                dto.setListaTarea(getTareas(idTienda, rs.getInt("id_tipo_concepto"), con));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

    public List<TareaDTO> getTareas(int idTienda, int idTipoConcepto,
            Connection con) {
        List<TareaDTO> lista = new ArrayList<>();
        String query = "SELECT r.id_tarea_revision AS idTarea, c.nombre_concepto AS nombreTarea, "
                + "	r.estado_tarea AS estatusTarea "
                + "FROM aps.tarea_revision AS r WITH(NOLOCK) "
                + "INNER JOIN aps.tarea_catalogo AS c WITH(NOLOCK) ON c.id_tarea_catalogo = r.id_tarea_revision "
                + "INNER JOIN aps.tienda AS t ON t.id_tienda = r.id_tienda  "
                + "INNER JOIN aps.tipo_concepto AS tc ON tc.id_tipo_concepto = c.id_tipo_concepto  "
                + "WHERE t.id_tienda = ? and tc.id_tipo_concepto = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idTienda);
            ps.setInt(2, idTipoConcepto);
            rs = ps.executeQuery();
            while (rs.next()) {
                TareaDTO dto = new TareaDTO();
                dto.setIdTarea(rs.getInt("idTarea"));
                dto.setNombreTarea(rs.getString("nombreTarea"));
                dto.setEstatusTarea(rs.getBoolean("estatusTarea"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

    public String actualizaEstatusTarea(InfoTareaDTO dto, String token,
            Connection con) {
        boolean ok = false;
        String sql = "UPDATE r "
                + "SET r.estado_tarea = ? "
                + "FROM aps.tarea_revision AS r WITH(NOLOCK) "
                + "INNER JOIN aps.usuario AS u ON u.id_usuario = r.id_usuario  "
                + "WHERE r.id_tarea_revision = ? "
                + "     AND r.id_tienda = ? "
                + "     AND u.token_usuario = ? ";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, dto.isValorEstatusTarea());
            ps.setInt(2, dto.getIdTarea());
            ps.setInt(3, dto.getIdTienda());
            ps.setString(4, token);
            ok = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return ok ? "Se ha actualizado el estatus de la tarea " + dto.getIdTarea() : "";
    }

}
