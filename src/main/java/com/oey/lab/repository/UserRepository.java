package com.oey.lab.repository;

import com.oey.lab.entity.User;
import com.oey.lab.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}