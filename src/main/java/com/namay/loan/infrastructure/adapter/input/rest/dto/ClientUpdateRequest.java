package com.namay.loan.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientUpdateRequest(
        @NotNull(message = "El ID del cliente es obligatorio para actualizar")
        Long id,

        @NotBlank(message = "El nombre completo es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String fullName,

        @NotBlank(message = "El celular es obligatorio para el envío de notificaciones")
        @Pattern(regexp = "^\\+?519\\d{8}$", message = "El celular debe incluir el código de país válido (Ej: +51999888777)")
        String phoneNumber,

        @Pattern(regexp = "^$|^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Si se proporciona un correo, debe ser válido")
        String email
) {}
