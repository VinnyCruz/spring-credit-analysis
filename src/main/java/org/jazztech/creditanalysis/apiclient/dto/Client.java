package org.jazztech.creditanalysis.apiclient.dto;

import java.util.UUID;
import lombok.Builder;

public record Client(
    UUID id
) {
    @Builder(toBuilder = true)
    public Client(UUID id) {
        this.id = id;
    }
}
