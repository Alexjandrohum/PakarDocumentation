package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.AfiliacionDAO;
import com.scpakar.scpakarappws.dao.PropiedadDAO;
import com.scpakar.scpakarappws.dto.AfiliacionDTO;
import com.scpakar.scpakarappws.dto.PropiedadCDW;
import com.scpakar.scpakarappws.util.UtilSCW;
import java.sql.Connection;

/**
 *
 * @author pablo.martinez
 */
public class AfiliacionDelegate {

    private final AfiliacionDAO afiliacionDao;
    private final PropiedadDAO propiedadDao;

    public AfiliacionDelegate() {
        this.afiliacionDao = new AfiliacionDAO();
        this.propiedadDao = new PropiedadDAO();
    }

    public boolean guardaAfiliacion(AfiliacionDTO afiliacion, Connection connection) {
        boolean flag = false;
        try {
            int ultimo = Integer.parseInt(propiedadDao.selectPropiedad(PropiedadCDW.SOCIO_ULTIMO.getIdPropiedad(), connection));

            if (propiedadDao.updatePropiedad(PropiedadCDW.SOCIO_ULTIMO.getIdPropiedad(), Integer.toString(ultimo + 1), connection)) {
                int longitud = Integer.parseInt(propiedadDao.selectPropiedad(PropiedadCDW.SOCIO_LONGITUD.getIdPropiedad(), connection));
                String serie = propiedadDao.selectPropiedad(PropiedadCDW.SOCIO_SERIE.getIdPropiedad(), connection);
                String precio = propiedadDao.selectPropiedad(PropiedadCDW.PRECIO.getIdPropiedad(), connection);
                String cuenta = propiedadDao.selectPropiedad(PropiedadCDW.CUENTA.getIdPropiedad(), connection);
                String id = afiliacionDao.getIdSocio(ultimo + 1, longitud, serie);
                String servidor = propiedadDao.selectPropiedad(PropiedadCDW.SERVIDOR.getIdPropiedad(), connection);
                int referencia = afiliacionDao.getReferencia(id);
                flag = afiliacionDao.insertSocio(id, afiliacion, referencia, connection)
                        && UtilSCW.enviaCorreo(afiliacion.getCorreo(), "Confirmación de Solicitud " + id,
                                afiliacionDao.getContenidoCorreoAfiliacion(id, afiliacion, cuenta, referencia, precio, servidor),
                                afiliacion.getCorreo(), connection);
            }
        } catch (NumberFormatException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        }
        return flag;
    }
}
