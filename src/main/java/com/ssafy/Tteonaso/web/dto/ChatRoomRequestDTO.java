package com.ssafy.Tteonaso.web.dto;

import lombok.Getter;
import lombok.Setter;

public class ChatRoomRequestDTO {
    @Getter
    @Setter
    public static class SearchDTO {
        String keyword;
    }
}
