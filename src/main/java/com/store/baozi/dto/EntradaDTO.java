package com.store.baozi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EntradaDTO(
        @NotNull(message = "O id do cliente não pode ser null")
        Long clienteId,

        @NotNull(message = "O id do produto não pode ser null")
        Long produtoId,

        @NotNull(message = "A quantidade não pode ser nula")
        @Positive(message = "A quantidade precisa ser um número positivo")
        Integer quantidade) {
}
