package com.nisum.createUser.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EmailDupedException.class)
    public ResponseEntity<Response> handle(EmailDupedException ex) {
        return ResponseEntity.badRequest().body(new Response(Error.DUPLICATE_EMAIL.getMessage()));
    }

    @ExceptionHandler(PasswordFormatException.class)
    public ResponseEntity<Response> handle(PasswordFormatException ex) {
        return ResponseEntity.badRequest().body(new Response(Error.BAD_PASSWORD_FORMAT.getMessage()));
    }

    @ExceptionHandler(AccessInvalid.class)
    public ResponseEntity<Response> AccessInvalid(AccessInvalid ex) {
        return ResponseEntity.badRequest().body(new Response(Error.BAD_ACCESS_INVALID.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handle(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .badRequest()
                .body(new Response(buildMessage(Error.BAD_PARAMS.getMessage(), ex.getBindingResult().getFieldErrors())));
    }

    private String buildMessage(String message, List<FieldError> errors) {
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        errors.forEach(e -> {
            sb
                    .append("Field: ")
                    .append(e.getField())
                    .append(" ")
                    .append(e.getDefaultMessage())
                    .append(", ");
        });

        String text = sb.toString();
        return text.trim().substring(0, text.length() - 2);
    }

    @Data
    @AllArgsConstructor
    public static class Response {

        @JsonProperty("mensaje")
        private String message;
    }

}
