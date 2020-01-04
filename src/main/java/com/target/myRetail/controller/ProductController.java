package com.target.myRetail.controller;

import com.target.myRetail.Exception.ProductNotAvailable;
import com.target.myRetail.domain.Product;
import com.target.myRetail.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/products")
@RestController
public class ProductController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductService productService;

    //@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductDetail(@PathVariable("id") String productId) {
        logger.info("start - getProductDetail  " + productId);
        Product product = null;
        try {
            product = productService.getProductById(productId);
        } catch (Exception e) {
            logger.debug("Product Not Found Exception  " + e);
            throw new ProductNotAvailable();
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
}
