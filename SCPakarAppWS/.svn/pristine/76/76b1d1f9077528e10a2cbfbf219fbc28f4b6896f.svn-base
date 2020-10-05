package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.ErrorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author antonio.ruiz
 */
public class RegistroDAO {

    public ErrorDTO activaSocio(String email, String idSocio, String password,
            String codigo, String serie, boolean reEmail, int origen, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ErrorDTO registro = new ErrorDTO();
        String query
                = "EXEC [scpakar_activacion_app] ?, ?, ?, ?, ?, ?, ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, password);
            ps.setString(3, serie);
            ps.setString(4, codigo);
            ps.setString(5, email);
            ps.setBoolean(6, reEmail);
            ps.setInt(7, origen);
            rs = ps.executeQuery();
            if (rs.next()) {
                registro.setErrorActivado(rs.getBoolean("ErrorActivado"));
                registro.setErrorCodigo(rs.getBoolean("ErrorCodigo"));
                registro.setErrorSerie(rs.getBoolean("ErrorSerie"));
                registro.setErrorSocio(rs.getBoolean("ErrorSocio"));
                registro.setExito(rs.getBoolean("Exito"));
                registro.setErrorBloqueoSocio(rs.getBoolean("ErrorBloqueoSocio"));
                registro.setReEmail(rs.getBoolean("BanderaEmail"));
                registro.setExistePassword(rs.getBoolean("ExistePassword"));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return registro;
    }
}
