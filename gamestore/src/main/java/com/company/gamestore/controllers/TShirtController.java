package com.company.gamestore.controllers;

import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.services.ServiceLayer;
import com.company.gamestore.viewmodels.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TShirtController {
    @Autowired
    ServiceLayer serviceLayer;

    /**
     * Post Method for adding t-shirt
     * @param tShirtViewModel
     * @return Returns the TShirtViewModel that was added to the service layer
     */
    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirtViewModel addTShirt(@RequestBody @Valid TShirtViewModel tShirtViewModel)
    {
        return serviceLayer.saveTShirt(tShirtViewModel);
    }

    /**
     * Put method to update the t-shirt
     * @param tShirt - Uses the t-shirt and updates it to the service layer
     */
    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirts(@RequestBody @Valid TShirtViewModel tShirt) {
        serviceLayer.updateTShirt(tShirt);
    }

    /**
     * Delete method to remove te t-shirt from the service layer
     * @param id - Uses the t-shirt id and deletes the t-shirt
     */
    @DeleteMapping("/tshirts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTShirt(@PathVariable int id) {
        serviceLayer.removeTShirt(id);
    }

    /**
     * Get method to grab needed t-shirt
     * @param id - Uses the t-shirt id to get the t-shirt
     * @return Returns the t-shirt from the id
     */
    @GetMapping("/tshirts/{id}")
    public TShirtViewModel getTShirtById(@PathVariable int id) {
        return serviceLayer.findTShirt(id);
    }

    /**
     * Get method to grab all t-shirts
     * @return Returns all t-shirts present in the service layer
     */
    @GetMapping("/tshirts")
    public List<TShirtViewModel> getAllTShirts() {
        return serviceLayer.findAllTShirt();
    }

    /**
     * Get method to get t-shirt by a specific color
     * @param color - uses color as an input for getting the t-shirts
     * @return Returns the t-shirt with specific color in a list
     */
    @GetMapping("/tshirts/byColor/{color}")
    public List<TShirtViewModel> getTShirtsByColor(@PathVariable String color) {
        return serviceLayer.findTShirtByColor(color);
//        return Optional
//                .ofNullable(serviceLayer.findTShirtByColor(color))
//                .orElseThrow(() -> new NotFoundException("Requested t-shirt was not found! [ color = " + color + "]"));
    }

    /**
     * Get method to get t-shirt by a specific color
     * @param size - uses size as an input for getting the t-shirts
     * @return Returns the t-shirt with specific size in a list
     */
    @GetMapping("/tshirts/bySize/{size}")
    public List<TShirtViewModel> getTShirtsBySize(@PathVariable String size) {
        return serviceLayer.findTShirtBySize(size);
    }
}
