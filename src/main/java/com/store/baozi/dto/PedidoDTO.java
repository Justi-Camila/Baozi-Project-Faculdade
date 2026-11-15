package com.store.baozi.dto;

import com.store.baozi.model.PedidoEntity;

import java.math.BigDecimal;

public record PedidoDTO(Long id, String nomeCliente, String nomeProduto, BigDecimal preco, Integer quantidade) {

    public PedidoDTO(PedidoEntity pedido) {
        this(pedido.getId(), pedido.getCliente().getNome(), pedido.getProduto().getNome(), pedido.getProduto().getPreco(), pedido.getQuantidade());
    }
}
