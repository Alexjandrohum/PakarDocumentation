package com.grupopakar.grupopakarappws.dao;

import com.grupopakar.grupopakarappws.dto.EmpleadoDTO;
import com.grupopakar.grupopakarappws.dto.PlantillaDTO;
import com.grupopakar.grupopakarappws.dto.ValidaPlantillaDTO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
public class PlantillaDAO {

    public BufferedImage getImagenEmpleado(Connection connection, String rutaFotoDefault, String idEmpleadoFortia) {
        BufferedImage image = null;
        String query = "SELECT ISNULL(FOTOGRAFIA,'') AS Foto FROM [PAKAR61].[RecHum].[dbo].[RH_TRAB] WHERE CLA_TRAB = ?";
        PreparedStatement ps = null;
        ResultSet r = null;

        try {
            File f = new File(rutaFotoDefault);
            if (connection != null) {
                ps = connection.prepareCall(query);
                ps.setString(1, idEmpleadoFortia);
                r = ps.executeQuery();
                if (r.next()) {
                    Blob foto = r.getBlob("Foto");
                    if (foto.length() == 0) {
                        image = ImageIO.read(f);
                    } else {
                        image = ImageIO.read(foto.getBinaryStream());
                    }
                } else {
                    image = ImageIO.read(f);
                }
            }
        } catch (SQLException | IOException ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return image;
    }

    public PlantillaDTO getPlantillaTienda(Connection connection, int idTienda) {
        PlantillaDTO plantilla = new PlantillaDTO();
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "SELECT \n"
                + "	e.clave_trabajador, e.numero_control, \n"
                + "	(e.nombre + ' '+e.apellido_paterno + ' ' + e.apellido_materno) AS nombre, \n"
                + "	e.puesto, \n"
                + "	CASE \n"
                + "		RIGHT(e.dia_descanso, 3) \n"
                + "		WHEN 'LUN' THEN 'Lunes' \n"
                + "		WHEN 'MAR' THEN 'Martes' \n"
                + "		WHEN 'MIE' THEN 'Miércoles' \n"
                + "		WHEN 'JUE' THEN 'Jueves' \n"
                + "		WHEN 'VIE' THEN 'Viernes' \n"
                + "		WHEN 'SAB' THEN 'Sábado' \n"
                + "		WHEN 'DOM' THEN 'Domingo' \n"
                + "		ELSE e.dia_descanso END AS dia_descanso,\n"
                + "	((SELECT ISNULL(valor,'') FROM aps.propiedad WHERE clave = 'ws_foto_empleado') + CAST(e.clave_trabajador AS VARCHAR)) AS url_foto, \n"
                + "	[aps].[validacion_plantilla_pendiente](e.clave_trabajador) AS valida_plantilla,\n"
                + "	ISNULL(tp.jerarquia,99) AS jerarquia \n"
                + "FROM aps.tienda t WITH(NOLOCK) \n"
                + "INNER JOIN aps.vw_empleado e ON t.numero_fortia = e.id_tienda COLLATE SQL_Latin1_General_CP1_CI_AS \n"
                + "LEFT JOIN aps.tienda_puesto tp ON t.tipo_tienda = tp.tipo_tienda COLLATE SQL_Latin1_General_CP1_CI_AS  AND e.puesto = tp.puesto COLLATE SQL_Latin1_General_CP1_CI_AS \n"
                + "WHERE t.id_tienda = ?\n"
                + "ORDER BY ISNULL(tp.jerarquia,99) ";

        try {
            if (connection != null) {
                ps = connection.prepareStatement(query);
                ps.setInt(1, idTienda);
                r = ps.executeQuery();
                while (r.next()) {
                    EmpleadoDTO e = new EmpleadoDTO();
                    e.setIdEmpleado(Integer.parseInt(r.getString("clave_trabajador")));
                    e.setNumeroControl(r.getString("numero_control"));
                    e.setNombreEmpleado(r.getString("nombre"));
                    e.setPuestoEmpleado(r.getString("puesto"));
                    e.setValidarPlantilla(r.getBoolean("valida_plantilla"));
                    e.setRutaImagen(r.getString("url_foto"));
                    e.setDescanso(r.getString("dia_descanso"));
                    plantilla.getPlantilla().add(e);
                }
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return plantilla;
    }

    public boolean registraRevisionPlantilla(Connection connection, String tokenUsuario, ValidaPlantillaDTO validacion) {
        boolean result = false;
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "EXEC [aps].[genera_revision_plantilla] ?,?,?,?,?";
        try {
            if (connection != null) {
                ps = connection.prepareStatement(query);
                ps.setString(1, tokenUsuario);
                ps.setInt(2, validacion.getIdTienda());
                ps.setInt(3, validacion.getIdEmpleadoARevisar());
                ps.setBoolean(4, validacion.isValorRevision());
                ps.setString(5, validacion.getComentario());
                r = ps.executeQuery();
                if (r.next()) {
                    String salida = r.getString("Resultado");
                    result = salida.startsWith("OK");
                }
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return result;
    }
}
