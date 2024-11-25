package com.ssafy.Tteonaso.web.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "여행 추천 응답 DTO")
public class GptTravelResponseDTO {
    @ArraySchema(
            arraySchema = @Schema(description = "추천 여행지 목록 리스트"),
            schema = @Schema(implementation = TravelRecommendationDTO.class))
    private List<TravelRecommendationDTO> travelRecommendations;

    @ArraySchema(
            arraySchema = @Schema(description = "추천 동선 계획"),
            schema = @Schema(description = "추천 동선 리스트"))
    private List<String> suggestedItinerary;

    @ArraySchema(
            arraySchema = @Schema(description = "추가적인 여행 팁"),
            schema = @Schema(description = "기타 팁 리스트"))
    private List<String> additionalTips;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "추천 여행지 상세 정보 DTO")
    public static class TravelRecommendationDTO {
        @Schema(description = "장소 이름", example = "서울")
        private String placeName;

        @Schema(description = "장소 설명", example = "도시의 중심에서 역사와 현대를 동시에 경험할 수 있는 장소.")
        private String description;

        @Schema(description = "추천 이유", example = "문화와 현대적 인프라가 잘 조화된 도시.")
        private String recommendationReason;
    }
}
