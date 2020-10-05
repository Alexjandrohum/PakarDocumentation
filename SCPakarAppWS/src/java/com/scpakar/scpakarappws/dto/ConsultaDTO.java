package com.scpakar.scpakarappws.dto;

import java.io.Serializable;
import mx.com.pakar.util.Util;

/**
 *
 * @author nicolas.canaan
 */
public class ConsultaDTO implements Serializable {

    private String idPedido;
    private int linea;
    private String llave;
    private String talla;
    private String codigo;
    private String articulo;
    private String material;
    private double precio;
    private int tiempo;
    private String ruta;
    private Estatus estatus;
    private String catalogo;
    private String inventario;
    private String llaveUnica;
    private String foto;
    private String resurtido;
    private boolean disponible;
    private boolean negado;
    private int idCanal;
    private String canal;
    private String idCaja;
    private String idPaqueteria;
    private String cubo;
    private String mostrador;
    private String idAlmacenOrigen;
    private String IdAlmacenDestino;
    private String fechaPedido;
    private String fechaSurtido;
    private String confirmado;
    private String tallaVisible;
    private String marca;
    private String nombreMarca;
    private String modelo;
    private String color;
    private double precioCliente;
    private double ganancia;
    private String nombreCliente;
    private int porcentaje;
    private String fechaPrometida;
    private String fechaCancela;
    private String fechaProrroga;
    private String idSocio;

