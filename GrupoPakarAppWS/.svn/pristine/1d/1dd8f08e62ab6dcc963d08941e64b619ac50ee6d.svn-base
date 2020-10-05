/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupopakar.grupopakarappws.delegate;

import com.grupopakar.grupopakarappws.dao.LoginDAO;
import com.grupopakar.grupopakarappws.dao.NotificacionesDAO;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.dto.MensajeDTO;
import com.grupopakar.grupopakarappws.dto.NotificacionDTO;
import com.grupopakar.grupopakarappws.util.EstadoHTTPEnum;
import com.grupopakar.grupopakarappws.util.GsonUtil;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosa.zalas
 */
public class NotificacionesDelegate {
    
    private final NotificacionesDAO notificacionesDAO;
    
    public NotificacionesDelegate(){
        this.notificacionesDAO = new NotificacionesDAO();
    }
    
    public EstadoDTO obtenerNotificacionesPendientes(String tokenDeUsuario, Connection con) {
        List<NotificacionDTO> lista = new ArrayList<NotificacionDTO>();
        EstadoDTO estado = new EstadoDTO();        
        if (LoginDAO.validaToken(tokenDeUsuario, con)) {
            lista = notificacionesDAO.obtenerNotificacionesPendientes(tokenDeUsuario, con);
             if (!lista.isEmpty()) {
                estado.setEstado(EstadoHTTPEnum.OK);
                estado.setObjeto(GsonUtil.gson.toJson(lista));
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ACEPTABLE);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("No existen notificaciones para el usuario.")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token inválido.")));
        }
        return estado;
    }
    
    public EstadoDTO revisaNotificacion(String tokenDeUsuario, int idNotificacion, Connection con){
        EstadoDTO estado = new EstadoDTO();    
        String respuesta = "";
        if (LoginDAO.validaToken(tokenDeUsuario, con)) {
            respuesta = notificacionesDAO.revisaNotificacion(idNotificacion, con);
             if (!respuesta.isEmpty()) {
                estado.setEstado(EstadoHTTPEnum.OK);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO(respuesta)));
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ACEPTABLE);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Ocurrió un error al realizar la actualización.")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token inválido.")));
        }
        return estado;
    }
}
