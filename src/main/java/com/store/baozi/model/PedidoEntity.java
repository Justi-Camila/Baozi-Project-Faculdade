package com.store.baozi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    private Integer quantidade;

    public PedidoEntity() {
    }

    public PedidoEntity(ClienteEntity cliente, ProdutoEntity produto, Integer quantidade) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
