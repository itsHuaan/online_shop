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
@Table(name = "tbl_payment_method")
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentMethodId;

    private String type;
    private String provider;
    private String accountNumber;
    private LocalDate expiryDate;
    private Boolean status = true;

    @OneToMany(mappedBy = "paymentMethod")
    private List<InvoiceEntity> invoices;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}

