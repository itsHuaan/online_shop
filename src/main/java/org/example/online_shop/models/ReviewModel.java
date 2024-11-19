package org.example.online_shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewModel {
    private Long reviewId;
    private Long productId;
    private Integer rating;
    private String comment;
    private String response;
    private String image;
    private Boolean status = true;
    private LocalDateTime createdDate;
}

