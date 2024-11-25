package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.domain.Member;
import com.ssafy.Tteonaso.jwt.JwtToken;
import com.ssafy.Tteonaso.web.dto.MemberRequestDTO;

public interface MemberService {
    Member signUp(MemberRequestDTO.SignUpDTO signUpDTO);

    JwtToken signIn(MemberRequestDTO.SignInDTO signInDTO);

    Member getMemberDetail(String email);

    Member updateMemberProfile(String email, MemberRequestDTO.UpdateDTO updateDTO);
}
