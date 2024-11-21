package org.example.online_shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailDto {
    private Long cartDetailId;
    private Long cartId;
    private ProductDto productDto;
    private int quantity;
}
