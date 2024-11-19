package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.domain.Member;
import com.ssafy.Tteonaso.web.dto.MemberRequestDTO;

public interface MemberService {
    Member signUp(MemberRequestDTO.SignUpDTO signUpDTO);

    Member signIn(MemberRequestDTO.SignInDTO signInDTO);
}
