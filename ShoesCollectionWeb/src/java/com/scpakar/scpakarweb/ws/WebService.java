package com.scpakar.scpakarweb.ws;

import com.scpakar.scpakarweb.dto.CatalogoDTO;
import com.scpakar.scpakarweb.dto.ChatMensajeDTO;
import com.scpakar.scpakarweb.dto.ClienteDTO;
import com.scpakar.scpakarweb.dto.ConsultaDTO;
import com.scpakar.scpakarweb.dto.CorridaDTO;
import com.scpakar.scpakarweb.dto.DatosPreguntasDTO;
import com.scpakar.scpakarweb.dto.DestacadoDTO;
import com.scpakar.scpakarweb.dto.ErrorDTO;
import com.scpakar.scpakarweb.dto.EstadoDTO;
import com.scpakar.scpakarweb.dto.ProductoDTO;
import com.scpakar.scpakarweb.dto.SeccionDTO;
import com.scpakar.scpakarweb.dto.SocioDTO;
import com.scpakar.scpakarweb.dto.SucursalDTO;
import com.scpakar.scpakarweb.dto.TallaDTO;
import com.scpakar.scpakarweb.dto.TotalEstatusPedidoDTO;
import com.scpakar.scpakarweb.dto.TotalPedidoDTO;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 *
 * @author pablo.martinez
 */
public interface WebService {

    /**
     * Catalogo WS métodos
     */
    @GET("catalogo")
    Call<List<CatalogoDTO>> getCatalogos(@Query("or") int idOrigen);

    @GET("catalogo/seccion/{idCatalogo}")
    Call<List<SeccionDTO>> getSecciones(@Path("idCatalogo") int idCatalogo);

    @GET("catalogo/producto/{idCatalogo}")
    Call<List<ProductoDTO>> getProductos(@Path("idCatalogo") int idCatalogo, @Query("pi") int pagInicial, @Query("pf") int pagFinal, @Query("idSocio") String idSocio);

    @GET("catalogo/talla")
    Call<List<TallaDTO>> getTallasProducto(@Query("ll") String llave, @Query("idSocio") String idSocio);

    @GET("catalogo/corrida")
    Call<List<CorridaDTO>> getCorridaProducto(@Query("c") String codigo, @Query("idSocio") String idSocio);

    @GET("catalogo/masVendidos")
    Call<List<ProductoDTO>> getTopProductos();

    @GET("catalogo/busqueda")
    Call<List<ProductoDTO>> getBusquedaProducto(@Query("bu") String busqueda, @Query("id") String idSocio);

    @GET("catalogo/articulos")
    Call<String> getNumeroArticulos(@Query("id") int idCatalogo, @Query("pi") int paginaInicial,
            @Query("pf") int paginaFinal);

    @GET("catalogo/opcion")
    Call<List<ProductoDTO>> getOpcionesProducto(@Query("ll") String llave, @Query("id") String idSocio);

    /**
     * Sucursl WS métodos
     */
    @GET("sucursal")
    Call<List<SucursalDTO>> getSucursales();

    @GET("sucursal/getEstadoSucursales/{pais}")
    Call<List<EstadoDTO>> getEstadoSucursales(@Path("pais") String pais);

    /**
     * Login WS métodos
     */
    @GET("login")
    Call<SocioDTO> login(@Query("us") String usuario, @Query("ps") String password);

    @GET("login/validaCorreo")
    Call<SocioDTO> validaCorreo(@Query("em") String email);

    @GET("login/activaSocio")
    Call<ErrorDTO> activaSocio(@Query("em") String email, @Query("id") String socio,
            @Query("pw") String password, @Query("code") String codigo,
            @Query("se") String serie, @Query("re") boolean reEmail,
            @Query("or") int origen);

    @GET("login/selectPaginaAfiliate")
    Call<String> selectPaginaAfiliate();

    @GET("login/recuperaPassword")
    Call<ErrorDTO> recuperaPassword(@Query("id") String idSocio, @Query("cr") String email);

    @GET("login/actualizaPassword")
    Call<String> actualizaPassword(@Path("id") String idSocio, @Query("pw") String password, @Query("pwa") String passwordActual);

    /**
     * Socio WS métodos
     */
    @GET("socio/nuevoCliente")
    Call<ErrorDTO> nuevoCliente(@Query("ids") String idSocio,
            @Query("tel") String telefono,
            @Query("nom") String nombreCliente,
            @Query("prc") int porcentaje);

    @GET("socio/getClientesSocio/{idSocio}")
    Call<List<ClienteDTO>> getClientesSocio(@Path("idSocio") String idSocio);

    @GET("socio/registraToken")
    Call<String> enviaTokenSocio(@Query("id") String idSocio, @Query("tk") String token);

