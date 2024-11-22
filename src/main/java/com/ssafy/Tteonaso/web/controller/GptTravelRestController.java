package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponse;
import com.ssafy.Tteonaso.converter.GptEnumConverter;
import com.ssafy.Tteonaso.domain.enums.*;
import com.ssafy.Tteonaso.service.GptService;
import com.ssafy.Tteonaso.web.dto.GptTravelRequestDTO;
import com.ssafy.Tteonaso.web.dto.GptTravelResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GptTravelRestController {
    private final GptService gptService;

    /**
     * 여행 추천 요청 처리 메서드
     */
    @PostMapping("/recommend")
    public Mono<ApiResponse<GptTravelResponseDTO>> getRecommendations(@RequestBody GptTravelRequestDTO requestDTO) {
        return gptService.getTravelRecommendations(requestDTO)
                .map(ApiResponse::onSuccess)
                .onErrorResume(error -> {
                    return Mono.just(ApiResponse.onFailure("GPT_ERROR", "여행 추천 생성 실패", null));
                });
    }

    /**
     * 여행 스타일(TravelStyle) Enum 반환
     */
    @GetMapping("/travel-styles")
    public ApiResponse<?> getTravelStyles() {
        var travelStyles = GptEnumConverter.convert(TravelStyle.class);

        return ApiResponse.onSuccess(travelStyles);
    }

    /**
     * 선호 여행 일정 스타일(SchedulePreference) Enum 반환
     */
    @GetMapping("/schedule-preferences")
    public ApiResponse<?> getSchedulePreferences() {
        var schedulePreferences =  GptEnumConverter.convert(SchedulePreference.class);

        return ApiResponse.onSuccess(schedulePreferences);
    }

    /**
     * 여행 동반자(CompanionType) Enum 반환
     */
    @GetMapping("/companions")
    public ApiResponse<?> getCompanions() {
        var companions =  GptEnumConverter.convert(CompanionType.class);

        return ApiResponse.onSuccess(companions);
    }

    /**
     * 여행 기간(TravelDuration) Enum 반환
     */
    @GetMapping("/travel-durations")
    public ApiResponse<?> getTravelDurations() {
        var travelDurations = GptEnumConverter.convert(TravelDuration.class);

        return ApiResponse.onSuccess(travelDurations);
    }

    /**
     * 여행지(TravelDestination) Enum 반환
     */
    @GetMapping("/destinations")
    public ApiResponse<?> getDestinations() {
        var destinations = GptEnumConverter.convert(TravelDestination.class);

        return ApiResponse.onSuccess(destinations);
    }
}
