package org.example.online_shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceModel {
    private Long invoiceId;
    private Long user_id;
    private String deliveryAddress;
    private double totalAmount;
    private String note;
    private Integer deliveryStatus;
    private Long paymentMethodId;
    private LocalDateTime createdDate;
}

