package com.example.cleanpedidos.adapter.in.web;

import com.example.cleanpedidos.adapter.in.web.dto.CrearPedidoRequest;
import com.example.cleanpedidos.adapter.in.web.dto.PedidoResponse;
import com.example.cleanpedidos.domain.valueobject.PedidoId;
import com.example.cleanpedidos.usecase.ConsultarPedidoUseCase;
import com.example.cleanpedidos.usecase.CrearPedidoUseCase;
import com.example.cleanpedidos.usecase.command.LineaPedidoCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final CrearPedidoUseCase crearUseCase;
    private final ConsultarPedidoUseCase consultarUseCase;

    public PedidoController(CrearPedidoUseCase crearUseCase,
                            ConsultarPedidoUseCase consultarUseCase) {
        this.crearUseCase = crearUseCase;
        this.consultarUseCase = consultarUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> crear(@Valid @RequestBody CrearPedidoRequest req) {
        PedidoId id = crearUseCase.ejecutar(
                req.clienteNombre(),
                req.lineas().stream()
                        .map(l -> new LineaPedidoCommand(
                                l.productoNombre(),
                                l.cantidad(),
                                l.precioUnitario()
                        ))
                        .toList()
        );

        return Map.of("pedidoId", id.toString());
    }

    @GetMapping("/{id}")
    public PedidoResponse buscar(@PathVariable String id) {
        return consultarUseCase.buscarPorId(new PedidoId(UUID.fromString(id)));
    }

    @GetMapping
    public List<PedidoResponse> listar() {
        return consultarUseCase.listarTodos();
    }
}