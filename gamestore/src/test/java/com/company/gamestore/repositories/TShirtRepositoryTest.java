package com.company.gamestore.repositories;

import com.company.gamestore.models.TShirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TShirtRepositoryTest {
    @Autowired
    TShirtRepository tShirtRepository;

    @BeforeEach
    public void setUp() throws Exception {
        tShirtRepository.deleteAll();
    }

    @Test
    public void shouldAddTShirt() {
        TShirt tShirt = new TShirt();
        tShirt.setColor("Blue");
        tShirt.setQuantity(10);
        tShirt.setDescription("Ocean T-Shirt");
        tShirt.setSize("Small");
        tShirt.setPrice(BigDecimal.valueOf(10.99));

        tShirt = tShirtRepository.save(tShirt);

        Optional<TShirt> tShirtOptional = tShirtRepository.findById(tShirt.gettShirtId());
        assertEquals(tShirtOptional.get(), tShirt);
    }

}
