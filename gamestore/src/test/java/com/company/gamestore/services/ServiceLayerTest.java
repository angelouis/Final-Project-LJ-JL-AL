package com.company.gamestore.services;

import com.company.gamestore.models.TShirt;
import com.company.gamestore.repositories.TShirtRepository;
import com.company.gamestore.viewmodels.TShirtViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    ServiceLayer serviceLayer;
    TShirtRepository tShirtRepository;

    @BeforeEach
    public void setUp() throws Exception {
        setUpTShirtRepositoryMock();
        serviceLayer = new ServiceLayer(tShirtRepository);
    }


    private void setUpTShirtRepositoryMock() throws Exception {
        tShirtRepository = mock(TShirtRepository.class);
        TShirt tShirt = new TShirt();
        tShirt.settShirtId(1);
        tShirt.setPrice(new BigDecimal("10.99"));
        tShirt.setColor("Yellow");
        tShirt.setSize("Medium");
        tShirt.setDescription("Sunshine sun");
        tShirt.setQuantity(12);

        TShirt tShirt1 = new TShirt();
        tShirt1.setPrice(new BigDecimal("10.99"));
        tShirt1.setColor("Yellow");
        tShirt1.setSize("Medium");
        tShirt1.setDescription("Sunshine sun");
        tShirt1.setQuantity(12);

        List tShirtList = new ArrayList();
        tShirtList.add(tShirt);
        tShirtList.add(tShirt1);

        doReturn(tShirt).when(tShirtRepository).save(tShirt1);
        doReturn(Optional.of(tShirt)).when(tShirtRepository).findById(1);
        doReturn(tShirtList).when(tShirtRepository).findAll();
        doReturn(tShirtList).when(tShirtRepository).findByColor("Yellow");
        doReturn(tShirtList).when(tShirtRepository).findBySize("Medium");
    }

    @Test
    public void shouldSaveTShirt() {
        // Perform the service call
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

//      // expectedResult = tShirtRepository.save(expectedResult);
        TShirtViewModel tShirt1 = new TShirtViewModel();
        tShirt1.setSize("Medium");
        tShirt1.setDescription("Sunshine sun");
        tShirt1.setColor("Yellow");
        tShirt1.setPrice(new BigDecimal("10.99"));
        tShirt1.setQuantity(12);

        tShirt1 = serviceLayer.saveTShirt(tShirt1);

        assertEquals(expectedResult, tShirt1);
    }

    @Test
    public void shouldFindTShirt() {
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

        TShirtViewModel tShirtViewModel = serviceLayer.findTShirt(1);
        assertEquals(tShirtViewModel, expectedResult);
    }

    @Test
    public void shouldFindTShirtByColor() {
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult);

        TShirtViewModel expectedResult1 = new TShirtViewModel();
        expectedResult1.settShirtId(2);
        expectedResult1.setSize("Medium");
        expectedResult1.setDescription("Sunshine sun");
        expectedResult1.setColor("Yellow");
        expectedResult1.setPrice(new BigDecimal("10.99"));
        expectedResult1.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult1);

        List<TShirtViewModel> tShirtViewModel = serviceLayer.findTShirtByColor("Yellow");
        assertEquals(2, tShirtViewModel.size());
    }

    @Test
    public void shouldFindTShirtBySize() {
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult);

        TShirtViewModel expectedResult1 = new TShirtViewModel();
        expectedResult1.settShirtId(2);
        expectedResult1.setSize("Medium");
        expectedResult1.setDescription("Sunshine sun");
        expectedResult1.setColor("Yellow");
        expectedResult1.setPrice(new BigDecimal("10.99"));
        expectedResult1.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult1);

        List<TShirtViewModel> tShirtViewModel = serviceLayer.findTShirtBySize("Medium");
        assertEquals(2, tShirtViewModel.size());
    }

    @Test
    public void shouldFindAllTShirts() {
        TShirtViewModel expectedResult = new TShirtViewModel();
        expectedResult.settShirtId(1);
        expectedResult.setSize("Medium");
        expectedResult.setDescription("Sunshine sun");
        expectedResult.setColor("Yellow");
        expectedResult.setPrice(new BigDecimal("10.99"));
        expectedResult.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult);

        TShirtViewModel expectedResult1 = new TShirtViewModel();
        expectedResult1.settShirtId(2);
        expectedResult1.setSize("Medium");
        expectedResult1.setDescription("Sunshine sun");
        expectedResult1.setColor("Yellow");
        expectedResult1.setPrice(new BigDecimal("10.99"));
        expectedResult1.setQuantity(12);

        serviceLayer.saveTShirt(expectedResult1);

        List<TShirtViewModel> tShirtViewModel = serviceLayer.findAllTShirt();
        assertEquals(2, tShirtViewModel.size());
    }
}
