package com.store.baozi.dto.response;

import com.store.baozi.model.ProdutoEntity;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        Boolean estoque
) {
    public ProdutoResponse(ProdutoEntity produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque());
    }

}
