package com.store.baozi.dto.response;

import com.store.baozi.model.ClienteEntity;

import java.time.LocalDate;

public record ClienteResponse(
        Long id,
        String nome,
        LocalDate clienteDesde
) {

    public ClienteResponse(ClienteEntity cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getClienteDesde());
    }
}
