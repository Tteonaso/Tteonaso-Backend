package com.ssafy.Tteonaso.web.dto;

import com.ssafy.Tteonaso.domain.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "여행 추천 요청 DTO")
public class GptTravelRequestDTO {
    @Schema(description = "출발 도시", example = "SEOUL")
    private TravelDestination departureCity;

    @Schema(description = "여행 기간", example = "TWO_NIGHTS_THREE_DAYS")
    private TravelDuration travelDuration;

    @Schema(description = "여행 동반자 유형", example = "WITH_FRIEND")
    private CompanionType companions;

    @Schema(description = "선호 여행 스타일 목록", example = "[\"SNS_HOT_PLACE\", \"FOOD_OVER_SIGHTSEEING\"]")
    private List<TravelStyle> travelStyles;

    @Schema(description = "선호하는 여행 일정 스타일", example = "RELAXED_SCHEDULE")
    private SchedulePreference schedulePreference;
}
