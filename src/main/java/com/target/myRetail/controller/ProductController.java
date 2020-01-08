package com.target.myRetail.controller;

import com.target.myRetail.Exception.ItemNotFountException;
import com.target.myRetail.Exception.MisMatchException;
import com.target.myRetail.Exception.ProductNotAvailable;
import com.target.myRetail.domain.Price;
import com.target.myRetail.domain.Product;
import com.target.myRetail.domain.Response;
import com.target.myRetail.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value="MyRetail", description = "Save/Retrieve/Update product and price Detail by product id")
@RequestMapping(value = "/products")
@RestController
public class ProductController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductService productService;

    @ApiOperation(value = "Gets the product and price detail by product id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Product inventory mismatch"),
            @ApiResponse(code = 404, message = "Product not available")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductDetail(@PathVariable("id") String productId) {
        logger.info("start - getProductDetail  " + productId);
        Product product = null;
        if(productId == null || productId.isEmpty()) {
            throw new ItemNotFountException();
        }
        try {
            product = productService.getProductById(productId);
        } catch (MisMatchException ex) {
            logger.debug("MisMatch Exception  " + ex);
            throw new MisMatchException();
        } catch (Exception e) {
            logger.debug("Product Not Found Exception  " + e);
            throw new ProductNotAvailable();
        }
        logger.info("End - getProductDetail");
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @ApiOperation(value = "Save the price detail by product id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 404, message = "Product not available")
    })
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> savePriceDetail(@RequestBody Price price, @PathVariable("id") String productId) {
        logger.info("start - savePriceDetail  " + productId);
        if(productId == null || productId.isEmpty()) {
            throw new ItemNotFountException();
        }
        try {
            price.setId(Integer.parseInt(productId));
            productService.savePriceDetail(price);
        } catch (Exception e) {
            logger.debug("Product Not Found Exception  " + e);
            throw new ProductNotAvailable();
        }
        logger.info("end - savePriceDetail");
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Data value saved."), HttpStatus.OK);
    }

    @ApiOperation(value = "Update the price detail by product id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Product inventory mismatch"),
            @ApiResponse(code = 404, message = "Product not available")
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updatePriceDetail(@RequestBody Price price, @PathVariable("id") String productId) {
        logger.info("start - updatePriceDetail  " + productId);
        if(productId == null || productId.isEmpty()) {
            throw new ItemNotFountException();
        }
        try {
            price.setId(Integer.parseInt(productId));
            productService.updatePriceDetail(price);
        } catch (MisMatchException ex) {
            logger.debug("MisMatch Exception  " + ex);
            throw new MisMatchException();
        } catch (Exception e) {
            logger.debug("Product Not Found Exception  " + e);
            throw new ProductNotAvailable();
        }
        logger.info("end - updatePriceDetail");
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Data value updated."), HttpStatus.OK);
    }
}
