package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.web.dto.GptTravelRequestDTO;
import reactor.core.publisher.Mono;

public interface GptService {
    Mono<Object> getTravelRecommendations(GptTravelRequestDTO requestDTO);
}
