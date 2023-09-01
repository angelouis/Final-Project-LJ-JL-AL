package com.company.gamestore.repositories;

import com.company.gamestore.models.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConsoleRepositoryTest {

    @Autowired
    ConsoleRepository consoleRepo;

    @BeforeEach
    public void setUp() throws Exception {
        consoleRepo.deleteAll();
    }

    @Test
    public void addConsole(){
        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        consoleRepo.save(console);

        Optional<Console> console1 = consoleRepo.findById(console.getId());

        assertEquals(console1.get(), console);
    }

    @Test
    public void getConsoleById() {
        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        consoleRepo.save(console);

        Optional<Console> console1 = consoleRepo.findById(console.getId());

        assertTrue(console1.isPresent());
    }

    @Test
    public void getAllConsoles() {
        //Arrange...

        //Act...
        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        consoleRepo.save(console);

        Console console2 = new Console();
        console2.setQuantity(10);
        console2.setMemory_amount("25 GB");
        console2.setProcessor("Core i5");
        console2.setPrice(new BigDecimal("100.99"));
        console2.setModel("Xbox One");
        console2.setManufacturer("Microsoft");

        consoleRepo.save(console2);

        List<Console> consoleList = consoleRepo.findAll();

        //Assert...
        assertEquals(2, consoleList.size());
    }

    @Test
    public void updateConsole() {

        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        consoleRepo.save(console);

        console.setPrice(new BigDecimal("180.99"));

        consoleRepo.save(console);

        Optional<Console> console1 = consoleRepo.findById(console.getId());

        assertEquals(console1.get(), console);

    }

    @Test
    public void deleteConsole() {

        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        consoleRepo.save(console);

        consoleRepo.deleteById(console.getId());

        Optional<Console> console1 = consoleRepo.findById(console.getId());

        assertFalse(console1.isPresent());
    }

    @Test
    public void getConsoleByManufacturer() {
        Console console = new Console();
        console.setQuantity(10);
        console.setMemory_amount("25 GB");
        console.setProcessor("Core i5");
        console.setPrice(new BigDecimal("100.99"));
        console.setModel("Xbox 360");
        console.setManufacturer("Microsoft");

        consoleRepo.save(console);

        List<Console> consoleList = consoleRepo.findByManufacturer(console.getManufacturer());

        assertEquals(1, consoleList.size());
    }

}