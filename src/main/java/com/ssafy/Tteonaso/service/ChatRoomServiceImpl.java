package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.domain.ChatRoom;
import com.ssafy.Tteonaso.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{

    public final ChatRoomRepository chatRoomRepository;

    @Override
    public List<ChatRoom> readAllChatRoom() {
        return chatRoomRepository.findAll();
    }
}
