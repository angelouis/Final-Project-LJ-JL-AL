package com.company.gamestore.controllers;

import com.company.gamestore.services.ServiceLayer;
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

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ServiceLayer serviceLayer;

    TShirtViewModel tShirt = new TShirtViewModel();

    @BeforeEach
    public void setUp() {
        tShirt.setSize("Medium");
        tShirt.setColor("Yellow");
        tShirt.setDescription("Beautiful Yellow Medium Shirt");
        tShirt.setPrice(BigDecimal.valueOf(10.99));
        tShirt.setQuantity(12);
    }

    @Test
    public void shouldAddTShirt() throws Exception {
        when(serviceLayer.saveTShirt(any(TShirtViewModel.class))).thenReturn(tShirt);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/tshirts")
                        .content(mapper.writeValueAsString(tShirt))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // COMMENT!!!!!
    // mock right method, url, and expecting the right status code
    @Test
    public void shouldUpdateTShirt() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/tshirts")
                        .content(mapper.writeValueAsString(tShirt))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTShirt() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/tshirts/{id}",1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetTShirtById() throws Exception {
        when(serviceLayer.findTShirt(1)).thenReturn(tShirt);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tshirts/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTShirts() throws Exception {
        when(serviceLayer.findAllTShirt()).thenReturn(Arrays.asList(tShirt));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tshirts"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTShirtsBySize() throws Exception {
        // Mock the behavior of bookRepository.findByAuthorId
        when(serviceLayer.findTShirtBySize(tShirt.getSize())).thenReturn(Arrays.asList(tShirt));

        // Perform the mockMvc request with the authorId as a path variable
        mockMvc.perform(MockMvcRequestBuilders.get("/tshirts/bySize/{size}}", tShirt.getSize()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTShirtsByColor() throws Exception {
        // Mock the behavior of bookRepository.findByAuthorId
        when(serviceLayer.findTShirtByColor(tShirt.getColor())).thenReturn(Arrays.asList(tShirt));

        // Perform the mockMvc request with the authorId as a path variable
        mockMvc.perform(MockMvcRequestBuilders.get("/tshirts/byColor/{color}}", tShirt.getColor()))
                .andExpect(status().isOk());
    }
}
