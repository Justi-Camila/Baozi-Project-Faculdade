package com.store.baozi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClienteRequest(
        @NotBlank(message = "Preencha o nome do cliente")
        String nome,

        @NotNull(message = "A data precisa estar preenchida")
        @PastOrPresent(message = "A data não pode ser no futuro")
        LocalDate clienteDesde
) {
}
