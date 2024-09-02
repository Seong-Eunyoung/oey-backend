package com.oey.lab.dto.mapper;

import com.oey.lab.dto.WishDto;
import com.oey.lab.entity.User;
import com.oey.lab.entity.Wish;
import com.oey.lab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WishMapper {

    private final UserRepository userRepository;

    public static WishDto mapToWishDto(Wish wish){
        return new WishDto(
                wish.getId(),
                wish.getUserId().getId(),
                wish.getContent(),
                wish.getType(),
                wish.getStatus()
        );
    }

    public Wish mapToWish(WishDto wishDto){
        User user = userRepository.findById(wishDto.getUserId()).get();

        return new Wish(
                wishDto.getId(),
                user,
                wishDto.getContent(),
                wishDto.getType(),
                wishDto.getStatus()
        );
    }
}