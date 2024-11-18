package com.ssafy.Tteonaso.converter;

import com.ssafy.Tteonaso.web.dto.MemberResponseDTO;

public class MemberConverter {
    public static MemberResponseDTO.TempTestDTO toTempTestDTO() {
        return MemberResponseDTO.TempTestDTO.builder()
                .testString("This is Test!")
                .build();
    }
}

