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
public class ProductModel {
    private Long productId;
    private String name;
    private String description;
    private double importPrice;
    private double salePrice;
    private Integer stock;
    private Integer totalPage;
    private Integer publishDate;
    private Long categoryId;
    private Long authorId;
    private Long discountId;
    private Boolean status = true;
    private LocalDateTime createdDate;
}

