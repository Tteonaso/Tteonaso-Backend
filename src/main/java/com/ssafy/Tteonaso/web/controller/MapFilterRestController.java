package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponse;
import com.ssafy.Tteonaso.service.MapFilterService;
import com.ssafy.Tteonaso.web.dto.MapFilterResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/map/address")
public class MapFilterRestController {
    private final MapFilterService mapFilterService;

    @GetMapping("/sido")
    public ApiResponse<MapFilterResponseDTO.GugunResponseDTO> getGugunBySido(@RequestParam String sidoName) {
        var gugunNames = mapFilterService.getGugunNamesBySido(sidoName);
        return ApiResponse.onSuccess(
                new MapFilterResponseDTO.GugunResponseDTO(gugunNames)
        );
    }

    @GetMapping("/gugun")
    public ApiResponse<MapFilterResponseDTO.DongResponseDTO> getDongByGugun(@RequestParam String gugunName) {
        var dongNames = mapFilterService.getDongNamesByGugun(gugunName);
        return ApiResponse.onSuccess(
                new MapFilterResponseDTO.DongResponseDTO(dongNames)
        );
    }
}

