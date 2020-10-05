package com.grupopakar.grupopakarappws.delegate;

import com.grupopakar.grupopakarappws.dao.LoginDAO;
import com.grupopakar.grupopakarappws.dao.PlantillaDAO;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.dto.MensajeDTO;
import com.grupopakar.grupopakarappws.dto.PlantillaDTO;
import com.grupopakar.grupopakarappws.dto.ValidaPlantillaDTO;
import com.grupopakar.grupopakarappws.util.EstadoHTTPEnum;
import com.grupopakar.grupopakarappws.util.GsonUtil;
import java.awt.image.BufferedImage;
import java.sql.Connection;

/**
 *
 * @author pablo.martinez
 */
public class PlantillaDelegate {

    private final PlantillaDAO plantillaDao;

    public PlantillaDelegate() {
        this.plantillaDao = new PlantillaDAO();
    }

    public BufferedImage getImagenEmpleado(Connection connection, String rutaFotoDefault, String idEmpleadoFortia) {
        return plantillaDao.getImagenEmpleado(connection, rutaFotoDefault, idEmpleadoFortia);
    }

    public EstadoDTO getPlantillaTienda(Connection connection, String tokenUsuario, int idTienda) {
        EstadoDTO estado = new EstadoDTO();
        if (LoginDAO.validaToken(tokenUsuario, connection)) {
            PlantillaDTO plantilla = plantillaDao.getPlantillaTienda(connection, idTienda);
            if (!plantilla.getPlantilla().isEmpty()) {
                estado.setEstado(EstadoHTTPEnum.OK);
                estado.setObjeto(GsonUtil.gson.toJson(plantilla));
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ACEPTABLE);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("No existen empleados para esta tienda")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token invalido")));
        }
        return estado;
    }

    public EstadoDTO validaPlantilla(Connection connection, String tokenUsuario, ValidaPlantillaDTO validacion) {
        EstadoDTO estado = new EstadoDTO();
        if (LoginDAO.validaToken(tokenUsuario, connection)) {
            if (validacion.isValorRevision() && !validacion.getComentario().isEmpty()) {
                boolean resultado = plantillaDao.registraRevisionPlantilla(connection, tokenUsuario, validacion);
                if (resultado) {
                    estado.setEstado(EstadoHTTPEnum.OK);
                    estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("la petición de validación se almacenó correctamente")));
                } else {
                    estado.setEstado(EstadoHTTPEnum.PETICION_ERROR);
                    estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Ocurrio un error al relaizar la actualización")));
                }
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_ACEPTABLE);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("La petición debe tener un comentario y activa la casilla de validación")));
            }
        } else {
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Token invalido")));
        }
        return estado;
    }

}
