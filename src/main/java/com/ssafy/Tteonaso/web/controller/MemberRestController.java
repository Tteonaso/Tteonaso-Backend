package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponse;
import com.ssafy.Tteonaso.converter.MemberConverter;
import com.ssafy.Tteonaso.web.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    @GetMapping("/test")
    public ApiResponse<MemberResponseDTO.TempTestDTO> testAPI() {
        return ApiResponse.onSuccess(MemberConverter.toTempTestDTO());
    }
}
