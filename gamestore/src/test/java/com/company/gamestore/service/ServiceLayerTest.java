package com.company.gamestore.service;

import com.company.gamestore.models.Game;
import com.company.gamestore.models.Invoice;
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
        service = new ServiceLayer(gameRepository,invoiceRepository,taxRepository,feeRepository);

    }
    @Test
    public void ShouldSaveGame(){
        Game game1 = new Game();
        game1.setId(1);
        game1.setQuantity(1);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");

        Game game2 = new Game();
        game2.setQuantity(1);
        game2.setStudio("Warner Bros");
        game2.setEsrbRating("E");
        game2.setPrice(bigDecimal);
        game2.setTitle("Batman");
        game2.setDescription("The newest in the batman series relive your roots as batman");

        game2 = gameRepository.save(game2);

        assertEquals(game1,game2);

    }
    @Test
    public void ShouldDeleteGame(){
        Game game1 = new Game();
        game1.setId(1);
        game1.setQuantity(1);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");

        Game game2 = new Game();
        game2.setQuantity(1);
        game2.setStudio("Warner Bros");
        game2.setEsrbRating("E");
        game2.setPrice(bigDecimal);
        game2.setTitle("Batman");
        game2.setDescription("The newest in the batman series relive your roots as batman");

        game2 = gameRepository.save(game2);

        assertEquals(game1,game2);

    }
    @Test
    public void ShouldFindByStudio(){
        Game game1 = new Game();
        game1.setId(1);
        game1.setQuantity(1);
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
        game1.setQuantity(1);
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
        game1.setQuantity(1);
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
        InvoiceViewModel invoiceViewModel1 = new InvoiceViewModel();
        invoiceViewModel1.setName(invoiceViewModel1.getName());
        invoiceViewModel1.setStreet(invoiceViewModel1.getStreet());
        invoiceViewModel1.setCity(invoiceViewModel1.getCity());
        invoiceViewModel1.setZipCode(invoiceViewModel1.getZipCode());
        invoiceViewModel1.setItemType(invoiceViewModel1.getItemType());
        invoiceViewModel1.setItemId(invoiceViewModel1.getItemId());
        invoiceViewModel1.setQuantity(3);

        InvoiceViewModel invoiceViewModel2 = new InvoiceViewModel();
        invoiceViewModel2.setName(invoiceViewModel1.getName());
        invoiceViewModel2.setStreet(invoiceViewModel1.getStreet());
        invoiceViewModel2.setCity(invoiceViewModel1.getCity());
        invoiceViewModel2.setZipCode(invoiceViewModel1.getZipCode());
        invoiceViewModel2.setItemType(invoiceViewModel1.getItemType());
        invoiceViewModel2.setItemId(invoiceViewModel1.getItemId());
        invoiceViewModel2.setQuantity(3);

        invoiceViewModel2 = invoiceRepository.save(invoiceViewModel2);


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
        invoice.setItemId(12);
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
        invoice1.setItemId(12);
        invoice1.setUnitPrice(bigDecimal);
        invoice1.setQuantity(2);
        invoice1.setSubTotal(bigDecimal);
        invoice1.setTax(bigDecimal);
        invoice1.setProcessingFee(bigDecimal);
        invoice1.setTotal(bigDecimal);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceRepository).save(invoice1);
        doReturn(invoiceList).when(invoiceRepository).findAll();
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(invoice.getId());
        doReturn(Optional.of(invoice)).when(invoiceRepository).findByName(invoice.getName());
    }
     private void setUpGameRepositoryMock(){
        gameRepository = mock(GameRepository.class);

         Game game = new Game();
         game.setId(1);
         game.setQuantity(1);
         game.setStudio("Warner Bros");
         game.setEsrbRating("E");
         game.setPrice(bigDecimal);
         game.setTitle("Batman");
         game.setDescription("The newest in the batman series relive your roots as batman");

         Game game2 = new Game();
         game2.setQuantity(1);
         game2.setStudio("Warner Bros");
         game2.setEsrbRating("E");
         game2.setPrice(bigDecimal);
         game2.setTitle("Batman");
         game2.setDescription("The newest in the batman series relive your roots as batman");

         List<Game> gameList = new ArrayList<>();
         gameList.add(game);

         doReturn(game).when(gameRepository).save(game2);
         doReturn(game).when(gameRepository).deleteById(game2.getId());
         doReturn(gameList).when(gameRepository).findAll();
         doReturn(Optional.of(game)).when(gameRepository).findById(game2.getId());
         doReturn(Optional.of(game)).when(gameRepository).findByStudio(game2.getStudio());
         doReturn(Optional.of(game)).when(gameRepository).findByTitle(game2.getTitle());
         doReturn(Optional.of(game)).when(gameRepository).findByEsrbRating(game2.getEsrbRating());
    }
}
