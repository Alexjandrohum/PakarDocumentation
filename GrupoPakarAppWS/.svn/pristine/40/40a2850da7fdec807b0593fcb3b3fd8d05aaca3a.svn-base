package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.PlantillaDelegate;
import com.grupopakar.grupopakarappws.util.Configuracion;
import com.grupopakar.grupopakarappws.util.Constante;
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
import javax.ws.rs.core.Response;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@Path("fotoEmpleado")
public class FotoEmpleadoWS {

    private final PlantillaDelegate delegate;
    @Context
    private ServletContext servletContext;

    public FotoEmpleadoWS() {
        this.delegate = new PlantillaDelegate();
    }

    /**
     *
     * @param idEmpleado número de control sin el prefijo E00
     * @return Foto del empleado, de no tener una regresa una imagen genérica.
     */
    @GET
    @Produces("image/png")
    @SuppressWarnings("null")
    public Response getFotoEmpleado(@QueryParam("id") String idEmpleado) {
        Response response = Response.noContent().build();
        Connection connection = null;
        try {
            String rutaFotoDefault = servletContext.getRealPath("/") + Constante.RUTA_IMAGEN.getValor();
            
            BufferedImage foto = ImageIO.read(new File(rutaFotoDefault + Imagen.CONEXION.getValor()));
            connection = Factory.getConnection(Configuracion.getJndi());

            if (connection != null) {
                foto = delegate.getImagenEmpleado(connection, rutaFotoDefault + Imagen.FOTO_EMPLEADO.getValor(), idEmpleado);
            }

            ByteArrayOutputStream bArray = new ByteArrayOutputStream();
            ImageIO.write(foto, "png", bArray);
            byte[] imageData = bArray.toByteArray();

            response = Response.ok(new ByteArrayInputStream(imageData)).build();

        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(connection);
        }
        return response;
    }
}
