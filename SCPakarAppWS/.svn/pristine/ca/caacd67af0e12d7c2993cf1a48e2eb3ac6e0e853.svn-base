package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.NotificacionDAO;
import com.scpakar.scpakarappws.dto.NotificacionDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author pablo.martinez
 */
public class NotificacionDelegate {

    private final NotificacionDAO notificacionDao;

    public NotificacionDelegate() {
        this.notificacionDao = new NotificacionDAO();
    }

    public List<NotificacionDTO> selectNotificacionSocio(String idSocio, Connection con) {
        return notificacionDao.selectNotificacionSocio(idSocio, con);
    }

    public boolean ocultaNotificacionesSocio(String idSocio, Connection con) {
        return notificacionDao.ocultaNotificacionesSocio(idSocio, con);
    }

    public boolean ocultaNotificacionSocio(String idSocio, String idContacto, Connection con) {
        return notificacionDao.ocultaNotificacionSocio(idSocio, idContacto, con);
    }

    public int getNotificacionSocioNoLeidas(String idSocio, Connection con) {
        return notificacionDao.getNotificacionSocioNoLeidas(idSocio, con);
    }

    public int getChatsNoLeidos(String idSocio, Connection con) {
        return notificacionDao.getChatsNoLeidos(idSocio, con);
    }
}
