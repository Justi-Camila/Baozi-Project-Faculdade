package com.store.baozi.dto.response;

import com.store.baozi.model.PedidoEntity;

import java.math.BigDecimal;

public record PedidoResponse(
        Long id,
        String nomeCliente,
        String nomeProduto,
        BigDecimal preco, 
        Integer quantidade) {

    public PedidoResponse(PedidoEntity pedido) {
        this(pedido.getId(), pedido.getCliente().getNome(), pedido.getProduto().getNome(), pedido.getProduto().getPreco(), pedido.getQuantidade());
    }
}
