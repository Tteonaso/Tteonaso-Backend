package com.ssafy.Tteonaso.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "GPT 일정 계획 요청 Enum item 응답 객체")
public class GptEnumResponseDTO {
    @Schema(description = "일정 계획 요청 Enum 이름", example = "ENUM_NAME")
    private String name;

    @Schema(description = "일정 계획 요청 Enum 설명", example = "일정 계획 요청 아이템 ENUM 설명")
    private String description;
}
