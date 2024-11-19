package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.apiPayload.code.status.ErrorStatus;
import com.ssafy.Tteonaso.apiPayload.exception.handler.MemberHandler;
import com.ssafy.Tteonaso.domain.Member;
import com.ssafy.Tteonaso.repository.MemberRepository;
import com.ssafy.Tteonaso.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public Member signUp(MemberRequestDTO.SignUpDTO signUpDTO) {
        if (memberRepository.selectByEmail(signUpDTO.getEmail()) != 0) {
            throw new MemberHandler(ErrorStatus.ALREADY_EXIST_MEMBER);
        }
        return null;
    }
}
