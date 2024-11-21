package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.web.dto.GptTravelRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class GptServiceImpl implements GptService{
    private static final String GPT_API_URL = "https://api.openai.com/v1/chat/completions";
    @Value("${OPEN_API_SECRET}")
    private String GPT_API_KEY;
    private final WebClient webClient;


    @Override
    public Mono<Object> getTravelRecommendations(GptTravelRequestDTO requestDTO) {
        var requestPayload = createGptRequestPayload(requestDTO);

        return webClient.post()
                .uri(GPT_API_URL)
                .header("Authorization", "Bearer " + GPT_API_KEY)
                .header("Content-Type", "application/json") // Content-Type 명시
                .bodyValue(requestPayload)
                .retrieve()
                .bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("GPT API Response: {}", response))
                .doOnError(error -> log.error("GPT API Error: {}", error.getMessage()));
    }

    private String createGptRequestPayload(GptTravelRequestDTO requestDTO) {
        // GPT 요청에 적합한 프롬프트 생성
        StringBuilder prompt = new StringBuilder();
        prompt.append("여행 추천 요청:\n")
                .append("출발 도시: ").append(requestDTO.getDepartureCity().getDescription()).append("\n")
                .append("여행 기간: ").append(requestDTO.getTravelDuration().getDescription()).append("\n")
                .append("동반자: ").append(requestDTO.getCompanions().getDescription()).append("\n")
                .append("여행 스타일: ")
                .append(String.join(", ", requestDTO.getTravelStyles().stream().map(Enum::name).toList()))
                .append("\n")
                .append("선호 일정: ").append(requestDTO.getSchedulePreference().getDescription()).append("\n")
                .append("이에 맞는 여행지를 추천해주세요.");

        // 올바른 JSON 형식으로 반환
        return """
        {
            "model": "gpt-4",
            "messages": [
                {
                    "role": "user",
                    "content": "%s"
                }
            ]
        }
        """.formatted(prompt.toString());
    }
}
