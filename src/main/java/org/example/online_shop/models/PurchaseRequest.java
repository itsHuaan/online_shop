package org.example.online_shop.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequest {
    private InvoiceModel invoice;
    private List<InvoiceDetailModel> invoiceDetailModelList;
}
