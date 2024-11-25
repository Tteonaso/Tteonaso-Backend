package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponseDTO;
import com.ssafy.Tteonaso.converter.MemberConverter;
import com.ssafy.Tteonaso.domain.Member;
import com.ssafy.Tteonaso.jwt.JwtSecurityUtil;
import com.ssafy.Tteonaso.jwt.JwtToken;
import com.ssafy.Tteonaso.service.MemberService;
import com.ssafy.Tteonaso.web.dto.MemberRequestDTO;
import com.ssafy.Tteonaso.web.dto.MemberResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberRestController {
    private final MemberService memberService;

    @GetMapping("/test")
    public ApiResponseDTO<MemberResponseDTO.TempTestDTO> testAPI() {
        return ApiResponseDTO.onSuccess(MemberConverter.toTempTestDTO());
    }

    @PostMapping("/signup")
    public ApiResponseDTO<MemberResponseDTO.SignUpResponseDTO> signUp(@ModelAttribute @Valid MemberRequestDTO.SignUpDTO signUpDTO) {
        Member newMember = memberService.signUp(signUpDTO);
        return ApiResponseDTO.onSuccess(MemberConverter.toSignUpResultDTO(newMember));
    }

    @PostMapping("/signin")
    public ApiResponseDTO<MemberResponseDTO.SignInResponseDTO> signIp(@RequestBody @Valid MemberRequestDTO.SignInDTO signInDTO) {
        JwtToken jwtToken = memberService.signIn(signInDTO);
        return ApiResponseDTO.onSuccess(MemberConverter.toSignInResultDTO(jwtToken));
    }

    @GetMapping()
    public ApiResponseDTO<MemberResponseDTO.MemberDetailDTO> getMemberDetail() {
        String email = JwtSecurityUtil.getCurrentMemberEmail();
        Member member = memberService.getMemberDetail(email);
        return ApiResponseDTO.onSuccess(MemberConverter.toMemberDetailDTO(member));
    }

    @PutMapping("/update")
    public ApiResponseDTO<MemberResponseDTO.MemberDetailDTO> updateMemberInfo(MemberRequestDTO.UpdateDTO updateDTO) {
        String email = JwtSecurityUtil.getCurrentMemberEmail();
        Member member = memberService.updateMemberProfile(email, updateDTO);
        return ApiResponseDTO.onSuccess(MemberConverter.toMemberDetailDTO(member));
    }
}
