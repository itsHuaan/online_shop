package org.example.online_shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_author")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    private String name;
    private Boolean status = true;

    @OneToMany(mappedBy = "author")
    private List<ProductEntity> products;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}

