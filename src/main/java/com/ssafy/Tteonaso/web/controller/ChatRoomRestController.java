package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponse;
import com.ssafy.Tteonaso.converter.ChatRoomConverter;
import com.ssafy.Tteonaso.domain.ChatRoom;
import com.ssafy.Tteonaso.service.ChatRoomService;
import com.ssafy.Tteonaso.web.dto.ChatRoomRequestDTO;
import com.ssafy.Tteonaso.web.dto.ChatRoomResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chatroom")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatRoomRestController {

    private final ChatRoomService chatRoomService;

    @GetMapping()
    public ApiResponse<List<ChatRoomResponseDTO.getChatRoomDTO>> readAllChatRoom() {
        List<ChatRoom> chatRoomList = chatRoomService.readAllChatRoom();

        return ApiResponse.onSuccess(chatRoomList.stream()
                .map(ChatRoomConverter::toGetChatRoomDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/search")
    public ApiResponse<List<ChatRoomResponseDTO.getChatRoomDTO>> searchChatRoom(@RequestBody @Valid ChatRoomRequestDTO.SearchDTO searchDTO) {
        List<ChatRoom> chatRoomList = chatRoomService.searchChatRoomByKeyword(searchDTO);

        return ApiResponse.onSuccess(chatRoomList.stream()
                .map(ChatRoomConverter::toGetChatRoomDTO)
                .collect(Collectors.toList()));
    }
}
