package com.scpakar.scpakarweb.vo;

import com.scpakar.scpakarweb.delegate.AccesoSocioDelegate;
import com.scpakar.scpakarweb.dto.SocioDTO;
import com.scpakar.scpakarweb.util.AESCrypt;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.com.pakar.util.Sesion;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@ManagedBean(name = "AccesoSocio")
@ViewScoped
public class AccesoSocioVO implements Serializable {

    private AccesoSocioDelegate delegate;
    private String idSocio;
    private String password;
    private SocioDTO socio;
    private String mensajeError;

    public AccesoSocioVO() {
    }

    @PostConstruct
    public void init() {
        delegate = new AccesoSocioDelegate();
        socio = new SocioDTO();
        idSocio = "";
        password = "";
        mensajeError = "";
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) {
        try {
            delegate = new AccesoSocioDelegate();

            idSocio = ois.readUTF();
            password = ois.readUTF();
            mensajeError = ois.readUTF();

        } catch (IOException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        }
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            oos.writeUTF(idSocio);
            oos.writeUTF(password);
            oos.writeUTF(mensajeError);
        } catch (IOException ex) {
            Util.registraError(ex);
        }
    }

    public void validaLogin() {
        mensajeError = "";
        try {
            if (!idSocio.isEmpty()) {
                socio = delegate.validaLogin(idSocio.trim(), AESCrypt.encryptMD5(password.trim()));
                System.out.println("Socio: " + socio);
                System.out.println("Valido: " + socio.isValido());
                System.out.println("Contra: " + AESCrypt.encryptMD5(password.trim()));
                if (socio != null && socio.isValido()) {
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    HttpSession sesion = (HttpSession) ec.getSession(false);
                    if (sesion == null) {
                        sesion = (HttpSession) ec.getSession(true);
                    }
                    sesion.setAttribute("Socio", socio);
                    Sesion.redirecciona("catalogo.jsf", ec);
                } else {
                    mensajeError = com.scpakar.scpakarweb.dto.Mensaje.ACCESO.getMensaje();
                }
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        }
    }

    public void validaAcceso() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sesion = (HttpSession) ec.getSession(false);
        SocioDTO s = (sesion != null) ? ((SocioDTO) sesion.getAttribute("Socio")) : new SocioDTO();
        System.out.println("SocioV: " + s);
        if (s != null && s.isValido()) {
            Sesion.redirecciona("catalogo.jsf", ec);
        }
    }

    public void limpiar() {
        mensajeError = "";
        socio = new SocioDTO();
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SocioDTO getSocio() {
        return socio;
    }

    public void setSocio(SocioDTO socio) {
        this.socio = socio;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

}
