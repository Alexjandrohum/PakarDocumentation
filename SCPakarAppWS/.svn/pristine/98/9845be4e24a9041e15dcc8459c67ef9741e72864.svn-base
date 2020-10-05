package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.EstadoDTO;
import com.scpakar.scpakarappws.dto.SucursalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author antonio.ruiz
 */
public class SucursalDAO {

    public List<SucursalDTO> selectSucursales(Connection con) {
        Statement st = null;
        ResultSet r = null;
        List<SucursalDTO> sucursales = new ArrayList<>();
        String query = "SELECT Latitud,Longitud,Whatsapp,Telefono,SinCosto,HorarioAtencion,NombrePublico,Tipo,Direccion,e.NombreEstado   "
                + "FROM almacen_informacion ai WITH(NOLOCK)  "
                + "INNER JOIN almacen a WITH(NOLOCK) ON ai.IdAlmacen = a.IdAlmacen "
                + "INNER JOIN estado e WITH(NOLOCK) ON a.EntidadFederativa = e.IdEstado";
        try {
            st = con.createStatement();
            r = st.executeQuery(query);
            while (r.next()) {
                SucursalDTO sucursal = new SucursalDTO();
                sucursal.setNombreEstado(r.getString("NombreEstado"));
                sucursal.setLatitud(r.getFloat("Latitud"));
                sucursal.setLongitud(r.getFloat("Longitud"));
                sucursal.setWhatsapp(r.getString("Whatsapp"));
                sucursal.setTelefono(r.getString("Telefono"));
                sucursal.setTelSinCosto(r.getString("SinCosto"));
                sucursal.setHorarioAtencion(r.getString("HorarioAtencion"));
                sucursal.setNombrePublico(r.getString("NombrePublico"));
                sucursal.setTipo(r.getString("Tipo"));
                sucursal.setDireccion(r.getString("Direccion"));
                sucursales.add(sucursal);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(st);
        }
        return sucursales;
    }

    public List<EstadoDTO> getEstadoSucursales(String pais, Connection con) {
        PreparedStatement ps = null;
        ResultSet r = null;
        List<EstadoDTO> estados = new ArrayList<>();
        String query = "SELECT UPPER(e.NombreEstado) AS NombreEstado , e.IdEstado  "
                + "FROM almacen a WITH(NOLOCK)  "
                + "INNER JOIN estado e WITH(NOLOCK) ON a.EntidadFederativa = e.IdEstado  "
                + "INNER JOIN pais p WITH(NOLOCK) ON e.IdPais = p.IdPais   "
                + "WHERE a.Abierta = 1  AND a.Tipo IN ('CT','CA') AND p.IdPais = ? "
                + "AND a.IdAlmacen IN (SELECT DISTINCT IdAlmacenDestino FROM logistica_almacen WITH(NOLOCK)) "
                + "AND a.IdAlmacen NOT IN (SELECT IdAlmacen FROM scp_oculta_almacen WITH(NOLOCK)) "
                + "GROUP BY e.NombreEstado, e.IdEstado";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, pais);
            r = ps.executeQuery();
            while (r.next()) {
                EstadoDTO estado = new EstadoDTO();
                estado.setNombreEstado(r.getString("NombreEstado"));
                estado.setSucursales(selectSucursales(con, r.getString("IdEstado")));
                estados.add(estado);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return estados;
    }

    private List<SucursalDTO> selectSucursales(Connection con, String estado) {
        PreparedStatement ps = null;
        ResultSet r = null;
        List<SucursalDTO> sucursales = new ArrayList<>();
        String query = "SELECT a.IdAlmacen, a.NombrePublico, a.Tipo, a.Direccion "
                + "FROM almacen a WITH(NOLOCK) "
                + "INNER JOIN estado e WITH(NOLOCK) ON a.EntidadFederativa = e.IdEstado  "
                + "INNER JOIN pais p WITH(NOLOCK) ON e.IdPais = p.IdPais  "
                + "WHERE a.Abierta = 1  AND a.Tipo IN ('CT','CA') AND e.IdEstado = ? "
                + "AND a.IdAlmacen IN (SELECT DISTINCT IdAlmacenDestino FROM logistica_almacen WITH(NOLOCK)) "
                + "AND a.IdAlmacen NOT IN (SELECT IdAlmacen FROM scp_oculta_almacen WITH(NOLOCK)) "
                + "ORDER BY a.EntidadFederativa, a.Tipo DESC";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, estado);
            r = ps.executeQuery();
            while (r.next()) {
                SucursalDTO sucursal = new SucursalDTO();
                sucursal.setDireccion(r.getString("Direccion"));
                sucursal.setIdAlmacen(r.getString("IdAlmacen"));
                sucursal.setNombrePublico(r.getString("NombrePublico"));
                sucursal.setTipo(r.getString("Tipo"));
                sucursales.add(sucursal);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);

        }
        return sucursales;
    }

}
