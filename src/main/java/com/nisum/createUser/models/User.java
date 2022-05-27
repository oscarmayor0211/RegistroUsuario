package com.nisum.createUser.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(length = 100)
    private String id = UUID.randomUUID().toString();

    @Column
    private String name;

    @Column(unique = false)
    @NotBlank
    @Pattern(
            message = "Incorrect email format",
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"
    )
    private String email;

    @Column
    LocalDateTime last_login;

    @Column
    private String password;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    public List<Phone> phones;

    @Column
    private LocalDateTime created = LocalDateTime.now();

    @Column
    private LocalDateTime modified;

    @Column
    private String accessToken;

    @Column
    boolean active;


    public User(String id, String name, String email, String password, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

}
