package org.example.online_shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetailModel {
    private InvoiceModel invoice;
    private ProductModel product;
    private double price;
    private Integer quantity;
    private Boolean status = true;
    private LocalDateTime createdDate = LocalDateTime.now();
}

