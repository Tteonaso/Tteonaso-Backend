package com.ssafy.Tteonaso.jwt;

import com.ssafy.Tteonaso.apiPayload.code.status.ErrorStatus;
import com.ssafy.Tteonaso.apiPayload.exception.handler.JwtHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class JwtSecurityUtil {
    public static String getCurrentMemberEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new JwtHandler(ErrorStatus.ATHENTICATION_NOT_FOUND);
        }
        return authentication.getName();
    }
}
