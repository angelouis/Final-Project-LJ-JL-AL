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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;

    GameRepository gameRepository;

    InvoiceRepository invoiceRepository;

    FeeRepository feeRepository;

    TaxRepository taxRepository;

    private BigDecimal bigDecimal = new BigDecimal("2.22");

    @BeforeEach
    public void SetUp() throws Exception{
        setUpGameRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpTaxRepositoryMock();
        setUpFeeRepositoryMock();
        service = new ServiceLayer(gameRepository,invoiceRepository,taxRepository,feeRepository);

    }
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

        List<Game> gameList = service.findAllGames();

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

        Game game = service.findGame(game1.getId());

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


        Optional<Game> game = service.getGameByStudio(game1.getStudio());


        assertEquals(game1,game.get());

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


        Optional<Game> game = service.getGameByEsrbRating(game1.getEsrbRating());


        assertEquals(game1,game.get());

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


        Optional<Game> game = service.getGameByTitle(game1.getTitle());


        assertEquals(game1,game.get());

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
        invoiceViewModel1.setQuantity(3);
        invoiceViewModel1.setState("CA");
        invoiceViewModel1.setSubTotal(bigDecimal);
        invoiceViewModel1.setProcessingFee(bigDecimal);
        invoiceViewModel1.setTotal(bigDecimal);
        invoiceViewModel1.setUnitPrice(bigDecimal);

        InvoiceViewModel invoice1 = new InvoiceViewModel();
        invoice1.setName(invoiceViewModel1.getName());
        invoice1.setStreet(invoiceViewModel1.getStreet());
        invoice1.setCity(invoiceViewModel1.getCity());
        invoice1.setZipCode(invoiceViewModel1.getZipCode());
        invoice1.setItemType(invoiceViewModel1.getItemType());
        invoice1.setItemId(invoiceViewModel1.getItemId());
        invoice1.setQuantity(3);
        invoice1.setState("CA");
        invoice1.setSubTotal(bigDecimal);
        invoice1.setProcessingFee(bigDecimal);
        invoice1.setTotal(bigDecimal);
        invoice1.setUnitPrice(bigDecimal);

        invoice1 = service.saveInvoice(invoice1);

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

        Optional<InvoiceViewModel> invoiceViewModel = service.getInvoiceByCustomerName(invoiceViewModel1.getName());

        assertEquals(invoiceViewModel1,invoiceViewModel.get());
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

        InvoiceViewModel invoice = service.findInvoice(invoice1.getId());

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

        List<InvoiceViewModel> invoiceList = service.findAllInvoice();

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

        doReturn(invoice).when(invoiceRepository).save(invoice1);
        doReturn(invoiceList).when(invoiceRepository).findAll();
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(1);
        doReturn(Optional.of(invoice)).when(invoiceRepository).findByName(invoice.getName());
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
         doReturn(Optional.of(game)).when(gameRepository).findByStudio(game2.getStudio());
         doReturn(Optional.of(game)).when(gameRepository).findByTitle(game2.getTitle());
         doReturn(Optional.of(game)).when(gameRepository).findByEsrbRating(game2.getEsrbRating());
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
