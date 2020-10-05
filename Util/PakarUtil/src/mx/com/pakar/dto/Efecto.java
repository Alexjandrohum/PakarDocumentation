package mx.com.pakar.dto;

import com.icesoft.faces.context.effects.Effect;
import com.icesoft.faces.context.effects.EffectQueue;
import com.icesoft.faces.context.effects.Highlight;
import com.icesoft.faces.context.effects.Shake;

/**
 *
 * @author alberto.quirino
 */
public enum Efecto {

    EFECTO_PANTALLA("efecto_sigo_pantalla", new Shake()),
    EFECTO_IMPORTA("efecto_sicon_importa", new Highlight("#fda505")),
    EFECTO_MENSAJE("efecto_papos_venta", new Highlight("#fda505"));
    
    private String nombre;
    private Effect efecto;
    private EffectQueue efectoCola;
    

    private Efecto(String nombre, Effect efecto) {
        this.nombre = nombre;
        this.efecto = efecto;
    }

    public EffectQueue getEfecto() {
        efectoCola = new EffectQueue(nombre);
        efectoCola.add(efecto);
        return efectoCola;
    }

    public String getNombre() {
        return nombre;
    }
    
}
