package com.example.cleanpedidos.adapter.in.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record LineaPedidoRequest(
        @NotBlank(message = "El nombre del producto es obligatorio")
        String productoNombre,

        @Min(value = 1, message = "La cantidad debe ser mayor a cero")
        int cantidad,

        @NotNull(message = "El precio unitario es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor a cero")
        BigDecimal precioUnitario
) {
}