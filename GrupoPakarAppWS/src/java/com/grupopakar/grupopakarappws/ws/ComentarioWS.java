package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.ComentarioDelegate;
import com.grupopakar.grupopakarappws.dto.ComentarioDTO;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.util.Configuracion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author carlos.juarez
 */
@Path("comentarios")
public class ComentarioWS {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComentarios(@HeaderParam("tokenDeUsuario") String tokenUsuario, @QueryParam("idTienda") String idTienda) {
        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            estado = new ComentarioDelegate().getComentarios(idTienda, tokenUsuario, con);
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }

    @POST
    @Path("registra")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registraComentario(@HeaderParam("tokenDeUsuario") String tokenUsuario, ComentarioDTO comentario) {
        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            estado = new ComentarioDelegate().registraComentario(comentario, tokenUsuario, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @HeaderParam("file") InputStream uploadedInputStream,
            @HeaderParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("path") String path) {

        // Path format //10.217.14.97/Installables/uploaded/
        System.out.println("path::" + path);
        String uploadedFileLocation = path
                + fileDetail.getFileName();

        // save it
        writeToFile(uploadedInputStream, uploadedFileLocation);

        String output = "File uploaded to : " + uploadedFileLocation;

        return Response.status(200).entity(output).build();

    }

// save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream,
            String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile2(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("path") String path) {

        // Path format //10.217.14.97/Installables/uploaded/
        System.out.println("path::" + path);
        String uploadedFileLocation = path
                + fileDetail.getFileName();

        // save it
        writeToFile2(uploadedInputStream, uploadedFileLocation);

        String output = "File uploaded to : " + uploadedFileLocation;

        return Response.status(200).entity(output).build();

    }

// save uploaded file to new location
    private void writeToFile2(InputStream uploadedInputStream,
            String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
