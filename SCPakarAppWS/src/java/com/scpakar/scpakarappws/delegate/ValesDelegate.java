/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.ValesDAO;
import com.scpakar.scpakarappws.dto.ValesDTO;
import java.sql.Connection;

/**
 *
 * @author alexjandrohum
 */
public class ValesDelegate {
    
    private final ValesDAO valesDAO;
    
    public ValesDelegate(){
        this.valesDAO = new ValesDAO();
    }
    
    public ValesDTO validarVale(String idSocio, String folioVale, float importe, Connection con){
        return valesDAO.validarVale(idSocio, folioVale, importe, con);
    }
    
    public boolean crearLinea(String idLiberacion, int idFormaPago, int linea, float importe, String folio, Connection con){
        return valesDAO.crearLinea(idLiberacion, idFormaPago, linea, importe, folio, con);
    }
    
    public int totalPago(String idLiberacion, Connection con){
        return valesDAO.liveracionPago(idLiberacion, con);
    }
    
}
