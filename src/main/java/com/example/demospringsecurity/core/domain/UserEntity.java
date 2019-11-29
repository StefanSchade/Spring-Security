package com.example.demospringsecurity.core.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionID = 2342423432L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String userId;

    @Column(nullable = false, length = 50)
    @NonNull
    private String firstName;

    @Column(nullable = false, length = 50)
    @NonNull
    private String lastName;

    @Column(nullable = false, length = 120)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String encryptedPassword;

    private String emailVerificationToken;

    @Column(nullable = false)
    private Boolean emailVerificationStatus = false;

}
