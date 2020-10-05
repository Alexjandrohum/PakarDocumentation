package com.scpakar.scpakarweb.vo;

import com.scpakar.scpakarweb.delegate.AfiliateDelegate;
import com.scpakar.scpakarweb.dto.AfiliacionDTO;
import com.scpakar.scpakarweb.dto.EstadoDTO;
import com.scpakar.scpakarweb.dto.Mensaje;
import com.scpakar.scpakarweb.dto.SucursalDTO;
import com.scpakar.scpakarweb.util.UtilSCW;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 * @author Pablo Martinez
 */
@ManagedBean(name = "Afiliate")
@ViewScoped
public class AfiliateVO implements Serializable {

    private AfiliateDelegate delegate;

    private AfiliacionDTO afiliado;
    private String mensaje;
    private String paso;

    private List<EstadoDTO> estados;
    private List<SucursalDTO> sucursales;
    private String idEstado;
    private String idAlmacen;

    public AfiliateVO() {
        delegate = new AfiliateDelegate();
        afiliado = new AfiliacionDTO();
        idEstado = "PUEBLA";
        idAlmacen = "";
        mensaje = "";
        paso = "personal";
        estados = delegate.selectEstadosMX();
        sucursales = delegate.selectAlmacenEstado(idEstado, estados);

    }

    private void readObject(ObjectInputStream ois) {
        try {
            afiliado = (AfiliacionDTO) ois.readObject();
            paso = ois.readUTF();
            idEstado = ois.readUTF();
            idAlmacen = ois.readUTF();
            mensaje = ois.readUTF();

            delegate = new AfiliateDelegate();

            estados = delegate.selectEstadosMX();
            if (!idEstado.isEmpty()) {
                sucursales = delegate.selectAlmacenEstado(idEstado, estados);
            }
        } catch (IOException | NullPointerException | ClassNotFoundException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        }
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            oos.writeObject(afiliado);
            oos.writeUTF(paso);
            oos.writeUTF(idEstado);
            oos.writeUTF(idAlmacen);
            oos.writeUTF(mensaje);
        } catch (IOException | NullPointerException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        }
    }

    public void cerrar() {
        paso = "personal";
        afiliado = new AfiliacionDTO();
        idEstado = "PUEBLA";
        idAlmacen = "";
        mensaje = "";
    }

    public void atras() {
        mensaje = "";
    }

    public String onFlowProcess(FlowEvent event) {
        String evento = event.getNewStep();
        switch (evento) {
            case "economico":
                siguiente();
                if (paso.equals("personal")) {
                    evento = event.getOldStep();
                }
                break;
            case "gracias":
                guarda();
                if (paso.equals("economico")) {
                    evento = event.getOldStep();
                }
                break;
        }
        return evento;
    }

    public void cambiaEstado() {
        sucursales = delegate.selectAlmacenEstado(idEstado, estados);
        idAlmacen = "";
    }

    public void siguiente() {
        afiliado.setNombre(afiliado.getNombre().trim().toUpperCase());
        afiliado.setApellidos(afiliado.getApellidos().trim().toUpperCase());
        afiliado.setDireccion(afiliado.getDireccion().trim().toUpperCase());
        afiliado.setColonia(afiliado.getColonia().trim().toUpperCase());
        afiliado.setCp(afiliado.getCp().trim());
        afiliado.setCiudad(afiliado.getCiudad().trim().toUpperCase());
        afiliado.setTelefono(afiliado.getTelefono().trim());
        afiliado.setCelular(afiliado.getCelular().trim());
        afiliado.setCorreo(afiliado.getCorreo().trim().toLowerCase());
        afiliado.setCorreoConfirma(afiliado.getCorreoConfirma().trim().toLowerCase());

        if (validaPaso1()) {
            afiliado.setEstado(idEstado);
            afiliado.setTienda(idAlmacen);
            paso = "economico";
        }
    }

    public void guarda() {
        afiliado.setCatalogos(afiliado.getCatalogos().trim().toUpperCase());

        if (validaPaso2()) {
            if (delegate.guardaAfiliacion(afiliado)) {
                paso = "gracias";
            } else {
                mensaje = Mensaje.ERROR.getMensaje();
            }
        }
    }

    private boolean validaPaso1() {
        if (afiliado.getNombre().isEmpty()) {
            return false;
        }
        if (afiliado.getApellidos().isEmpty()) {
            return false;
        }
        if (afiliado.getDireccion().isEmpty()) {
            return false;
        }
        if (afiliado.getColonia().isEmpty()) {
            return false;
        }
        if (afiliado.getCp().length() != 5) {
            return false;
        }
        if (afiliado.getCiudad().isEmpty()) {
            return false;
        }
        if (idAlmacen.isEmpty()) {
            return false;
        }
        if (afiliado.getTelefono().isEmpty()) {
            return false;
        }
        if (afiliado.getCelular().isEmpty()) {
            return false;
        }
        if (!UtilSCW.isCorreoValido(afiliado.getCorreo())) {
            return false;
        }
        if (!afiliado.getCorreo().equalsIgnoreCase(afiliado.getCorreoConfirma())) {
            return false;
        }

        return true;
    }

    private boolean validaPaso2() {
        if (afiliado.getOtro().equals("SI")) {
            if (afiliado.getCatalogos().isEmpty()) {
                return false;
            }
        }
        if (afiliado.getEstadoCivil().isEmpty()) {
            return false;
        }
        if (afiliado.getEstudios().isEmpty()) {
            return false;
        }
        if (afiliado.getDependientes().isEmpty()) {
            return false;
        }
        if (afiliado.getOcupacion().isEmpty()) {
            return false;
        }
        if (afiliado.getEnteraste().isEmpty()) {
            return false;
        }
        if (afiliado.getRazon().isEmpty()) {
            return false;
        }
        if (afiliado.getOtro().isEmpty()) {
            return false;
        }

        return true;
    }

    public AfiliacionDTO getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(AfiliacionDTO afiliado) {
        this.afiliado = afiliado;
    }

    public String getPaso() {
        return paso;
    }

    public void setPaso(String paso) {
        this.paso = paso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<EstadoDTO> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoDTO> estados) {
        this.estados = estados;
    }

    public List<SucursalDTO> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<SucursalDTO> sucursales) {
        this.sucursales = sucursales;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

}