    @GET("socio/validaSocio/{idSocio}")
    Call<SocioDTO> validaSocio(@Path("idSocio") String idSocio);

    @GET("socio/modificarCliente")
    Call<ErrorDTO> modificarCliente(@Query("idc") String idCliente, @Query("nm") String nombre,
            @Query("pc") int porcentaje, @Query("ids") String idSocio,
            @Query("tel") String telefono, @Query("act") boolean activo);

    /**
     * Pedido WS métodos.
     */
    @GET("pedido/creaPedido/{idSocio}")
    Call<String> nuevoPedido(@Path("idSocio") String idSocio,
            @Query("idAlmacenDestino") String almacen,
            @Query("idCanal") int idCanal);

    @GET("pedido/agregaProducto/{idSocio}")
    Call<String> agregarProducto(@Path("idSocio") String idSocio,
            @Query("codigo") String codigo,
            @Query("llave") String llave,
            @Query("talla") String talla,
            @Query("cantidad") int cantidad,
            @Query("idPedido") String idPedido,
            @Query("idCliente") String idCliente,
            @Query("porcentaje") int porcentaje);

    @GET("pedido/detalleApp")
    Call<List<ConsultaDTO>> getPedidoDetalle(@Query("id") String idPedido);

    @GET("pedido/busquedaApp/{codigo}")
    Call<List<ConsultaDTO>> buscarCodigo(@Path("codigo") String codigo);

    @GET("pedido/elimina/{idPedido}")
    Call<String> eliminarLinea(@Path("idPedido") String idPedido, @Query("linea") int linea);

    @GET("pedido/total")
    Call<TotalPedidoDTO> getTotalPedido(@Query("id") String idPedido);

    @GET("pedido/confirma")
    Call<String> enviarPedido(@Query("idPedido") String idPedido, @Query("Comentario") String comentario);

    @GET("pedido/cancela/{idPedido}")
    Call<String> cancelarPedido(@Path("idPedido") String idPedido);

    @GET("pedido/consulta/{id}")
    Call<List<ConsultaDTO>> getConsultaPedidoDetalle(@Path("id") String idSocio,
            @Query("idC") String idCliente,
            @Query("ti") int tipoPedido);

    @GET("pedido/valida")
    Call<String> validaPedido(@Query("idPedido") String idPedido);

    @GET("pedido/consultaTotalPedidos/{id}")
    Call<TotalEstatusPedidoDTO> getTotalEstatusPedido(@Path("id") String idSocio);

    /**
     * WebService que descarga archivo .pdf
     *
     * @param url liga del pdf
     */
    @GET
    Call<ResponseBody> descargaPdf(@Url String url);

    /**
     * Chat WS métodos
     */
    @GET("chat/{idSocio}")
    Call<List<ChatMensajeDTO>> getMensajesChat(@Path("idSocio") String idSocio);

    @GET("chat/{idSocio}/enviaMensaje")
    Call<String> enviaMensajeChat(@Path("idSocio") String idSocio, @Query("mensaje") String mensaje);

    /**
     * Banner WS métodos
     */
    @GET("banner/destacados")
    Call<List<DestacadoDTO>> getDestacados();

    @GET("banner/intro")
    Call<List<String>> getBannersIntro();

    //Propiedad Métodos
    @GET("propiedad/obtienPropiedad/{clave}")
    Call<String> selectPropiedad(@Path("clave") String clavePropiedad);

    @GET("propiedad/actualizaPropiedad/{clave}&{valor}")
    Call<String> updatePropiedad(@Path("clave") String clavePropiedad, @Path("valor") String valorPropiedad);

    //Contacto Métodos
    @GET("contacto/enviaContacto")
    Call<String> enviaContacto(@Query("nm") String nombre, @Query("crr") String correo,
            @Query("tel") String telefono, @Query("cel") String celular, @Query("msg") String mensaje);

    //Afiliación Métodos
    @GET("afiliacion/afiliate")
    Call<String> guardaAfiliacion(@Query("nm") String nombre, @Query("ap") String apellidos,
            @Query("di") String direccion, @Query("col") String colonia, @Query("cp") String cp,
            @Query("cd") String ciudad, @Query("et") String estado, @Query("ti") String tienda,
            @Query("tel") String telefono, @Query("cel") String celular, @Query("crr") String correo,
            @Query("cv") String estadoCivil, @Query("est") String estudios, @Query("dep") String dependientes,
            @Query("ocup") String ocupacion, @Query("ent") String enteraste, @Query("rz") String razon,
            @Query("ot") String otro, @Query("cat") String catalogos);

    // Preguntas Frecuentes Métodos
    @GET("preguntasFrecuentes")
    Call<DatosPreguntasDTO> getDatosPreguntas();
}
