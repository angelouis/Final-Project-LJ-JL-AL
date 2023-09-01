package com.company.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tshirt")
public class TShirt implements Serializable {
    /**
     * Instance variables for the t-shirt model
     */

    @Id
    @Column(name = "tshirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "You must input a value for id")
    private int tShirtId;

    @NotEmpty(message = "You must provide a size for the t-shirt")
    @NotNull(message = "You must provide a size for the t-shirt")
    @Size(max = 20, message = "Size must not be larger than 20 characters")
    private String size;

    @NotEmpty(message = "You must provide a color for the t-shirt")
    @NotNull(message = "You must provide a color for the t-shirt")
    @Size(max = 20, message = "Color must not be larger than 20 characters")
    private String color;

    @NotNull(message = "You must provide a description for the t-shirt")
    @Size(max = 255, message = "Description must not be larger than 255 characters")
    private String description;

    @NotNull(message = "You must provide a price for the t-shirt")
    @DecimalMin(value = "0.00", inclusive = true, message = "Price must be at least $0.00, not negative or null")
    @DecimalMax(value = "999.99", inclusive = true, message = "Price must be less than ${value}")
    private BigDecimal price;

    @Min(value = 0, message = "Quantity must be greater than 0")
    @NotNull(message = "You must provide a quantity for the t-shirt")
    private int quantity;

    public TShirt(String size, String color, String description, BigDecimal price, int quantity) {
    }

    public TShirt() {

    }

    /**
     * Gets the id of the t-shirt object
     *
     * @return Returns the id of a t-shirt as an int
     */
    public int gettShirtId() {
        return tShirtId;
    }

    /**
     * Sets the id of the t-shirt
     *
     * @param tShirtId - needs the t-shirt id to set the shirt id
     */
    public void settShirtId(int tShirtId) {
        this.tShirtId = tShirtId;
    }

    /**
     * Gets the size of the t-shirt object
     *
     * @return Returns the size of a t-shirt as a String
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the t-shirt object
     *
     * @param size - needs the t-shirt size to set the size of the t-shirt
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the color of the t-shirt object
     *
     * @return Returns the color of a t-shirt as a String
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the t-shirt object
     *
     * @param color - needs the t-shirt color to set the size of the t-shirt
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets the description of the t-shirt object
     *
     * @return Returns the description of a t-shirt as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the t-shirt object
     *
     * @param description - needs the t-shirt description to set the description of the t-shirt
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the t-shirt object
     *
     * @return Returns the price of the t-shirt object
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the t-shirt object
     *
     * @param price - needs the t-shirt price to set the price of the t-shirt
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the quanitty of the t-shirt object
     *
     * @return Returns the quantity of t-shirts
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of t-shirts
     *
     * @param quantity - Needs the quantity of t-shirts to set the amount of t-shirts available in that design
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "tShirtId=" + tShirtId +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirt tShirt = (TShirt) o;
        return tShirtId == tShirt.tShirtId && quantity == tShirt.quantity && Objects.equals(size, tShirt.size) && Objects.equals(color, tShirt.color) && Objects.equals(description, tShirt.description) && Objects.equals(price, tShirt.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tShirtId, size, color, description, price, quantity);
    }
}
