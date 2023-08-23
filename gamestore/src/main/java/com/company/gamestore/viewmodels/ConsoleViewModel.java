package com.company.gamestore.viewmodels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

public class ConsoleViewModel {

    private int id;

    @NotEmpty(message = "You must supply a value for model.")
    private String model;

    @NotEmpty(message = "You must supply a value for manufacturer.")
    private String manufacturer;

    private String memory_amount;

    private String processor;

    @NotEmpty(message = "You must supply a value for price.")
    private BigDecimal price;

    @NotEmpty(message = "You must supply a value for quantity.")
    @Min(value = 0, message = "Quantity must be greater than 0")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemory_amount() {
        return memory_amount;
    }

    public void setMemory_amount(String memory_amount) {
        this.memory_amount = memory_amount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        ConsoleViewModel that = (ConsoleViewModel) o;
        return getId() == that.getId() && getQuantity() == that.getQuantity() && Objects.equals(getModel(), that.getModel()) && Objects.equals(getManufacturer(), that.getManufacturer()) && Objects.equals(getMemory_amount(), that.getMemory_amount()) && Objects.equals(getProcessor(), that.getProcessor()) && Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModel(), getManufacturer(), getMemory_amount(), getProcessor(), getPrice(), getQuantity());
    }

    @Override
    public String toString() {
        return "ConsoleViewModel{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memory_amount='" + memory_amount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
