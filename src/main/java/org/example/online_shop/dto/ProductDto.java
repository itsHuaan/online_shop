package org.example.online_shop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private double importPrice;
    private double salePrice;
    private Integer stock;
    private Integer sold;
    private Integer totalPage;
    private Integer publishDate;
    private String categoryName;
    private String authorName;
    private double discountPercentage;
    private boolean status;
}
