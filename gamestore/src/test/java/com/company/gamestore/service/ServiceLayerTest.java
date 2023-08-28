package com.company.gamestore.service;

import com.company.gamestore.models.Console;
import com.company.gamestore.repositories.ConsoleRepository;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class ServiceLayerTest {

    ServiceLayer service;

    ConsoleRepository consoleRepository;

    @BeforeEach
    public void setUp() throws Exception {
        setUpConsoleRepositoryMock();


        service = new ServiceLayer(consoleRepository);

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
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        List cList = new ArrayList();
        cList.add(console);

        doReturn(console).when(consoleRepository).save(console2);
        doReturn(Optional.of(console)).when(consoleRepository).findById(10);
        doReturn(cList).when(consoleRepository).findAll();
        doReturn(Optional.of(console)).when(consoleRepository).findByManufacturer("Microsoft");



    }

}