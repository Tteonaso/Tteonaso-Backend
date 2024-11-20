package com.ssafy.Tteonaso.converter;


import com.ssafy.Tteonaso.domain.ChatRoom;
import com.ssafy.Tteonaso.web.dto.ChatRoomResponseDTO;

public class ChatRoomConverter {
    public static ChatRoomResponseDTO.getChatRoomDTO toGetChatRoomDTO(ChatRoom chatRoom) {
        return ChatRoomResponseDTO.getChatRoomDTO.builder()
                .chatRoomId(chatRoom.getChatRoomId())
                .sido(chatRoom.getAddress().getSidoName())
                .gugun(chatRoom.getAddress().getGugunName())
                .dong(chatRoom.getAddress().getDongName())
                .participants((long) chatRoom.getChatRoomMemberList().size())
                .build();
    }
}
