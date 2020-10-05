package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.SocioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author antonio.ruiz
 */
public class LoginDAO {

    public SocioDTO validaCorreo(String email, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        SocioDTO socio = new SocioDTO();
        String query
                = "SELECT TOP 1 ac.IdSocio,s.Nombre,s.Bloqueado "
                + "FROM scp_activacion_codigo ac WITH(NOLOCK)  "
                + "INNER JOIN socio s WITH(NOLOCK)   "
                + "ON ac.IdSocio = s.IdSocio  "
                + "INNER JOIN socio_password sp WITH(NOLOCK)  "
                + "ON sp.IdSocio = s.IdSocio  "
                + "WHERE sp.Correo = ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                socio.setCorreo(email);
                socio.setIdSocio(rs.getString("IdSocio"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setBloqueado((rs.getInt("Bloqueado") != 0));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return socio;
    }

    public String selectPaginaAfiliate(Connection con) {
        Statement st = null;
        ResultSet rs = null;
        String url = "";
        String query = "SELECT valor as URL "
                + "FROM propiedad WITH(NOLOCK) "
                + "WHERE clave = 'Pagina Afiliate'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                url = rs.getString("URL");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(st);
        }
        return url;
    }

    public SocioDTO login(String usuario, String password, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        SocioDTO socio = new SocioDTO();
        String query = "SELECT TOP 1 s.IdSocio, s.Nombre, ISNULL(sp.Correo, 'SIN_REGISTRO') AS Correo, s.Bloqueado "
                + "FROM socio s WITH(NOLOCK) "
                + "LEFT JOIN scp_activacion_codigo ac WITH(NOLOCK)  "
                + "ON ac.IdSocio = s.IdSocio  "
                + "LEFT JOIN socio_password sp WITH(NOLOCK) "
                + "ON sp.IdSocio = s.IdSocio "
                + "WHERE s.IdSocio = ? AND (sp.Password = ? OR sp.Password IS NULL)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                socio.setCorreo(rs.getString("Correo").trim());
                socio.setIdSocio(socio.getCorreo().equals("SIN_REGISTRO") ? "" : rs.getString("IdSocio"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setBloqueado((rs.getInt("Bloqueado") != 0));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return socio;
    }

    public SocioDTO recuperaPassword(String idSocio, String correo, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        SocioDTO socio = new SocioDTO();
        String query = "SELECT TOP 1 ac.IdSocio,s.Nombre,sp.Correo,s.Bloqueado  "
                + "FROM scp_activacion_codigo ac WITH(NOLOCK)  "
                + "INNER JOIN socio s WITH(NOLOCK)   "
                + "ON ac.IdSocio = s.IdSocio   "
                + "INNER JOIN socio_password sp WITH(NOLOCK)  "
                + "ON sp.IdSocio = s.IdSocio  "
                + "WHERE sp.IdSocio = ? AND sp.Correo = ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                socio.setIdSocio(rs.getString("IdSocio"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setCorreo(rs.getString("Correo"));
                socio.setBloqueado((rs.getInt("Bloqueado") != 0));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return socio;
    }

    public String getContenidoCorreoRecuperacion(SocioDTO socio, String password) {
        String contenido = "<h2>" + socio.getNombre() + ":</h2><br/><br/>"
                + "Hemos recibido su solicitud de restablecimiento de contraseña.<br/>"
                + "Por seguridad se le ha generado una nueva contraseña temporal, la cual le recomendamos cambiar desde el módulo de configuración de su cuenta.<br/><br/>"
                + "<h2>"
                + "<b>Socio No. " + socio.getIdSocio() + "</b><br/>"
                + "Contraseña Temporal: " + password + "<br/>"
                + "</h2>"
                + "<br/>"
                + "<h2><b><font color=\"blue\">Shoes Collection Pakar</font></b></h2>";
        return contenido;
    }

    public boolean actualizaPassword(String idSocio, String password, Connection con) {
        boolean result = false;
        PreparedStatement ps = null;
        String query = "UPDATE socio_password SET [Password] = ? WHERE IdSocio = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, idSocio);
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

    public boolean actualizaPassword(String idSocio, String password, String passwordActual, Connection con) {
        boolean result = false;
        PreparedStatement ps = null;
        String query = "UPDATE socio_password SET [Password] = ? WHERE IdSocio = ? AND [Password] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, idSocio);
            ps.setString(3, passwordActual);
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
}
