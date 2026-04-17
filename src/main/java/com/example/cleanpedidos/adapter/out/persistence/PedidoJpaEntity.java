package com.example.cleanpedidos.adapter.out.persistence;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class PedidoJpaEntity {

    @Id
    private String id;

    @Column(name = "cliente_nombre", nullable = false)
    private String clienteNombre;

    @Column(nullable = false)
    private String estado;

    @ElementCollection
    @CollectionTable(name = "pedido_lineas", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<LineaPedidoEmbeddable> lineas = new ArrayList<>();

    public PedidoJpaEntity() {
    }

    public PedidoJpaEntity(String id, String clienteNombre, String estado, List<LineaPedidoEmbeddable> lineas) {
        this.id = id;
        this.clienteNombre = clienteNombre;
        this.estado = estado;
        this.lineas = lineas;
    }

    public String getId() {
        return id;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public String getEstado() {
        return estado;
    }

    public List<LineaPedidoEmbeddable> getLineas() {
        return lineas;
    }
}