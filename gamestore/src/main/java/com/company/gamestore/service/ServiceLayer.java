package com.company.gamestore.service;

import com.company.gamestore.models.Fee;
import com.company.gamestore.models.Game;
import com.company.gamestore.models.Invoice;
import com.company.gamestore.models.Tax;
import com.company.gamestore.repositories.FeeRepository;
import com.company.gamestore.repositories.GameRepository;
import com.company.gamestore.repositories.InvoiceRepository;
import com.company.gamestore.repositories.TaxRepository;
import com.company.gamestore.viewmodels.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {
    private GameRepository gameRepository;

    private InvoiceRepository invoiceRepository;

    private TaxRepository taxRepository;

    private FeeRepository feeRepository;

    @Autowired
    public ServiceLayer(GameRepository gameRepository, InvoiceRepository invoiceRepository,TaxRepository taxRepository,FeeRepository feeRepository) {
        this.gameRepository = gameRepository;
        this.invoiceRepository = invoiceRepository;
        this.taxRepository = taxRepository;
        this.feeRepository = feeRepository;
    }
    //Invoice API
    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel){

        Invoice invoice = new Invoice();
        BigDecimal subtotal = new BigDecimal("0");
        BigDecimal taxTotal = new BigDecimal("0");
        BigDecimal feeTotal = new BigDecimal("0");
        BigDecimal additionalProcessing = new BigDecimal("15.99");
        BigDecimal totalAmount = new BigDecimal("0");
        BigDecimal maxTotal = new BigDecimal("999999.99");

        if("game".equalsIgnoreCase(invoiceViewModel.getItemType())){
            invoice.setItemType(invoiceViewModel.getItemType());
            Game game = findGame(invoiceViewModel.getItemId());
             if(invoiceViewModel.getItemId().equals(game.getId())){
                 invoice.setItemId(invoiceViewModel.getItemId());
                 if(game.getQuantity() > 0){
                     if(game.getQuantity() - invoiceViewModel.getQuantity() > 0){
                         game.setQuantity(game.getQuantity() - invoiceViewModel.getQuantity());
                         saveGame(game);
                         invoice.setQuantity(invoiceViewModel.getQuantity());
                         subtotal = game.getPrice().multiply(new BigDecimal(invoiceViewModel.getQuantity()));
                         invoice.setUnitPrice(game.getPrice());
                         invoiceViewModel.setUnitPrice(game.getPrice());
                     }else{
                         throw new IllegalArgumentException("The quantity that is required there is more than is available of that item");
                     }

                 }else {
                     throw new IllegalArgumentException("Item quantity is less than 0");
                 }
             }else{
                 throw new IllegalArgumentException("Item id was not found");
             }
        }
        else if( invoiceViewModel.getItemType().equals("console") || invoiceViewModel.getItemType().equals("Console")) {

        }
        else if(invoiceViewModel.getItemType().equals("tshirt") || invoiceViewModel.getItemType().equals("Tshirt")) {

        }else{
            throw new IllegalArgumentException("Item type is not one of the three game, tshirt or console");
        }

        subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);
        invoice.setSubTotal(subtotal);
        invoiceViewModel.setSubTotal(subtotal);

        taxTotal = subtotal.multiply(getTaxByState(invoiceViewModel.getState()).get().getRate());


        taxTotal = taxTotal.setScale(2, RoundingMode.HALF_UP);
        invoice.setTax(taxTotal);
        invoiceViewModel.setTax(taxTotal);

        feeTotal = getFeeByItemType(invoice.getItemType()).get().getFee();

        if(invoice.getQuantity() > 10){
            feeTotal = feeTotal.add(additionalProcessing);
        }
        feeTotal = feeTotal.setScale(2, RoundingMode.HALF_UP);

        invoice.setProcessingFee(feeTotal);
        invoiceViewModel.setProcessingFee(feeTotal);

        totalAmount = invoice.getTax().add(invoice.getProcessingFee().add(invoice.getSubTotal()));
        totalAmount = totalAmount.setScale(2, RoundingMode.HALF_UP);

        if (totalAmount.compareTo(maxTotal) > 0) {
            throw new IllegalArgumentException("Your total cant exceed 999999.99");
        }

        invoice.setTotal(totalAmount);
        invoiceViewModel.setTotal(totalAmount);

        invoice.setZipCode(invoiceViewModel.getZipCode());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setState(invoiceViewModel.getState());
        invoice = invoiceRepository.save(invoice);
       // Optional <Invoice> test = invoiceRepository.findByName(invoiceViewModel.getName());

        invoiceViewModel = buildInvoiceViewModel(invoice);

        //invoiceViewModel.setId(invoice.getId());


        return invoiceViewModel;
    }
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setZipCode(invoice.getZipCode());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setSubTotal(invoice.getSubTotal());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());
        invoiceViewModel.setId(invoice.getId());
        invoiceViewModel.setState(invoice.getState());

        return invoiceViewModel;
    }

    public InvoiceViewModel findInvoice(int id){
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice.isPresent()) {
            return buildInvoiceViewModel(invoice.get());
        }
        else {
            throw new NotFoundException("Invoice not found");
        }
    }

    public List<InvoiceViewModel> findAllInvoice(){

        List<Invoice> invoiceList = invoiceRepository.findAll();
        if (invoiceList == null) {return null;}

        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();

        for(Invoice invoice:invoiceList){

            InvoiceViewModel  invoiceViewModel = buildInvoiceViewModel(invoice);
            invoiceViewModelList.add(invoiceViewModel);
        }
        return invoiceViewModelList;
    }
    public Optional<InvoiceViewModel> getInvoiceByCustomerName(String name) {

        return Optional.of(buildInvoiceViewModel(invoiceRepository.findByName(name).get()));
    }
    //Tax API
    public Optional<Tax> getTaxByState(String state){
       return taxRepository.findTaxByState(state);
    }

    //Fee API
    public Optional<Fee> getFeeByItemType(String productType){
        return feeRepository.findFeeByProductType(productType);
    }

    //Game API

    public Game saveGame(Game game){
        return gameRepository.save(game);
    }


    public Game findGame(int id){
        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
            return game.get();
        }
        else {
            throw new NotFoundException("Game not found");
        }
    }

    public List<Game> findAllGames(){
        List<Game> gameList =  gameRepository.findAll();
        if(gameList == null){
            throw new NotFoundException("Game list has a null value");
        }else{
            return gameList;
        }
    }


    public void updateGame(Game game){
        gameRepository.save(game);
    }


    public void removeGame(int id){
        gameRepository.deleteById(id);
    }

    public Optional<Game> getGameByEsrbRating(String esrbRating) {

        return gameRepository.findByEsrbRating(esrbRating);
    }

    public Optional<Game> getGameByTitle(String title) {

        return gameRepository.findByTitle(title);
    }

    public Optional<Game> getGameByStudio(String studio){

        return gameRepository.findByStudio(studio);
    }


}

