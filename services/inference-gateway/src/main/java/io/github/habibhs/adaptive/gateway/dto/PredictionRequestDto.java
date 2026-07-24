package io.github.habibhs.adaptive.gateway.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * REST request body for POST /api/v1/predict.
 */
public record PredictionRequestDto(
        @NotBlank(message = "text must not be blank") String text
) {
}

