package com.target.myRetail.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product Id Not Available.")
public class ItemNotFountException extends RuntimeException {
    public ItemNotFountException(){ }
}
