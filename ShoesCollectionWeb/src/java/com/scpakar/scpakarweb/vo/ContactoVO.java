package com.scpakar.scpakarweb.vo;

import com.scpakar.scpakarweb.delegate.ContactoDelegate;
import com.scpakar.scpakarweb.dto.ContactoDTO;
import com.scpakar.scpakarweb.util.UtilSCW;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Pablo Martinez
 */
@ManagedBean(name = "Contacto")
@ViewScoped
@SuppressWarnings("CallToPrintStackTrace")
public class ContactoVO implements Serializable {

    private ContactoDelegate delegate;
    private String mensaje;
    private String mensajeError;
    private ContactoDTO contactoDto;

    public ContactoVO() {
        delegate = new ContactoDelegate();
        contactoDto = new ContactoDTO();
        mensaje = "";
        mensajeError = "";
    }

    private void readObject(java.io.ObjectInputStream ois) {
        try {
            contactoDto = (ContactoDTO) ois.readObject();

            delegate = new ContactoDelegate();

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            mx.com.pakar.util.Util.registraError(ex);
        }
    }

    private void writeObject(java.io.ObjectOutputStream oos) {
        try {
            oos.writeObject(contactoDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            mx.com.pakar.util.Util.registraError(ex);
        }
    }

    public void limpiar() {
        contactoDto = new ContactoDTO();
    }

    public void enviaCorreo() {
        if (validaFormulario() && UtilSCW.isCorreoValido(contactoDto.getCorreo())) {
            boolean exito = delegate.enviaCorreoContacto(contactoDto);
            System.out.println("exito: " + exito);
            if (exito) {
                limpiar();
                mensaje = "Correo enviado.";
                mensajeError = null;
            } else {
                mensaje = null;
                mensajeError = "Error inesperado, por favor intentelo más tarde.";
            }
        }
    }

    private boolean validaFormulario() {
        if (contactoDto.getNombre().isEmpty()) {
            return false;
        }
        if (contactoDto.getCorreo().isEmpty()) {
            return false;
        }
        if (contactoDto.getTelefono().isEmpty()) {
            return false;
        }
        if (contactoDto.getCelular().isEmpty()) {
            return false;
        }
        if (contactoDto.getMensaje().isEmpty()) {
            return false;
        }
        return true;
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

    public ContactoDTO getContactoDto() {
        return contactoDto;
    }

    public void setContactoDto(ContactoDTO contactoDto) {
        this.contactoDto = contactoDto;
    }

}
