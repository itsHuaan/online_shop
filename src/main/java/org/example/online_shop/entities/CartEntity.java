package org.example.online_shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private double totalPrice = 0;
    private Boolean status = true;

    @OneToMany(mappedBy = "cart")
    private List<CartDetailEntity> cartDetails = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}

