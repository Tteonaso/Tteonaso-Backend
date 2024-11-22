package com.ssafy.Tteonaso.converter;

import com.ssafy.Tteonaso.web.dto.GptEnumResponseDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GptEnumConverter {

    public static <T extends Enum<T>> List<GptEnumResponseDTO> convert(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(enumConstant -> {
                    try {
                        String description = (String) enumClass
                                .getDeclaredMethod("getDescription")
                                .invoke(enumConstant);

                        return new GptEnumResponseDTO(enumConstant.name(), description);
                    } catch (Exception e) {
                        throw new RuntimeException("GPT Enum Convert 실패: " + enumClass.getSimpleName(), e);
                    }
                })
                .collect(Collectors.toList());
    }
}
