package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.PedidoDelegate;
import com.scpakar.scpakarappws.dto.CanalPedido;
import com.scpakar.scpakarappws.dto.ConsultaDTO;
import com.scpakar.scpakarappws.dto.Estatus;
import com.scpakar.scpakarappws.dto.PaqueteriaDTO;
import com.scpakar.scpakarappws.dto.PedidoRecuperadoDTO;
import com.scpakar.scpakarappws.dto.Propiedades;
import com.scpakar.scpakarappws.dto.TipoEnvio;
import com.scpakar.scpakarappws.dto.TotalEstatusPedidoDTO;
import com.scpakar.scpakarappws.dto.TotalPedidoDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@Path("pedido")
public class PedidoWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final PedidoDelegate delegate;

    public PedidoWS() {
        delegate = new PedidoDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("creaPedido/{idSocio}&{idCanal}")
    public String creaPedido(@PathParam("idSocio") String idSocio, @PathParam("idCanal") int idCanal) {
        String idPedido = "";
        Connection con = null;
        try {
            String idUsuario = "";
            switch (idCanal) {
                case 0:
                    idUsuario = "catalogo.movil";
                    break;
                case 1:
                    idUsuario = "catalogo.web";
                    break;
                default:
                    idUsuario = idSocio;
                    break;
            }
            con = Factory.getConnection(Configuracion.getJndi());
            idPedido = delegate.agregaPedido(idSocio, idCanal, idUsuario, con);
            if (!idPedido.isEmpty()) {
                delegate.actualizaCanalConfirmacion(idPedido, idCanal, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(idPedido);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("creaPedido/{idSocio}")
    public String creaPedidoApp(@PathParam("idSocio") String idSocio, @QueryParam("idCanal") int idCanal, @QueryParam("idAlmacenDestino") String idAlmacenDestino) {
        String idPedido = "";
        Connection con = null;
        try {
            String idUsuario = "";
            switch (idCanal) {
                case 0:
                    idUsuario = "catalogo.movil";
                    break;
                case 1:
                    idUsuario = "catalogo.web";
                    break;
                default:
                    idUsuario = idSocio;
                    break;
            }
            con = Factory.getConnection(Configuracion.getJndi());
            if (!idSocio.isEmpty() && !idAlmacenDestino.isEmpty()) {
                idPedido = delegate.agregaPedido(idSocio, idCanal, idUsuario, con);
            }
            if (!idPedido.isEmpty()) {
                delegate.actualizaCanalDestino(idPedido, idCanal, idAlmacenDestino, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return idPedido;
    }

    /**
     * Pedidos para paqueteria a domicilio
     *
     * @param idSocio numero de socio
     * @param idCanal canal origen android o ios
     * @param paqueteria datos de socio
     * @return idPedido
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("creaPedidoDomicilio")
    public String creaPedidoDomicilio(@QueryParam("idSocio") String idSocio, @QueryParam("idCanal") int idCanal, PaqueteriaDTO paqueteria) {
        String idPedido = "";
        Connection con = null;
        try {
            String idUsuario;
            switch (idCanal) {
                case 0:
                    idUsuario = "catalogo.movil";
                    break;
                case 1:
                    idUsuario = "catalogo.web";
                    break;
                default:
                    idUsuario = idSocio;
                    break;
            }
            if (paqueteria != null) {
                con = Factory.getConnection(Configuracion.getJndi());
                String idAlmacenDestino = paqueteria.getIdAlmacen();
                if (!idSocio.isEmpty() && !idAlmacenDestino.isEmpty()) {
                    idPedido = delegate.agregaPedido(idSocio, idCanal, idUsuario, con);
                }
                if (!idPedido.isEmpty()) {
                    delegate.actualizaCanal(con, idSocio, CanalPedido.CANAL25.getIdCanal(), paqueteria.getTelefono(), false, idUsuario);
                    delegate.actualizaPedidoDomicilio(con, idPedido, idCanal, idAlmacenDestino,
                            true, paqueteria.getDestinatario(), paqueteria.getDireccion(), paqueteria.getReferencia());
                }
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return idPedido;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("infoDomicilio")
    public String ultimaInfoDomicilio(@QueryParam("idSocio") String idSocio) {
        PaqueteriaDTO info = null;
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                info = delegate.ultimaInfoDomicilio(con, idSocio);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(info);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("actualizaDestino/{idPedido}&{idAlmacenDestino}")
    public String actualizaDestino(@PathParam("idPedido") String idPedido, @PathParam("idAlmacenDestino") String idAlmacenDestino) {
        boolean resultado = false;
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            resultado = delegate.actualizaDestino(idPedido, idAlmacenDestino, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("busquedaApp/{codigo}")
    public String buscaCodigo(@PathParam("codigo") String codigo, @QueryParam("idSocio") String idSocio) {
        List<ConsultaDTO> productos = new ArrayList<>();
        Connection con = null;
        try {
            if (idSocio == null || idSocio.isEmpty()) {
                idSocio = "";
            }
            con = Factory.getConnection(Configuracion.getJndi());
            productos = delegate.buscaCodigo(codigo, idSocio, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(productos);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("productoDuplicado/{Llave}&{Talla}&{idSocio}")
    public String buscaProductoDuplicado(@PathParam("Llave") String llave, @PathParam("Talla") String talla, @PathParam("idSocio") String idSocio) {
        List<ConsultaDTO> productos = new ArrayList<>();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            productos = delegate.buscaProductosDuplicados(llave, talla, idSocio, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(productos);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("agregaProducto/{idSocio}")
    public String agregaProducto(@PathParam("idSocio") String idSocio, @QueryParam("codigo") String codigo, @QueryParam("llave") String llave,
            @QueryParam("talla") String talla, @QueryParam("cantidad") int cantidad, @QueryParam("idPedido") String idPedido,
            @QueryParam("idCliente") String idCliente, @QueryParam("porcentaje") int porcentaje) {
        boolean resultado = false;
        boolean agrega = false;
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty() && idPedido != null && !idPedido.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                for (int i = 0; i < cantidad; i++) {
                    resultado = delegate.agregaProducto(idPedido, llave, talla, codigo, con);
                    //Genera detalle scp_cliente_pedido
                    if (idCliente != null && !idCliente.isEmpty()) {
                        agrega = delegate.agregaPedidoClientePorcentaje(idPedido, idSocio, idCliente, porcentaje, con);
                        if (!resultado || !agrega) {
                            break;
                        }
                    }
                    if (!resultado) {
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("detalle/{idPedido}")
    public String getPedidoDetalle(@PathParam("idPedido") String idPedido) {
        List<ConsultaDTO> productos = new ArrayList<>();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            productos = delegate.buscaProductosDetalle(idPedido, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(productos);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("detalleApp")
    public String getPedidoDetalleApp(@QueryParam("id") String idPedido) {
        List<ConsultaDTO> productos = new ArrayList<>();
        Connection con = null;
        try {
            if (idPedido != null && !idPedido.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                productos = delegate.buscaProductosDetalleApp(idPedido, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(productos);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consulta/{id}")
    public String getConsultaPedidoDetalleApp(@PathParam("id") String idSocio,
            @QueryParam("idC") String idCliente, @QueryParam("ti") int tipo) {
        List<ConsultaDTO> productos = new ArrayList<>();
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                if (idCliente == null || idCliente.isEmpty()) {
                    productos = delegate.consultaProductosDetalleApp(idSocio, tipo, con);
                } else {
                    productos = delegate.consultaProductosDetalleApp(idSocio, idCliente, con);
                }
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(productos);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("recuperaPedido/{IdSocio}")
    public String getRecuperaPedidoApp(@PathParam("IdSocio") String idSocio) {
        String idUltimoPedido = "";
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            List<PedidoRecuperadoDTO> pedidos = delegate.getPedidosNuevos(idSocio, con);
            if (!pedidos.isEmpty()) {
                idUltimoPedido = pedidos.get(0).getIdPedido();
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return idUltimoPedido;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("total")
    public String getTotalPedido(@QueryParam("id") String idPedido) {
        TotalPedidoDTO total = new TotalPedidoDTO();
        Connection con = null;
        try {
            if (idPedido != null && !idPedido.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                total = delegate.selectTotales(idPedido, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(total);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("elimina/{idPedido}")  //ocultaNegado o cancelaNuevo
    public String eliminaProducto(@PathParam("idPedido") String idPedido, @QueryParam("linea") int linea) {
        boolean resultado = false;
        Connection con = null;
        try {
            if (!idPedido.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                resultado = delegate.eliminaProductoApp(idPedido, linea, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("cancela/{idPedido}")
    public String cancelaPedido(@PathParam("idPedido") String idPedido) {
        boolean resultado = false;
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            resultado = delegate.cambiaEstatusPedido(idPedido, Estatus.CANCELADO.getNombre(), con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("confirma")
    public String confirmaPedido(@QueryParam("idPedido") String idPedido, @QueryParam("Comentario") String comentario,
            @QueryParam("paqueteria") boolean paqueteria) {
        boolean resultado = false;
        Connection con = null;
        if (idPedido.isEmpty()) {
            return String.valueOf(resultado);
        } else {
            try {
                con = Factory.getConnection(Configuracion.getJndi());
                resultado = delegate.confirmaPedido(idPedido, "", "", "", con);
                if (resultado) {
                    delegate.actualizaPaqueteria(idPedido, paqueteria, con);
                }
            } catch (Exception ex) {
                Util.registraError(ex);
            } finally {
                Factory.close(con);
            }
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("paqueteria/{idPedido}&{Paqueteria}")
    public String actualizaPaqueteria(@PathParam("idPedido") String idPedido, @PathParam("Paqueteria") boolean paqueteria) {
        boolean resultado = false;
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            resultado = delegate.actualizaPaqueteria(idPedido, paqueteria, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }

    /**
     *
     * @param idPedido Identificador de pedido
     * @param destinatario Nombre de quien recibirá el paquete
     * @param direccionEnvio Dirección destino del paquete, separar con '|' las
     * direcciones ver <code>PedidoEnvioDTO.getDomicilio()</code>
     * @param comentario Comentario referente al envio del paquete
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("confirmaPaqueteria/{idPedido}&{Destinatario}&{DireccionEnvio}&{Comentario}")
    public String confirmaPaqueteria(@PathParam("idPedido") String idPedido, @PathParam("Destinatario") String destinatario,
            @PathParam("DireccionEnvio") String direccionEnvio, @PathParam("Comentario") String comentario) {
        boolean resultado = false;
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            resultado = delegate.confirmaEnvio(idPedido, destinatario, direccionEnvio, comentario, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("valida")
    public String validaPedido(@QueryParam("idPedido") String idPedido) {
        boolean resultado = false;
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            resultado = delegate.validaPedido(idPedido, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultaTotalPedidos/{id}")
    public String consultaTotalPedidos(@PathParam("id") String idSocio) {
        TotalEstatusPedidoDTO total = new TotalEstatusPedidoDTO();
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                total = delegate.getTotalEstatusPedidos(idSocio, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(total);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("prorroga")
    public String asignaProrrogaPedido(@QueryParam("idp") String idPedido, @QueryParam("ds") int dias) {
        boolean result = false;
        String idUsuario = "catalogo.movil";
        Connection con = null;
        try {
            if (idPedido != null && !idPedido.isEmpty() && dias > 0) {
                con = Factory.getConnection(Configuracion.getJndi());
                result = delegate.pedidoProrroga(idUsuario, idPedido, dias, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(result);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("prorrogaPermitida")
    public String getDiasPermitidosProrroga(@QueryParam("id") String idPedido) {
        int dias = 0;
        Connection con = null;
        try {
            if (idPedido != null && !idPedido.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                dias = delegate.selectDiasProrroga(idPedido, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(dias);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("abandonar")
    public String marcarPedidoAbandonado(@QueryParam("idp") String idPedido) {
        boolean result = false;
        String idUsuario = "catalogo.movil";
        Connection con = null;
        try {
            if (idPedido != null && !idPedido.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                result = delegate.marcarAbandonado(idUsuario, idPedido, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(result);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("abandonarProducto")
    public String marcarProductoAbandonado(@QueryParam("idp") String idPedido, @QueryParam("ln") int linea, @QueryParam("cm") String comentario) {
        boolean result = false;
        String idUsuario = "catalogo.movil";
        Connection con = null;
        try {
            if (comentario != null && !comentario.isEmpty() && idPedido != null && !idPedido.isEmpty() && linea > 0) {
                con = Factory.getConnection(Configuracion.getJndi());
                result = delegate.marcarProductoAbandonado(idUsuario, idPedido, linea, comentario, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(result);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultaEnvioDisponible")
    public String consultaEnvioDisponible(@QueryParam("idSocio") String idSocio) {
        TipoEnvio envio = TipoEnvio.NINGUNO;
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                String valor = delegate.consultaEnvioDisponible(con, Propiedades.ENVIO_DISPONIBLE_APP.getClave());
                int idTipo = Integer.valueOf(valor);
                envio = TipoEnvio.getTipoEnvio(idTipo);
            }
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(envio);
    }
}
