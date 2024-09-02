package com.oey.lab.runner;

import com.oey.lab.entity.User;
import com.oey.lab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Component
@Profile("test")
@RequiredArgsConstructor
public class InsertRunner implements ApplicationRunner {
    final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User yoon = new User();
        yoon.setUserName("윤");
        yoon.setEmail("daniel9924@naver.com");
        yoon.setPassword(passwordEncoder.encode("0601"));
        yoon.setRole("BF");

        User eunyoung = new User();
        eunyoung.setUserName("은영");
        eunyoung.setEmail("s081209@naver.com");
        eunyoung.setPassword(passwordEncoder.encode("0601"));
        eunyoung.setRole("GF");

        userRepository.saveAll(List.of(yoon, eunyoung));
    }
}