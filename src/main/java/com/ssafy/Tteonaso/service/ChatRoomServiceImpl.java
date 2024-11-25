package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.domain.ChatRoom;
import com.ssafy.Tteonaso.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<ChatRoom> searchChatRoomByKeyword(String keyword) {

        return chatRoomRepository.findAll().stream()
                .filter(chatRoom -> chatRoom.getAddressName() != null && chatRoom.getAddressName().contains(keyword))
                .collect(Collectors.toList());
    }
}
