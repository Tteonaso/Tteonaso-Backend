package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponse;
import com.ssafy.Tteonaso.domain.ChatRoom;
import com.ssafy.Tteonaso.service.ChatRoomService;
import com.ssafy.Tteonaso.web.dto.ChatRoomResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chatroom")
public class ChatRoomRestController {

    private final ChatRoomService chatRoomService;

    @GetMapping()
    public ApiResponse<ChatRoomResponseDTO.getChatRoomDTO> readAllChatRoom() {
        List<ChatRoom> chatRoomList = chatRoomService.readAllChatRoom();
        return ApiResponse.onSuccess(null);
    }
}
