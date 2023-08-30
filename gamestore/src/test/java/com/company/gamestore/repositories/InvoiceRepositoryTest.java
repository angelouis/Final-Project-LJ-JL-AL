package com.company.gamestore.repositories;

import com.company.gamestore.models.Game;
import com.company.gamestore.models.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    GameRepository gameRepository;

    private BigDecimal bigDecimal = new BigDecimal("2.22");

    Game game;
    
    Invoice invoice;
    
    @BeforeEach
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
        gameRepository.deleteAll();

        
        game = new Game();

        game.setId(1);
        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

        game = gameRepository.save(game);
        
    }
    @Test
    public void shouldGetCreateInvoice(){
        invoice = new Invoice();
        invoice.setName("Carl");
        invoice.setStreet("2334 Shardoad");
        invoice.setCity("austin");
        invoice.setZipCode("65774");
        invoice.setItemType("Game");
        invoice.setState("CA");
        invoice.setItemId(game.getId());
        invoice.setUnitPrice(bigDecimal);
        invoice.setQuantity(2);
        invoice.setSubTotal(bigDecimal);
        invoice.setTax(bigDecimal);
        invoice.setProcessingFee(bigDecimal);
        invoice.setTotal(bigDecimal);

        assertEquals(invoiceRepository.findAll().size(),0);
        invoice = invoiceRepository.save(invoice);

        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getId());
        assertEquals(invoiceRepository.findAll().size(),1);
    }

    @Test
    public void shouldGetAll(){
        invoice = new Invoice();
        invoice.setName("Carl");
        invoice.setStreet("2334 Shard turn road");
        invoice.setCity("CA");
        invoice.setZipCode("65774");
        invoice.setItemType("Game");
        invoice.setItemId(game.getId());
        invoice.setUnitPrice(bigDecimal);
        invoice.setQuantity(2);
        invoice.setSubTotal(bigDecimal);
        invoice.setTax(bigDecimal);
        invoice.setProcessingFee(bigDecimal);
        invoice.setTotal(bigDecimal);
        invoice.setState("CA");
       invoice = invoiceRepository.save(invoice);

        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertEquals(invoiceList.size(),1);
    }

    @Test
    public void shouldGetById(){
        invoice = new Invoice();
        invoice.setName("Carl");
        invoice.setStreet("2334 Shard turn road");
        invoice.setCity("CA");
        invoice.setZipCode("65774");
        invoice.setItemType("Game");
        invoice.setItemId(game.getId());
        invoice.setUnitPrice(bigDecimal);
        invoice.setQuantity(2);
        invoice.setSubTotal(bigDecimal);
        invoice.setTax(bigDecimal);
        invoice.setProcessingFee(bigDecimal);
        invoice.setTotal(bigDecimal);
        invoice.setState("CA");
        invoice = invoiceRepository.save(invoice);

        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getId());
        assertEquals(invoice1.get(), invoice);
    }
    @Test
    public void shouldGetCustomerByName(){
        invoice = new Invoice();
        invoice.setName("Carl");
        invoice.setStreet("2334 Shard turn road");
        invoice.setCity("CA");
        invoice.setZipCode("65774");
        invoice.setItemType("Game");
        invoice.setItemId(game.getId());
        invoice.setUnitPrice(bigDecimal);
        invoice.setQuantity(2);
        invoice.setSubTotal(bigDecimal);
        invoice.setTax(bigDecimal);
        invoice.setProcessingFee(bigDecimal);
        invoice.setTotal(bigDecimal);
        invoice.setState("CA");
        invoice = invoiceRepository.save(invoice);

        Optional<Invoice> invoice1 = invoiceRepository.findByName(invoice.getName());
        assertEquals(invoice1.get(), invoice);
    }
}
