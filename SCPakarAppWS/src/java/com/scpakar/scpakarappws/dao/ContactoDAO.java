package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.ContactoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
public class ContactoDAO {

    public String getContenidoCorreoContacto(ContactoDTO dto) {
        String contenido = "<h2>Solicitud de Contacto</h2>"
                + "<b>Correo enviado por el Sr(a): " + dto.getNombre() + "<br/>"
                + "Correo electronico: <a href='mailto:" + dto.getCorreo() + "'>" + dto.getCorreo() + "</a><br/>"
                + "Telefono Fijo: " + dto.getTelefono() + "<br/>"
                + "Telefono Celular: " + dto.getCelular() + "<br/>"
                + "Comentario: <br/> " + dto.getMensaje() + "</b><br/>";
        return contenido;
    }

    public boolean insertComentarioContacto(ContactoDTO dto, Connection con) {
        boolean exito = false;
        PreparedStatement ps = null;
        String qry = "INSERT INTO scw_correo_contacto VALUES(?,?,?,?,?,GETDATE())";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getCorreo());
            ps.setString(3, dto.getTelefono());
            ps.setString(4, dto.getCelular());
            ps.setString(5, dto.getMensaje());
            exito = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return exito;
    }
}
