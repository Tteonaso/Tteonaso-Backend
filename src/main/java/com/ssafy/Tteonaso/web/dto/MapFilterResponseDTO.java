package com.ssafy.Tteonaso.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MapFilterResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "구군 응답 DTO")
    public static class GugunResponseDTO {
        @Schema(description = "구군 이름 목록", example = "[\"강남구\", \"서초구\", \"송파구\"]")
        List<String> gugunNames;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "동 응답 DTO")
    public static class DongResponseDTO {
        @Schema(description = "동 이름 목록", example = "[\"역삼동\", \"삼성동\", \"논현동\"]")
        List<String> dongNames;
    }
}
