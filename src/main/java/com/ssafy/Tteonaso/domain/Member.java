package com.ssafy.Tteonaso.domain;

import com.ssafy.Tteonaso.domain.common.BaseEntity;
import com.ssafy.Tteonaso.domain.enums.Gender;
import com.ssafy.Tteonaso.domain.enums.Role;
import com.ssafy.Tteonaso.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long memberId;

    String name;

    String password;


    @Enumerated(EnumType.STRING)
    Gender gender;

    String email;

    String phone;

    String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    Status status;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'MEMBER'")
    Role role;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    List<Follow> followerList;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    List<Follow> followingList;

}
