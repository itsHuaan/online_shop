package org.example.online_shop.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountDto {
    private Long discountId;
    private String name;
    private double discountPercentage;
    private LocalDate expiryDate;
    private LocalDateTime createDate;
    private boolean status;
}
