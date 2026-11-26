package com.store.baozi.controller;

import com.store.baozi.dto.request.ClienteRequest;
import com.store.baozi.dto.response.ClienteResponse;
import com.store.baozi.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@Valid @RequestBody ClienteRequest cliente) {

        var resposta = clienteService.criar(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() {
        var resposta = clienteService.listar();

        return ResponseEntity.ok().body(resposta);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> listarPorId(@PathVariable Long id) {
        var resposta = clienteService.listarPorId(id);

        return ResponseEntity.ok().body(resposta);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }


}
