package com.ssafy.Tteonaso.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatRoomResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getChatRoomDTO {
        Long chatRoomId;
        Long participants;
        String location;
    }
}
