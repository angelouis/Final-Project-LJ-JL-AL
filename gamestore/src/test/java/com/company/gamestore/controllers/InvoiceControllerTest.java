package com.company.gamestore.controllers;

import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.models.Game;
import com.company.gamestore.models.Invoice;
import com.company.gamestore.repositories.GameRepository;
import com.company.gamestore.repositories.InvoiceRepository;
import com.company.gamestore.services.ServiceLayer;
import com.company.gamestore.viewmodels.InvoiceViewModel;
import com.company.gamestore.viewmodels.TShirtViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InvoiceRepository invoiceRepository;

    @MockBean
    GameRepository gameRepository;

    @MockBean
    ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();
    Game game;

    Invoice invoice;

    private BigDecimal bigDecimal = new BigDecimal("2.22");

    InvoiceViewModel invoiceViewModel;

    @BeforeEach
    public void setUp(){
        gameRepository.deleteAll();
        invoiceRepository.deleteAll();

        game = new Game();
        //game.setId(1);
        game.setQuantity(100);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");
        game = gameRepository.save(game);


        invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("Carl");
        invoiceViewModel.setStreet("Hound round");
        invoiceViewModel.setCity("Austin");
        invoiceViewModel.setZipCode("56723");
        invoiceViewModel.setItemType("game");
        invoiceViewModel.setState("CA");
        invoiceViewModel.setItemId(24);
        invoiceViewModel.setQuantity(3);
        invoiceViewModel.setId(1);
    }
    @Test
    public void testCreateInvoiceShouldReturn201() throws Exception{
        String gameJson = mapper.writeValueAsString(invoiceViewModel);
        when(serviceLayer.saveInvoice(any(InvoiceViewModel.class))).thenReturn(invoiceViewModel);
        

        mockMvc.perform(post("/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    public void testGetInvoiceByIdShouldReturn200() throws Exception{

        when(serviceLayer.findInvoice(invoiceViewModel.getId())).thenReturn(invoiceViewModel);

        mockMvc.perform(get("/invoice/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testGetAllInvoiceShouldReturn200() throws Exception{

        InvoiceViewModel invoiceViewModel1 = new InvoiceViewModel();
        invoiceViewModel1.setName(invoiceViewModel.getName());
        invoiceViewModel1.setStreet(invoiceViewModel.getStreet());
        invoiceViewModel1.setCity(invoiceViewModel.getCity());
        invoiceViewModel1.setZipCode(invoiceViewModel.getZipCode());
        invoiceViewModel1.setItemType(invoiceViewModel.getItemType());
        invoiceViewModel1.setItemId(invoiceViewModel.getItemId());
        invoiceViewModel1.setQuantity(3);
        when(serviceLayer.saveInvoice(invoiceViewModel)).thenReturn(invoiceViewModel1);

        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();
        invoiceViewModelList.add(invoiceViewModel);
        invoiceViewModelList.add(invoiceViewModel1);

        when(serviceLayer.findAllInvoice()).thenReturn(invoiceViewModelList);

        mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInvoiceByCustomerNameShouldReturn200() throws Exception{

        when(serviceLayer.getInvoiceByCustomerName(invoiceViewModel.getName())).thenReturn(Collections.singletonList(invoiceViewModel));

        mockMvc.perform(get("/invoice/name/{name}","Carl"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn422WhenAddingInvoiceFails() throws Exception {
        InvoiceViewModel invoiceViewModel1 = new InvoiceViewModel();

        invoiceViewModel1.setQuantity(null);
        when(serviceLayer.saveInvoice(any(InvoiceViewModel.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/invoice")
                        .content(mapper.writeValueAsString(invoiceViewModel1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void shouldReturn404WhenGameNotFoundByName() throws Exception {
        when(serviceLayer.getInvoiceByCustomerName(invoiceViewModel.getName())).thenThrow(NotFoundException.class);
        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/invoice/name/{name}", invoiceViewModel.getName())
                    .content(mapper.writeValueAsString(game))
                    .contentType(MediaType.APPLICATION_JSON));
            fail("Expected NotFoundException to be thrown");
        } catch (NestedServletException e) {
            assertThat(e.getCause(), instanceOf(NotFoundException.class));
        }
    }
}
