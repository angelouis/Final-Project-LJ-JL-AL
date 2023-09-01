package com.company.gamestore.repositories;

import com.company.gamestore.models.TShirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TShirtRepositoryTest {
    @Autowired
    TShirtRepository tShirtRepository;

    @BeforeEach
    public void setUp() throws Exception {
        tShirtRepository.deleteAll();
    }

    /**
     * Tests whether one can add a t-shirt through the addition of specific inputs that make a t-shirt and finds it in the repo
     */
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

    @Test
    public void shouldHandleTShirtNotFound() {
        int id = 10;
        Optional<TShirt> foundTShirt = tShirtRepository.findById(id);
        assertFalse(foundTShirt.isPresent());

        try {
            TShirt nonExistentTShirt = foundTShirt.orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            assertNull(e.getMessage());
        }
    }

    /**
     * Tests whether one can find a specific t-shirt by searching the repo that contains various by the t-shirt id
     */
    @Test
    public void shouldGetTShirtById() {
        TShirt tShirt2 = new TShirt();
        tShirt2.setDescription("Lovely tshirt");
        tShirt2.setColor("Green");
        tShirt2.setSize("Medium");
        tShirt2.setPrice(BigDecimal.valueOf(11.32));
        tShirt2.setQuantity(12);
        tShirt2 = tShirtRepository.save(tShirt2);

        TShirt tShirt1 = new TShirt();
        tShirt1.setQuantity(33);
        tShirt1.setSize("Small");
        tShirt1.setPrice(BigDecimal.valueOf(12.89));
        tShirt1.setDescription("Small frog");
        tShirt1.setColor("Forest Green");
        tShirt1 = tShirtRepository.save(tShirt1);

        Optional<TShirt> foundTShirt = tShirtRepository.findById(tShirt1.gettShirtId());
        assertEquals(foundTShirt.get(), tShirt1);
    }

    /**
     * Tests if one can retrieve all t-shirts in a list
     */
    @Test
    public void shouldGetAllTShirts() {
        TShirt tShirt = new TShirt();
        tShirt.setQuantity(34);
        tShirt.setSize("X-Large");
        tShirt.setPrice(BigDecimal.valueOf(12.57));
        tShirt.setDescription("Small cow");
        tShirt.setColor("Black and White");

        tShirt = tShirtRepository.save(tShirt);
        List<TShirt> tShirtList = tShirtRepository.findAll();
        assertEquals(tShirtList.size(), 1);
    }

    /**
     * Tests whether an input of t-shirt can be updated by retrieving the id and creating a new t-shirt object with the updated information
     */
    @Test
    public void shouldUpdateTShirt() {
        TShirt tShirt = new TShirt();
        tShirt.setQuantity(20);
        tShirt.setSize("2X");
        tShirt.setPrice(BigDecimal.valueOf(11.57));
        tShirt.setDescription("Small Beautiful Butterfly");
        tShirt.setColor("Blue");
        tShirt = tShirtRepository.save(tShirt);

        tShirt.setColor("Purple");
        tShirt.setSize("Large");
        tShirtRepository.save(tShirt);

        Optional<TShirt> tShirt1 = tShirtRepository.findById(tShirt.gettShirtId());
        assertEquals(tShirt1.get(), tShirt);
    }

    /**
     * Tests whether a t-shirt can be deleted by finding the t-shirt with its id in the repo
     */
    @Test
    public void shouldDeleteTShirtById() {
        TShirt tShirt = new TShirt();
        tShirt.setQuantity(2);
        tShirt.setSize("3X");
        tShirt.setPrice(BigDecimal.valueOf(14.59));
        tShirt.setDescription("Baby Shark");
        tShirt.setColor("Yellow");
        tShirt = tShirtRepository.save(tShirt);

        Optional<TShirt> tShirt1 = tShirtRepository.findById(tShirt.gettShirtId());

        assertEquals(tShirt1.get(), tShirt);
        tShirtRepository.deleteById(tShirt.gettShirtId());
        tShirt1 = tShirtRepository.findById(tShirt.gettShirtId());
        assertFalse(tShirt1.isPresent());
    }

    /**
     * Tests whether the t-shirt with a specific color can be retrieved and found by using the color string
     * Checks how many t-shirts with the specific size is within the list with how many there should be
     */
    @Test
    public void shouldGetTShirtByColor() {
        TShirt tShirt = new TShirt();
        tShirt.setQuantity(3);
        tShirt.setSize("4X");
        tShirt.setPrice(BigDecimal.valueOf(14.99));
        tShirt.setDescription("Baby Whale");
        tShirt.setColor("Gray");
        tShirt = tShirtRepository.save(tShirt);

        TShirt tShirt1 = new TShirt();
        tShirt1.setQuantity(4);
        tShirt1.setSize("1X");
        tShirt1.setPrice(BigDecimal.valueOf(10.59));
        tShirt1.setDescription("Baby Giraffe");
        tShirt1.setColor("Red");
        tShirt1 = tShirtRepository.save(tShirt1);

        List<TShirt> tShirtList = tShirtRepository.findByColor("Red");
        assertEquals(tShirtList.size(), 1);
    }

    /**
     * Tests whether the t-shirt with a specific size can be retrieved and found by using the size string
     * Checks how many t-shirts with the specific color is within the list with how many there should be
     */
    @Test
    public void shouldGetTShirtBySize() {
        TShirt tShirt = new TShirt();
        tShirt.setQuantity(12);
        tShirt.setSize("Small");
        tShirt.setPrice(BigDecimal.valueOf(7.50));
        tShirt.setDescription("Baby Turtle");
        tShirt.setColor("Green");
        tShirt = tShirtRepository.save(tShirt);

        TShirt tShirt1 = new TShirt();
        tShirt1.setQuantity(10);
        tShirt1.setSize("Medium");
        tShirt1.setPrice(BigDecimal.valueOf(10.59));
        tShirt1.setDescription("Baby Seal");
        tShirt1.setColor("Gray");
        tShirt1 = tShirtRepository.save(tShirt1);

        List<TShirt> tShirtList = tShirtRepository.findBySize("Medium");
        assertEquals(tShirtList.size(), 1);
    }

}
