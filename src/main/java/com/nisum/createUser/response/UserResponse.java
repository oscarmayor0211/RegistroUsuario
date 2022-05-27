package com.nisum.createUser.response;

import com.nisum.createUser.models.User;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UserResponse {

    String id ;
    LocalDateTime created;
    LocalDateTime modified;
    boolean isActive;
    String token;
    LocalDateTime last_login;


    public static UserResponse of(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .isActive(user.isActive())
                .token(user.getAccessToken())
                .last_login(user.getLast_login())
                .build();
    }
}
