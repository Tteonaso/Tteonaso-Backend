package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponseDTO;
import com.ssafy.Tteonaso.converter.GptEnumConverter;
import com.ssafy.Tteonaso.domain.enums.*;
import com.ssafy.Tteonaso.service.GptService;
import com.ssafy.Tteonaso.web.dto.GptEnumResponseDTO;
import com.ssafy.Tteonaso.web.dto.GptTravelRequestDTO;
import com.ssafy.Tteonaso.web.dto.GptTravelResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "GPT Travel API", description = "GPT를 활용한 여행 추천 및 관련 정보 API")
public class GptTravelRestController {
    private final GptService gptService;

    @Operation(summary = "여행 추천 요청", description = "GPT를 활용하여 사용자의 요청에 따른 여행 추천 결과를 반환합니다.")
    @PostMapping("/recommend")
    public Mono<ApiResponseDTO<GptTravelResponseDTO>> getRecommendations(
            @Parameter(
                    description = "여행 추천 요청 정보",
                    required = true,
                    schema = @Schema(implementation = GptTravelRequestDTO.class)
            )
            @RequestBody GptTravelRequestDTO requestDTO) {
        return gptService.getTravelRecommendations(requestDTO)
                .map(ApiResponseDTO::onSuccess)
                .onErrorResume(error -> {
                    return Mono.just(ApiResponseDTO.onFailure("GPT_ERROR", "여행 추천 생성 실패", null));
                });
    }

    @Operation(summary = "여행 스타일 목록 조회", description = "여행 스타일 Enum 목록을 반환합니다.")
    @GetMapping("/travel-styles")
    public ApiResponseDTO<List<GptEnumResponseDTO>> getTravelStyles() {
        var travelStyles = GptEnumConverter.convert(TravelStyle.class);

        return ApiResponseDTO.onSuccess(travelStyles);
    }

    @Operation(summary = "선호 일정 스타일 목록 조회", description = "선호하는 여행 일정 스타일 Enum 목록을 반환합니다.")
    @GetMapping("/schedule-preferences")
    public ApiResponseDTO<List<GptEnumResponseDTO>> getSchedulePreferences() {
        var schedulePreferences =  GptEnumConverter.convert(SchedulePreference.class);

        return ApiResponseDTO.onSuccess(schedulePreferences);
    }

    @Operation(summary = "여행 동반자 목록 조회", description = "여행 동반자 유형 Enum 목록을 반환합니다.")
    @GetMapping("/companions")
    public ApiResponseDTO<List<GptEnumResponseDTO>> getCompanions() {
        var companions =  GptEnumConverter.convert(CompanionType.class);

        return ApiResponseDTO.onSuccess(companions);
    }

    @Operation(summary = "여행 기간 목록 조회", description = "여행 기간 Enum 목록을 반환합니다.")
    @GetMapping("/travel-durations")
    public ApiResponseDTO<List<GptEnumResponseDTO>> getTravelDurations() {
        var travelDurations = GptEnumConverter.convert(TravelDuration.class);

        return ApiResponseDTO.onSuccess(travelDurations);
    }

    @Operation(summary = "여행지 목록 조회", description = "여행지 Enum 목록을 반환합니다.")
    @GetMapping("/destinations")
    public ApiResponseDTO<List<GptEnumResponseDTO>> getDestinations() {
        var destinations = GptEnumConverter.convert(TravelDestination.class);

        return ApiResponseDTO.onSuccess(destinations);
    }
}
