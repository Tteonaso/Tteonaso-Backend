package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponse;
import com.ssafy.Tteonaso.web.dto.ChatRoomResponseDTO;
import com.ssafy.Tteonaso.web.dto.MapFilterResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chatroom")
public class ChatRoomRestController {

    @GetMapping()
    public ApiResponse<ChatRoomResponseDTO.getChatRoomDTO> readAllChatRoom() {
        return ApiResponse.onSuccess(null);
    }
}
