package com.pomiot.bookkeeper.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Getter @Setter
    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Getter @Setter
    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Getter @Setter
    @Column(name = "enabled", nullable = false)
    @NotNull
    private Boolean enabled;

    @Getter @Setter
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter @Setter
    @OneToMany(mappedBy = "owner")
    List<Book> booksOwned;
}