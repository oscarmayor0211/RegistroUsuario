package com.nisum.createUser.exception;

import lombok.Getter;

enum Error {

    BAD_PARAMS("Incorrect param in the request, "),
    DUPLICATE_EMAIL("email already registered"),
    BAD_ACCESS_INVALID("Access Invalid"),
    BAD_PASSWORD_FORMAT("The password must be contains " +
            "at least one digit, at least one lowercase Latin character, " +
            "at least one uppercase Latin character, " +
            "at least one special character, " +
            "a length of at least 8 characters and a maximum of 20 characters");

    @Getter
    private final String message;

    Error(String message) {
        this.message = message;
    }
}
