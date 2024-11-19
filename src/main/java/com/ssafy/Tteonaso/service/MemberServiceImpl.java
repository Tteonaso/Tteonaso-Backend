package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.apiPayload.code.status.ErrorStatus;
import com.ssafy.Tteonaso.apiPayload.exception.handler.MemberHandler;
import com.ssafy.Tteonaso.domain.Member;
import com.ssafy.Tteonaso.repository.MemberRepository;
import com.ssafy.Tteonaso.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public Member signUp(MemberRequestDTO.SignUpDTO signUpDTO) {

        return null;
    }
}
