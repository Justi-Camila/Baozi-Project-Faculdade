package com.store.baozi.controller;

import com.store.baozi.dto.request.ProdutoRequest;
import com.store.baozi.dto.response.ProdutoResponse;
import com.store.baozi.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@Valid @RequestBody ProdutoRequest produto) {

        var resposta = produtoService.criar(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listar() {
        var resposta = produtoService.listar();

        return ResponseEntity.ok().body(resposta);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> listarPorId(@PathVariable Long id) {
        var resposta = produtoService.listarPorId(id);

        return ResponseEntity.ok().body(resposta);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);

        return ResponseEntity.noContent().build();
    }


}
