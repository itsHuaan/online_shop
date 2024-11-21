package org.example.online_shop.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    @Size(max = 10000)
    private String content;
    private String imageUrl;
    private Boolean status = true;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}

