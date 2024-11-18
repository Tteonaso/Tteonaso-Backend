package com.ssafy.Tteonaso.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;
    private final int EXPIRED;

    public JwtTokenProvider(Key key, int EXPIRED) {
        this.key = key;
        this.EXPIRED = EXPIRED;
    }
}
