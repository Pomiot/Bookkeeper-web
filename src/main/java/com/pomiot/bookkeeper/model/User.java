package com.pomiot.bookkeeper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @JsonIgnore
    @Column(name = "enabled", nullable = false)
    @NotNull
    private Boolean enabled;

    @JsonIgnore
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    List<Book> booksOwned;
}