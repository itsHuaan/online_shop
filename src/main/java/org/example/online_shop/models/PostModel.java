package org.example.online_shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostModel {
    private Long postId;
    private String title;
    private String content;
    private Boolean status = true;
    private LocalDateTime createdDate;
}

