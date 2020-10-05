package com.scpakar.scpakarweb.vo;

import com.scpakar.scpakarweb.delegate.ActivacionDelegate;
import com.scpakar.scpakarweb.delegate.RecuperaDelegate;
import com.scpakar.scpakarweb.dto.ActivacionDTO;
import com.scpakar.scpakarweb.dto.ErrorDTO;
import com.scpakar.scpakarweb.util.Mensaje;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@ManagedBean(name = "Recupera")
@ViewScoped
public class RecuperaPasswordVO implements Serializable {

    private RecuperaDelegate delegate;
    private String idSocio;
    private String correo;
    private String mensaje;
    private String mensajeError;
    private Mensaje msg;

    /**
     * Creates a new instance of RecuperaPasswordVO
     */
    public RecuperaPasswordVO() {
    }

    @PostConstruct
    public void init() {
        delegate = new RecuperaDelegate();
        msg = new Mensaje(FacesContext.getCurrentInstance());
        idSocio = "";
        correo = "";
        mensaje = "";
        mensajeError = "";
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) {
        try {
            delegate = new RecuperaDelegate();
            msg = new Mensaje(FacesContext.getCurrentInstance());

            idSocio = ois.readUTF();
            correo = ois.readUTF();
            mensaje = ois.readUTF();
            mensajeError = ois.readUTF();
        } catch (IOException ex) {
            Util.registraError(ex);
        }
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            oos.writeUTF(idSocio);
            oos.writeUTF(correo);
            oos.writeUTF(mensaje);
            oos.writeUTF(mensajeError);
        } catch (IOException ex) {
            Util.registraError(ex);
        }
    }

    public void recuperaPassword() {
        mensaje = "";
        mensajeError = "";
        if (!idSocio.isEmpty() && !correo.isEmpty()) {
            ErrorDTO resultado = delegate.recuperaPassword(idSocio, correo);
            if (resultado.isExito()) {
                mensaje = "SE HA ENVIADO LA NUEVA CONTRASEÑA A SU CORREO REGISTRADO.";
            } else if (resultado.isErrorSocio()) {
                msg.enviaError("formulario:txtRecuperaNumeroSocio", "", "INVÁLIDO");
                msg.enviaError("formulario:txtRecuperaCorreo", "", "INVÁLIDO");
                mensajeError = "NÚMERO DE SOCIO O CORREO INVÁLIDO.";
            } else if (resultado.isErrorBloqueoSocio()) {
                msg.enviaError("formulario:txtRecuperaNumeroSocio", "", "BLOQUEADO");
                mensajeError = "SOCIO BLOQUEADO. REACTIVA TU MEMBRESÍA EN TU SUCURSAL MÁS CERCANA.";
            } else if (resultado.isErrorTemporada()) {
                msg.enviaError("formulario:txtRecuperaNumeroSocio", "", "ACTIVACIÓN VENCIDA");
                mensajeError = "ACTIVACIÓN VENCIDA, ACUDE A TU SUCURSAL PARA UNA NUEVA ACTIVACIÓN DE CATÁLOGO DIGITAL.";
            }
        }
    }

    public void cerrar() {
        idSocio = "";
        correo = "";
        mensaje = "";
        mensajeError = "";
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

}
