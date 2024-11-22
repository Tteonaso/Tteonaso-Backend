package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.web.dto.GptTravelRequestDTO;
import com.ssafy.Tteonaso.web.dto.GptTravelResponseDTO;
import reactor.core.publisher.Mono;

public interface GptService {
    Mono<GptTravelResponseDTO> getTravelRecommendations(GptTravelRequestDTO requestDTO);
}
