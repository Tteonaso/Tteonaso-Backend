package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponse;
import com.ssafy.Tteonaso.domain.enums.*;
import com.ssafy.Tteonaso.service.GptService;
import com.ssafy.Tteonaso.web.dto.GptTravelRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GptTravelRestController {
    private final GptService gptService;

    /**
     * 여행 추천 요청 처리 메서드
     */
    @PostMapping("/recommend")
    public ApiResponse<?> getRecommendations(@RequestBody GptTravelRequestDTO requestDTO) {
        var recommendations = gptService.getTravelRecommendations(requestDTO);

        return ApiResponse.onSuccess(recommendations);
    }

    /**
     * 여행 스타일(TravelStyle) Enum 반환
     */
    @GetMapping("/travel-styles")
    public ApiResponse<?> getTravelStyles() {
        var travelStyles = Arrays.stream(TravelStyle.values())
                .map(style -> Map.of(
                        "name", style.name(),
                        "description", style.getDescription()
                ))
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(travelStyles);
    }

    /**
     * 선호 여행 일정 스타일(SchedulePreference) Enum 반환
     */
    @GetMapping("/schedule-preferences")
    public ApiResponse<?> getSchedulePreferences() {
        var schedulePreferences =  Arrays.stream(SchedulePreference.values())
                .map(pref -> Map.of(
                        "name", pref.name(),
                        "description", pref.getDescription()
                ))
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(schedulePreferences);
    }

    /**
     * 여행 동반자(CompanionType) Enum 반환
     */
    @GetMapping("/companions")
    public ApiResponse<?> getCompanions() {
        var companions =  Arrays.stream(CompanionType.values())
                .map(companion -> Map.of(
                        "name", companion.name(),
                        "description", companion.getDescription()
                ))
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(companions);
    }

    /**
     * 여행 기간(TravelDuration) Enum 반환
     */
    @GetMapping("/travel-durations")
    public ApiResponse<?> getTravelDurations() {
        var travelDurations = Arrays.stream(TravelDuration.values())
                .map(duration -> Map.of(
                        "name", duration.name(),
                        "description", duration.getDescription()
                ))
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(travelDurations);
    }

    /**
     * 여행지(TravelDestination) Enum 반환
     */
    @GetMapping("/destinations")
    public ApiResponse<?> getDestinations() {
        var destinations = Arrays.stream(TravelDestination.values())
                .map(destination -> Map.of(
                        "name", destination.name(),
                        "description", destination.getDescription()
                ))
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(destinations);
    }
}
