package com.example.cleanpedidos.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class LineaPedidoEmbeddable {

    @Column(name = "producto_nombre", nullable = false)
    private String productoNombre;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 15, scale = 2)
    private BigDecimal precioUnitario;

    public LineaPedidoEmbeddable() {
    }

    public LineaPedidoEmbeddable(String productoNombre, int cantidad, BigDecimal precioUnitario) {
        this.productoNombre = productoNombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
}