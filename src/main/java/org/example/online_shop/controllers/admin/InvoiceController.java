package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.dto.InvoiceDto;
import org.example.online_shop.models.PurchaseRequest;
import org.example.online_shop.services.impl.InvoiceService;
import org.example.online_shop.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "08. Invoice")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Operation(summary = "Get Invoices", tags = {"08. Invoice"})
    @GetMapping("/get-invoice")
    public ResponseEntity<?> getInvoice(@RequestParam(required = false) Long invoiceId) {
        if (invoiceId != null) {
            InvoiceDto invoice = invoiceService.findById(invoiceId);
            return invoice != null
                    ? new ResponseEntity<>(invoice, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<InvoiceDto> invoices = invoiceService.findAll();
        return invoices != null
                ? new ResponseEntity<>(invoices, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Purchase", tags = {"08. Invoice"})
    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseInvoice(@RequestBody PurchaseRequest purchaseRequest) {
        invoiceService.saveInvoice(purchaseRequest.getInvoice(), purchaseRequest.getInvoiceDetailModelList());
        return new ResponseEntity<>("Purchased", HttpStatus.OK);
    }
}
