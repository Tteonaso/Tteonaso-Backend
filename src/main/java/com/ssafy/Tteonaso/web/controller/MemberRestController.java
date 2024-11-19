package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponse;
import com.ssafy.Tteonaso.converter.MemberConverter;
import com.ssafy.Tteonaso.domain.Member;
import com.ssafy.Tteonaso.service.MemberService;
import com.ssafy.Tteonaso.web.dto.MemberRequestDTO;
import com.ssafy.Tteonaso.web.dto.MemberResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberRestController {
    private final MemberService memberService;

    @GetMapping("/test")
    public ApiResponse<MemberResponseDTO.TempTestDTO> testAPI() {
        return ApiResponse.onSuccess(MemberConverter.toTempTestDTO());
    }

    @PostMapping("/signup")
    public ApiResponse<MemberResponseDTO.SignUpResponseDTO> signUp(@ModelAttribute @Valid MemberRequestDTO.SignUpDTO signUpDTO) {
        Member newMember = memberService.signUp(signUpDTO);
        return ApiResponse.onSuccess(null);
    }
}
