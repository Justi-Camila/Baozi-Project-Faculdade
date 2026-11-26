package com.store.baozi.controller;

import com.store.baozi.dto.request.PedidoRequest;
import com.store.baozi.dto.response.PedidoResponse;
import com.store.baozi.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;


    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> cadastrar(@Valid @RequestBody PedidoRequest pedido) {
        var resposta = pedidoService.criar(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listar() {
        var resposta = pedidoService.listar();

        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> listarPorId(@PathVariable Long id) {
        var resposta = pedidoService.listarPorId(id);

        return ResponseEntity.ok().body(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}
