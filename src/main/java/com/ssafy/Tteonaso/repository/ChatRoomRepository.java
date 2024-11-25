package com.ssafy.Tteonaso.repository;

import com.ssafy.Tteonaso.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByAddressNameContaining(String keyword);
}
