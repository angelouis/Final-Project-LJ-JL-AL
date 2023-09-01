package com.company.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game {
    /**
     * Instance variables for the game model
     */
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Game title cant be empty")
    @Size(max = 50, message = "Game title cant be over 50 characters")
    private String title;
    @NotEmpty(message = "Game esrbrating cant be empty")
    @Size(max =50, message = "Game esrbrating cant be over 50 characters")
    @Column(name = "esrb_Rating")
    private String esrbRating;
    @NotEmpty(message = "Game description cant be empty")
    @Size(max = 255, message = "Game description cant be over 50 characters")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull(message = "Decimal cant be empty")
    @Digits(integer=5, fraction=2)
    private BigDecimal price;
    @NotEmpty(message = "Game studio cant be empty")
    @Size(max =50, message = "Game studio cant be over 50 characters")
    private String studio;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(esrbRating, game.esrbRating) && Objects.equals(description, game.description) && Objects.equals(price, game.price) && Objects.equals(studio, game.studio) && Objects.equals(quantity, game.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, esrbRating, description, price, studio, quantity);
    }
}
