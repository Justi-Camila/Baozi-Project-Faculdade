package com.store.baozi.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "Preencha o nome do produto")
        String nome,

        @Positive(message = "O preçc precisa ser um número positivo")
        @NotNull(message = "O preço não pode ser nulo")
        @Digits(integer = 5, fraction = 2)
        BigDecimal preco,

        @NotNull(message = "O estoque não pode ser nulo")
        Boolean estoque
) {
}
