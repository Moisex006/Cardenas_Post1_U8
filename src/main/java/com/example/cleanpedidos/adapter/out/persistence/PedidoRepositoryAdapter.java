package com.example.cleanpedidos.adapter.out.persistence;

import com.example.cleanpedidos.domain.entity.Pedido;
import com.example.cleanpedidos.domain.valueobject.Dinero;
import com.example.cleanpedidos.domain.valueobject.EstadoPedido;
import com.example.cleanpedidos.domain.valueobject.PedidoId;
import com.example.cleanpedidos.usecase.port.PedidoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository jpa;

    public PedidoRepositoryAdapter(PedidoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void guardar(Pedido pedido) {
        jpa.save(toEntity(pedido));
    }

    @Override
    public Optional<Pedido> buscarPorId(PedidoId id) {
        return jpa.findById(id.toString()).map(this::toDomain);
    }

    @Override
    public List<Pedido> buscarTodos() {
        return jpa.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private PedidoJpaEntity toEntity(Pedido pedido) {
        List<LineaPedidoEmbeddable> lineas = pedido.getLineas().stream()
                .map(l -> new LineaPedidoEmbeddable(
                        l.productoNombre(),
                        l.cantidad(),
                        l.precioUnitario().cantidad()
                ))
                .toList();

        return new PedidoJpaEntity(
                pedido.getId().toString(),
                pedido.getClienteNombre(),
                pedido.getEstado().name(),
                lineas
        );
    }

    private Pedido toDomain(PedidoJpaEntity entity) {
        Pedido pedido = new Pedido(
                new PedidoId(UUID.fromString(entity.getId())),
                entity.getClienteNombre()
        );

        for (LineaPedidoEmbeddable linea : entity.getLineas()) {
            pedido.agregarLinea(
                    linea.getProductoNombre(),
                    linea.getCantidad(),
                    new Dinero(linea.getPrecioUnitario())
            );
        }

        if (EstadoPedido.CONFIRMADO.name().equals(entity.getEstado())) {
            pedido.confirmar();
        }

        return pedido;
    }
}