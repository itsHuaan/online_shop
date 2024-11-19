package org.example.online_shop.models;

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
public class PaymentMethodModel {
    private Long paymentMethodId;
    private String type;
    private String provider;
    private String accountNumber;
    private LocalDate expiryDate;
    private Boolean status = true;
    private LocalDateTime createdDate = LocalDateTime.now();
}

