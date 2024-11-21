package com.ssafy.Tteonaso.web.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
    public enum MessageType {
        JOIN, TALK, LEAVE
    }

    private MessageType messageType;
    private Long chatRoomId;
    private String message;
    private String sender;
}
