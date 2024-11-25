package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponseDTO;
import com.ssafy.Tteonaso.apiPayload.ApiResponseDTO;
import com.ssafy.Tteonaso.converter.GptEnumConverter;
import com.ssafy.Tteonaso.domain.enums.*;
import com.ssafy.Tteonaso.service.GptService;
import com.ssafy.Tteonaso.web.dto.GptTravelRequestDTO;
import com.ssafy.Tteonaso.web.dto.GptTravelResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GptTravelRestController {
    private final GptService gptService;

    @Operation(
            summary = "여행 추천 요청",
            description = "GPT를 활용하여 사용자의 요청에 따른 여행 추천 결과를 반환합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "추천된 여행 정보",
                            content = @Content(schema = @Schema(implementation = GptTravelResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "여행 추천 생성 실패",
                            content = @Content(schema = @Schema(implementation = ApiResponseDTO.class))
                    )
            }
    )
    @PostMapping("/recommend")
    public Mono<ApiResponseDTO<GptTravelResponseDTO>> getRecommendations(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "여행 추천 요청 정보",
                    required = true,
                    content = @Content(schema = @Schema(implementation = GptTravelRequestDTO.class))
            )
            @RequestBody GptTravelRequestDTO requestDTO) {
        return gptService.getTravelRecommendations(requestDTO)
                .map(ApiResponseDTO::onSuccess)
                .onErrorResume(error -> {
                    return Mono.just(ApiResponseDTO.onFailure("GPT_ERROR", "여행 추천 생성 실패", null));
                });
    }

    @Operation(summary = "여행 스타일 목록 조회", description = "여행 스타일 Enum 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "여행 스타일 목록 반환")
    @GetMapping("/travel-styles")
    public ApiResponseDTO<?> getTravelStyles() {
        var travelStyles = GptEnumConverter.convert(TravelStyle.class);

        return ApiResponseDTO.onSuccess(travelStyles);
    }

    @Operation(summary = "선호 일정 스타일 목록 조회", description = "선호하는 여행 일정 스타일 Enum 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "일정 스타일 목록 반환")
    @GetMapping("/schedule-preferences")
    public ApiResponseDTO<?> getSchedulePreferences() {
        var schedulePreferences =  GptEnumConverter.convert(SchedulePreference.class);

        return ApiResponseDTO.onSuccess(schedulePreferences);
    }

    @Operation(summary = "여행 동반자 목록 조회", description = "여행 동반자 유형 Enum 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "동반자 유형 목록 반환")
    @GetMapping("/companions")
    public ApiResponseDTO<?> getCompanions() {
        var companions =  GptEnumConverter.convert(CompanionType.class);

        return ApiResponseDTO.onSuccess(companions);
    }

    @Operation(summary = "여행 기간 목록 조회", description = "여행 기간 Enum 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "여행 기간 목록 반환")
    @GetMapping("/travel-durations")
    public ApiResponseDTO<?> getTravelDurations() {
        var travelDurations = GptEnumConverter.convert(TravelDuration.class);

        return ApiResponseDTO.onSuccess(travelDurations);
    }

    @Operation(summary = "여행지 목록 조회", description = "여행지 Enum 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "여행지 목록 반환")
    @GetMapping("/destinations")
    public ApiResponseDTO<?> getDestinations() {
        var destinations = GptEnumConverter.convert(TravelDestination.class);

        return ApiResponseDTO.onSuccess(destinations);
    }
}
