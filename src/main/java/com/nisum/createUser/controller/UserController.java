package com.nisum.createUser.controller;

import com.nisum.createUser.response.UserResponse;
import com.nisum.createUser.exception.AccessInvalid;
import com.nisum.createUser.exception.EmailDupedException;
import com.nisum.createUser.exception.ErrorHandler;
import com.nisum.createUser.exception.PasswordFormatException;
import com.nisum.createUser.models.User;
import com.nisum.createUser.service.UserService;
import com.nisum.createUser.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Api
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl service;


    @PostMapping
    @ApiOperation(value = "Create an user", response = UserResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieves a users"),
            @ApiResponse(code = 400, message = "Return an error because business logic", response = ErrorHandler.Response.class)
    })
    public ResponseEntity<UserResponse> create(@RequestBody @Validated User body) throws EmailDupedException, PasswordFormatException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.of(userService.createUser(body)));
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update an user", response = UserResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieves a users"),
            @ApiResponse(code = 400, message = "Return an error because business logic", response = ErrorHandler.Response.class)
    })
    public ResponseEntity<User> updatePersona(@Validated @PathVariable String id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body((userService.updatePersona(user)));
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all users", response = UserResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieves a users"),
            @ApiResponse(code = 400, message = "Return an error because business logic", response = ErrorHandler.Response.class)
    })
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body((userService.getAllUsers()));
    }


    @PostMapping("/access")
    @ApiOperation(value = "Access", response = UserResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieves a users"),
            @ApiResponse(code = 400, message = "Return an error because business logic", response = ErrorHandler.Response.class)
    })
    public ResponseEntity<UserResponse> access(@RequestBody User body) throws AccessInvalid {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.of(service.access(body)));
    }
}
