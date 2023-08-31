package com.company.gamestore.controllers;

import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.models.Console;
import com.company.gamestore.repositories.ConsoleRepository;
import com.company.gamestore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsoleController.class)
class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleRepository consoleRepository;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCreateConsole() throws Exception {

        // ACT
        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        Console console1 = new Console();
        console1.setId(5);
        console1.setQuantity(10);
        console1.setMemory_amount("25 GB");
        console1.setProcessor("Core i5");
        console1.setPrice(new BigDecimal("100.99"));
        console1.setModel("Xbox 360");
        console1.setManufacturer("Microsoft");

        when(serviceLayer.saveConsole(console)).thenReturn(console1);

        mockMvc.perform(post("/consoles")               // Perform the POST request
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(console))
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isCreated()); // ASSERT (status code is 201)

    }

    @Test
    public void shouldGetConsoleById() throws Exception {
        // ACT
        Console console = new Console();
        console.setId(1);
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");


        when(serviceLayer.findConsole(1))
                .thenReturn(console);

        mockMvc.perform(get("/consoles/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllConsoles() throws Exception {
        // ACT
        Console console = new Console();
        console.setId(1);
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        Console console1 = new Console();
        console1.setId(2);
        console1.setQuantity(20);
        console1.setMemory_amount("35 GB");
        console1.setProcessor("Core i5");
        console1.setPrice(new BigDecimal("150.99"));
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("Nintendo");

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console1);
        consoleList.add(console);

        when(serviceLayer.findAllConsoles())
                .thenReturn(consoleList);

        mockMvc.perform(get("/consoles")               // Perform the GET request
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isOk()); // ASSERT (status code is 200)
    }

    @Test
    public void shouldUpdateConsole() throws Exception {
        // ACT
        Console console = new Console();
        console.setId(1);
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        serviceLayer.saveConsole(console);

        doNothing().when(serviceLayer).updateConsole(console);

        mockMvc.perform(put("/consoles")               // Perform the PUT request
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(console))
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeletePublisher() throws Exception {

        Console console = new Console();
        console.setId(1);
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        serviceLayer.saveConsole(console);

        doNothing().when(serviceLayer).removeConsole(1);

        mockMvc.perform(delete("/consoles/1")               // Perform the DELETE request
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldGetConsoleByManufacturer() throws Exception {
        // ACT
        Console console = new Console();
        console.setId(1);
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);


        when(serviceLayer.findConsolesByManufacturer("Microsoft"))
                .thenReturn(consoleList);

        mockMvc.perform(get("/consoles/manufacturer/Microsoft")               // Perform the GET request
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isOk()); // ASSERT (status code is 200)
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyModel() throws Exception {
        Console console = new Console();
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setManufacturer("Microsoft");

        mockMvc.perform(post("/consoles")               // Perform the POST request
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(console))
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyManufacturer() throws Exception {
        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");

        mockMvc.perform(post("/consoles")               // Perform the POST request
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(console))
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyPrice() throws Exception {
        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        mockMvc.perform(post("/consoles")               // Perform the POST request
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(console))
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAQuantityLessThanZero() throws Exception {
        Console console = new Console();
        console.setQuantity(-5);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setModel("Xbox One");
        console.setManufacturer("Microsoft");
        console.setPrice(new BigDecimal("100.99"));

        mockMvc.perform(post("/consoles")               // Perform the POST request
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(console))
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn404WhenAttemptingToDeleteAConsoleThatDoesNotExist() throws Exception {
       doThrow(NotFoundException.class).when(serviceLayer).removeConsole(500);


        mockMvc.perform(delete("/consoles/500"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn404StatusCodeIfConsoleNotFound() throws Exception {

        when(serviceLayer.findConsole(500))
                .thenReturn(null);


        mockMvc.perform(get("/consoles/500"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn404StatusCodeIfManufacturerNotFound() throws Exception {

        when(serviceLayer.findConsolesByManufacturer("Microsoft"))
                .thenReturn(null);


        mockMvc.perform(get("/consoles/manufacturer/Microsoft"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }





}