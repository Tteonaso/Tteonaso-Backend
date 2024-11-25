package com.ssafy.Tteonaso.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class SignUpDTO {
        String name;
        String email;
        String password;
        String gender;
        String phone;
        Integer age;
    }

    @Getter
    @Setter
    public static class UpdateDTO {
        String name;
        String password;
        String phone;
        Integer age;
    }


    @Getter
    @Setter
    public static class SignInDTO {
        String email;
        String password;
    }
}
