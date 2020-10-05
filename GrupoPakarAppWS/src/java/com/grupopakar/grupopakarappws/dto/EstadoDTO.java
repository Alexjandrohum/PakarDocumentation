/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupopakar.grupopakarappws.dto;

import com.grupopakar.grupopakarappws.util.EstadoHTTPEnum;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author carlos.juarez
 */
public class EstadoDTO {

    private EstadoHTTPEnum estado;
    private String objeto;

    public EstadoHTTPEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoHTTPEnum estado) {
        this.estado = estado;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Response getResponse() {
        switch (this.estado) {
            case OK:
                return Response.ok(this.objeto, MediaType.APPLICATION_JSON).build();
            case PETICION_ERROR:
                return Response.status(Response.Status.BAD_REQUEST).entity(this.objeto).build();
            case NO_AUTORIZADO:
                return Response.status(Response.Status.UNAUTHORIZED).entity(this.objeto).build();
            case NO_ENCONTRADO:
                return Response.status(Response.Status.NOT_FOUND).entity(this.objeto).build();
            case NO_ACEPTABLE:
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(this.objeto).build();
            case NO_DISPONIBLE:
                return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(this.objeto).build();
            default:
                return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(this.objeto).build();
        }
    }
}
