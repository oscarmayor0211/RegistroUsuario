package com.nisum.createUser.service.impl;

import com.nisum.createUser.exception.AccessInvalid;
import com.nisum.createUser.exception.EmailDupedException;
import com.nisum.createUser.exception.PasswordFormatException;
import com.nisum.createUser.jwtAdapter.JwtAdapter;
import com.nisum.createUser.jwtAdapter.JwtConfig;
import com.nisum.createUser.models.User;
import com.nisum.createUser.repository.UserRepository;
import com.nisum.createUser.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtAdapter jwtAdapter;

    @Autowired
    private final JwtConfig config;

    @Override
    public User createUser(User user) throws EmailDupedException, PasswordFormatException {
        LocalDateTime fecha = LocalDateTime.now();
        user.setCreated(fecha);
        user.setLast_login(fecha);
        user.setActive(Boolean.TRUE);
        user.setAccessToken(jwtAdapter.generateToken(user.getId()));
        validatePassword(user.getPassword());
            if (this.existsByEmail(user.getEmail())){
                throw  new EmailDupedException();
            }else{
                return userRepository.save(user);
            }
    }

    @Override
    public boolean existsByPassword(String password) {
        return userRepository.existsByPassword(password);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User updatePersona(User user) {
        LocalDateTime modified = LocalDateTime.now();
        User userUpdate = userRepository.findById(user.getId());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setModified(modified);
        userUpdate.setName(user.getName());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setPhones(user.getPhones());
        return userRepository.save(userUpdate);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User access(User user) throws AccessInvalid {
        LocalDateTime last_login = LocalDateTime.now();
        User usr = findByEmail(user.getEmail());
        if (existsByEmail(user.getEmail()) && existsByPassword(user.getPassword())) {
            usr.setLast_login(last_login);
            userRepository.save(usr);
            return usr;
        }else{
            throw new AccessInvalid();
        }
    }

    public void validatePassword(String password) throws PasswordFormatException {
        Pattern pattern = Pattern.compile(config.getPatternPassword());
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new PasswordFormatException();
        }
    }

}
