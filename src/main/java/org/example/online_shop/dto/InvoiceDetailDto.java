package org.example.online_shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetailDto {
    private Long invoiceDetailId;
    private Long productId;
    private String productName;
    private double price;
    private Integer quantity;
    private Boolean status;
    private LocalDateTime createdDate;
}
