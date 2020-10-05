package com.grupopakar.grupopakarappws.delegate;

import com.grupopakar.grupopakarappws.dao.LoginDAO;
import com.grupopakar.grupopakarappws.dao.TareaDAO;
import com.grupopakar.grupopakarappws.dto.ActividadDTO;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.dto.InfoTareaDTO;
import com.grupopakar.grupopakarappws.dto.MensajeDTO;
import com.grupopakar.grupopakarappws.util.EstadoHTTPEnum;
import com.grupopakar.grupopakarappws.util.GsonUtil;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author alberto.quirino
 */
public class TareaDelegate {

    private TareaDAO dao;

    public TareaDelegate() {
        dao = new TareaDAO();
    }

    public EstadoDTO obtieneActividades(String token, int idTienda, 
            Connection con) {
        EstadoDTO estado = new EstadoDTO();
        if (LoginDAO.validaToken(token, con)) {
            List<ActividadDTO> actividades = dao.getActividades(idTienda, con);
            if (!actividades.isEmpty()) {
                estado.setEstado(EstadoHTTPEnum.OK);
                estado.setObjeto(GsonUtil.gson.toJson(actividades));
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ENCONTRADO);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("No hay actividades")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token inválido")));
        }
        return estado;
    }
    
    public EstadoDTO actualizaEstatusTarea(String token, InfoTareaDTO dto, 
            Connection con) {
        EstadoDTO estado = new EstadoDTO();
        String respuesta = "";
        if (LoginDAO.validaToken(token, con)) {
            respuesta = dao.actualizaEstatusTarea(dto, token, con);
            if (!respuesta.isEmpty()) {
                estado.setEstado(EstadoHTTPEnum.OK);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO(respuesta)));
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ACEPTABLE);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Ocurrió un error al realizar la actualización de la tarea.")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token inválido")));
        }
        return estado;
    }
    
}
