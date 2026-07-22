package com.namay.loan.infrastructure.adapter.input.rest.dto;

import com.namay.loan.domain.model.constant.ClientStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientStatusRequest(
        @NotBlank(message = "El documento es obligatorio")
        @Pattern(regexp = "^[A-Za-z0-9]{8,15}$", message = "El documento debe ser válido (DNI, RUC, CE o Pasaporte) y no contener símbolos")
        String document,

        @NotNull(message = "El estado es obligatorio")
        ClientStatus status
) {}
