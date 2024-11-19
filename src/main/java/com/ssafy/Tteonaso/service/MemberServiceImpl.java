package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.apiPayload.code.status.ErrorStatus;
import com.ssafy.Tteonaso.apiPayload.exception.handler.JwtHandler;
import com.ssafy.Tteonaso.apiPayload.exception.handler.MemberHandler;
import com.ssafy.Tteonaso.converter.MemberConverter;
import com.ssafy.Tteonaso.domain.Member;
import com.ssafy.Tteonaso.jwt.JwtToken;
import com.ssafy.Tteonaso.jwt.JwtTokenProvider;
import com.ssafy.Tteonaso.repository.MemberRepository;
import com.ssafy.Tteonaso.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    @Transactional
    public Member signUp(MemberRequestDTO.SignUpDTO signUpDTO) {
        if (memberRepository.existsByEmail(signUpDTO.getEmail())) {
            throw new MemberHandler(ErrorStatus.ALREADY_EXIST_MEMBER);
        }
        String profileImage = "";
        Member newMember = MemberConverter.toMember(signUpDTO, profileImage, passwordEncoder.encode(signUpDTO.getPassword()));
        return memberRepository.save(newMember);
    }

    @Override
    public JwtToken signIn(MemberRequestDTO.SignInDTO signInDTO) {
        if (!memberRepository.existsByEmail(signInDTO.getEmail())) {
            throw new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        try {
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(signInDTO.getEmail(), signInDTO.getPassword());

            Authentication authentication
                    = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            return jwtTokenProvider.generateToken(authentication);
        } catch (Exception e) {
            throw new JwtHandler(ErrorStatus.PASSWORD_NOT_MATCH);
        }
    }
}
