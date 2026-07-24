package io.github.habibhs.adaptive.gateway.controller;

import io.github.habibhs.adaptive.gateway.dto.PredictionRequestDto;
import io.github.habibhs.adaptive.gateway.dto.PredictionResponseDto;
import io.github.habibhs.adaptive.gateway.service.PredictionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Thin REST controller. No business logic here — just delegates to
 * PredictionService.
 */
@RestController
@RequestMapping("/api/v1")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/predict")
    public PredictionResponseDto predict(@Valid @RequestBody PredictionRequestDto request) {
        return predictionService.predict(request);
    }
}

