package com.store.baozi.service;

import com.store.baozi.dto.request.PedidoRequest;
import com.store.baozi.dto.response.PedidoResponse;
import com.store.baozi.model.PedidoEntity;
import com.store.baozi.repository.ClienteRepository;
import com.store.baozi.repository.PedidoRepository;
import com.store.baozi.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteRepository clienteRepository;

    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public PedidoResponse criar(PedidoRequest entrada) {
        var cliente = clienteRepository.findById(entrada.clienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        var produto = produtoRepository.findById(entrada.produtoId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if (!produto.getEstoque()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto sem estoque");
        }

        var pedido = pedidoRepository.save(new PedidoEntity(cliente, produto, entrada.quantidade()));

        return new PedidoResponse(pedido);
    }

    public List<PedidoResponse> listar() {
        var pedidos = pedidoRepository.findAll();

        return pedidos.stream().map(PedidoResponse::new).toList();
    }

    public PedidoResponse listarPorId(Long id) {
        var pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        return new PedidoResponse(pedido);
    }

    public void deletar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um pedido com o id informado");
        }

        pedidoRepository.deleteById(id);
    }
}
