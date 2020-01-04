package com.target.myRetail.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not available.")
public class ProductNotAvailable extends RuntimeException {
    public ProductNotAvailable() {
    }
}
