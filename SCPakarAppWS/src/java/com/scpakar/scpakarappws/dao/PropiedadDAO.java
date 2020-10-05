package com.scpakar.scpakarappws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
public class PropiedadDAO {

    public String selectPropiedad(String clavePropiedad, Connection connection) {
        String valorPropiedad = "";
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "SELECT valor FROM propiedad WITH(NOLOCK) WHERE clave = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, clavePropiedad);
            r = ps.executeQuery();
            if (r.next()) {
                valorPropiedad = r.getString("valor").trim();
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return valorPropiedad;
    }

    public boolean updatePropiedad(String clave, String valor, Connection connection) {
        boolean ok = false;
        PreparedStatement ps = null;
        String query = "UPDATE propiedad SET valor = ? WHERE clave = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, valor);
            ps.setString(2, clave);
            ok = ps.executeUpdate() >= 0;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return ok;
    }
}
