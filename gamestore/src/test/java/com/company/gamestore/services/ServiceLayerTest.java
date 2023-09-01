package com.company.gamestore.services;

import com.company.gamestore.models.*;
import com.company.gamestore.repositories.*;
import com.company.gamestore.viewmodels.InvoiceViewModel;
import com.company.gamestore.viewmodels.TShirtViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer serviceLayer;

    GameRepository gameRepository;

    InvoiceRepository invoiceRepository;

    FeeRepository feeRepository;

    TaxRepository taxRepository;
    TShirtRepository tShirtRepository;

    ConsoleRepository consoleRepository;

    private BigDecimal bigDecimal = new BigDecimal("2.22");

    @BeforeEach
    public void SetUp() throws Exception{
        setUpGameRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpTaxRepositoryMock();
        setUpFeeRepositoryMock();
        setUpTShirtRepositoryMock();
        setUpConsoleRepositoryMock();

         serviceLayer = new ServiceLayer(gameRepository,invoiceRepository,taxRepository,feeRepository, tShirtRepository, consoleRepository);
    }
    //CONSOLE SERVICE TEST
    @Test
    public void shouldSaveConsole(){

        Console expectedResult = new Console();
        expectedResult.setId(10);
        expectedResult.setQuantity(10);
        expectedResult.setMemory_amount("25 GB");
        expectedResult.setProcessor("Core i5");
        expectedResult.setPrice(new BigDecimal("100.99"));
        expectedResult.setModel("Xbox 360");
        expectedResult.setManufacturer("Microsoft");


        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        console = consoleRepository.save(console);

        assertEquals(expectedResult,console);
    }

    @Test
    public void shouldFindConsoleById(){

        Console expectedResult = new Console();
        expectedResult.setId(10);
        expectedResult.setQuantity(10);
        expectedResult.setMemory_amount("25 GB");
        expectedResult.setProcessor("Core i5");
        expectedResult.setPrice(new BigDecimal("100.99"));
        expectedResult.setModel("Xbox 360");
        expectedResult.setManufacturer("Microsoft");

        Console console = serviceLayer.findConsole(10);

        assertEquals(expectedResult, console);
    }

    @Test
    public void shouldReturnNullIfConsoleNotFound(){

        Console console = serviceLayer.findConsole(50);

        assertEquals(null, console);
    }

    @Test
    public void shouldFindAllConsoles(){

        Console expectedResult = new Console();
        expectedResult.setId(10);
        expectedResult.setQuantity(10);
        expectedResult.setMemory_amount("25 GB");
        expectedResult.setProcessor("Core i5");
        expectedResult.setPrice(new BigDecimal("100.99"));
        expectedResult.setModel("Xbox 360");
        expectedResult.setManufacturer("Microsoft");

        List<Console> eList = new ArrayList<>();
        eList.add(expectedResult);

        List<Console> cList = consoleRepository.findAll();

        assertEquals(eList, cList);
    }

    @Test
    public void shouldFindConsoleByManufacturer(){

        List<Console> expectedResult = new ArrayList<>();
        Console console1 = new Console();
        console1.setId(10);
        console1.setQuantity(10);
        console1.setMemory_amount("25 GB");
        console1.setProcessor("Core i5");
        console1.setPrice(new BigDecimal("100.99"));
        console1.setModel("Xbox 360");
        console1.setManufacturer("Microsoft");

        expectedResult.add(console1);

        List<Console> console = serviceLayer.findConsolesByManufacturer("Microsoft");

        assertEquals(expectedResult, console);
    }


    private void setUpConsoleRepositoryMock() throws Exception {
        consoleRepository = mock(ConsoleRepository.class);
        Console console = new Console();
        console.setId(10);
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        Console console2 = new Console();
        console2.setQuantity(10);
        console2.setMemory_amount("25 GB");
        console2.setProcessor("Core i5");
        console2.setPrice(new BigDecimal("100.99"));
        console2.setModel("Xbox 360");
        console2.setManufacturer("Microsoft");

        List cList = new ArrayList();
        cList.add(console);

        doReturn(console).when(consoleRepository).save(console2);
        doReturn(Optional.of(console)).when(consoleRepository).findById(10);
        doReturn(cList).when(consoleRepository).findAll();
        doReturn(cList).when(consoleRepository).findByManufacturer("Microsoft");
    }

    //GAME SERVICETEST
    @Test
    public void ShouldSaveGame(){
        Game game1 = new Game();
        game1.setId(1);
        game1.setQuantity(100);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");

        Game game2 = new Game();
        game2.setQuantity(100);
        game2.setStudio("Warner Bros");
        game2.setEsrbRating("E");
        game2.setPrice(bigDecimal);
        game2.setTitle("Batman");
        game2.setDescription("The newest in the batman series relive your roots as batman");

        game2 = gameRepository.save(game2);

        assertEquals(game1,game2);
    }
    @Test
    public void shouldFindAllGames(){

        Game game1 = new Game();
        game1.setId(1);
        game1.setQuantity(100);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");


        Game game2 = new Game();
        game2.setId(2);
        game2.setQuantity(100);
        game2.setStudio("Warner Bros");
        game2.setEsrbRating("E");
        game2.setPrice(bigDecimal);
        game2.setTitle("Baan");
        game2.setDescription("The newest in the batman series relive your roots as batman");

        List<Game> gameList =  serviceLayer.findAllGames();

        assertEquals(2, gameList.size());
    }
    @Test
    public void shouldFindGameById(){

        Game game1 = new Game();
        game1.setId(1);
        game1.setQuantity(100);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");

        Game game =  serviceLayer.findGame(game1.getId());

        assertEquals(game1, game);
    }
    @Test
    public void ShouldFindByStudio(){
        Game game1 = new Game();
        game1.setId(1);
        game1.setQuantity(100);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");



        List<Game> game =  serviceLayer.getGameByStudio(game1.getStudio());


        assertEquals(game1,game.get(0));


    }
    @Test
    public void ShouldFindByEsrbRating(){
        Game game1 = new Game();
        game1.setId(1);
        game1.setQuantity(100);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");


       List<Game> game =  serviceLayer.getGameByEsrbRating(game1.getEsrbRating());


        assertEquals(game1,game.get(0));


    }
    @Test
    public void ShouldFindByTitle(){
        Game game1 = new Game();
        game1.setId(1);
        game1.setQuantity(100);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");


        List<Game> game =  serviceLayer.getGameByTitle(game1.getTitle());

        assertEquals(game1,game.get(0));


    }

    // Beginning of T-Shirt
    private void setUpTShirtRepositoryMock() throws Exception {
        tShirtRepository = mock(TShirtRepository.class);
        TShirt tShirt = new TShirt();
        tShirt.settShirtId(1);
        tShirt.setPrice(new BigDecimal("10.99"));
        tShirt.setColor("Yellow");
        tShirt.setSize("Medium");
        tShirt.setDescription("Sunshine sun");
        tShirt.setQuantity(12);

        TShirt tShirt1 = new TShirt();
        tShirt1.setPrice(new BigDecimal("10.99"));
        tShirt1.setColor("Yellow");
        tShirt1.setSize("Medium");
        tShirt1.setDescription("Sunshine sun");
        tShirt1.setQuantity(12);

        List tShirtList = new ArrayList();
        tShirtList.add(tShirt);
        tShirtList.add(tShirt1);

        doReturn(tShirt).when(tShirtRepository).save(tShirt1);
        doReturn(Optional.of(tShirt)).when(tShirtRepository).findById(1);
        doReturn(tShirtList).when(tShirtRepository).findAll();
        doReturn(tShirtList).when(tShirtRepository).findByColor("Yellow");
        doReturn(tShirtList).when(tShirtRepository).findBySize("Medium");
    }

    /**
     * Tests whether a t-shirt can be properly saved with all the inputs placed in
     */
    @Test
    public void shouldSaveTShirt() {
        // Perform the  serviceLayer call
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

        TShirtViewModel tShirt1 = new TShirtViewModel();
        tShirt1.setSize("Medium");
        tShirt1.setDescription("Sunshine sun");
        tShirt1.setColor("Yellow");
        tShirt1.setPrice(new BigDecimal("10.99"));
        tShirt1.setQuantity(12);

        tShirt1 = serviceLayer.saveTShirt(tShirt1);

        assertEquals(expectedResult, tShirt1);
    }

    /**
     * Tests if one can find a t-shirt by its id in the service layer
     */
    @Test
    public void shouldFindTShirt() {
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

        TShirtViewModel tShirtViewModel = serviceLayer.findTShirt(1);
        assertEquals(tShirtViewModel, expectedResult);
    }

    /**
     * Tests if one can find t-shirt(s) by the color and returns as a list
     */
    @Test
    public void shouldFindTShirtByColor() {
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult);

        TShirtViewModel expectedResult1 = new TShirtViewModel();
        expectedResult1.settShirtId(2);
        expectedResult1.setSize("Medium");
        expectedResult1.setDescription("Sunshine sun");
        expectedResult1.setColor("Yellow");
        expectedResult1.setPrice(new BigDecimal("10.99"));
        expectedResult1.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult1);

        List<TShirtViewModel> tShirtViewModel = serviceLayer.findTShirtByColor("Yellow");
        assertEquals(2, tShirtViewModel.size());
    }

    /**
     * Tests if one can find t-shirt(s) by the size and returns as a list
     */
    @Test
    public void shouldFindTShirtBySize() {
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult);

        TShirtViewModel expectedResult1 = new TShirtViewModel();
        expectedResult1.settShirtId(2);
        expectedResult1.setSize("Medium");
        expectedResult1.setDescription("Sunshine sun");
        expectedResult1.setColor("Yellow");
        expectedResult1.setPrice(new BigDecimal("10.99"));
        expectedResult1.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult1);

        List<TShirtViewModel> tShirtViewModel = serviceLayer.findTShirtBySize("Medium");
        assertEquals(2, tShirtViewModel.size());
    }

    /**
     * Tests whether all the t-shirts can be returned from the service layer
     */
    @Test
    public void shouldFindAllTShirts() {
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult);

        TShirtViewModel expectedResult1 = new TShirtViewModel();
        expectedResult1.settShirtId(2);
        expectedResult1.setSize("Medium");
        expectedResult1.setDescription("Sunshine sun");
        expectedResult1.setColor("Yellow");
        expectedResult1.setPrice(new BigDecimal("10.99"));
        expectedResult1.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult1);

        List<TShirtViewModel> tShirtViewModel = serviceLayer.findAllTShirt();
        assertEquals(2, tShirtViewModel.size());
    }
    //Invoice Service Tests
    @Test
    public void ShouldSaveInvoice(){

        Game game2 = new Game();
        game2.setId(1);
        game2.setQuantity(100);
        game2.setStudio("Warner Bros");
        game2.setEsrbRating("E");
        game2.setPrice(bigDecimal);
        game2.setTitle("Batman");
        game2.setDescription("The newest in the batman series relive your roots as batman");

        game2 = gameRepository.save(game2);

        InvoiceViewModel invoiceViewModel1 = new InvoiceViewModel();
        invoiceViewModel1.setName("Carl");
        invoiceViewModel1.setStreet("2334 Shardoad");
        invoiceViewModel1.setCity("austin");
        invoiceViewModel1.setZipCode("65774");
        invoiceViewModel1.setItemType("Game");
        invoiceViewModel1.setItemId(1);
        invoiceViewModel1.setQuantity(2);
        invoiceViewModel1.setState("CA");
        invoiceViewModel1.setSubTotal(bigDecimal);
        invoiceViewModel1.setProcessingFee(bigDecimal);
        invoiceViewModel1.setTax(bigDecimal);
        invoiceViewModel1.setTotal(bigDecimal);
        invoiceViewModel1.setUnitPrice(bigDecimal);
        invoiceViewModel1.setId(1);

        InvoiceViewModel invoice1 = new InvoiceViewModel();
        invoice1.setName("Carl");
        invoice1.setStreet("2334 Shardoad");
        invoice1.setCity("austin");
        invoice1.setZipCode("65774");
        invoice1.setItemType("Game");
        invoice1.setItemId(1);
        invoice1.setQuantity(2);
        invoice1.setState("CA");

        try {
            invoice1 = serviceLayer.saveInvoice(invoice1);
        }catch (NullPointerException e){
            System.err.println("NullPointerException occurred: " + e.getMessage());
            e.printStackTrace();
        }

        assertEquals(invoiceViewModel1, invoice1);

    }
    @Test
    public void ShouldFindInvoiceByName(){
        InvoiceViewModel invoiceViewModel1 = new InvoiceViewModel();
        invoiceViewModel1.setName("Carl");
        invoiceViewModel1.setStreet("2334 Shardoad");
        invoiceViewModel1.setCity("austin");
        invoiceViewModel1.setZipCode("65774");
        invoiceViewModel1.setItemType("Game");
        invoiceViewModel1.setItemId(1);
        invoiceViewModel1.setQuantity(2);
        invoiceViewModel1.setState("CA");
        invoiceViewModel1.setSubTotal(bigDecimal);
        invoiceViewModel1.setProcessingFee(bigDecimal);
        invoiceViewModel1.setTotal(bigDecimal);
        invoiceViewModel1.setUnitPrice(bigDecimal);
        invoiceViewModel1.setTax(bigDecimal);
        invoiceViewModel1.setId(1);

        List<InvoiceViewModel> invoiceViewModel =  serviceLayer.getInvoiceByCustomerName(invoiceViewModel1.getName());

        assertEquals(invoiceViewModel1,invoiceViewModel.get(0));
    }
    @Test
    public void shouldFindInvoiceById(){


        InvoiceViewModel invoice1 = new InvoiceViewModel();
        invoice1.setName("Carl");
        invoice1.setStreet("2334 Shardoad");
        invoice1.setCity("austin");
        invoice1.setZipCode("65774");
        invoice1.setItemType("Game");
        invoice1.setState("CA");
        invoice1.setItemId(1);
        invoice1.setUnitPrice(bigDecimal);
        invoice1.setQuantity(2);
        invoice1.setSubTotal(bigDecimal);
        invoice1.setTax(bigDecimal);
        invoice1.setProcessingFee(bigDecimal);
        invoice1.setTotal(bigDecimal);
        invoice1.setId(1);

        InvoiceViewModel invoice =  serviceLayer.findInvoice(invoice1.getId());

        assertEquals(invoice, invoice1);
    }
    @Test
    public void shouldFindAllInvoice(){


        InvoiceViewModel invoice1 = new InvoiceViewModel();
        invoice1.setName("Carl");
        invoice1.setStreet("2334 Shardoad");
        invoice1.setCity("austin");
        invoice1.setZipCode("65774");
        invoice1.setItemType("Game");
        invoice1.setState("CA");
        invoice1.setItemId(1);
        invoice1.setUnitPrice(bigDecimal);
        invoice1.setQuantity(2);
        invoice1.setSubTotal(bigDecimal);
        invoice1.setTax(bigDecimal);
        invoice1.setProcessingFee(bigDecimal);
        invoice1.setTotal(bigDecimal);
        invoice1.setId(1);

        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setName("Carl");
        invoice2.setStreet("2334 Shardoad");
        invoice2.setCity("austin");
        invoice2.setZipCode("65774");
        invoice2.setItemType("Game");
        invoice2.setState("CA");
        invoice2.setItemId(1);
        invoice2.setUnitPrice(bigDecimal);
        invoice2.setQuantity(2);
        invoice2.setSubTotal(bigDecimal);
        invoice2.setTax(bigDecimal);
        invoice2.setProcessingFee(bigDecimal);
        invoice2.setTotal(bigDecimal);
        invoice2.setId(2);

        List<InvoiceViewModel> invoiceList =  serviceLayer.findAllInvoice();

        assertEquals(2, invoiceList.size());
    }
    private void setUpInvoiceRepositoryMock(){
        invoiceRepository = mock(InvoiceRepository.class);

        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setName("Carl");
        invoice.setStreet("2334 Shardoad");
        invoice.setCity("austin");
        invoice.setZipCode("65774");
        invoice.setItemType("Game");
        invoice.setState("CA");
        invoice.setItemId(1);
        invoice.setUnitPrice(bigDecimal);
        invoice.setQuantity(2);
        invoice.setSubTotal(bigDecimal);
        invoice.setTax(bigDecimal);
        invoice.setProcessingFee(bigDecimal);
        invoice.setTotal(bigDecimal);

        Invoice invoice1 = new Invoice();
        invoice1.setName("Carl");
        invoice1.setStreet("2334 Shardoad");
        invoice1.setCity("austin");
        invoice1.setZipCode("65774");
        invoice1.setItemType("Game");
        invoice1.setState("CA");
        invoice1.setItemId(1);
        invoice1.setUnitPrice(bigDecimal);
        invoice1.setQuantity(2);
        invoice1.setSubTotal(bigDecimal);
        invoice1.setTax(bigDecimal);
        invoice1.setProcessingFee(bigDecimal);
        invoice1.setTotal(bigDecimal);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        invoiceList.add(invoice1);

        doReturn(invoice).when(invoiceRepository).save(any(Invoice.class));
        doReturn(invoiceList).when(invoiceRepository).findAll();
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(1);
        doReturn(invoiceList).when(invoiceRepository).findByName(invoice.getName());
    }
     private void setUpGameRepositoryMock(){
        gameRepository = mock(GameRepository.class);

         Game game = new Game();
         game.setId(1);
         game.setQuantity(100);
         game.setStudio("Warner Bros");
         game.setEsrbRating("E");
         game.setPrice(bigDecimal);
         game.setTitle("Batman");
         game.setDescription("The newest in the batman series relive your roots as batman");

         Game game2 = new Game();
         game2.setQuantity(100);
         game2.setStudio("Warner Bros");
         game2.setEsrbRating("E");
         game2.setPrice(bigDecimal);
         game2.setTitle("Batman");
         game2.setDescription("The newest in the batman series relive your roots as batman");

         List<Game> gameList = new ArrayList<>();
         gameList.add(game);
         gameList.add(game2);

         doReturn(game).when(gameRepository).save(game2);
         doReturn(gameList).when(gameRepository).findAll();
         doReturn(Optional.of(game)).when(gameRepository).findById(1);
         doReturn(gameList).when(gameRepository).findByStudio(game2.getStudio());
         doReturn(gameList).when(gameRepository).findByTitle(game2.getTitle());
         doReturn(gameList).when(gameRepository).findByEsrbRating(game2.getEsrbRating());
    }
    private void setUpTaxRepositoryMock(){
        taxRepository = mock(TaxRepository.class);
        Tax tax = new Tax();
        tax.setRate(bigDecimal);
        tax.setState("CA");

        Tax tax2 = new Tax();
        tax2.setRate(bigDecimal);
        tax2.setState("CA");

        doReturn(Optional.of(tax)).when(taxRepository).findTaxByState(tax2.getState());
    }
    private void setUpFeeRepositoryMock(){
        feeRepository = mock(FeeRepository.class);
        Fee fee = new Fee();
        fee.setFee(bigDecimal);
        fee.setProductType("Game");

        Fee fee2 = new Fee();
        fee2.setFee(bigDecimal);
        fee2.setProductType("Game");

        doReturn(Optional.of(fee)).when(feeRepository).findFeeByProductType(fee2.getProductType());
    }
}
