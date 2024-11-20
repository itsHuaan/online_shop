package org.example.online_shop.dto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
    private Long authorId;
    private String name;
    private Boolean status;
    private LocalDateTime createdDate;
}
