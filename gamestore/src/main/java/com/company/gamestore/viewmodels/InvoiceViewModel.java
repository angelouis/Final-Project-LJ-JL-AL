package com.company.gamestore.viewmodels;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {


    private Integer id;

    @NotEmpty(message = "Invoice name cant be empty")
    @Size(max = 50, message = "Invoice name cant be over 50 characters")
    private String name;

    @NotEmpty(message = "Invoice street cant be empty")
    @Size(max = 100, message = "Invoice street cant be over 100 characters")
    private String street;

    @NotEmpty(message = "Invoice city cant be empty")
    @Size(max = 50, message = "Invoice city cant be over 50 characters")
    private String city;

    @NotEmpty(message = "Invoice city cant be empty")
    @Size(max = 2, message = "Invoice state cant be over 2 characters EX: FL")
    private String state;

    @NotNull(message = "Invoice zipcode cant be empty")
    @Size(max = 10, message = "Invoice zipcode cant be over 10 characters")
    private String zipcode;

    @NotEmpty(message = "Invoice item type cant be empty")
    @Size(max = 50, message = "Invoice item type cant be over 50 characters")
    private String itemType;

    private Integer itemId;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull(message = "Unit price cant be empty")
    @Digits(integer=8, fraction=2)
    private BigDecimal unitPrice;

    @NotEmpty(message = "Quantity cant be empty")
    private Integer quantity;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull(message = "subTotal cant be empty")
    @Digits(integer=8, fraction=2)
    private BigDecimal subTotal;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull(message = "Tax cant be empty")
    @Digits(integer=8, fraction=2)
    private BigDecimal tax;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull(message = "Processing fee cant be empty")
    @Digits(integer=8, fraction=2)
    private BigDecimal processingFee;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull(message = "Total cant be empty")
    @Digits(integer=8, fraction=2)
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipcode;
    }

    public void setZipCode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipcode, that.zipcode) && Objects.equals(itemType, that.itemType) && Objects.equals(itemId, that.itemId) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(quantity, that.quantity) && Objects.equals(subTotal, that.subTotal) && Objects.equals(tax, that.tax) && Objects.equals(processingFee, that.processingFee) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subTotal, tax, processingFee, total);
    }
}
