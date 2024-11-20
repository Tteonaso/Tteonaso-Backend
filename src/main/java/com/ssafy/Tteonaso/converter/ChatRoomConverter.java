package com.ssafy.Tteonaso.converter;


import com.ssafy.Tteonaso.domain.ChatRoom;
import com.ssafy.Tteonaso.web.dto.ChatRoomResponseDTO;

public class ChatRoomConverter {
    public static ChatRoomResponseDTO.getChatRoomDTO toGetChatRoomDTO(ChatRoom chatRoom) {
        String location = chatRoom.getAddress().getSidoName() + " " + chatRoom.getAddress().getGugunName();
        if (chatRoom.getAddress().getDongName() != null) {
            location += " ";
            location += chatRoom.getAddress().getDongName();
        }
        return ChatRoomResponseDTO.getChatRoomDTO.builder()
                .chatRoomId(chatRoom.getChatRoomId())
                .participants((long) chatRoom.getChatRoomMemberList().size())
                .location(location)
                .build();
    }
}
