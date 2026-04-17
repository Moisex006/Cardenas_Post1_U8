package com.example.cleanpedidos.adapter.in.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CrearPedidoRequest(
        @NotBlank(message = "El cliente es obligatorio")
        String clienteNombre,

        @NotEmpty(message = "Debe enviar al menos una línea")
        List<@Valid LineaPedidoRequest> lineas
) {
}