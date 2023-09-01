package com.company.gamestore.services;

import com.company.gamestore.exceptions.TShirtUpdateException;
import com.company.gamestore.exceptions.TShirtViewModelBuildingException;
import com.company.gamestore.models.*;
import com.company.gamestore.repositories.*;
import com.company.gamestore.viewmodels.InvoiceViewModel;
import com.company.gamestore.viewmodels.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
//import org.webjars.NotFoundException;
import com.company.gamestore.exceptions.NotFoundException;

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

    private TShirtRepository tShirtRepository;

    private ConsoleRepository consoleRepository;

    @Autowired
    public ServiceLayer(GameRepository gameRepository, InvoiceRepository invoiceRepository, TaxRepository taxRepository, FeeRepository feeRepository, TShirtRepository tShirtRepository,ConsoleRepository consoleRepository) {
        this.gameRepository = gameRepository;
        this.invoiceRepository = invoiceRepository;
        this.taxRepository = taxRepository;
        this.feeRepository = feeRepository;
        this.tShirtRepository = tShirtRepository;
        this.consoleRepository = consoleRepository;
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
        Game game = new Game();
        if("game".equalsIgnoreCase(invoiceViewModel.getItemType())){
            invoice.setItemType(invoiceViewModel.getItemType());
            game = findGame(invoiceViewModel.getItemId());
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
        else if("console".equalsIgnoreCase(invoiceViewModel.getItemType())){
            invoice.setItemType(invoiceViewModel.getItemType());
            Console console = findConsole(invoiceViewModel.getItemId());
            if(invoiceViewModel.getItemId().equals(console.getId())){
                invoice.setItemId(invoiceViewModel.getItemId());
                if(console.getQuantity() > 0){
                    if(console.getQuantity() - invoiceViewModel.getQuantity() > 0){
                        console.setQuantity(console.getQuantity() - invoiceViewModel.getQuantity());
                        saveConsole(console);
                        invoice.setQuantity(invoiceViewModel.getQuantity());
                        subtotal = console.getPrice().multiply(new BigDecimal(invoiceViewModel.getQuantity()));
                        invoice.setUnitPrice(console.getPrice());
                        invoiceViewModel.setUnitPrice(console.getPrice());
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
        else if("tshirt".equalsIgnoreCase(invoiceViewModel.getItemType())){
            invoice.setItemType(invoiceViewModel.getItemType());
            TShirtViewModel tShirt = findTShirt(invoiceViewModel.getItemId());
            if(invoiceViewModel.getItemId().equals(tShirt.gettShirtId())){
                invoice.setItemId(invoiceViewModel.getItemId());
                if(tShirt.getQuantity() > 0){
                    if(tShirt.getQuantity() - invoiceViewModel.getQuantity() > 0){
                        tShirt.setQuantity(tShirt.getQuantity() - invoiceViewModel.getQuantity());
                        saveTShirt(tShirt);
                        invoice.setQuantity(invoiceViewModel.getQuantity());
                        subtotal = tShirt.getPrice().multiply(new BigDecimal(invoiceViewModel.getQuantity()));
                        invoice.setUnitPrice(tShirt.getPrice());
                        invoiceViewModel.setUnitPrice(tShirt.getPrice());
                    }else{
                        throw new IllegalArgumentException("The quantity that is required there is more than is available of that item");
                    }
                }else {
                    throw new IllegalArgumentException("Item quantity is less than 0");
                }
            }else{
                throw new IllegalArgumentException("Item id was not found");
            }

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
    //CONSOLE API
    public Console saveConsole (Console console) {
        return  consoleRepository.save(console);

    }

    public Console findConsole(int id) {
        Optional<Console> console = consoleRepository.findById(id);

        if(console.isPresent()) {
            return console.get();
        }
        else{
            return null;
        }

    }

    public List<Console> findAllConsoles() {

        return consoleRepository.findAll();
    }

    public List<Console> findConsolesByManufacturer(String manufacturer) {


        List<Console> console = consoleRepository.findByManufacturer(manufacturer);

        if(!console.isEmpty()) {
            return console;
        }
        else{
            return null;
        }

    }

    public void updateConsole(Console console) {

        consoleRepository.save(console);
    }

    public void removeConsole(int id) {

        consoleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        consoleRepository.deleteById(id);


    }
    //Tshirt API
    /**
     * Saves the t-shirt with the specific instance variables
     * @param viewModel - uses the viewmodel t-shirt to save the t-shirt in the t-shirt repo
     * @return returns the t-shirt that was saved
     */
    //@javax.transaction.Transactional
    public TShirtViewModel saveTShirt(TShirtViewModel viewModel) throws com.company.gamestore.exceptions.NotFoundException {
        if (viewModel == null) {
            throw new com.company.gamestore.exceptions.NotFoundException("Failed to save T-Shirt");
        }

        TShirt tShirt = new TShirt();
        tShirt.setQuantity(viewModel.getQuantity());
        tShirt.setSize(viewModel.getSize());
        tShirt.setDescription(viewModel.getDescription());
        tShirt.setColor(viewModel.getColor());
        tShirt.setPrice(viewModel.getPrice());
        tShirt = tShirtRepository.save(tShirt);

        viewModel.settShirtId(tShirt.gettShirtId());
        return viewModel;
    }

    /**
     * Finds the t-shirt within the t-shirt repo
     *
     * @param id - uses the id to find the t-shirt
     * @return Returns the t-shirt as a t-shirt viewmodel if it is present or else it gives null
     */
    @javax.transaction.Transactional
    public TShirtViewModel findTShirt(int id) {
        Optional<TShirt> tShirt = tShirtRepository.findById(id);

        if (tShirt == null || tShirt.isEmpty() || !tShirt.isPresent()) {
            throw new com.company.gamestore.exceptions.NotFoundException("T-shirt with ID " + id + " not found");
        }

        return buildTShirtViewModel(tShirt.get());
    }

    /**
     * Builds the t-shirt viewModel with the specific instances variables
     *
     * @param tShirt uses the t-shirt as a way to create a viewmodel
     * @return returns the filled out t-shirt viewmodel
     */
    public TShirtViewModel buildTShirtViewModel(TShirt tShirt) {
        try {
            TShirtViewModel tvm = new TShirtViewModel();
            tvm.settShirtId(tShirt.gettShirtId());
            tvm.setColor(tShirt.getColor());
            tvm.setDescription(tShirt.getDescription());
            tvm.setPrice(tShirt.getPrice());
            tvm.setQuantity(tShirt.getQuantity());
            tvm.setSize(tShirt.getSize());

            return tvm;
        } catch (Exception e) {
            throw new TShirtViewModelBuildingException("Error Building TShirtViewModel");
        }
    }

    /**
     * Get all the t-shirt from the repo and then converts the t-shirts to viewmodel t-shirts
     *
     * @return Returns the list of viewmodel t-shirts
     */
    @javax.transaction.Transactional
    public List<TShirtViewModel> findAllTShirt() throws TShirtViewModelBuildingException {
        List<TShirt> tShirtList = tShirtRepository.findAll();
        List<TShirtViewModel> tvmList = new ArrayList<>();
        try {
            for (TShirt tShirt : tShirtList) {
                TShirtViewModel tvm = buildTShirtViewModel(tShirt);
                tvmList.add(tvm);
            }
        } catch (Exception e) {
            throw new TShirtViewModelBuildingException("Error building T-ShirtViewModel");
        }

        return tvmList;
    }

    /**
     * Updates the t-shirt by creating a new t-shirt model with the updated information
     * @param viewModel - this is the t-shirt viewmoddel we will use to create the new t-shirt value with the updated info
     */
    @javax.transaction.Transactional
    public void updateTShirt(TShirtViewModel viewModel) {

        //if viewmodel is inside of the repo
        //do optional tshirt = tshirtrepo.findbyid
        // save the tshirt
        // else throw error
        try {
            TShirt tShirt = new TShirt();
            tShirt.settShirtId(viewModel.gettShirtId());
            tShirt.setPrice(viewModel.getPrice());
            tShirt.setColor(viewModel.getColor());
            tShirt.setDescription(viewModel.getDescription());
            tShirt.setQuantity(viewModel.getQuantity());
            tShirt.setSize(viewModel.getSize());

            tShirtRepository.save(tShirt);
        } catch (Exception e) {
            throw new TShirtUpdateException("Error Updating T-Shirt");
        }
    }

    /**
     * Removes the t-shirt from the repository by id
     * @param id - Uses the id to delete the t-shirt from the repo
     */
//    @Transactional
    public void removeTShirt(int id) {
        tShirtRepository.findById(id)
                .orElseThrow(() -> new com.company.gamestore.exceptions.NotFoundException());

        tShirtRepository.deleteById(id);

    }

    /**
     * Gets all the t-shirts from the repo by the color and then currents the t-shirts into viewmodel t-shirts
     * @param color - Uses the color as a parameter to chose which t-shirts to place in the list
     * @return - Returns a list of viewmodel t-shirts with the specific color
     */
    @javax.transaction.Transactional
    public List<TShirtViewModel> findTShirtByColor(String color) {
        List<TShirt> tShirtList = tShirtRepository.findByColor(color);

        List<TShirtViewModel> tvmList = new ArrayList<>();

        try {
            for (TShirt tShirt : tShirtList) {
                TShirtViewModel tvm = buildTShirtViewModel(tShirt);
                tvmList.add(tvm);
            }
        } catch (Exception e) {
            throw new TShirtViewModelBuildingException("Error building TShirt");
        }

        return tvmList;
    }

    /**
     * Gets all the t-shirts from the repo by the size and then currents the t-shirts into viewmodel t-shirts
     * @param size - Uses the size as a parameter to chose which t-shirts to place in the list
     * @return - Returns a list of viewmodel t-shirts with the specific size
     */
    @javax.transaction.Transactional
    public List<TShirtViewModel> findTShirtBySize(String size) {
        List<TShirt> tShirtList = tShirtRepository.findBySize(size);

        List<TShirtViewModel> tvmList = new ArrayList<>();

        try {
            for (TShirt tShirt : tShirtList) {
                TShirtViewModel tvm = buildTShirtViewModel(tShirt);
                tvmList.add(tvm);
            }
        } catch (Exception e) {
            throw new TShirtViewModelBuildingException("Error building TShirt");
        }

        return tvmList;
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

