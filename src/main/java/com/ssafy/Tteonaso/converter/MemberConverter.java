package com.ssafy.Tteonaso.converter;

import com.ssafy.Tteonaso.domain.Member;
import com.ssafy.Tteonaso.domain.enums.Gender;
import com.ssafy.Tteonaso.web.dto.MemberRequestDTO;
import com.ssafy.Tteonaso.web.dto.MemberResponseDTO;

import java.util.ArrayList;

public class MemberConverter {
    public static MemberResponseDTO.TempTestDTO toTempTestDTO() {
        return MemberResponseDTO.TempTestDTO.builder()
                .testString("This is Test!")
                .build();
    }

    public static Member toMember(MemberRequestDTO.SignUpDTO signUpDTO, String profileImageUrl, String password) {
        Gender gender = null;
        switch (signUpDTO.getGender()) {
            case "MALE":
                gender = Gender.MALE;
                break;
            case "FEMALE":
                gender = Gender.FEMALE;
                break;
        }

        return Member.builder()
                .name(signUpDTO.getName())
                .email(signUpDTO.getEmail())
                .password(password)
                .phone(signUpDTO.getPhone())
                .gender(gender)
                .followingList(new ArrayList<>())
                .followerList(new ArrayList<>())
                .build();
    }
}

