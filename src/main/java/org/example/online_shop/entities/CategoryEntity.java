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
@Table(name = "tbl_category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;
    private String description;
    private Boolean status = true;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}

