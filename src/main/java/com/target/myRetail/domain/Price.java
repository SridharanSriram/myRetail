package com.target.myRetail.domain;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Price {
    @Id
    @NotNull(message = "product id cannot be null")
    private Integer id;

    @Min(value = 1, message = "the value cannot be zero")
    private Double value;

    @NotEmpty(message = "currency has to be specified")
    private String currencyCode;

    /**
     * parameterized constructor
     *
     * @param id
     * @param value
     * @param currencyCode
     */
    public Price(Integer id, Double value, String currencyCode) {
        this.setId(id);
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public Price() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
