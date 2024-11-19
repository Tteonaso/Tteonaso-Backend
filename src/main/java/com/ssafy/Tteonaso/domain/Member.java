package com.ssafy.Tteonaso.domain;

import com.ssafy.Tteonaso.domain.enums.Gender;
import com.ssafy.Tteonaso.domain.enums.Status;

import java.time.LocalDateTime;

public class Member {
    Long memberId;
    String name;
    String password;
    //Address address;
    Gender gender;
    String email;
    String phone;
    String profileImage;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    Status status;
}
