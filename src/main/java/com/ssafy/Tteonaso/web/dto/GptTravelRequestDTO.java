package com.ssafy.Tteonaso.web.dto;

import com.ssafy.Tteonaso.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GptTravelRequestDTO {
    // 출발 도시
    private TravelDestination departureCity;

    // 원하는 여행 기간
    private TravelDuration travelDuration;

    // 누구와 떠나는지
    private CompanionType companions;

    // 원하는 여행 스타일 (다중 선택 가능)
    private List<TravelStyle> travelStyles;

    // 선호하는 여행 일정 스타일 (널널한 일정, 빼곡한 일정)
    private SchedulePreference schedulePreference;
}
