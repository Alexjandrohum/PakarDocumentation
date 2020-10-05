package com.scpakar.scpakarweb.vo;

import com.scpakar.scpakarweb.delegate.CatalogoDelegate;
import com.scpakar.scpakarweb.dto.CatalogoDTO;
import com.scpakar.scpakarweb.dto.CorridaDTO;
import com.scpakar.scpakarweb.dto.DestacadoDTO;
import com.scpakar.scpakarweb.dto.ProductoDTO;
import com.scpakar.scpakarweb.dto.SeccionDTO;
import com.scpakar.scpakarweb.dto.SocioDTO;
import com.scpakar.scpakarweb.dto.TallaDTO;
import com.scpakar.scpakarweb.util.Mensaje;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pablo.martinez
 */
@ManagedBean(name = "Catalogo")
@ViewScoped
@SuppressWarnings("CallToPrintStackTrace")
public class CatalogoVO implements Serializable {

    private CatalogoDelegate delegate;
    private Mensaje msg;
    private List<DestacadoDTO> destacados;
    private List<CatalogoDTO> catalogos;
    private CatalogoDTO catalogo;
    private List<SeccionDTO> seccionCatalogo;
    private SeccionDTO seccion;
    private List<ProductoDTO> productosCatalogo;
    private ProductoDTO producto;
    private List<TallaDTO> tallas;
    private String talla;
    private List<CorridaDTO> corridas;
    private String corrida;
    private SocioDTO socio;

