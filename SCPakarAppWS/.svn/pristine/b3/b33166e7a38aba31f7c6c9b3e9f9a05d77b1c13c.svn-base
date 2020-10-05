package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.NotificacionDTO;
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
 * @author pablo.martinez
 */
public class NotificacionDAO {

    public List<NotificacionDTO> selectNotificacionSocio(String idSocio, Connection con) {
        List<NotificacionDTO> notificaciones = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "EXEC [dbo].[scpakar_notificaciones_app] ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            r = ps.executeQuery();
            while (r.next()) {
                NotificacionDTO n = new NotificacionDTO();
                n.setIdContacto(r.getString("IdContacto"));
                n.setIdSocio(r.getString("IdSocio"));
                n.setTitulo(r.getString("Titulo"));
                n.setNotificacion(r.getString("Notificacion"));
                n.setFecha(Util.formatoFechaGuionHoraSQL(r.getTimestamp("Fecha")));
                n.setLeido(r.getBoolean("Leido"));
                n.setOculto(r.getBoolean("Oculto"));
                n.setIdTipoNotificacion(r.getInt("IdTipoNotificacion"));

                notificaciones.add(n);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return notificaciones;
    }

    public boolean ocultaNotificacionesSocio(String idSocio, Connection con) {
        boolean result = false;
        PreparedStatement ps = null;
        String query = "UPDATE scp_socio_notificaciones SET Oculto = 1 WHERE IdSocio = ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return result;
    }

    public boolean ocultaNotificacionSocio(String idSocio, String idContacto, Connection con) {
        boolean result = false;
        PreparedStatement ps = null;
        String query = "UPDATE scp_socio_notificaciones SET Oculto = 1 WHERE IdSocio = ? AND IdContacto = ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, idContacto);
            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return result;
    }

    public int getNotificacionSocioNoLeidas(String idSocio, Connection con) {
        int notificaciones = 0;
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "SELECT COUNT(IdContacto) AS Notificaciones "
                + "FROM scp_socio_notificaciones WITH(NOLOCK) "
                + "WHERE IdSocio = ? AND Oculto = 0 AND Leido = 0 "
                + "OPTION (OPTIMIZE FOR UNKNOWN, MAXDOP 2)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            r = ps.executeQuery();
            if (r.next()) {
                notificaciones = r.getInt("Notificaciones");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return notificaciones;
    }

    public int getChatsNoLeidos(String idSocio, Connection con) {
        int notificaciones = 0;
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "SELECT COUNT(IdSocio) AS Notificaciones "
                + "FROM scp_chat_conversacion WITH(NOLOCK) "
                + "WHERE IdSocio = ? AND Oculta = 0 AND Leido = 0 "
                + "OPTION (OPTIMIZE FOR UNKNOWN, MAXDOP 2)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            r = ps.executeQuery();
            if (r.next()) {
                notificaciones = r.getInt("Notificaciones");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return notificaciones;
    }

}
