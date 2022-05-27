package com.nisum.createUser.service;

import com.nisum.createUser.exception.EmailDupedException;
import com.nisum.createUser.exception.PasswordFormatException;
import com.nisum.createUser.models.User;

import java.util.List;

public interface UserService {

    User createUser(User user) throws EmailDupedException, PasswordFormatException;
    boolean existsByPassword(String password);
    boolean existsByEmail(String email);
    User findById(String id);
    User updatePersona(User user);
    List<User> getAllUsers();
    User findByEmail(String email);

}
