package org.example.online_shop.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDto {
    private Long invoiceId;
    private Long userId;
    private String deliveryAddress;
    private double totalAmount;
    private String note;
    private Integer deliveryStatus;
    private String paymentMethod;
    private Boolean status;
    private List<InvoiceDetailDto> invoiceDetails;
    private LocalDateTime createdDate;
}