    public ConsultaDTO() {
        this.idPedido = "";
        this.llave = "";
        this.talla = "";
        this.codigo = "";
        this.articulo = "";
        this.material = "";
        this.ruta = "";
        this.catalogo = "";
        this.inventario = "";
        this.llaveUnica = "";
        this.foto = "";
        this.resurtido = "";
        this.canal = "";
        this.idCaja = "";
        this.idPaqueteria = "";
        this.cubo = "";
        this.mostrador = "";
        this.idAlmacenOrigen = "";
        this.IdAlmacenDestino = "";
        this.fechaPedido = "";
        this.fechaSurtido = "";
        this.confirmado = "";
        this.tallaVisible = "";
        this.marca = "";
        this.nombreMarca = "";
        this.modelo = "";
        this.color = "";
        this.nombreCliente = "";
        this.fechaPrometida = "";
        this.fechaCancela = "";
        this.fechaProrroga = "";
        this.idSocio = "";
        estatus = Estatus.NUEVO;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getPrecioCliente() {
        return precioCliente;
    }

    public void setPrecioCliente(double precioCliente) {
        this.precioCliente = precioCliente;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getPrecioTexto() {
        return Util.formatoNumero(precio);
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTiempoTexto() {
        return "" + tiempo;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public String getLlaveUnica() {
        return llaveUnica;
    }

    public void setLlaveUnica(String llaveUnica) {
        this.llaveUnica = llaveUnica;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getResurtido() {
        return resurtido;
    }

    public void setResurtido(String resurtido) {
        this.resurtido = resurtido;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isNegado() {
        return negado;
    }

    public void setNegado(boolean negado) {
        this.negado = negado;
    }

    public String getTallaVisible() {
        return tallaVisible;
    }

    public void setTallaVisible(String tallaVisible) {
        this.tallaVisible = tallaVisible;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.idPedido != null ? this.idPedido.hashCode() : 0);
        hash = 79 * hash + this.linea;
        hash = 79 * hash + (this.llave != null ? this.llave.hashCode() : 0);
        hash = 79 * hash + (this.talla != null ? this.talla.hashCode() : 0);
        hash = 79 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        hash = 79 * hash + (this.articulo != null ? this.articulo.hashCode() : 0);
        hash = 79 * hash + (this.material != null ? this.material.hashCode() : 0);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 79 * hash + this.tiempo;
        hash = 79 * hash + (this.ruta != null ? this.ruta.hashCode() : 0);
        hash = 79 * hash + (this.estatus != null ? this.estatus.hashCode() : 0);
        hash = 79 * hash + (this.catalogo != null ? this.catalogo.hashCode() : 0);
        hash = 79 * hash + (this.inventario != null ? this.inventario.hashCode() : 0);
        hash = 79 * hash + (this.llaveUnica != null ? this.llaveUnica.hashCode() : 0);
        hash = 79 * hash + (this.foto != null ? this.foto.hashCode() : 0);
        hash = 79 * hash + (this.resurtido != null ? this.resurtido.hashCode() : 0);
        hash = 79 * hash + (this.disponible ? 1 : 0);
        hash = 79 * hash + (this.negado ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConsultaDTO other = (ConsultaDTO) obj;
        if ((this.idPedido == null) ? (other.idPedido != null) : !this.idPedido.equals(other.idPedido)) {
            return false;
        }
        if (this.linea != other.linea) {
            return false;
        }
        if ((this.llave == null) ? (other.llave != null) : !this.llave.equals(other.llave)) {
            return false;
        }
        if ((this.talla == null) ? (other.talla != null) : !this.talla.equals(other.talla)) {
            return false;
        }
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        if ((this.articulo == null) ? (other.articulo != null) : !this.articulo.equals(other.articulo)) {
            return false;
        }
        if ((this.material == null) ? (other.material != null) : !this.material.equals(other.material)) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (this.tiempo != other.tiempo) {
            return false;
        }
        if ((this.ruta == null) ? (other.ruta != null) : !this.ruta.equals(other.ruta)) {
            return false;
        }
        if (this.estatus != other.estatus) {
            return false;
        }
        if ((this.catalogo == null) ? (other.catalogo != null) : !this.catalogo.equals(other.catalogo)) {
            return false;
        }
        if ((this.inventario == null) ? (other.inventario != null) : !this.inventario.equals(other.inventario)) {
            return false;
        }
        if ((this.llaveUnica == null) ? (other.llaveUnica != null) : !this.llaveUnica.equals(other.llaveUnica)) {
            return false;
        }
        if ((this.foto == null) ? (other.foto != null) : !this.foto.equals(other.foto)) {
            return false;
        }
        if ((this.resurtido == null) ? (other.resurtido != null) : !this.resurtido.equals(other.resurtido)) {
            return false;
        }
        if (this.disponible != other.disponible) {
            return false;
        }
        if (this.negado != other.negado) {
            return false;
        }
        return true;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(String idCaja) {
        this.idCaja = idCaja;
    }

    public String getCubo() {
        return cubo;
    }

    public void setCubo(String cubo) {
        this.cubo = cubo;
    }

    @Override
    public String toString() {
        return "ConsultaDTO{" + "idPedido=" + idPedido + ", linea=" + linea + ", llave=" + llave + ", talla=" + talla + ", codigo=" + codigo + ", articulo=" + articulo + ", material=" + material + ", precio=" + precio + ", tiempo=" + tiempo + ", ruta=" + ruta + ", estatus=" + estatus + ", catalogo=" + catalogo + ", inventario=" + inventario + ", llaveUnica=" + llaveUnica + ", foto=" + foto + ", resurtido=" + resurtido + ", disponible=" + disponible + ", negado=" + negado + ", idCanal=" + idCanal + ", canal=" + canal + ", idCaja=" + idCaja + ", idPaqueteria=" + idPaqueteria + ", cubo=" + cubo + ", mostrador=" + mostrador + ", idAlmacenOrigen=" + idAlmacenOrigen + ", IdAlmacenDestino=" + IdAlmacenDestino + ", fechaPedido=" + fechaPedido + ", fechaSurtido=" + fechaSurtido + ", confirmado=" + confirmado + ", tallaVisible=" + tallaVisible + ", marca=" + marca + ", nombreMarca=" + nombreMarca + ", modelo=" + modelo + ", color=" + color + ", precioCliente=" + precioCliente + ", ganancia=" + ganancia + ", nombreCliente=" + nombreCliente + ", porcentaje=" + porcentaje + ", fechaPrometida=" + fechaPrometida + ", fechaCancela=" + fechaCancela + ", fechaProrroga=" + fechaProrroga + ", idSocio=" + idSocio + '}';
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getFechaPrometida() {
        return fechaPrometida;
    }

    public void setFechaPrometida(String fechaPrometida) {
        this.fechaPrometida = fechaPrometida;
    }

    public String getFechaCancela() {
        return fechaCancela;
    }

    public void setFechaCancela(String fechaCancela) {
        this.fechaCancela = fechaCancela;
    }

    public String getFechaProrroga() {
        return fechaProrroga;
    }

    public void setFechaProrroga(String fechaProrroga) {
        this.fechaProrroga = fechaProrroga;
    }

    public String getMostrador() {
        return mostrador;
    }

    public void setMostrador(String mostrador) {
        this.mostrador = mostrador;
    }

    public String getIdPaqueteria() {
        return idPaqueteria;
    }

    public void setIdPaqueteria(String idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }

    public String getIdAlmacenOrigen() {
        return idAlmacenOrigen;
    }

    public void setIdAlmacenOrigen(String idAlmacenOrigen) {
        this.idAlmacenOrigen = idAlmacenOrigen;
    }

    public String getIdAlmacenDestino() {
        return IdAlmacenDestino;
    }

    public void setIdAlmacenDestino(String IdAlmacenDestino) {
        this.IdAlmacenDestino = IdAlmacenDestino;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getFechaSurtido() {
        return fechaSurtido;
    }

    public void setFechaSurtido(String fechaSurtido) {
        this.fechaSurtido = fechaSurtido;
    }

    public String getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(String confirmado) {
        this.confirmado = confirmado;
    }
}
