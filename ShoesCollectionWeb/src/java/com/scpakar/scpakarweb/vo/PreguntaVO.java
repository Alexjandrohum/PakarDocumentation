package com.scpakar.scpakarweb.vo;

import com.scpakar.scpakarweb.delegate.PreguntaDelegate;
import com.scpakar.scpakarweb.dto.DatosPreguntasDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@ManagedBean(name = "Pregunta")
@ViewScoped
public class PreguntaVO implements Serializable {

    private PreguntaDelegate delegate;
    private DatosPreguntasDTO datos;

    /**
     * Creates a new instance of Pregunta
     */
    public PreguntaVO() {
        delegate = new PreguntaDelegate();
        datos = delegate.getDatosPreguntas();
    }

    private void readObject(ObjectInputStream ois) {
        try {
            delegate = new PreguntaDelegate();
            datos = (DatosPreguntasDTO) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Util.registraError(ex);
        }

    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            oos.writeObject(datos);
        } catch (IOException ex) {
            Util.registraError(ex);
        }
    }

    public DatosPreguntasDTO getDatos() {
        return datos;
    }

    public void setDatos(DatosPreguntasDTO datos) {
        this.datos = datos;
    }

}
