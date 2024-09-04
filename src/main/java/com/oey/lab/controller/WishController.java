// FollowController
package com.oey.lab.controller;

import com.oey.lab.dto.WishDto;
import com.oey.lab.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/wish")
public class WishController {

    private final WishService wishService;

    @GetMapping
    public ResponseEntity<List<WishDto>> wishList() {
        List<WishDto> wishes = wishService.wishList();
        return new ResponseEntity<>(wishes, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<WishDto> makeWish(@RequestBody WishDto wishDto) {
        WishDto madeWish =  wishService.makeWish(wishDto);
        return new ResponseEntity<>(madeWish, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteWish(@RequestBody WishDto wishDto) {
        return null;
    }







}