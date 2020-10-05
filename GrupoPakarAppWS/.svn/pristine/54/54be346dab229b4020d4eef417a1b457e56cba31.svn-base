package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.InfoTiendaDelegate;
import com.grupopakar.grupopakarappws.delegate.PlantillaDelegate;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.util.Configuracion;
import com.grupopakar.grupopakarappws.util.Constante;
import com.grupopakar.grupopakarappws.util.EstadoHTTPEnum;
import com.grupopakar.grupopakarappws.util.Imagen;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author carlos.juarez
 */
@Path("fotoTienda")
public class FotoTiendaWS {
    private ServletContext servletContext;
    private InfoTiendaDelegate delegate;

    public FotoTiendaWS() {
        this.delegate = new InfoTiendaDelegate();
    }

    /**
     *
     * @param NumeroTienda numero de tienda en el formato PK001
     * @return Foto de la tienda, de no tener una regresa una imagen genérica.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("null")
    public Response getFotoTienda(@QueryParam("id") String numeroTienda) {
        Response response = Response.noContent().build();
        Connection connection = null;
        EstadoDTO estado = new EstadoDTO();
        try {
            // Dummy por ahora
            connection = Factory.getConnection(Configuracion.getJndi());
            estado.setEstado(EstadoHTTPEnum.OK);
            //estado.setObjeto("http://grupopakar.mx/img_tiendas/fachada/AL003.jpg");
            estado.setObjeto(delegate.getUrlImagenTienda(connection, numeroTienda));
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(connection);
        }
        return estado.getResponse();
    }
}
