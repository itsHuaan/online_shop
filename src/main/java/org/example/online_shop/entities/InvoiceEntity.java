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
@Table(name = "tbl_invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String deliveryAddress;
    private double totalAmount;
    private String note;
    private Integer deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethodEntity paymentMethod;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetailEntity> invoiceDetails = new ArrayList<>();

    private Boolean status = true;


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}

