package com.company.gamestore.service;

import com.company.gamestore.models.Console;
import com.company.gamestore.repositories.ConsoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        Console console = service.findConsole(10);

        assertEquals(expectedResult, console);


    }

    @Test
    public void shouldReturnNullIfConsoleNotFound(){

        Console console = service.findConsole(50);

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

        List<Console> console = service.findConsolesByManufacturer("Microsoft");

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

}