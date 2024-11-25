package com.ssafy.Tteonaso.web.controller;

import com.ssafy.Tteonaso.apiPayload.ApiResponseDTO;
import com.ssafy.Tteonaso.converter.ChatRoomConverter;
import com.ssafy.Tteonaso.domain.ChatRoom;
import com.ssafy.Tteonaso.service.ChatRoomService;
import com.ssafy.Tteonaso.web.dto.ChatRoomResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chatroom")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "ChatRoom API", description = "채팅방 관련 API")
public class ChatRoomRestController {

    private final ChatRoomService chatRoomService;

    @Operation(
            summary = "모든 채팅방 조회",
            description = "현재 존재하는 모든 채팅방을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "성공적으로 채팅방 목록 반환",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChatRoomResponseDTO.getChatRoomDTO.class)))
            )
    })
    @GetMapping()
    public ApiResponseDTO<List<ChatRoomResponseDTO.getChatRoomDTO>> readAllChatRoom() {
        List<ChatRoom> chatRoomList = chatRoomService.readAllChatRoom();

        return ApiResponseDTO.onSuccess(chatRoomList.stream()
                .map(ChatRoomConverter::toGetChatRoomDTO)
                .collect(Collectors.toList()));
    }


    @Operation(
            summary = "채팅방 검색",
            description = "키워드를 기준으로 채팅방을 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "성공적으로 검색된 채팅방 반환",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChatRoomResponseDTO.getChatRoomDTO.class)))
            )
    })
    @GetMapping("/search")
    public ApiResponseDTO<List<ChatRoomResponseDTO.getChatRoomDTO>> searchChatRoom(@Schema(description = "검색 키워드", example = "서울") @RequestParam String keyword) {
        List<ChatRoom> chatRoomList = chatRoomService.searchChatRoomByKeyword(keyword);

        return ApiResponseDTO.onSuccess(chatRoomList.stream()
                .map(ChatRoomConverter::toGetChatRoomDTO)
                .collect(Collectors.toList()));
    }
}
