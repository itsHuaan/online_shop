package org.example.online_shop.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModel {
    private Long categoryId;
    private String name;
    private String description;
    private Boolean status = true;
    private LocalDateTime createdDate = LocalDateTime.now();
}

