package org.example.online_shop.services;

import org.example.online_shop.dto.InvoiceDto;
import org.example.online_shop.mappers.impl.InvoiceMapper;
import org.example.online_shop.models.InvoiceDetailModel;
import org.example.online_shop.models.InvoiceModel;

import java.util.List;

public interface IInvoiceService extends IBaseService<InvoiceDto, InvoiceModel, Long>{
    int saveInvoice(InvoiceModel invoiceModel, List<InvoiceDetailModel> invoiceDetailModels);
}
