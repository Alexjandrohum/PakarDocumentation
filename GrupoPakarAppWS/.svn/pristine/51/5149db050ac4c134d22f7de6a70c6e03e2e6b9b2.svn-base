package com.grupopakar.grupopakarappws.delegate;

import com.grupopakar.grupopakarappws.dao.LoginDAO;
import com.grupopakar.grupopakarappws.dao.TiendaDAO;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.dto.MensajeDTO;
import com.grupopakar.grupopakarappws.dto.ModuloDTO;
import com.grupopakar.grupopakarappws.dto.TiendaDTO;
import com.grupopakar.grupopakarappws.util.EstadoHTTPEnum;
import com.grupopakar.grupopakarappws.util.GsonUtil;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos.juarez
 */
public class TiendaDelegate {

    TiendaDAO tiendaDao;

    public TiendaDelegate() {
        this.tiendaDao = new TiendaDAO();
    }

    public EstadoDTO obtieneTiendasCercanas(String token, String lat, String lon, Connection con) {
        EstadoDTO estado = new EstadoDTO();
        if (LoginDAO.validaToken(token, con)) {
            if (tiendaDao.validaLatitud(lat) && tiendaDao.validaLongitud(lon)) {
                List<TiendaDTO> tiendas = tiendaDao.getTiendasCercanas(token, lat, lon, con);
                if (!tiendas.isEmpty()) {
                    estado.setEstado(EstadoHTTPEnum.OK);
                    estado.setObjeto(GsonUtil.gson.toJson(tiendas));
                } else {
                    estado.setEstado(EstadoHTTPEnum.NO_ENCONTRADO);
                    estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("No existen tiendas cercanas")));
                }
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ACEPTABLE);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Coordenadas no validas")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token invalido")));
        }
        return estado;
    }

    public EstadoDTO buscaTiendas(String token, String desc, Connection con) {
        EstadoDTO estado = new EstadoDTO();
        if (LoginDAO.validaToken(token, con)) {
            List<TiendaDTO> tiendas = tiendaDao.getTiendasCoincidentes(token, desc, con);
            if (!tiendas.isEmpty()) {
                estado.setEstado(EstadoHTTPEnum.OK);
                estado.setObjeto(GsonUtil.gson.toJson(tiendas));
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ENCONTRADO);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("No existen coincidencias")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token invalido")));
        }
        return estado;
    }
    
    public EstadoDTO getModulos(String token, String numeroControl, Connection con){
        EstadoDTO estado = new EstadoDTO();
        if (LoginDAO.validaToken(token, con)) {
            List<ModuloDTO> modulos = tiendaDao.getModulos(numeroControl, con);
            if (!modulos.isEmpty()) {
                estado.setEstado(EstadoHTTPEnum.OK);
                estado.setObjeto(GsonUtil.gson.toJson(modulos));
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ENCONTRADO);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("No hay modulos disponibles")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token invalido")));
        }
        
        return estado;
    }
}
