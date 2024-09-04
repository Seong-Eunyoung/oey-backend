// FollowServiceImpl
package com.oey.lab.service.impl;

import com.oey.lab.dto.WishDto;
import com.oey.lab.dto.mapper.WishMapper;
import com.oey.lab.entity.User;
import com.oey.lab.entity.Wish;
import com.oey.lab.exception.ResourceNotFoundException;
import com.oey.lab.repository.UserRepository;
import com.oey.lab.repository.WishRepository;
import com.oey.lab.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final WishMapper wishMapper;

    @Override
    public List<WishDto> wishList() {
        List<Wish> wishes  = wishRepository.findAll();
        return wishes.stream()
                .map(WishMapper::mapToWishDto)
                .collect(Collectors.toList());
    }

    @Override
    public WishDto makeWish(WishDto wishDto) {
        Wish wish = wishMapper.mapToWish(wishDto);
        Wish madeWish = wishRepository.save(wish);
        return WishMapper.mapToWishDto(madeWish);  // static 아니라 이렇게 사용 ..
    }


}