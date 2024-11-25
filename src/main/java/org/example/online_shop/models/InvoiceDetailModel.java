package org.example.online_shop.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetailModel {
    private Long invoiceId;
    private Long productId;
    private Integer quantity;
    private Boolean status = true;
    private LocalDateTime createdDate = LocalDateTime.now();
}

