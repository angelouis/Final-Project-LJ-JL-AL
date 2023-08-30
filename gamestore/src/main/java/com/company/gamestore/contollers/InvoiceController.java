package com.company.gamestore.contollers;

import com.company.gamestore.models.Invoice;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodels.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController implements Serializable {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody InvoiceViewModel invoiceViewModel){
        return serviceLayer.saveInvoice(invoiceViewModel);
    }

    @GetMapping("/invoice/{id}")
    public InvoiceViewModel getInvoiceById(@PathVariable int id){
        return serviceLayer.findInvoice(id);
    }

    @GetMapping("/invoices")
    public List<InvoiceViewModel> getAllInvoices(){
        return serviceLayer.findAllInvoice();
    }

    @GetMapping("/invoice/name/{name}")
    public Optional<InvoiceViewModel> getInvoiceByCustomerName(@PathVariable String name){
        return serviceLayer.getInvoiceByCustomerName(name);
    }


}
