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
public class PreguntaDAO {

    public String getListaCatalogos(String temporada, Connection connection) {
        String catalogos = "";
        PreparedStatement ps = null;
        ResultSet r = null;
        String qry = "SELECT	"
                + "     CASE WHEN catalogo = 'NINA' THEN 'NIÑA' ELSE "
                + "		CASE WHEN catalogo = 'NINO' THEN 'NIÑO' ELSE catalogo"
                + "		END "
                + "     END + ', ' AS catalogos "
                + "FROM catalogo "
                + "WHERE temporada = ? "
                + "	AND visible = 1 AND catalogo <> 'DESCUENTOS' "
                + "ORDER BY orden";
        try {
            ps = connection.prepareStatement(qry);
            ps.setString(1, temporada);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                catalogos += rs.getString("catalogos");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return catalogos.substring(0, catalogos.length() - 2);
    }
}
