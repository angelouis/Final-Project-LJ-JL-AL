package com.company.gamestore.viewmodels;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class GameViewModel {
    private int id;

    @NotEmpty(message = "Game title cant be empty")
    @Size(max = 50, message = "Game title cant be over 50 characters")
    private String title;
    @NotEmpty(message = "Game esrbrating cant be empty")
    @Size(max =50, message = "Game esrbrating cant be over 50 characters")
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

    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameViewModel that = (GameViewModel) o;
        return id == that.id && quantity == that.quantity && Objects.equals(title, that.title) && Objects.equals(esrbRating, that.esrbRating) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(studio, that.studio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, esrbRating, description, price, studio, quantity);
    }

    @Override
    public String toString() {
        return "GameViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
