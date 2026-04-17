package com.example.cleanpedidos.usecase.impl;

import com.example.cleanpedidos.domain.entity.Pedido;
import com.example.cleanpedidos.domain.valueobject.Dinero;
import com.example.cleanpedidos.domain.valueobject.PedidoId;
import com.example.cleanpedidos.usecase.CrearPedidoUseCase;
import com.example.cleanpedidos.usecase.command.LineaPedidoCommand;
import com.example.cleanpedidos.usecase.port.PedidoRepositoryPort;

import java.util.List;
import java.util.Objects;

public class CrearPedidoService implements CrearPedidoUseCase {

    private final PedidoRepositoryPort repo;

    public CrearPedidoService(PedidoRepositoryPort repo) {
        this.repo = Objects.requireNonNull(repo);
    }

    @Override
    public PedidoId ejecutar(String clienteNombre, List<LineaPedidoCommand> lineas) {
        if (lineas == null || lineas.isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener al menos una línea");
        }

        Pedido pedido = new Pedido(PedidoId.nuevo(), clienteNombre);

        for (LineaPedidoCommand linea : lineas) {
            pedido.agregarLinea(
                    linea.productoNombre(),
                    linea.cantidad(),
                    new Dinero(linea.precioUnitario())
            );
        }

        pedido.confirmar();
        repo.guardar(pedido);

        return pedido.getId();
    }
}