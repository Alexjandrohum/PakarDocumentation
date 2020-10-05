package com.scpakar.scpakarweb.vo;

import com.scpakar.scpakarweb.dto.SocioDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@ManagedBean(name = "MenuPrincipal")
@ViewScoped
public class MenuPrincipalVO implements Serializable {

    private SocioDTO socio;

    /**
     * Creates a new instance of MenuPrincipalVO
     */
    public MenuPrincipalVO() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            HttpSession sesion = (HttpSession) ec.getSession(false);
            socio = (sesion != null) ? ((SocioDTO) sesion.getAttribute("Socio")) : new SocioDTO();
        } catch (Exception ex) {

        }
        if (socio == null || !socio.isValido()) {
            socio = new SocioDTO();
        }
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) {
        try {
            socio = (SocioDTO) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Util.registraError(ex);
        }
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            oos.writeObject(socio);
        } catch (IOException ex) {
            Util.registraError(ex);
        }
    }

    public void salir() {
        socio = new SocioDTO();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            HttpSession sesion = (HttpSession) ec.getSession(false);
            sesion.setAttribute("Socio", null);
            sesion.invalidate();

        } catch (Exception ex) {
            Util.registraError(ex);
        }
        try {
            ec.redirect("catalogo.jsf");
        } catch (Exception ex) {
            Util.registraError(ex);
        }
    }

    public boolean isVisible() {
        return socio.isValido();
    }

    public SocioDTO getSocio() {
        return socio;
    }

    public void setSocio(SocioDTO socio) {
        this.socio = socio;
    }

}
