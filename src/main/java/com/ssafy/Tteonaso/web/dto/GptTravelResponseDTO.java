package com.ssafy.Tteonaso.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GptTravelResponseDTO {
    // 추천 여행지 목록
    private List<TravelRecommendationDTO> travelRecommendations;

    // 추천 동선 계획
    private List<String> suggestedItinerary;

    // 기타 팁
    private List<String> additionalTips;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TravelRecommendationDTO {
        private String placeName;
        private String description;
        private String recommendationReason;
    }
}
