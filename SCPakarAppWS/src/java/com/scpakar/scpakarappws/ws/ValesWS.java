/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scpakar.scpakarappws.ws;

import com.scpakar.scpakarappws.delegate.ValesDelegate;
import com.scpakar.scpakarappws.dto.ValesDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author alexjandrohum
 */
@Path("vales")
public class ValesWS {

    private final ValesDelegate delegate;

    public ValesWS() {
        delegate = new ValesDelegate();
    }

    @GET
    @Path("validar")
    public ValesDTO validarVale(@QueryParam("idSocio") String idSocio,
            @QueryParam("folioVale") String folioVale,
            @QueryParam("importe") Float importe) {
        ValesDTO vales = new ValesDTO();
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()
                    && folioVale != null && !folioVale.isEmpty()
                    && importe != null && importe > 0) {
                con = Factory.getConnection(Configuracion.getJndi());
                vales = delegate.validarVale(idSocio, folioVale, importe, con);
                if (vales.isValido()) {

                }
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return vales;
    }
    //String idLiberacion, int idFormaPago, int linea, float importe, String folio, Connection con

    @GET
    @Path("linea")
    public boolean insertarLinea(@QueryParam("idSocio") String idSocio,
            @QueryParam("folioVale") String folioVale,
            @QueryParam("importe") Float importe, @QueryParam("idLiberacion") String idLiberacion, @QueryParam("idFormaPago") int idFormaPago, @QueryParam("linea") int linea) {
        boolean insertar = false;
        int total = 0;
        ValesDTO vales = new ValesDTO();
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()
                    && folioVale != null && !folioVale.isEmpty()
                    && importe != null && importe > 0) {
                con = Factory.getConnection(Configuracion.getJndi());
                vales = delegate.validarVale(idSocio, folioVale, importe, con);
                if (vales.isValido()) {
                    insertar = delegate.crearLinea(idLiberacion, idFormaPago, linea, importe, folioVale, con);
                    if (insertar) {
                        total = delegate.totalPago(idLiberacion, con);
                    }
                }
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return insertar;
    }

    @GET
    @Path("total")
    public int total(@QueryParam("idLiberacion") String idLiberacion) {
        int total = 0;
        Connection con = null;
        try {
            if (idLiberacion != null && !idLiberacion.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                
                total = delegate.totalPago(idLiberacion, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return total;
    }
}
