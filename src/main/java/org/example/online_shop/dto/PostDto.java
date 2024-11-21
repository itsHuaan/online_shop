package org.example.online_shop.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long postId;
    private String title;
    @Size(max = 10000)
    private String content;
    private String imageUrl;
    private Boolean status = true;
    private LocalDateTime createdDate;
}
