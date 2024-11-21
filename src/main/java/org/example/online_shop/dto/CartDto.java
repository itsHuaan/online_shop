package org.example.online_shop.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Long cartId;
    private UserDto user;
    private double totalPrice = 0.0;
    private List<CartDetailDto> cartDetailDtoList;
    private LocalDateTime createdDate;
}
