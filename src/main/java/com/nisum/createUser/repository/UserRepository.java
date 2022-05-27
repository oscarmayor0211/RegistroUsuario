package com.nisum.createUser.repository;

import com.nisum.createUser.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findById(String id);
    boolean existsByPassword(String password);
    User findByEmail(String email);

}
