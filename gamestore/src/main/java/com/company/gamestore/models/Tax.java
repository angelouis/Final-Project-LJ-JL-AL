package com.company.gamestore.models;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tax")
public class Tax {

    @Id
    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "rate", precision = 8, scale = 2, nullable = false)
    private BigDecimal rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return Objects.equals(state, tax.state) && Objects.equals(rate, tax.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }
}
