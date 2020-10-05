package com.scpakar.scpakarweb.vo;

import com.scpakar.scpakarweb.delegate.ActivacionDelegate;
import com.scpakar.scpakarweb.dto.ActivacionDTO;
import com.scpakar.scpakarweb.dto.ErrorDTO;
import com.scpakar.scpakarweb.dto.SocioDTO;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author pablo.martinez
 */
@ManagedBean(name = "Activacion")
@ViewScoped
public class ActivacionVO implements Serializable {

    private ActivacionDelegate delegate;
    private ActivacionDTO activa;
    private Mensaje msg;
    private String mensaje;
    private String mensajeError;

    /**
     * Creates a new instance of ActivacionVO
     */
    public ActivacionVO() {
        this.delegate = new ActivacionDelegate();
    }

    @PostConstruct
    public void init() {
        msg = new Mensaje(FacesContext.getCurrentInstance());
        activa = new ActivacionDTO();
        mensaje = "";
        mensajeError = "";
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) {
        try {
            delegate = new ActivacionDelegate();
            msg = new Mensaje(FacesContext.getCurrentInstance());

            activa = (ActivacionDTO) ois.readObject();
            mensaje = ois.readUTF();
            mensajeError = ois.readUTF();
        } catch (IOException | ClassNotFoundException ex) {
            Util.registraError(ex);
        }
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            oos.writeObject(activa);
            oos.writeUTF(mensaje);
            oos.writeUTF(mensajeError);
        } catch (IOException ex) {
            Util.registraError(ex);
        }
    }

    public void activar() {
        System.out.println(activa);
        validaActivacion(false);
    }

    public void actualizaCorreo() {
        activa.setSobreEscribe(true);
        validaActivacion(true);
    }

    private void validaActivacion(boolean reEscribe) {
        mensaje = "";
        mensajeError = "";
        if (activa.isValido()) {
            SocioDTO socio = delegate.validaCorreo(activa.getCorreo());
            if (!socio.isValido()) {
                ErrorDTO resultado = delegate.activaSocio(activa);
                System.out.println(resultado);
                if (resultado.isExito()) {
                    mensajeError = "";
                    activa = new ActivacionDTO();
                    if (reEscribe) {
                        mensaje = "SOCIO ACTUALIZADO CON ÉXITO, ¡PUEDES INICIAR SESIÓN!";
                        RequestContext.getCurrentInstance().execute("PF('dlgActualizaInformacion').hide();");
                    } else {
                        mensaje = "SOCIO ACTIVADO CON ÉXITO, ¡PUEDES INICIAR SESIÓN!";
                    }
                } else if (resultado.isErrorActivado()) {
                    msg.enviaError("formulario:txtActivaSerie", "", "UTILIZADA");
                    msg.enviaError("formulario:txtActivaCodigo", "", "UTILIZADO");
                    mensajeError = "ESTA ACTIVACIÓN YA FUE UTILIZADA.";
                } else if (resultado.isErrorCodigo()) {
                    msg.enviaError("formulario:txtActivaCodigo", "", "INCORRECTO");
                    mensajeError = "CÓDIGO INCORRECTO.";
                } else if (resultado.isErrorSerie()) {
                    msg.enviaError("formulario:txtActivaSerie", "", "INCORRECTA");
                    mensajeError = "SERIE INCORRECTA.";
                } else if (resultado.isErrorSocio()) {
                    msg.enviaError("formulario:txtActivaIdSocio", "", "NO EXISTE");
                    mensajeError = "NO EXISTE ESTE NÚMERO DE SOCIO.";
                } else if (!resultado.isExistePassword()) {
                    msg.enviaError("formulario:txtActivaPasswordConf", "", "INCORRECTA");
                    msg.enviaError("formulario:txtActivaPassword", "", "INCORRECTA");
                    mensajeError = "LA CONTRASEÑA ES INCORRECTA.";
                } else if (resultado.isErrorBloqueoSocio()) {
                    msg.enviaError("formulario:txtActivaIdSocio", "", "BLOQUEADO");
                    mensajeError = "SOCIO BLOQUEADO. REACTIVA TU MEMBRESÍA EN TU SUCURSAL MÁS CERCANA.";
                } else if (resultado.isErrorTemporada()) {
                    msg.enviaError("formulario:txtActivaIdSocio", "", "ACTIVACIÓN VENCIDA");
                    mensajeError = "ACTIVACIÓN VENCIDA, ACUDE A TU SUCURSAL PARA UNA NUEVA ACTIVACIÓN DE CATÁLOGO DIGITAL.";
                } else if (resultado.isReEmail()) {
                    msg.enviaError("formulario:txtActivaIdSocio", "", "REGISTRADO");
                    mensajeError = "SOCIO REGISTRADO";
                    RequestContext.getCurrentInstance().execute("PF('dlgActualizaInformacion').show();");
                }
            } else {
                mensaje = "";
                msg.enviaError("formulario:txtActivaSerie", "", "UTILIZADA");
                msg.enviaError("formulario:txtActivaCodigo", "", "UTILIZADO");
                mensajeError = "ESTA ACTIVACIÓN YA FUE UTILIZADA.";
            }
        } else if (!activa.isPasswordIgual()) {
            msg.enviaError("formulario:txtActivaPasswordConf", "", "NO COINCIDEN");
            msg.enviaError("formulario:txtActivaPassword", "", "NO COINCIDEN");
        }
    }

    public void cerrar() {
        activa = new ActivacionDTO();
        mensaje = "";
        mensajeError = "";
    }

    public ActivacionDTO getActiva() {
        return activa;
    }

    public void setActiva(ActivacionDTO activa) {
        this.activa = activa;
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
