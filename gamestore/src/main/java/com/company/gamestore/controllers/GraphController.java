package com.company.gamestore.controllers;

import com.company.gamestore.models.TShirt;
import com.company.gamestore.repositories.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class GraphController implements Serializable {
    @Autowired
    TShirtRepository tShirtRepository;

    /**
     * find t-shirt by id in the t-shirt repository
     *
     * @param id - uses the t-shirt id as input
     * @return Returns the t-shirt or null by finding the t-shirt in the t-shirt repository using the id
     */
    @QueryMapping
    public TShirt findTShirt(@Argument int id) {
        return tShirtRepository.findById(id).orElse(null);
    }

    /**
     * find t-shirt by color in the t-shirt repository
     *
     * @param color - uses the t-shirt color as input
     * @return Returns the t-shirt in a list by finding the t-shirt in the t-shirt repository using the color
     */
    @QueryMapping
    public List<TShirt> findByColor(@Argument String color) {
        return tShirtRepository.findByColor(color);
    }

    /**
     * find t-shirt by size in the t-shirt repository
     *
     * @param size - uses the t-shirt size as input
     * @return Returns the t-shirt in a list by finding the t-shirt in the t-shirt repository using the size
     */
    @QueryMapping
    public List<TShirt> findBySize(@Argument String size) {
        return tShirtRepository.findBySize(size);
    }

    /**
     * Uses the parameters as factors to create the new t-shirt
     *
     * @param size
     * @param color
     * @param description
     * @param price
     * @param quantity
     * @return Returns the saved the new t-shirt in the tshirtrepository
     */
    @MutationMapping
    public TShirt addTShirt(
            @Argument String size,
            @Argument String color,
            @Argument String description,
            @Argument BigDecimal price,
            @Argument int quantity
    ) {
        TShirt newTShirt = new TShirt(size, color, description, price, quantity);
        return tShirtRepository.save(newTShirt);
    }

    @MutationMapping
    public void deleteTShirtById(@Argument int id) {
        tShirtRepository.deleteById(id);
    }

    /**
     * places in the parameters first, checks whether the t-shirt exists in the repo by the id, and then updates it
     *
     * @param id
     * @param size
     * @param color
     * @param description
     * @param price
     * @param quantity
     * @return Returns the saved updated t-shirt in the repo
     */
    @MutationMapping
    public TShirt updateTShirt(
            @Argument int id,
            @Argument String size,
            @Argument String color,
            @Argument String description,
            @Argument BigDecimal price,
            @Argument int quantity
    ) {
        TShirt updateTShirt = tShirtRepository.findById(id).orElseThrow();
        updateTShirt.setPrice(price);
        updateTShirt.setDescription(description);
        updateTShirt.setSize(size);
        updateTShirt.setQuantity(quantity);
        updateTShirt.setColor(color);
        return tShirtRepository.save(updateTShirt);
    }
}
