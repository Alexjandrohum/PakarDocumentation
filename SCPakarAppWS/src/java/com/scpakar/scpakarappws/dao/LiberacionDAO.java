package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.ConsultaDTO;
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
 * @author antonio.ruiz
 */
public class LiberacionDAO {

    public List<ConsultaDTO> getProductosDisponibles(Connection con, String idSocio, String idPedido) {
        List<ConsultaDTO> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = " EXEC [dbo].[scpakar_app_trae_disponible] ?,? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, idPedido);
            rs = ps.executeQuery();
            while (rs.next()) {
                ConsultaDTO dto = new ConsultaDTO();
                dto.setIdPedido(rs.getString("IdPedido").trim());
                dto.setLinea(rs.getInt("Linea"));
                dto.setFoto(rs.getString("Imagen").trim());
                dto.setLlave(rs.getString("Llave").trim());
                dto.setTalla(rs.getString("Talla").trim());
                dto.setTallaVisible(rs.getString("TallaVisible").trim());
                dto.setMarca(rs.getString("Marca").trim());
                dto.setNombreMarca(rs.getString("MarcaCompleta").trim().replace("�", ""));
                dto.setModelo(rs.getString("Modelo").trim());
                dto.setColor(rs.getString("Color").trim());
                dto.setArticulo(rs.getString("Descripcion").trim());
                dto.setCodigo(rs.getString("Codigo").trim());
                dto.setPrecio(rs.getDouble("Precio"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

}
