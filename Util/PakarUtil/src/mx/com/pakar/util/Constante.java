package mx.com.pakar.util;

import static java.io.File.separator;

/**
 *
 * @author nicolas.canaan
 */
public enum Constante {

    LOG( "WEB-INF" + separator + "log4j" + separator + "log4j.properties" ),
    REPORTES( "WEB-INF" + separator + "reportes" + separator ),
    EXPORTAR( "WEB-INF" + separator + "ext" + separator ),
    IMPORTAR( "WEB-INF" + separator + "imp" + separator ),
    INICIO( "/sop/" );
    
    private String valor;

    private Constante(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    
}
