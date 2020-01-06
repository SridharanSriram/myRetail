package com.target.myRetail.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * Feign client to call remote API.
 */
@FeignClient(
        name = "product-service",
        url = "https://redsky.target.com/v2/pdp/tcin"
)
public interface ProductClient {
    /**
     * @param productId
     * @return ResponseEntity
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{productId}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics"
    )
    public ResponseEntity<String> getProductInfoById(@PathVariable("productId") String productId);
}
