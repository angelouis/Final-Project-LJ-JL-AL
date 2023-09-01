package com.company.gamestore.controllers;

import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.exceptions.TShirtUpdateException;
import com.company.gamestore.exceptions.TShirtViewModelBuildingException;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ServiceLayer serviceLayer;

    TShirtViewModel tShirt = new TShirtViewModel();

    @BeforeEach
    public void setUp() {
        tShirt.settShirtId(1);
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
        mockMvc.perform(delete("/tshirts/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetTShirtById() throws Exception {
        when(serviceLayer.findTShirt(1)).thenReturn(tShirt);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tshirts/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTShirts() throws Exception {
        when(serviceLayer.findAllTShirt()).thenReturn(Collections.singletonList(tShirt));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tshirts"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTShirtsBySize() throws Exception {
        // Mock the behavior of bookRepository.findByAuthorId
        when(serviceLayer.findTShirtBySize(tShirt.getSize())).thenReturn(Collections.singletonList(tShirt));

        // Perform the mockMvc request with the authorId as a path variable
        mockMvc.perform(MockMvcRequestBuilders.get("/tshirts/bySize/{size}}", tShirt.getSize()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTShirtsByColor() throws Exception {
        // Mock the behavior of bookRepository.findByAuthorId
        when(serviceLayer.findTShirtByColor(tShirt.getColor())).thenReturn(Collections.singletonList(tShirt));

        // Perform the mockMvc request with the authorId as a path variable
        mockMvc.perform(MockMvcRequestBuilders.get("/tshirts/byColor/{color}}", tShirt.getColor()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404WhenAddingTShirtFails() throws Exception {
        when(serviceLayer.saveTShirt(any(TShirtViewModel.class))).thenThrow(NotFoundException.class);


        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/tshirts")
                    .content(mapper.writeValueAsString(tShirt))
                    .contentType(MediaType.APPLICATION_JSON));
            fail("Expected NotFoundException to be thrown");
        } catch (NestedServletException e) {
            assertThat(e.getCause(), instanceOf(NotFoundException.class));
        }
    }

    // doesn't work
    @Test
    public void shouldReturn422WhenAddingTShirtFails() throws Exception {
        TShirtViewModel tShirtViewModel = new TShirtViewModel();

        tShirt.setColor(null);
        when(serviceLayer.saveTShirt(any(TShirtViewModel.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/tshirts")
                        .content(mapper.writeValueAsString(tShirt))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }




    @Test
    public void shouldReturn404WhenTShirtNotFound() throws Exception {
        when(serviceLayer.findTShirt(anyInt())).thenThrow(NotFoundException.class);

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/tshirts/{id}", 1)
                    .content(mapper.writeValueAsString(tShirt))
                    .contentType(MediaType.APPLICATION_JSON));
            fail("Expected NotFoundException to be thrown");
        } catch (NestedServletException e) {
            assertThat(e.getCause(), instanceOf(NotFoundException.class));
        }
    }


    @Test
    public void shouldReturn422ForInvalidColor() throws Exception {
        when(serviceLayer.findTShirtByColor("")).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/tshirts/byColor/{color}",""))
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void shouldReturn422ForInvalidSize() throws Exception {
        when(serviceLayer.findTShirtBySize("")).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/tshirts/bySize/{size}",""))
                .andExpect(status().isUnprocessableEntity());
    }

}
