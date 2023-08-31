package com.company.gamestore.viewmodels;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class TShirtViewModel {
    /**
     * Instance variables for the t-shirt model
     */

    private int tShirtId;

    @Valid
    @NotEmpty(message = "You must provide a size for the t-shirt")
    @NotNull(message = "You must provide a size for the t-shirt")
    @Size(max = 20, message = "Size must not be larger than 20 characters")
    private String size;
    @Valid
    @NotEmpty(message = "You must provide a color")
    @NotNull(message = "You must provide a color for the t-shirt")
    @Size(max = 20, message = "Color must not be larger than 20 characters")
    private String color;
    @Valid
    @NotEmpty(message = "You must provide a description")
    private String description;
    @Valid
    @NotNull(message = "You must provide a price")
    private BigDecimal price;
    @Valid
    @Min(value = 0, message = "Quantity must be greater than 0")
    @NotNull(message = "You must provide a quantity")
    private int quantity;

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
        return "TShirtViewModel{" +
                "tShirtId=" + tShirtId +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TShirtViewModel that = (TShirtViewModel) o;
//        return tShirtId == that.tShirtId && quantity == that.quantity && Objects.equals(size, that.size) && Objects.equals(color, that.color) && Objects.equals(description, that.description) && Objects.equals(price, that.price);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirtViewModel that = (TShirtViewModel) o;
        return Objects.equals(gettShirtId(), that.gettShirtId()) &&
                Objects.equals(getSize(), that.getSize()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tShirtId, size, color, description, price, quantity);
    }
}
