package com.grupopakar.grupopakarappws.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class EmpleadoDTO implements Serializable {

    private int idEmpleado;
    private String numeroControl;
    private String nombreEmpleado;
    private String puestoEmpleado;
    private boolean validarPlantilla;
    private String descanso;
    private String rutaImagen;
    

    public EmpleadoDTO() {
        this.idEmpleado = 0;
        this.numeroControl = "";
        this.nombreEmpleado = "";
        this.puestoEmpleado = "";
        this.rutaImagen = "";
        this.descanso = "";
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getPuestoEmpleado() {
        return puestoEmpleado;
    }

    public void setPuestoEmpleado(String puestoEmpleado) {
        this.puestoEmpleado = puestoEmpleado;
    }

    public boolean isValidarPlantilla() {
        return validarPlantilla;
    }

    public void setValidarPlantilla(boolean validarPlantilla) {
        this.validarPlantilla = validarPlantilla;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.idEmpleado);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmpleadoDTO other = (EmpleadoDTO) obj;
        if (!Objects.equals(this.idEmpleado, other.idEmpleado)) {
            return false;
        }
        return true;
    }

    public String getDescanso() {
        return descanso;
    }

    public void setDescanso(String descanso) {
        this.descanso = descanso;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" + "idEmpleado=" + idEmpleado + ", numeroControl=" + numeroControl + ", nombreEmpleado=" + nombreEmpleado + ", puestoEmpleado=" + puestoEmpleado + ", validarPlantilla=" + validarPlantilla + ", rutaImagen=" + rutaImagen + ", descanso=" + descanso + '}';
    }
    
    

}
