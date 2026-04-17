package com.example.cleanpedidos.usecase.command;

import java.math.BigDecimal;

public record LineaPedidoCommand(
        String productoNombre,
        int cantidad,
        BigDecimal precioUnitario
) {
}