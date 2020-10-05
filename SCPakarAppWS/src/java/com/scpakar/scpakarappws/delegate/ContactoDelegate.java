package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.ContactoDAO;
import com.scpakar.scpakarappws.dao.PropiedadDAO;
import com.scpakar.scpakarappws.dto.ContactoDTO;
import com.scpakar.scpakarappws.dto.PropiedadCDW;
import com.scpakar.scpakarappws.util.UtilSCW;
import java.sql.Connection;

/**
 *
 * @author pablo.martinez
 */
public class ContactoDelegate {

    private final ContactoDAO contactoDao;
    private final PropiedadDAO propiedadDAO;

    public ContactoDelegate() {
        this.contactoDao = new ContactoDAO();
        this.propiedadDAO = new PropiedadDAO();
    }

    public boolean enviaCorreoContacto(ContactoDTO dto, Connection con) {
        String correoDudasTlmkt = propiedadDAO.selectPropiedad(PropiedadCDW.CORREO_PEDIDOS_TLMK.getIdPropiedad(), con);
        String correoDudasMkt = propiedadDAO.selectPropiedad(PropiedadCDW.CORREO_DUDAS_MKT.getIdPropiedad(), con);
        boolean exito = contactoDao.insertComentarioContacto(dto, con);
        if (exito) {
            exito = UtilSCW.enviaCorreos(
                    new String[]{correoDudasTlmkt, correoDudasMkt},
                    //                        new String[]{"alberto.quirino@grupopakar.com.mx","nicolas.canaan@grupopakar.com.mx"}, 
                    "SOLICITUD DE CONTACTO",
                    contactoDao.getContenidoCorreoContacto(dto),
                    dto.getCorreo(), con);
        }
        return exito;
    }
}
