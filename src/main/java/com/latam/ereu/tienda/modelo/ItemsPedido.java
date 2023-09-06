package com.latam.ereu.tienda.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items_pedido")
public class ItemsPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int cantidad;
    private BigDecimal precioUnitario;
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    public ItemsPedido() {
    }

    public ItemsPedido(int cantidad, Producto producto, Pedido pedido) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.pedido = pedido;
        this.precioUnitario = producto.getPrecio();
    }

    public long getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getValor() {
        return this.precioUnitario.multiply(new BigDecimal(this.cantidad));
    }
}
