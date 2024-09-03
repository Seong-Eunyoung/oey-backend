package com.oey.lab.repository;

import com.oey.lab.entity.User;
import com.oey.lab.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
}