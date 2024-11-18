package org.example.online_shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_discount")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    private String name;
    private double discountPercentage;
    @Temporal(TemporalType.DATE)
    private LocalDate expiryDate;
    private Boolean status = true;

    @OneToMany(mappedBy = "discount")
    private List<ProductEntity> products;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}

