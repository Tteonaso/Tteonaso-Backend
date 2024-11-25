package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.domain.ChatRoom;
import com.ssafy.Tteonaso.web.dto.ChatRoomRequestDTO;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoom> readAllChatRoom();

    List<ChatRoom> searchChatRoomByKeyword(ChatRoomRequestDTO.SearchDTO searchDTO);
}
