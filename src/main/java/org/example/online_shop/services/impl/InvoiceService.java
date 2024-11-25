package org.example.online_shop.services.impl;

import org.example.online_shop.dto.InvoiceDto;
import org.example.online_shop.entities.InvoiceDetailEntity;
import org.example.online_shop.entities.InvoiceEntity;
import org.example.online_shop.mappers.impl.InvoiceDetailMapper;
import org.example.online_shop.mappers.impl.InvoiceMapper;
import org.example.online_shop.models.InvoiceDetailModel;
import org.example.online_shop.models.InvoiceModel;
import org.example.online_shop.repositories.IInvoiceDetailRepository;
import org.example.online_shop.repositories.IInvoiceRepository;
import org.example.online_shop.services.IInvoiceService;
import org.example.online_shop.utils.specifications.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InvoiceService implements IInvoiceService {
    private final IInvoiceDetailRepository invoiceDetailRepository;
    private final IInvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final InvoiceDetailMapper invoiceDetailMapper;

    @Autowired
    public InvoiceService(IInvoiceDetailRepository invoiceDetailRepository, IInvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, InvoiceDetailMapper invoiceDetailMapper) {
        this.invoiceDetailRepository = invoiceDetailRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.invoiceDetailMapper = invoiceDetailMapper;
    }

    @Override
    public List<InvoiceDto> findAll() {
        return invoiceRepository.findAll(Specification.where(InvoiceSpecification.isActive())).stream().map(invoiceMapper::toDTO).toList();
    }

    @Override
    public InvoiceDto findById(Long id) {
        return invoiceMapper.toDTO(Objects.requireNonNull(invoiceRepository.findById(id).orElse(null)));
    }

    @Override
    public int save(InvoiceModel invoiceModel) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveInvoice(InvoiceModel invoiceModel, List<InvoiceDetailModel> invoiceDetailModels) {
        InvoiceEntity invoice = invoiceMapper.toEntity(invoiceModel);
        invoiceRepository.save(invoice);
        double totalAmount = 0.0;
        for (InvoiceDetailModel detailModel : invoiceDetailModels) {
            detailModel.setInvoiceId(invoice.getInvoiceId());
            InvoiceDetailEntity invoiceDetail = invoiceDetailMapper.toEntity(detailModel);
            invoiceDetailRepository.save(invoiceDetail);
            totalAmount += invoiceDetail.getPrice();
        }
        invoice.setTotalAmount(totalAmount);
        invoiceRepository.save(invoice);
        return 1;
    }
}
