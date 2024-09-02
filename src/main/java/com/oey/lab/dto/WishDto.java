package com.oey.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishDto {
    private Long id;
    private Long userId;
    private String content;
    private String type;
    private Boolean status;
}