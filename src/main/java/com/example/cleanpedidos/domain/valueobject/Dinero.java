package com.example.cleanpedidos.domain.valueobject;

import java.math.BigDecimal;
import java.util.Objects;

public record Dinero(BigDecimal cantidad) {

    public static final Dinero CERO = new Dinero(BigDecimal.ZERO);

    public Dinero {
        Objects.requireNonNull(cantidad, "La cantidad no puede ser nula");
        if (cantidad.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Dinero no puede ser negativo");
        }
    }

    public Dinero sumar(Dinero otro) {
        Objects.requireNonNull(otro, "El dinero a sumar no puede ser nulo");
        return new Dinero(this.cantidad.add(otro.cantidad));
    }
}