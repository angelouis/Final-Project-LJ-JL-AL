package com.company.gamestore.controllers;

import com.company.gamestore.services.ServiceLayer;
import com.company.gamestore.viewmodels.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController implements Serializable {

    @Autowired
    ServiceLayer serviceLayer;

    // creates and adds an invoice in the service layer with a post mapping
    @PostMapping("/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel){
        return serviceLayer.saveInvoice(invoiceViewModel);
    }

    // gets a console by id in the service layer
    @GetMapping("/invoice/{id}")
    public InvoiceViewModel getInvoiceById(@PathVariable int id){
        return serviceLayer.findInvoice(id);
    }

    // gets all the invoices in a list
    @GetMapping("/invoices")
    public List<InvoiceViewModel> getAllInvoices(){
        return serviceLayer.findAllInvoice();
    }

    // gets the invoices by name (unique string)
    @GetMapping("/invoice/name/{name}")
    public Optional<InvoiceViewModel> getInvoiceByCustomerName(@PathVariable String name){
        return serviceLayer.getInvoiceByCustomerName(name);
    }


}
