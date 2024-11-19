package org.example.online_shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartModel {
    private Long user_id;
    private double totalPrice = 0;
    private Boolean status = true;
    private LocalDateTime createdDate = LocalDateTime.now();
}

