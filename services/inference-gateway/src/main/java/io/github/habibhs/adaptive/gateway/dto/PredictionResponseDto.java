package io.github.habibhs.adaptive.gateway.dto;

/**
 * REST response body returned to the client for POST /api/v1/predict.
 */
public record PredictionResponseDto(
        String prediction,
        double confidence
) {
}

