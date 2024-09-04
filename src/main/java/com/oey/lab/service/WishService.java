// FollowService
package com.oey.lab.service;

import com.oey.lab.dto.WishDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WishService {

    List<WishDto> wishList();

    WishDto makeWish(WishDto wishDto);
}