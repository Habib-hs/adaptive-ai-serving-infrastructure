package io.github.habibhs.adaptive.gateway.service;

import io.github.habibhs.adaptive.gateway.dto.PredictionRequestDto;
import io.github.habibhs.adaptive.gateway.dto.PredictionResponseDto;

public interface PredictionService {

    PredictionResponseDto predict(PredictionRequestDto request);
}

