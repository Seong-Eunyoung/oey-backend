package com.oey.lab.controller;

import com.oey.lab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{userName}")
    public ResponseEntity<Long> getUserId(@PathVariable String userName) {
        System.out.println(userName);
        Long userId = userRepository.findByUserName(userName).orElseThrow().getId();
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

}