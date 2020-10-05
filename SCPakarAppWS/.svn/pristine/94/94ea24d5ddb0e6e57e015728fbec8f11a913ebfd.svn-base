package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.CabeceraCuponDTO;
import com.scpakar.scpakarappws.dto.CuponDTO;
import com.scpakar.scpakarappws.dto.EstatusCupon;
import com.scpakar.scpakarappws.dto.PaqueteriaDTO;
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
public class CuentaDAO {

    public List<CabeceraCuponDTO> getCupones(String idSocio, Connection connection) {
        List<CabeceraCuponDTO> cupones = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "SELECT p.IdSocio, ISNULL(p.Vigente,0) + ISNULL(p.Canjeado, 0) AS Vigente,  "
                + "ISNULL(p.Vencido, 0) AS Vencido, ISNULL(-p.Canjeado, 0) AS Canjeado,  "
                + "ISNULL(-p.Canjeado,0) + ISNULL(p.Vigente,0) + ISNULL(p.Vencido,0) + ISNULL(p.Canjeado,0) AS Historico  "
                + "FROM (  "
                + "SELECT ISNULL(cs.IdSocio,?)AS IdSocio, SUM(cs.Cantidad) AS Cantidad,  "
                + "CASE WHEN cs.TipoMovimiento = 0 AND cs.Fecha < c.FechaFinal THEN 'VIGENTE'   "
                + "WHEN cs.TipoMovimiento = 1 THEN 'CANJEADO'   "
                + "WHEN cs.Fecha > c.FechaFinal THEN 'VENCIDO'  "
                + "END AS Tipo  "
                + "FROM cupon c WITH(NOLOCK)  "
                + "LEFT JOIN cupon_socio cs WITH(NOLOCK)   "
                + "ON c.IdCupon = cs.IdCupon  "
                + "WHERE cs.IdSocio = ? OR cs.IdSocio IS NULL "
                + "GROUP BY cs.IdSocio, cs.TipoMovimiento, cs.Fecha, c.FechaFinal  "
                + ") AS c pivot (  "
                + "    SUM(c.Cantidad) FOR c.Tipo IN ([Vigente],[Vencido],[Canjeado],[Historial])  "
                + ") AS p";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, idSocio);
            r = ps.executeQuery();
            if (r.next()) {
                cupones.add(new CabeceraCuponDTO(r.getInt("Vigente"), EstatusCupon.VIGENTE, selectCupon(idSocio, EstatusCupon.VIGENTE, connection)));
                cupones.add(new CabeceraCuponDTO(r.getInt("Canjeado"), EstatusCupon.CANJEADO, selectCupon(idSocio, EstatusCupon.CANJEADO, connection)));
                cupones.add(new CabeceraCuponDTO(r.getInt("Vencido"), EstatusCupon.VENCIDO, selectCupon(idSocio, EstatusCupon.VENCIDO, connection)));
                List<CuponDTO> cuponesTemporal = new ArrayList<>();
                for (CabeceraCuponDTO c : cupones) {
                    cuponesTemporal.addAll(c.getCupones());
                }
                cupones.add(new CabeceraCuponDTO(r.getInt("Historico"), EstatusCupon.HISTORIAL, cuponesTemporal));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return cupones;
    }

