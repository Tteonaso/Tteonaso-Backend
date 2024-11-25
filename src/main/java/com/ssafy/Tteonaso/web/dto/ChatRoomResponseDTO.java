package com.ssafy.Tteonaso.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatRoomResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "채팅방 응답 DTO")
    public static class getChatRoomDTO {
        @Schema(description = "채팅방 ID", example = "1")
        Long chatRoomId;

        @Schema(description = "참여자 수", example = "5")
        Long participants;

        @Schema(description = "채팅방 위치", example = "서울특별시 종로구 효자동")
        String location;
    }
}
