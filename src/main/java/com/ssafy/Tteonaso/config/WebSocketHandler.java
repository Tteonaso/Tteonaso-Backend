package com.ssafy.Tteonaso.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.Tteonaso.web.dto.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;
    private final Set<WebSocketSession> sessions = new HashSet<>();
    private final Map<Long, Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();

    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} 사용자 연결", session.getId());
        sessions.add(session);
        session.sendMessage(new TextMessage("WebSocket 연결 완료"));
    }

    // 소켓 메시지 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        ChatMessageDTO chatMessageDTO = mapper.readValue(payload, ChatMessageDTO.class);
        log.info("session {}", chatMessageDTO.toString());

        if (chatMessageDTO.getMessageType().equals(ChatMessageDTO.MessageType.JOIN)) {
            chatRoomSessionMap.computeIfAbsent(chatMessageDTO.getChatRoomId(), s -> new HashSet<>()).add(session);
            chatMessageDTO.setMessage("님이 입장하셨습니다.");
        } else if (chatMessageDTO.getMessageType().equals(ChatMessageDTO.MessageType.LEAVE)) {
            chatRoomSessionMap.get(chatMessageDTO.getChatRoomId()).remove(session);
            chatMessageDTO.setMessage("님이 퇴장하셨습니다.");
        }

        // 메시지를 보낼 때, 세션이 열린 상태인지 확인
        for (WebSocketSession webSocketSession : chatRoomSessionMap.get(chatMessageDTO.getChatRoomId())) {
            if (webSocketSession.isOpen()) {  // 세션이 열려 있는지 확인
                webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsString(chatMessageDTO)));
            } else {
                // 세션이 닫혔다면 맵에서 제거
                chatRoomSessionMap.get(chatMessageDTO.getChatRoomId()).remove(webSocketSession);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} 연결 끊김", session.getId());
        session.sendMessage(new TextMessage("WebSocket 연결 종료"));
        sessions.remove(session);

        // 연결이 끊어진 세션을 채팅방 세션 맵에서 제거
        for (Set<WebSocketSession> roomSessions : chatRoomSessionMap.values()) {
            roomSessions.remove(session);
        }
    }
}
