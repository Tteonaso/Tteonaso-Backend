package com.ssafy.Tteonaso.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.Tteonaso.web.dto.GptTravelRequestDTO;
import com.ssafy.Tteonaso.web.dto.GptTravelResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class GptServiceImpl implements GptService{
    private static final String GPT_API_URL = "https://api.openai.com/v1/chat/completions";
    @Value("${OPEN_API_SECRET}")
    private String GPT_API_KEY;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;


    @Override
    public Mono<GptTravelResponseDTO> getTravelRecommendations(GptTravelRequestDTO requestDTO) {
        var requestPayload = createGptRequestPayload(requestDTO);

        return webClient.post()
                .uri(GPT_API_URL)
                .header("Authorization", "Bearer " + GPT_API_KEY)
                .header("Content-Type", "application/json") // Content-Type 명시
                .bodyValue(requestPayload)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseGptResponseToDto)
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(3))
                                .maxBackoff(Duration.ofSeconds(10))
                                .filter(throwable -> throwable instanceof WebClientResponseException.ServiceUnavailable)
                )
                .doOnSuccess(response -> log.info("GPT API Response: {}", response))
                .doOnError(error -> log.error("GPT API Error: {}", error.getMessage()));
    }

    private GptTravelResponseDTO parseGptResponseToDto(String gptResponse) {
        try {
            // JSON 응답을 JsonNode로 파싱
            JsonNode root = objectMapper.readTree(gptResponse);
            log.info("Parsed GPT Response: {}", root.toPrettyString());

            // 'choices[0].message.content'를 JSON으로 다시 파싱
            JsonNode contentNode = root.get("choices").get(0).get("message").get("content");
            if (contentNode == null || !contentNode.isTextual()) {
                throw new RuntimeException("Invalid GPT Response: Missing 'content'");
            }

            // content를 JSON 문자열로 다시 파싱
            JsonNode resultNode = objectMapper.readTree(contentNode.asText()).get("result");
            if (resultNode == null) {
                throw new RuntimeException("Invalid GPT Response: Missing 'result'");
            }

            // 추천 여행지 목록 파싱
            List<GptTravelResponseDTO.TravelRecommendationDTO> recommendations = parseTravelRecommendations(resultNode.get("travelRecommendations"));

            // 추천 동선 계획 파싱
            List<String> itinerary = parseStringList(resultNode.get("suggestedItinerary"));

            // 추가 팁 파싱
            List<String> tips = parseStringList(resultNode.get("additionalTips"));

            // DTO 생성 및 반환
            return GptTravelResponseDTO.builder()
                    .travelRecommendations(recommendations)
                    .suggestedItinerary(itinerary)
                    .additionalTips(tips)
                    .build();

        } catch (Exception e) {
            log.error("Failed to parse GPT response: {}", e.getMessage());
            throw new RuntimeException("Failed to parse GPT response", e);
        }
    }


    // Helper 메서드: TravelRecommendationDTO 리스트 파싱
    private List<GptTravelResponseDTO.TravelRecommendationDTO> parseTravelRecommendations(JsonNode node) {
        if (node == null || !node.isArray()) {
            return List.of();
        }
        return StreamSupport.stream(node.spliterator(), false)
                .map(recommendation -> GptTravelResponseDTO.TravelRecommendationDTO.builder()
                        .placeName(recommendation.path("placeName").asText(""))
                        .description(recommendation.path("description").asText(""))
                        .recommendationReason(recommendation.path("recommendationReason").asText(""))
                        .build()
                ).collect(Collectors.toList());
    }

    // Helper 메서드: 문자열 리스트 파싱
    private List<String> parseStringList(JsonNode node) {
        if (node == null || !node.isArray()) {
            return List.of();
        }
        return StreamSupport.stream(node.spliterator(), false)
                .map(JsonNode::asText)
                .collect(Collectors.toList());
    }


    private Map<String, Object> createGptRequestPayload(GptTravelRequestDTO requestDTO) {
        StringBuilder prompt = new StringBuilder();

        // 프롬프트 시작
        prompt.append("You are a professional travel planner. Based on the client's requirements, generate a travel itinerary in JSON format. ")
                .append("Ensure the output includes daily plans for the specified duration, with the final day having a lighter schedule to account for departure. ")
                .append("The JSON response must include three main components: recommended places, daily itinerary, and additional tips. All output must be written in Korean.\n\n")

                // 요구사항 설명
                .append("### Client Requirements ###\n")
                .append("1. Departure City: ").append(requestDTO.getDepartureCity().name())
                .append(". The plan should include destinations and activities near this city.\n")
                .append("2. Travel Duration: ").append(requestDTO.getTravelDuration().name())
                .append(". Provide daily plans for this duration.\n")
                .append("3. Companion Type: ").append(requestDTO.getCompanions().name())
                .append(". Tailor activities suitable for this type of companion.\n")
                .append("4. Preferred Travel Styles: ").append(String.join(", ", requestDTO.getTravelStyles()
                        .stream()
                        .map(Enum::name)
                        .toList()))
                .append(". Select destinations and activities aligned with these styles.\n")
                .append("5. Preferred Schedule Style: ").append(requestDTO.getSchedulePreference().name())
                .append(". If 'RELAXED_SCHEDULE', keep plans light with 1-2 activities per day. If 'DENSE_SCHEDULE', include multiple activities per day.\n\n")

                // 응답 형식
                .append("### Response Format ###\n")
                .append("{\n")
                .append("  \"result\": {\n")
                .append("    \"travelRecommendations\": [\n")
                .append("      { \"placeName\": \"Place Name\", \"description\": \"Place Description\", \"recommendationReason\": \"Reason for Recommendation\" }\n")
                .append("    ],\n")
                .append("    \"suggestedItinerary\": [\n")
                .append("      \"Day 1: Activity 1, Activity 2\",\n")
                .append("      \"Day 2: Activity 1, Activity 2\",\n")
                .append("      \"...\"\n")
                .append("    ],\n")
                .append("    \"additionalTips\": [\n")
                .append("      \"General travel advice and preparation items.\",\n")
                .append("      \"Example: Comfortable shoes, power banks, weather-appropriate clothing.\"\n")
                .append("    ]\n")
                .append("  }\n")
                .append("}\n\n")

                // 추가 지침
                .append("### Notes ###\n")
                .append("- Use the `name` values of enums for concise input.\n")
                .append("- Write all recommendations, itineraries, and tips in **Korean**.\n")
                .append("- Ensure the itinerary matches the travel styles, duration, and companion type.\n")
                .append("- Provide clear and actionable travel tips, including necessary preparation items.\n")
                .append("- Ensure all keys in the JSON response match the format exactly.\n\n")

                // 예시
                .append("### Example ###\n")
                .append("Input:\n")
                .append("{\n")
                .append("  \"departureCity\": \"SEOUL\",\n")
                .append("  \"travelDuration\": \"FIVE_NIGHTS_SIX_DAYS\",\n")
                .append("  \"companions\": \"WITH_CHILDREN\",\n")
                .append("  \"travelStyles\": [\"WITH_NATURE\", \"FAMOUS_TOURIST_SPOTS\"],\n")
                .append("  \"schedulePreference\": \"RELAXED_SCHEDULE\"\n")
                .append("}\n\n")
                .append("Output:\n")
                .append("{\n")
                .append("  \"result\": {\n")
                .append("    \"travelRecommendations\": [\n")
                .append("      { \"placeName\": \"남산타워\", \"description\": \"서울의 전경을 볼 수 있는 명소\", \"recommendationReason\": \"가족과 함께 가기 좋은 서울의 대표 관광지입니다.\" }\n")
                .append("    ],\n")
                .append("    \"suggestedItinerary\": [\n")
                .append("      \"Day 1: 경복궁 방문, 북촌 한옥마을 산책\",\n")
                .append("      \"Day 2: 남산타워에서 전경 감상, 명동에서 쇼핑\",\n")
                .append("      \"Day 3: 한강공원에서 자전거 타기\",\n")
                .append("      \"Day 4: 서울숲 산책\",\n")
                .append("      \"Day 5: 동대문 디자인 플라자 탐방\",\n")
                .append("      \"Day 6: 호텔 체크아웃 후 서울역 주변 산책\"\n")
                .append("    ],\n")
                .append("    \"additionalTips\": [\n")
                .append("      \"서울의 대중교통은 매우 편리하므로 지하철을 적극 이용하세요.\",\n")
                .append("      \"날씨에 따라 적절한 옷을 준비하고, 편한 신발을 착용하세요.\",\n")
                .append("      \"아이들과 방문할 경우, 충분한 간식과 물을 준비하세요.\"\n")
                .append("    ]\n")
                .append("  }\n")
                .append("}");

        // Return the payload
        return Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", prompt.toString()
                        )
                ),
                "temperature", 0.5,
                "max_tokens", 1000
        );
    }
}
