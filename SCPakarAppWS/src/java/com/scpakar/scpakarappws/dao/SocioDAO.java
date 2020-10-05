package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.AppOrigen;
import com.scpakar.scpakarappws.dto.ClienteDTO;
import com.scpakar.scpakarappws.dto.ErrorDTO;
import com.scpakar.scpakarappws.dto.SocioDTO;
import com.scpakar.scpakarappws.util.Formato;
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
public class SocioDAO {

    public ErrorDTO nuevoCliente(String nombre, int porcentaje, String idSocio, String telefono, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ErrorDTO error = new ErrorDTO();
        String query = "EXEC [scpakar_nuevo_cliente_app] ?,?,?,?,?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "");
            ps.setString(2, nombre);
            ps.setInt(3, porcentaje);
            ps.setString(4, idSocio);
            ps.setString(5, telefono);
            rs = ps.executeQuery();
            if (rs.next()) {
                error.setErrorIdCliente(rs.getBoolean("ErrorIdCliente"));
                error.setExito(rs.getBoolean("Exito"));
                error.setNuevoCliente(rs.getString("NuevoIdCliente").trim());
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return error;
    }

    public List<ClienteDTO> getClientesSocio(String idSocio, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ClienteDTO> clientes = new ArrayList<>();
        String query = "SELECT IdCliente, Nombre, Porcentaje, Telefono FROM scp_cliente WITH(NOLOCK) "
                + "WHERE IdSocio = ? AND Activo = 1";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setIdCliente(rs.getString("IdCliente"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setPorcentaje(rs.getInt("Porcentaje"));
                cliente.setTelefono(rs.getString("Telefono"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return clientes;
    }

    public boolean registraTokenSocio(String idSocio, String token, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean res = false;
        String query = "EXEC scpakar_registra_token ?,?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                res = true;
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return res;
    }

    public SocioDTO validaSocio(String idSocio, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        SocioDTO socio = new SocioDTO();
        String query
                = "SELECT TOP 1 s.Nombre,s.Bloqueado,s.FechaAlta "
                + "FROM socio s WITH(NOLOCK) "
                + "INNER JOIN socio_password sp WITH(NOLOCK) "
                + "ON s.IdSocio = sp.IdSocio "
                + "WHERE s.IdSocio = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            rs = ps.executeQuery();
            if (rs.next()) {
                socio.setIdSocio(idSocio);
                socio.setNombre(rs.getString("Nombre"));
                socio.setFechaAlta(Formato.formatoFechaDiagonal(rs.getString("FechaAlta")));
                socio.setBloqueado((rs.getInt("Bloqueado") != 0));
            } else {
                socio.setIdSocio(idSocio);
                socio.setBloqueado(true);
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

    public ErrorDTO modificarCliente(String idCliente, String nombre, int porcentaje, String idSocio, String telefono, boolean activo, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ErrorDTO error = new ErrorDTO();
        String query = "EXEC [scpakar_modifica_cliente_app] ?,?,?,?,?,?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idCliente);
            ps.setString(2, nombre);
            ps.setInt(3, porcentaje);
            ps.setString(4, idSocio);
            ps.setString(5, telefono);
            ps.setBoolean(6, activo);
            rs = ps.executeQuery();
            if (rs.next()) {
                error.setErrorIdCliente(rs.getBoolean("ErrorIdCliente"));
                error.setExito(rs.getBoolean("Exito"));
                error.setErrorActivado(rs.getBoolean("errorActivo"));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return error;
    }

    public boolean validaVersion(String version, AppOrigen origen, Connection con) {
        Statement st = null;
        ResultSet rs = null;
        boolean resultado = false;
        String query = "";
        switch (origen) {
            case ANDROID:
                query = "SELECT valor FROM propiedad WITH(NOLOCK) WHERE clave = 'Version Android'";
                break;
            case IOS:
                query = "SELECT valor FROM propiedad WITH(NOLOCK) WHERE clave = 'Version iOS'";
                break;
        }
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                String[] versionApp = version.split("\\.");
                String[] versionSql = rs.getString(1).split("\\.");
                int length = Math.max(versionApp.length, versionSql.length);
                for (int i = 0; i < length; i++) {
                    int app = i < versionApp.length
                            ? Integer.parseInt(versionApp[i]) : 0;
                    int sql = i < versionSql.length
                            ? Integer.parseInt(versionSql[i]) : 0;
                    if (app < sql) {
                        resultado = true;
                        break;
                    }
                    if (app > sql) {
                        resultado = false;
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(st);
        }
        return resultado;
    }

    public SocioDTO buscaSocioActivo(String idSocio, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        SocioDTO socio = null;
        String query
                = "SELECT TOP 1 ac.Serie, ac.Codigo "
                + "FROM scp_activacion_codigo ac WITH(NOLOCK) "
                + "INNER JOIN socio s WITH(NOLOCK) "
                + "ON ac.IdSocio = s.IdSocio "
                + "WHERE ac.IdSocio = ? AND s.bloqueado = 0 "
                + "ORDER BY ac.Fecha DESC";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            rs = ps.executeQuery();
            if (rs.next()) {
                socio = new SocioDTO();
                socio.setIdSocio(idSocio);
                socio.setSerie(rs.getString("Serie"));
                socio.setCodigo(rs.getString("Codigo"));
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

    public List<String> getOpcionesCancelar(Connection con) {
        Statement st = null;
        ResultSet rs = null;
        List<String> opciones = new ArrayList<>();
        String query = "SELECT Mensaje FROM scp_cancela_opcion WITH(NOLOCK) "
                + "WHERE Activo = 1";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                opciones.add(rs.getString("Mensaje"));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(st);
        }
        return opciones;
    }
}
