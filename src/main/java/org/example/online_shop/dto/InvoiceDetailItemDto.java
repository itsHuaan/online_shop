package org.example.online_shop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetailItemDto {
    private Long productId;
    private String productName;
    private double price;
    private Integer quantity;
}
