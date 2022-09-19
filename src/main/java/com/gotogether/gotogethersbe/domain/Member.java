package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.domain.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키

    @Column(nullable = false)
    private String email; //이메일

    @Column(nullable = false)
    private String password; //비밀번호

    @Column(nullable = false)
    private String name; //이름

    private Date birth; //생일

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String email, String password, String name, Authority authority) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.authority = authority;
    }
}
