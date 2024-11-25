package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponseDTO;
import com.ssafy.Tteonaso.service.MapFilterService;
import com.ssafy.Tteonaso.web.dto.MapFilterResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/map")
@CrossOrigin(origins = "http://localhost:5173")
public class MapFilterRestController {
    private final MapFilterService mapFilterService;

    @GetMapping("/address/sido")
    public ApiResponseDTO<MapFilterResponseDTO.GugunResponseDTO> getGugunBySido(@RequestParam String sidoName) {
        var gugunNames = mapFilterService.getGugunNamesBySido(sidoName);
        return ApiResponseDTO.onSuccess(
                new MapFilterResponseDTO.GugunResponseDTO(gugunNames)
        );
    }

    @GetMapping("/address/gugun")
    public ApiResponseDTO<MapFilterResponseDTO.DongResponseDTO> getDongByGugun(@RequestParam String gugunName) {
        var dongNames = mapFilterService.getDongNamesByGugun(gugunName);
        return ApiResponseDTO.onSuccess(
                new MapFilterResponseDTO.DongResponseDTO(dongNames)
        );
    }
}

