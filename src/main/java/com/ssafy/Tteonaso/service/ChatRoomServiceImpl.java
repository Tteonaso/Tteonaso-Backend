package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.domain.ChatRoom;
import com.ssafy.Tteonaso.repository.ChatRoomRepository;
import com.ssafy.Tteonaso.web.dto.ChatRoomRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{

    public final ChatRoomRepository chatRoomRepository;

    @Override
    public List<ChatRoom> readAllChatRoom() {
        return chatRoomRepository.findAll();
    }

    @Override
    public List<ChatRoom> searchChatRoomByKeyword(ChatRoomRequestDTO.SearchDTO searchDTO) {
        String keyword = searchDTO.getKeyword().trim();

        return chatRoomRepository.findAll().stream()
                .filter(chatRoom -> chatRoom.getAddressName() != null && chatRoom.getAddressName().contains(keyword))
                .collect(Collectors.toList());
    }
}
