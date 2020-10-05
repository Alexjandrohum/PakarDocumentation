package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.AfiliacionDTO;
import com.scpakar.scpakarappws.dto.ConfirmaDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
public class AfiliacionDAO {

    public boolean insertSocio(String idSocio, AfiliacionDTO afiliacion, int referencia, Connection connection) {
        boolean flag = false;
        PreparedStatement ps = null;
        String query = "INSERT INTO scw_afiliacion(id_socio,nombre,apellidos,direccion,colonia,cp,ciudad,estado,tienda,telefono,celular,correo,"
                + "estado_civil,estudios,dependientes,ocupacion,enteraste,razon,otro,catalogos,fecha,referencia,fecha_pago,comprobante) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE(),?,NULL,NULL)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, afiliacion.getNombre());
            ps.setString(3, afiliacion.getApellidos());
            ps.setString(4, afiliacion.getDireccion());
            ps.setString(5, afiliacion.getColonia());
            ps.setString(6, afiliacion.getCp());
            ps.setString(7, afiliacion.getCiudad());
            ps.setString(8, afiliacion.getEstado());
            ps.setString(9, afiliacion.getTienda());
            ps.setString(10, afiliacion.getTelefono());
            ps.setString(11, afiliacion.getCelular());
            ps.setString(12, afiliacion.getCorreo());
            ps.setString(13, afiliacion.getEstadoCivil());
            ps.setString(14, afiliacion.getEstudios());
            ps.setString(15, afiliacion.getDependientes());
            ps.setString(16, afiliacion.getOcupacion());
            ps.setString(17, afiliacion.getEnteraste());
            ps.setString(18, afiliacion.getRazon());
            ps.setString(19, afiliacion.getOtro());
            ps.setString(20, afiliacion.getCatalogos());
            ps.setInt(21, referencia);
            flag = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return flag;
    }

    public boolean updateAfiliacion(ConfirmaDTO confirma, Connection connection) {
        boolean ok = false;
        PreparedStatement ps = null;
        String query = "UPDATE scw_afiliacion SET fecha_pago = ?, comprobante = ? WHERE id_socio = ? AND referencia = ? AND fecha_pago IS NULL";
        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, new Date(confirma.getFecha().getTime()));
            ps.setString(2, confirma.getComprobante());
            ps.setString(3, confirma.getIdSocio());
            ps.setInt(4, confirma.getReferencia());
            ok = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return ok;
    }

    public ConfirmaDTO selectAfiliacion(String idSocio, int referencia, Connection connection) {
        ConfirmaDTO confirma = null;
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "SELECT nombre+' '+apellidos AS nombre FROM scw_afiliacion WITH(NOLOCK) WHERE id_socio = ? AND referencia = ? AND fecha_pago IS NULL";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setInt(2, referencia);
            r = ps.executeQuery();
            if (r.next()) {
                confirma = new ConfirmaDTO();
                confirma.setIdSocio(idSocio);
                confirma.setReferencia(referencia);
                confirma.setNombre(r.getString("nombre"));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return confirma;
    }

    public String getIdSocio(int ultimo, int longitud, String serie) {
        String id = serie;
        int l = serie.length();
        int u = Integer.toString(ultimo).length();
        for (int i = l; i < longitud - u; i++) {
            id += "0";
        }
        return id + ultimo;
    }

    public String getContenidoCorreoAfiliacion(String idSocio, AfiliacionDTO afiliacion, String cuenta, int referencia, String precio, String servidor) {
        String contenido = "<h2>" + afiliacion.getNombre() + " " + afiliacion.getApellidos() + ":</h2><br/><br/>"
                + "Hemos recibido su solicitud de informaci&oacute;n para ser socio en cat&aacute;logos digitales satisfactoriamente.<br/>"
                + "Te informamos que te encuentras en el proceso de inscripci&oacute;n para poder gozar de los beneficios que obtienes al ser Socio en el sitio de internet SCPakar.<br/><br/>"
                + "Informaci&oacute;n necesaria para realizar tu pago:<br/><br/>"
                + "<b>Socio No. " + idSocio + "</b><br/>"
                + "<b>Pago en ventanilla</b><br/>"
                + "Instituci&oacute;n Bancaria BBVA Bancomer<br/>"
                + "Convenio CIE: " + cuenta + "<br/>"
                + "Referencia: " + idSocio + referencia + "<br/>"
                + "Importe: $" + precio + " MXN<br/><br/>"
                + "Una vez realizado su pago haz click <a href='" + servidor + "confirma.jsf?rf=" + idSocio + referencia + "'>aqu&iacute;</a> para ingresar los datos de su pago.<br/><br/><br/>"
                + "Saludos,<br/>"
                + "Shoes Collection Pakar";
        return contenido;
    }

    public String getContenidoCorreoConfirmacion(ConfirmaDTO confirma, String cuenta, String precio, String servidor) {
        String contenido = confirma.getNombre() + " ha realizado el proceso de afiliaci&oacute;n.<br/><br/>"
                + "Informaci&oacute;n de pago:<br/><br/>"
                + "<b>Socio No. " + confirma.getIdSocio() + "</b><br/>"
                + "<b>Pago en ventanilla</b><br/>"
                + "Instituci&oacute;n Bancaria BBVA Bancomer<br/>"
                + "Convenio CIE: " + cuenta + "<br/>"
                + "Referencia: " + confirma.getIdSocio() + confirma.getReferencia() + "<br/>"
                + "Importe: $" + precio + " MXN<br/>"
                + "Fecha de pago: " + Util.formatoFechaDiagonal(confirma.getFecha()) + " <br/>"
                + "<b>Gu&iacute;a CIE: " + confirma.getComprobante() + " <b/><br/><br/>"
                + "Una vez validado la informaci&oacute; haz click <a href='" + servidor + "afilia.jsf?rf=" + confirma.getIdSocio() + confirma.getReferencia() + "'>aqu&iacute;</a> para finalizar el proceso de inscripci&oacute;n del socio.<br/><br/>"
                + "Se adjunta archivo con solicitud de registro.<br/><br/><br/>"
                + "Shoes Collection Pakar";
        return contenido;
    }

    public int getReferencia(String idSocio) {
        //return (int) (Math.round(Math.random()*1000)%10);

        idSocio = Util.analizaLetras(idSocio);
        char[] idReverse = new char[idSocio.length()];
        int x = 0;
        for (int i = 1; i <= idSocio.length(); i++) {
            idReverse[x] = idSocio.toCharArray()[idSocio.toCharArray().length - i];
            x++;
        }

        int res = 0;
        int i = 1;
        for (char c : idReverse) {
            if ((i % 2) == 0) {
                res += Character.getNumericValue(c) * 1;
            } else {
                int mult = Character.getNumericValue(c) * 2;
                if (mult > 9) {
                    String decena = String.valueOf(mult);
                    int sumDec = 0;
                    for (char c1 : decena.toCharArray()) {
                        sumDec += Character.getNumericValue(c1);
                    }
                    res += sumDec;
                } else {
                    res += mult;
                }
            }
            i++;
        }

        return Util.analizaDecenaCercana(res) - res;
    }

}
