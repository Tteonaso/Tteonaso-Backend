package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{
    @Override
    public List<ChatRoom> readAllChatRoom() {
        return null;
    }
}
