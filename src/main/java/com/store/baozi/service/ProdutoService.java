package com.store.baozi.service;

import com.store.baozi.dto.request.ProdutoRequest;
import com.store.baozi.dto.response.ProdutoResponse;
import com.store.baozi.model.ProdutoEntity;
import com.store.baozi.repository.PedidoRepository;
import com.store.baozi.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final PedidoRepository pedidoRepository;

    public ProdutoService(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public ProdutoResponse criar(ProdutoRequest produto) {
        var produtoCriado = produtoRepository.save(new ProdutoEntity(produto.nome(), produto.preco(), produto.estoque()));
        return new ProdutoResponse(produtoCriado);
    }

    public List<ProdutoResponse> listar() {
        return produtoRepository.findAll().stream().map(ProdutoResponse::new).toList();
    }

    public ProdutoResponse listarPorId(Long id) {
        return new ProdutoResponse(buscar(id));
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um produto com o id informado");
        }

        if (pedidoRepository.existsByProdutoId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O produto possui pedidos e não pode ser excluído");
        }

        produtoRepository.deleteById(id);
    }

    private ProdutoEntity buscar(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um produto com o id informado"));
    }
}
