package com.pomiot.bookkeeper.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    private Boolean verified;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "username")
    private User user;

    public VerificationToken(){}

    public VerificationToken(String token, User user){
        this.token = token;
        this.user = user;
        this.verified = false;
    }
}
