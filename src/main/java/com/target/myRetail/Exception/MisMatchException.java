package com.target.myRetail.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Product not matching with inventory.")
public class MisMatchException extends RuntimeException{
    public MisMatchException(){ }
}
