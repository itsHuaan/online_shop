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
@Table(name = "tbl_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    private Boolean status = true;

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "user")
    private List<CartEntity> carts;

    @OneToMany(mappedBy = "user")
    private List<InvoiceEntity> invoices;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}

