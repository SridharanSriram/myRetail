package com.target.myRetail.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
public class Product {
    @Id
    public String productId;
    @Transient
    public String productName;
    public Map<String, String> current_price;

    /**
     * parameterized constructor
     *
     * @param productId
     * @param productName
     * @param current_price
     */
    public Product(String productId, String productName, Map<String, String> current_price) {
        this.productId = productId;
        this.productName = productName;
        this.current_price = current_price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Map<String, String> getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Map<String, String> current_price) {
        this.current_price = current_price;
    }
}
