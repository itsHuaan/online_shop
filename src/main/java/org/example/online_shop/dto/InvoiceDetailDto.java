package org.example.online_shop.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetailDto {
    private Long invoiceId;
    private Long userId;
    private String deliveryAddress;
    private double totalAmount;
    private String note;
    private Integer deliveryStatus;
    private Long paymentMethodId;
    private List<InvoiceDetailItemDto> invoiceDetails;
}
