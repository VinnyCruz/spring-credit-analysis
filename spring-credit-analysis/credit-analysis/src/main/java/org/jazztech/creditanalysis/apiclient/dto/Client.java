package org.jazztech.creditanalysis.apiclient.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

public record Client (
    UUID id
) {
    @Builder(toBuilder = true)
    public Client(UUID id) {
        this.id = id;
    }
}