    private List<CuponDTO> selectCupon(String idSocio, EstatusCupon estatusCupon, Connection connection) {
        List<CuponDTO> cupones = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "";
        try {
            switch (estatusCupon) {
                case VIGENTE:
                    query = "SELECT cp.IdCupon, cp.Nombre, REPLACE(cp.Politicas, 'Válido a partir de ', '') AS Vigencia,    "
                            + "SUM(CASE WHEN TipoMovimiento = 0 THEN cs.Cantidad ELSE 0 END) AS Entregados,   "
                            + "SUM(CASE WHEN TipoMovimiento = 1 THEN cs.Cantidad ELSE 0 END) AS Aplicados,   "
                            + "SUM(cs.Cantidad) AS Disponibles, cp.Imagen "
                            + "FROM cupon cp WITH(NOLOCK)   "
                            + "INNER JOIN cupon_socio cs WITH(NOLOCK)   "
                            + "ON cp.IdCupon = cs.IdCupon   "
                            + "WHERE cs.IdSocio = ? "
                            + "AND GETDATE() BETWEEN cp.FechaInicial AND cp.FechaFinal "
                            + "GROUP BY cp.IdCupon, cp.Nombre, cp.Imagen, cp.Politicas ";
                    break;
                case CANJEADO:
                    query = "SELECT cp.IdCupon, cp.Nombre, CONVERT(VARCHAR,cs.Fecha,103) AS Vigencia,   "
                            + "SUM(CASE WHEN TipoMovimiento = 0 THEN cs.Cantidad ELSE 0 END) AS Entregados,   "
                            + "-SUM(CASE WHEN TipoMovimiento = 1 THEN cs.Cantidad ELSE 0 END) AS Disponibles,   "
                            + "SUM(cs.Cantidad) AS Aplicados, cp.Imagen "
                            + "FROM cupon cp WITH(NOLOCK)   "
                            + "INNER JOIN cupon_socio cs WITH(NOLOCK)   "
                            + "ON cp.IdCupon = cs.IdCupon   "
                            + "WHERE cs.IdSocio = ? "
                            + "GROUP BY cp.IdCupon, cp.Nombre, cp.Imagen,  CONVERT(VARCHAR,cs.Fecha,103) "
                            + "HAVING -SUM(CASE WHEN TipoMovimiento = 1 THEN cs.Cantidad ELSE 0 END) > 0";
                    break;
                case VENCIDO:
                    query = "SELECT cp.IdCupon, cp.Nombre, CONVERT(VARCHAR,cp.FechaFinal,103) AS Vigencia,   "
                            + "SUM(CASE WHEN TipoMovimiento = 0 THEN cs.Cantidad ELSE 0 END) AS Entregados,   "
                            + "SUM(CASE WHEN TipoMovimiento = 1 THEN cs.Cantidad ELSE 0 END) AS Aplicados,   "
                            + "SUM(cs.Cantidad) AS Disponibles, cp.Imagen "
                            + "FROM cupon cp WITH(NOLOCK)   "
                            + "INNER JOIN cupon_socio cs WITH(NOLOCK)   "
                            + "ON cp.IdCupon = cs.IdCupon   "
                            + "WHERE cs.IdSocio = ? "
                            + "AND cs.Fecha > cp.FechaFinal "
                            + "GROUP BY cp.IdCupon, cp.Nombre, cp.Imagen, cp.FechaFinal";
                    break;
            }
            ps = connection.prepareStatement(query);
            ps.setString(1, idSocio);
            r = ps.executeQuery();
            while (r.next()) {
                CuponDTO c = new CuponDTO();
                c.setImagen(r.getString("imagen"));
                c.setVigencia(r.getString("vigencia").toUpperCase()); //Util.format
                c.setDisponibles(r.getInt("disponibles"));
                c.setEntregados(r.getInt("entregados"));
                c.setEstatus(estatusCupon);
                cupones.add(c);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return cupones;
    }

    public List<PaqueteriaDTO> validaCodigoPostal(String cp, Connection con) {
        String query = "SELECT DISTINCT s.Colonia, s.Ciudad, s.estado, ISNULL(e.IdAlmacen,'') AS IdAlmacen "
                + " FROM sepomex s WITH(NOLOCK) "
                + "  LEFT JOIN scp_CodigosEstafeta e ON e.CodigoPostal = s.Cp COLLATE Latin1_General_CI_AS "
                + " WHERE s.Cp = ? "
                + " ORDER BY s.Colonia;";
        List<PaqueteriaDTO> datos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet r = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, cp);
            r = ps.executeQuery();
            while (r.next()) {
                PaqueteriaDTO d = new PaqueteriaDTO();
                d.setCiudad(r.getString("Ciudad").trim());
                d.setColonia(r.getString("Colonia").trim());
                d.setEstado(r.getString("Estado").trim());
                d.setIdAlmacen(r.getString("IdAlmacen").trim());
                datos.add(d);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return datos;
    }

    public boolean registraCP(Connection con, String idSocio, String cp) {
        String query = "INSERT INTO scp_historico_cp_socio VALUES (?,?, GETDATE())";
        boolean res = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, cp);
            res = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return res;
    }
}
