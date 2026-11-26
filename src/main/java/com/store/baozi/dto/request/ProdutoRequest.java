package com.store.baozi.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "Preencha o nome do produto")
        String nome,

        @Positive
        @Digits(integer = 5, fraction = 2)
        BigDecimal preco,

        @NotNull
        Boolean estoque
) {
}
