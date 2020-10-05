/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupopakar.grupopakarappws.dao;

import com.grupopakar.grupopakarappws.dto.NotificacionDTO;
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
 * @author rosa.zalas
 */
public class NotificacionesDAO {

    /**
     * Actualiza la información de una tienda
     *
     * @param tokenUsuario dato para buscar las notificaciones
     * @param con Conexión activa a la base de datos.
     * @return Objeto tipo <code>List<NotificacionDTO></code> .
     */
    public List<NotificacionDTO> obtenerNotificacionesPendientes(String tokenUsuario, Connection con) {
        List<NotificacionDTO> lista = new ArrayList<NotificacionDTO>();
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "SELECT n.id_notificacion, tn.id_modulo, ut.id_tienda, n.fecha_creacion, "
                    + "s.nombre + '''\n''' +  u.numero_control + '''\n''' + tn.leyenda + '''\n''' + "
                    + "CAST(tn.id_tipo_notificacion AS VARCHAR(5)) + '''\n''' + c.comentario AS cuerpo "
                    + "FROM aps.notificacion n WITH(NOLOCK) "
                    + "INNER JOIN aps.tipo_notificacion tn WITH(NOLOCK) ON tn.id_tipo_notificacion = n.id_tipo_notificacion "
                    + "INNER JOIN aps.tarea_revision tr WITH(NOLOCK) ON tr.id_notificacion = n.id_notificacion  "
                    + "INNER JOIN aps.usuario_tienda ut WITH(NOLOCK) ON ut.id_tienda = tr.id_tienda "
                    + "INNER JOIN aps.usuario u WITH(NOLOCK) ON u.id_usuario = ut.id_usuario AND u.token_usuario = ? "
                    + "INNER JOIN aps.tienda s WITH(NOLOCK) ON s.id_tienda = ut.id_tienda "
                    + "INNER JOIN aps.comentario c WITH(NOLOCK) ON c.id_notificacion = n.id_notificacion "
                    + "WHERE n.id_estatus = 1 "
                    + "ORDER BY n.fecha_creacion";
            ps = con.prepareStatement(sql);
            ps.setString(1, tokenUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                NotificacionDTO dto = new NotificacionDTO();
                dto.setIdNotificacion(rs.getInt("id_notificacion"));
                dto.setIdModulo(rs.getInt("id_tienda"));
                dto.setFechaNotificacion(rs.getString("fecha_creacion"));
                dto.setCuerpo(rs.getString("cuerpo"));
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

    public String revisaNotificacion(int idNotificacion, Connection con) {
        String query = "";
        boolean ok = false;
        PreparedStatement ps = null;
        try {
            query = "UPDATE aps.notificacion SET id_estatus = 3 WHERE id_notificacion = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, idNotificacion);
            ok = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return ok ? "La información se actualizó correctamente" : "";
    }
}
