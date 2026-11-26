package com.store.baozi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteRequest(
        @NotBlank(message = "Preencha o nome do cliente")
        String nome,

        @NotNull
        LocalDate clienteDesde
) {
}
