package com.ssafy.Tteonaso.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempTestDTO {
        String testString;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpResponseDTO {
        Long memberId;
        String name;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInResponseDTO {
        String accessToken;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberDetailDTO {
        Long memberId;
        String name;
        String gender;
        String email;
        String phone;
        String profileImage;
        String role;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class MemberStateDTO {
        String email;
        String status;
    }
}
