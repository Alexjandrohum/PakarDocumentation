/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.ValesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author alexjandrohum
 */
public class ValesDAO {
    
    public ValesDTO validarVale(String idSocio, String folioVale, float importe, Connection con){
        ValesDTO vales = new ValesDTO();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String query = "EXEC [scpakar_spValidaVale] ?, ?, ?";
        try{
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, folioVale);
            ps.setFloat(3, importe);
            rs = ps.executeQuery();
            if (rs.next()) {
                vales.setValido(rs.getBoolean("Valido"));
                vales.setResultado(rs.getString("Resultado"));
            }
        }catch(SQLException ex){
            Util.registraError(ex);
        }catch(Exception ex){
            Util.registraError(ex);
        }finally{
            Factory.close(rs);
            Factory.close(ps);
        }
        return vales;
    }
    
    public boolean crearLinea(String idLiberacion, int idFormaPago, int linea, float importe, String folio, Connection con){
        boolean crear = false;
        PreparedStatement ps = null;
        String query = "INSERT INTO scp_LiberacionPago(IdLiberacion,IdFormaPago,Linea,Importe,Folio,Comprobante) VALUES(?,?,?,?,?,?)";
        try{
            ps = con.prepareStatement(query);
            ps.setString(1, idLiberacion);
            ps.setInt(2, idFormaPago);
            ps.setInt(3, maxLinea(idLiberacion, idFormaPago, con));
            ps.setFloat(4, importe);
            ps.setString(5, folio);
            crear = ps.executeUpdate() == 1;
            
        }catch(SQLException ex){
            Util.registraError(ex);
        }catch(Exception ex){
            Util.registraError(ex);
        }finally{
            Factory.close(ps);
        }
        return crear;
    }

    public int maxLinea(String idLiberacion, int idFormaPago, Connection con){
        int lineas = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT (ISNULL(MAX(Linea),0)+1) AS Linea FROM scp_LiberacionPago WHERE IdLiberacion=? AND IdFormaPago =?";
        try{
            ps = con.prepareStatement(query);
            ps.setString(1, idLiberacion);
            ps.setInt(2, idFormaPago);//3
            rs = ps.executeQuery();
            if (rs.next()) {
                lineas = rs.getInt("Linea");
            }
        }catch(SQLException ex){
            Util.registraError(ex);
        }catch(Exception ex){
            Util.registraError(ex);
        }finally{
            Factory.close(ps);
        }
        return lineas;
    }
    
    public int liveracionPago(String idLiberacion, Connection con){
        int liberacion = 0;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String query = "EXEC [scpakar_spActTotalPagarLiberacion] ?";
        try{
            ps = con.prepareStatement(query);
            ps.setString(1, idLiberacion);
            rs = ps.executeQuery();
            if (rs.next()) {
                liberacion = rs.getInt("TotalPagado");
            }
        }catch(SQLException ex){
            Util.registraError(ex);
        }catch(Exception ex){
            Util.registraError(ex);
        }finally{
            Factory.close(rs);
            Factory.close(ps);
        }
        return liberacion;
    }
}

/*
            USE [scpakar]
            GO
            DECLARE @RC int
            DECLARE @pcIdSocio varchar(25)
            DECLARE @pcNumVale varchar(30)
            DECLARE @pnImporte decimal(19,4)
            -- TODO: Set parameter values here.
            EXECUTE @RC = [dbo].[scpakar_spValidaVale] 
               @pcIdSocio
              ,@pcNumVale
              ,@pnImporte
            GO

             */
