package com.ssafy.Tteonaso.repository;


import com.ssafy.Tteonaso.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
}
