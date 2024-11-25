package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponseDTO;
import com.ssafy.Tteonaso.service.MapFilterService;
import com.ssafy.Tteonaso.web.dto.MapFilterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/map")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Map Filter API", description = "지도 필터링 관련 API")
public class MapFilterRestController {
    private final MapFilterService mapFilterService;

    @Operation(
            summary = "시도에 따른 구군 정보 조회",
            description = "입력받은 시도 이름을 기준으로 구군 정보를 반환합니다.")
    @GetMapping("/address/sido")
    public ApiResponseDTO<MapFilterResponseDTO.GugunResponseDTO> getGugunBySido(
            @Parameter(description = "시도 이름", example = "서울특별시")
            @RequestParam String sidoName) {
        var gugunNames = mapFilterService.getGugunNamesBySido(sidoName);
        return ApiResponseDTO.onSuccess(
                new MapFilterResponseDTO.GugunResponseDTO(gugunNames)
        );
    }

    @Operation(
            summary = "구군에 따른 동 정보 조회",
            description = "입력받은 구군 이름을 기준으로 동 정보를 반환합니다."
    )
    @GetMapping("/address/gugun")
    public ApiResponseDTO<MapFilterResponseDTO.DongResponseDTO> getDongByGugun(
            @Parameter(description = "구군 이름", example = "강남구")
            @RequestParam String gugunName) {
        var dongNames = mapFilterService.getDongNamesByGugun(gugunName);
        return ApiResponseDTO.onSuccess(
                new MapFilterResponseDTO.DongResponseDTO(dongNames)
        );
    }
}