    /**
     * Creates a new instance of CatalogoVO
     */
    public CatalogoVO() {
        delegate = new CatalogoDelegate();
        destacados = new ArrayList<>();
        catalogos = new ArrayList<>();
        catalogo = new CatalogoDTO();
        seccionCatalogo = new ArrayList<>();
        seccion = new SeccionDTO();
        productosCatalogo = new ArrayList<>();
        producto = new ProductoDTO();
        tallas = new ArrayList<>();
        talla = "";
        msg = new Mensaje(FacesContext.getCurrentInstance());
        corridas = new ArrayList<>();
        corrida = "";
        socio = new SocioDTO();
    }

    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            HttpSession sesion = (HttpSession) ec.getSession(false);
            socio = (sesion != null) ? ((SocioDTO) sesion.getAttribute("Socio")) : new SocioDTO();
        } catch (Exception ex) {
        }
        if (socio == null || !socio.isValido()) {
            socio = new SocioDTO();
        }

        destacados = delegate.selectBannerActivo();
        catalogos = delegate.selectCatalogos();
        if (!catalogos.isEmpty()) {
            catalogo = catalogos.get(0);
            seccionCatalogo = delegate.selectSeccion(catalogo.getIdCatalogo());
            if (!seccionCatalogo.isEmpty()) {
                seccion = seccionCatalogo.get(0);
            } else {
                seccion = new SeccionDTO();
            }
            productosCatalogo = delegate.selectProductoCatalogo(catalogo.getIdCatalogo(), seccion.getPagina(), seccion.getPaginaFinal(), socio.getIdSocio());
        }

        System.out.println("C: " + catalogo);
        System.out.println("Ss: " + seccionCatalogo.size());
        System.out.println("S: " + seccion);
        System.out.println("P: " + productosCatalogo.size());
        System.out.println("SC: " + socio);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) {
        try {
            delegate = new CatalogoDelegate();
            msg = new Mensaje(FacesContext.getCurrentInstance());

            //Desserialización de objetos
            catalogos = (List<CatalogoDTO>) ois.readObject();
            catalogo = (CatalogoDTO) ois.readObject();
            seccion = (SeccionDTO) ois.readObject();
            producto = (ProductoDTO) ois.readObject();
            talla = ois.readUTF();
            corrida = ois.readUTF();
            socio = (SocioDTO) ois.readObject();

            // Reconstrucción de objetos
            if (!catalogos.isEmpty() && catalogo.getIdCatalogo() != 0) {
                seccionCatalogo = delegate.selectSeccion(catalogo.getIdCatalogo());
            }
            if (catalogo.getIdCatalogo() != 0) {
                productosCatalogo = delegate.selectProductoCatalogo(catalogo.getIdCatalogo(), seccion.getPagina(), seccion.getPaginaFinal(), socio.getIdSocio());
            }
            if (producto != null && !producto.getLlave().isEmpty()) {
                corridas = delegate.selectCorridaProducto(producto.getCodigo(), socio.getIdSocio());
                if (!corridas.isEmpty() && !corrida.isEmpty()) {
                    tallas = delegate.selectTallasProducto(corrida, socio.getIdSocio());
                } else {
                    tallas = delegate.selectTallasProducto(producto.getLlave(), socio.getIdSocio());
                }
            } else {
                corridas = new ArrayList<>();
                tallas = new ArrayList<>();
            }

            destacados = delegate.selectBannerActivo();

            System.out.println("RO Catalogos: " + catalogos.size());
            System.out.println("RO Catalogo: " + catalogo);
            System.out.println("RO Secciones: " + seccionCatalogo.size());
            System.out.println("RO Seccion: " + seccion);
            System.out.println("RO Productos: " + productosCatalogo.size());
            System.out.println("RO Producto: " + producto);
            System.out.println("RO Corridas: " + corridas.size());
            System.out.println("RO Corrida: " + corrida);
            System.out.println("RO Tallas: " + tallas.size());
            System.out.println("RO Talla: " + talla);
            System.out.println("RO Socio: " + socio);
            System.out.println("---------------------------------------------------------");
        } catch (IOException | NullPointerException | ClassNotFoundException ex) {
            ex.printStackTrace();
            mx.com.pakar.util.Util.registraError(ex);
        }
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            oos.writeObject(catalogos);
            oos.writeObject(catalogo);
            oos.writeObject(seccion);
            oos.writeObject(producto);
            oos.writeUTF(talla);
            oos.writeUTF(corrida);
            oos.writeObject(socio);
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
            mx.com.pakar.util.Util.registraError(ex);
        }
    }

    public void seleccionaCatalogo(CatalogoDTO c) {
        try {
            catalogo = c;
//            System.out.println("Catalogo: " + catalogo);
            seccionCatalogo = delegate.selectSeccion(catalogo.getIdCatalogo());
            if (!seccionCatalogo.isEmpty()) {
                seccion = seccionCatalogo.get(0);
            } else {
                seccion = new SeccionDTO();
            }
            productosCatalogo = delegate.selectProductoCatalogo(catalogo.getIdCatalogo(), seccion.getPagina(), seccion.getPaginaFinal(), socio.getIdSocio());
        } catch (Exception ex) {
            ex.printStackTrace();
            mx.com.pakar.util.Util.registraError(ex);
        }
    }

    public void seleccionaSeccion(SeccionDTO sec) {
        seccion = sec;
//        System.out.println("Seccion: " + seccion);
        productosCatalogo = delegate.selectProductoCatalogo(catalogo.getIdCatalogo(), seccion.getPagina(), seccion.getPaginaFinal(), socio.getIdSocio());
    }

    public void seleccionaProducto(ProductoDTO p) {
        producto = p;
//        System.out.println("Producto: " + producto);
        if (producto != null && !producto.getLlave().isEmpty()) {
            corridas = delegate.selectCorridaProducto(producto.getCodigo(), socio.getIdSocio());
            if (!corridas.isEmpty()) {
                corrida = producto.getLlave();
                tallas = delegate.selectTallasProducto(corrida, socio.getIdSocio());
            } else {
                tallas = delegate.selectTallasProducto(producto.getLlave(), socio.getIdSocio());
            }
        } else {
            msg.enviaError("ERROR", "Error al seleccionar producto.");
        }
    }

    public void cambiaCorridaProducto() {
        if (!corrida.isEmpty()) {
            CorridaDTO c = new CorridaDTO();
            c.setLlave(corrida);

            int indice = corridas.indexOf(c);
            if (indice != -1) {
                c = corridas.get(indice);
                producto.setLlave(c.getLlave());
                producto.setPrecio(c.getPrecio());
                tallas = delegate.selectTallasProducto(corrida, socio.getIdSocio());
                talla = "";
            }
        }
    }

    public void cambiaTalla() {
        if (!talla.isEmpty()) {
            TallaDTO t = new TallaDTO();
            t.setTalla(talla);

            int indice = tallas.indexOf(t);
            if (indice != -1) {
                t = tallas.get(indice);
                producto.setLlave(t.getLlave());
                producto.setPrecio(t.getPrecio());
            }
        }
    }

    public void cierraDialogoProducto() {
        corrida = "";
        talla = "";
        producto = new ProductoDTO();
    }

    public List<CatalogoDTO> getCatalogos() {
        return catalogos;
    }

    public void setCatalogos(List<CatalogoDTO> catalogos) {
        this.catalogos = catalogos;
    }

    public CatalogoDTO getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(CatalogoDTO catalogo) {
        this.catalogo = catalogo;
    }

    public List<SeccionDTO> getSeccionCatalogo() {
        return seccionCatalogo;
    }

    public void setSeccionCatalogo(List<SeccionDTO> seccionCatalogo) {
        this.seccionCatalogo = seccionCatalogo;
    }

    public SeccionDTO getSeccion() {
        return seccion;
    }

    public void setSeccion(SeccionDTO seccion) {
        this.seccion = seccion;
    }

    public List<ProductoDTO> getProductosCatalogo() {
        return productosCatalogo;
    }

    public void setProductosCatalogo(List<ProductoDTO> productosCatalogo) {
        this.productosCatalogo = productosCatalogo;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public List<TallaDTO> getTallas() {
        return tallas;
    }

    public void setTallas(List<TallaDTO> tallas) {
        this.tallas = tallas;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public List<CorridaDTO> getCorridas() {
        return corridas;
    }

    public void setCorridas(List<CorridaDTO> corridas) {
        this.corridas = corridas;
    }

    public String getCorrida() {
        return corrida;
    }

    public void setCorrida(String corrida) {
        this.corrida = corrida;
    }

    public List<DestacadoDTO> getDestacados() {
        return destacados;
    }

    public void setDestacados(List<DestacadoDTO> destacados) {
        this.destacados = destacados;
    }

    public SocioDTO getSocio() {
        return socio;
    }

    public void setSocio(SocioDTO socio) {
        this.socio = socio;
    }

}
