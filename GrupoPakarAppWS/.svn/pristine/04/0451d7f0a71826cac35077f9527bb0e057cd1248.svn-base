/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupopakar.grupopakarappws.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos.juarez
 */
public class ComentarioDTO {
    
    private int idComentario;
    private int idComentarioPadre;
    private int idTienda;
    private Date fecha;
    private String nombreUsuario;
    private String comentario;
    private List<ComentarioDTO> respuestas;

    public ComentarioDTO() {
        this.respuestas = new ArrayList<ComentarioDTO>();
        idComentarioPadre = 0;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<ComentarioDTO> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<ComentarioDTO> respuestas) {
        this.respuestas = respuestas;
    }

    public int getIdComentarioPadre() {
        return idComentarioPadre;
    }

    public void setIdComentarioPadre(int idComentarioPadre) {
        this.idComentarioPadre = idComentarioPadre;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    @Override
    public String toString() {
        return "ComentarioDTO{" + "idComentario=" + idComentario + ", idComentarioPadre=" + idComentarioPadre + ", idTienda=" + idTienda + ", fecha=" + fecha + ", nombreUsuario=" + nombreUsuario + ", comentario=" + comentario + ", respuestas=" + respuestas + '}';
    }


    
}
