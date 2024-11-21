package org.example.online_shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Long categoryId;
    private String name, description;
    private LocalDateTime createDate;
    private boolean status;
}
