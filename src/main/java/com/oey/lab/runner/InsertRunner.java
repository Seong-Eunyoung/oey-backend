package com.oey.lab.runner;

import com.oey.lab.entity.User;
import com.oey.lab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class InsertRunner implements ApplicationRunner {
    final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User yoon = new User();
        yoon.setUserName("yoon");
        yoon.setEmail("daniel9924@naver.com");
        yoon.setPassword(passwordEncoder.encode("0601"));
        yoon.setRole("ROLE_USER");

        User eunyoung = new User();
        eunyoung.setUserName("young");
        eunyoung.setEmail("s081209@naver.com");
        eunyoung.setPassword(passwordEncoder.encode("0601"));
        eunyoung.setRole("ROLE_USER");

        userRepository.saveAll(List.of(yoon, eunyoung));
    }
}