package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.domain.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public class MemberDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberRequest {
        private String email;
        private String password;
        private String name;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birth;

        public Member toMember(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .birth(birth)
                    .authority(Authority.ROLE_USER)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberResponse {
        private Long id;
        private String email;
        private String name;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birth;

        public static MemberResponse of(Member member) {
            return MemberResponse.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .birth(member.getBirth())
                    .build();
        }
    }
}