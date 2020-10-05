/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupopakar.grupopakarappws.delegate;

import com.grupopakar.grupopakarappws.dao.ComentarioDAO;
import com.grupopakar.grupopakarappws.dao.LoginDAO;
import com.grupopakar.grupopakarappws.dto.ComentarioDTO;
import com.grupopakar.grupopakarappws.dto.ConstanteDTO;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.dto.MensajeDTO;
import com.grupopakar.grupopakarappws.util.EstadoHTTPEnum;
import com.grupopakar.grupopakarappws.util.GsonUtil;
import java.sql.Connection;
import java.util.List;
import mx.com.pakar.util.Util;

/**
 *
 * @author carlos.juarez
 */
public class ComentarioDelegate {

    public ComentarioDAO comentarioDao;

    public ComentarioDelegate() {
        this.comentarioDao = new ComentarioDAO();
    }

    public EstadoDTO getComentarios(String idTienda, String token, Connection con) {
        EstadoDTO estado = new EstadoDTO();
        if (LoginDAO.validaToken(token, con)) {
            List<ComentarioDTO> comentarios = comentarioDao.getComentarios(Integer.valueOf(idTienda), con);
            if (!comentarios.isEmpty()) {
                estado.setEstado(EstadoHTTPEnum.OK);
                estado.setObjeto(GsonUtil.gson.toJson(comentarios));
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ENCONTRADO);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("No existen comentarios para la tienda indicada")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token invalido")));
        }
        return estado;
    }

    public EstadoDTO registraComentario(ComentarioDTO comentario, String token, Connection con) {
        EstadoDTO estado = new EstadoDTO();
        try {

            if (LoginDAO.validaToken(token, con)) {
                int idNotificacion = 0;
                if (comentario.getIdComentarioPadre() > 0) {
                    // Es la respuesta a un comentario
                    idNotificacion = comentarioDao.registraNotificacion(ConstanteDTO.TIPO_NOTIFICACION_RESPUESTA_COMENTARIO, con);
                    if (comentarioDao.registraComentario(comentario, token, idNotificacion, con)) {
                        estado.setEstado(EstadoHTTPEnum.OK);
                        estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Comentario registrado")));
                    } else {
                        estado.setEstado(EstadoHTTPEnum.PETICION_ERROR);
                        estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Ocurrio un error al realizar el registro del comentario")));
                    }
                } else {
                    // No es la respuesta de algun comentario
                    if (comentarioDao.isSupervisor(comentario.getIdTienda(), token, con)) {
                        // Cuando el usuario que captura el comentario NO es el supervisor de la tienda
                        idNotificacion = comentarioDao.registraNotificacion(ConstanteDTO.TIPO_NOTIFICACION_NUEVO_COMENTARIO, con);
                        if (comentarioDao.registraComentario(comentario, token, idNotificacion, con)) {
                            estado.setEstado(EstadoHTTPEnum.OK);
                            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Comentario registrado")));
                        } else {
                            estado.setEstado(EstadoHTTPEnum.PETICION_ERROR);
                            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Ocurrio un error al realizar el registro del comentario")));
                        }
                    } else {
                        // Es un comentario escrito por el mismo supervisor de la tienda
                        if (comentarioDao.registraComentario(comentario, token, idNotificacion, con)) {
                            estado.setEstado(EstadoHTTPEnum.OK);
                            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Comentario registrado")));
                        } else {
                            estado.setEstado(EstadoHTTPEnum.PETICION_ERROR);
                            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Ocurrio un error al realizar el registro del comentario")));
                        }
                    }
                }
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token invalido")));
            }
        } catch (Exception e) {
            Util.registraError(e);
        } finally {

        }
        return estado;
    }

}
