package org.example.online_shop.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDetailModel {
    private Long cartId;
    private Long productId;
    private Integer quantity;
    private Boolean status = true;
    private LocalDateTime createdDate = LocalDateTime.now();
}

